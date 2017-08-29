package kmitl.lab03.paniti58070080.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.LinkedList;

import kmitl.lab03.paniti58070080.simplemydot.model.Dot;


public class DotView extends View {

    private Paint paint;
    private Dot dot;
    private LinkedList<Dot> dotlist = new LinkedList<>();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        dotlist.add(dot);
        for(Dot newdot : dotlist){
            if(newdot != null){
                int color = newdot.getColor();
                paint.setColor(color);
                canvas.drawCircle(newdot.getCenterX(), newdot.getCenterY(), newdot.getRadius(), paint);
            }
        }
    }

    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    public void setDot(Dot dot) {
        this.dot = dot;
    }

    public void clearlist(){
        dot = null;
        dotlist.clear();
    }
}
