import java.io.*;

/**
 * Created by Kuba on 2017-01-12.
 */
public class HuffmanFreqArray {

    private int[] charFreq;

    public HuffmanFreqArray(String fileName) throws IOException {
        int[] charFreq = getCharFreq(fileName);


    }
    public HuffmanFreqArray(){}


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
        HuffmanCode code = new HuffmanCode();
        HuffmanTree ht = code.getHuffmanTree(charFreq);
        String[] codes = code.getCodes(ht.getNode());
        for (int i=0;i<codes.length;i++){
            if (charFreq[i]!=0)
                System.out.printf("%-15s%-15d%-15s\n",(char)i+"",charFreq[i],codes[i]);
        }
    }

}
