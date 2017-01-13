import java.io.*;

/**
 * Created by Kuba on 2017-01-12.
 */
public class IOHandler {
    private FileInputStream inputStream;
    private FileOutputStream outputStream;
    private int currentByte;
    private int currentBit=8;
    private int binaryVal;
    private int count =0;

    public IOHandler() {
    }

    public void outputHandler(File file) throws IOException{
        this.outputStream = new FileOutputStream(file);
    }
    public void inputHandler(File file) throws IOException{
        this.inputStream = new FileInputStream(file);
    }

    public boolean readBit() throws IOException {
        if (currentBit ==8){
            currentBit = 0;
            currentByte = inputStream.read();
            if (currentByte == -1)
                throw new EOFException();
        }
        boolean value=(currentByte &(1<<(7-currentBit)))!=0;
        currentBit++;
        return value;
    }

    public void writeBit(String bitString) throws IOException{
        for (int i=0;i<bitString.length();i++){
            writeBit(bitString.charAt(i));
        }
    }

    public void writeBit(char bit) throws IOException{
        count++;
        binaryVal = binaryVal<<1;
        if (bit=='1')
            binaryVal = binaryVal | (int) 1;
        if (count == 8){
            outputStream.write(binaryVal);
            count=0;
        }
    }

}
