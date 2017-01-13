/**
 * Created by Kuba on 2017-01-12.
 */
public class HuffmanNode {

    private char Char;
    private int freq;
    private HuffmanNode left, right;
    private String code ="";

    public HuffmanNode(){}
    public HuffmanNode(char Char, String code){}

    public HuffmanNode(char Char, int freq){
        this.Char = Char;
        this.freq = freq;
    }



    public void setFreq(Integer freq) {
        this.freq = freq;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public char getChar() {
        return Char;
    }

    public void setChar(char Char) {
        this.Char = Char;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

}
