import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by Kuba on 2017-01-12.
 */
public class HuffmanCode {

    private PriorityQueue<HuffmanTree> huffmanTreePriorityQueue = new PriorityQueue<HuffmanTree>();
    public HuffmanCode(){}

    public String[] getCodes(HuffmanNode node) {
        if (node==null){
            return null;
        }
        String[] codes = new String[256];
        generateCode(node, codes);
        return codes;
    }


    public void generateCode(HuffmanNode node, String[] codes){
        if(node==null) return;
        if(node.getLeft()!=null){   //base condition for recursion
            node.getLeft().setCode(node.getCode()+"0"); //set left child code to 0
            generateCode(node.getLeft(),codes); //recursive call

            node.getRight().setCode(node.getCode()+"1"); //set right child code to 1
            generateCode(node.getRight(),codes); //recursive call
        }
        else{
            codes[(int)node.getChar()]=node.getCode();
        }
    }

    public HuffmanTree getHuffmanTree(int[] counts) {
        for (int i=0; i<counts.length; i++){
            if (counts[i]>0)
                huffmanTreePriorityQueue.add(new HuffmanTree((char)i,counts[i]));
        }
        while (huffmanTreePriorityQueue.size()>1){
            HuffmanTree left = huffmanTreePriorityQueue.remove();
            HuffmanTree right = huffmanTreePriorityQueue.remove();
            huffmanTreePriorityQueue.add(new HuffmanTree(left,right));
        }
        return huffmanTreePriorityQueue.remove();
    }


    public String getLeaves(HuffmanNode root, IOHandler input) throws IOException{
        String text="";
        if (root == null){
            return null;
        }
        else if (root.getLeft()== null && root.getRight() == null){
            text = text + root.getChar();
        }
        else{
            if (input.readBit()){
                text=text+getLeaves(root.getRight(),input);
            }
            else {
                text=text+getLeaves(root.getLeft(),input);
            }
        }
        return text;

    }



    public int[] getCharFreq(String fileName) throws IOException {
        BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(new File(fileName)));
        int[] charFreq = new int[256];
        int read;
        while ((read=fileInput.read())!=-1){
            charFreq[read]++;
        }
        fileInput.close();

        return charFreq;
    }

    public void generateHuffmanDictionary(String fileName) throws IOException {
        int[] charFreq = getCharFreq(fileName);
        System.out.printf("%-15s%-15s%-15s\n", "Character","Frequency","Code");
        HuffmanTree ht= getHuffmanTree(charFreq);
        String[] codes =getCodes(ht.getNode());
        for (int i=0;i<codes.length;i++){
            if (charFreq[i]!=0)
                System.out.printf("%-15s%-15d%-15s\n",(char)i+"",charFreq[i],codes[i]);
        }
    }



    public void encode(String fileName, String[] codes)throws IOException{
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(new File(fileName)));
        IOHandler output = new IOHandler();
        output.outputHandler(new File(fileName + "Encoded"));
        int read;

        while (( read = inputStream.read()) >= 0){
            output.writeBit(codes[read]);
        }
    }




    public void compressFile() throws IOException{
        System.out.print("Enter file path:");
        Scanner input = new Scanner(System.in);
        String file = input.next();
        System.out.println(" Generating huffman code ");
        generateHuffmanDictionary(file);
        System.out.println(" Compressing file ");
        int[] codes = getCharFreq(file);
        HuffmanTree ht = getHuffmanTree(codes);
        String[] assignCodes = getCodes(ht.getNode());
        encode(file,assignCodes);

        System.out.println("Compression finished");
        PrintWriter output = new PrintWriter(new File("Table"));
        for(int i=0;i<codes.length;i++){
            output.write(String.valueOf(codes[i]));
            output.println();
        }
        output.flush();
        output.close();
    }


    public void decode()throws IOException {
        System.out.println("Enter the path of file you want to decode: ");
        Scanner scanner = new Scanner(System.in);
        String compressedFile = scanner.next();
        BufferedReader reader = new BufferedReader(new FileReader("Table"));
        String line;
        String[] freqReader = new String[256];
        int counter = 0;
        while ((line = reader.readLine())!=null){
            if (!line.matches("null")){
                freqReader[counter] = line;
            }
            counter++;
        }

        int[] codes = new int[256];
        for ( int i = 0; i<256;i++) {
            codes[i]=Integer.parseInt(freqReader[i]);
        }
        HuffmanCode tree = new HuffmanCode();
        HuffmanTree ht = tree.getHuffmanTree(codes);
        String[] treeCodes = tree.getCodes(ht.getNode());
        IOHandler input = new IOHandler();
        input.inputHandler(new File(compressedFile));
        String test="";
        try{
            while (true){
                test=test+tree.getLeaves(ht.getNode(),input);
            }
        } catch (EOFException ex){
            System.out.println("End of file");

        }
        System.out.println("File is decoded");
        PrintWriter output = new PrintWriter(new File("Decoded.txt"));
        output.write(test);
        output.close();



    }
}
