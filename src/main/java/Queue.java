/**
 * Created by Kuba on 2017-01-13.
 */


import java.util.ArrayList;

public class Queue<E extends Comparable>{
    private ArrayList<E> list=new ArrayList<E>();

    public Queue(){
    }

    public void addItem(E item){
        list.add(item);
        int position=list.size()-1;

        while(position>0 && list.get(position).compareTo(list.get(position-1))>0){
            E temp=list.get(position-1);
            list.set(position-1, list.get(position));
            list.set(position,temp);
        }
    }


    public E removeItem(){
        if(list.size()==0)
            return null;
        E removedItem=list.get(0);
        list.remove(0);
        int i=0;
        while(i<list.size()){
            list.set(i, list.get(i++));
            i++;
        }
        return removedItem;
    }

    public int getSize(){
        return list.size();
    }

}
