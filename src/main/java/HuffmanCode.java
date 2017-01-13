import java.io.IOException;

/**
 * Created by Kuba on 2017-01-12.
 */
public class HuffmanCode {

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
        Queue<HuffmanTree> huffmanTreeQueue = new Queue<HuffmanTree>();
        for (int i=0; i<counts.length; i++){
            if (counts[i]>0)
                huffmanTreeQueue.addItem(new HuffmanTree((char)i,counts[i]));
        }
        while (huffmanTreeQueue.getSize()>1){
            HuffmanTree left = huffmanTreeQueue.removeItem();
            HuffmanTree right = huffmanTreeQueue.removeItem();
            huffmanTreeQueue.addItem(new HuffmanTree(left,right));
        }
        return huffmanTreeQueue.removeItem();
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
