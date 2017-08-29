package kmitl.lab03.paniti58070080.simplemydot;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import kmitl.lab03.paniti58070080.simplemydot.model.Dot;
import kmitl.lab03.paniti58070080.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.OnDotChangedListener{

    private DotView dotView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dotview);
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());
        int color = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
        new Dot(this, centerX, centerY, 50, color);
    }

    @Override
    public void onDotChanged(Dot dot) {
        dotView.setDot(dot);
        dotView.invalidate();
    }

    public void onClearDot(View view){
        dotView.clearlist();
        dotView.invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            int pos[] = new int[2];
            dotView.getLocationOnScreen(pos);
            Random random = new Random();
            int centerX = (int)event.getX()-pos[0];
            int centerY = (int)event.getY()-pos[1];
            int color = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
            new Dot(this, centerX, centerY, 50, color);
        }
        return false;
    }
}
