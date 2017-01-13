import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Kuba on 2017-01-10.
 */
public class App {
    public static void main(String[] args) throws IOException{
        char Char;
        HuffmanCode huffmanEncoder = new HuffmanCode();
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("1. Encode File");
            System.out.println("2. Decode file");
            System.out.println("3. Exit");
            int choice = input.nextInt();
            switch (choice)
            {
                case 1:
                    huffmanEncoder.compressFile();
                    break;
                case 2:
                    huffmanEncoder.decode();
                    break;
                case 3:
                    System.exit(0);
                    default:
                        System.out.println("Wrong entry \n");
                        break;
            }
            System.out.println("\nDo you wish to repeat?");
            Char = input.next().charAt(0);
        }while (Char=='Y' || Char== 'y');
    }
}
