package kmitl.lab05.paniti58070080.simplemydot.model;

import java.util.LinkedList;

public class Dots {
    private LinkedList<Dot> MoreDot = new LinkedList<>();

    public interface OnDotsChangeListener{
        void onDotsChanged(Dots dots);
    }
    private OnDotsChangeListener listener;

    public void setListener(OnDotsChangeListener listener){
        this.listener = listener;
    }

    public LinkedList<Dot> getAllDot(){
        return MoreDot;
    }

    public void addDot(Dot dot){
        MoreDot.add(dot);
        this.listener.onDotsChanged(this);
    }

    public void clearDots(){
        MoreDot.clear();
        this.listener.onDotsChanged(this);
    }

    public int findDot(int x, int y){
        for(int i = 0; i < MoreDot.size(); i++){
            int posX = MoreDot.get(i).getCenterX();
            int posY = MoreDot.get(i).getCenterY();
            double distance = Math.sqrt(Math.pow(posX - x, 2) + Math.pow(posY - y, 2));
            if(distance <= MoreDot.get(i).getRadius()){
                return i;
            }
        }
        return -1;
    }

    public void removeDot(int index){
        MoreDot.remove(index);
        this.listener.onDotsChanged(this);
    }
}
