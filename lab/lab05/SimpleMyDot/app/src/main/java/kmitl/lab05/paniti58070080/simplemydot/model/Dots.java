package kmitl.lab05.paniti58070080.simplemydot.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.LinkedList;

public class Dots implements Parcelable{

    private ArrayList<Dot> MoreDot = new ArrayList<>();
    private OnDotsChangeListener listener;

    public Dots(Dots.OnDotsChangeListener dotChangedListener) {
        this.listener = dotChangedListener;
    }

    protected Dots(Parcel in) {
        MoreDot = in.createTypedArrayList(Dot.CREATOR);
    }

    public static final Creator<Dots> CREATOR = new Creator<Dots>() {
        @Override
        public Dots createFromParcel(Parcel in) {
            return new Dots(in);
        }

        @Override
        public Dots[] newArray(int size) {
            return new Dots[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public interface OnDotsChangeListener{
        void onDotsChanged(Dots dots);
    }


    public void setListener(OnDotsChangeListener listener){
        this.listener = listener;
    }

    public ArrayList<Dot> getAllDot(){
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
