import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

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
        if(node.getLeft()!=null){
            node.getLeft().setCode(node.getCode()+"0");

            node.getRight().setCode(node.getCode()+"1");

            generateCode(node.getLeft(),codes);
            generateCode(node.getRight(),codes);
        }
        else{
            codes[(int)node.getChar()]= node.getCode();
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
}
