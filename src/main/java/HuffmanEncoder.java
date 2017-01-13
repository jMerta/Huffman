import java.io.*;
import java.util.Scanner;

/**
 * Created by Kuba on 2017-01-12.
 */
public class HuffmanEncoder {

    public HuffmanEncoder() {
    }

    private HuffmanCode huffmanCode = new HuffmanCode();
    private HuffmanFreqArray huffmanFreqArray = new HuffmanFreqArray();

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
        huffmanFreqArray.generateHuffmanDictionary(file);
        System.out.println(" Compressing file ");
        int[] codes = huffmanFreqArray.getCharFreq(file);
        HuffmanTree ht = huffmanCode.getHuffmanTree(codes);
        String[] assignCodes = huffmanCode.getCodes(ht.getNode());
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
