package kmitl.lab05.paniti58070080.simplemydot.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;

import kmitl.lab05.paniti58070080.simplemydot.R;
import kmitl.lab05.paniti58070080.simplemydot.model.Dot;
import kmitl.lab05.paniti58070080.simplemydot.model.Dots;
import kmitl.lab05.paniti58070080.simplemydot.view.DotView;

public class MainFragment extends Fragment implements DotView.OnDotViewPressListener, Dots.OnDotsChangeListener, View.OnClickListener{

    DotView dotView;
    private Dots dots;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            dots = savedInstanceState.getParcelable("dots");
        } else {
            dots = new Dots(this);
        }
        dots.setListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initView(rootView);
        dotView.setDots(dots);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            dotView.setDots(dots);
        }
        dotView.invalidate();
    }

    public void initView(View rootView){
        dotView = rootView.findViewById(R.id.dotview);
        Button btnRandom = rootView.findViewById(R.id.random_button);
        Button btnClear = rootView.findViewById(R.id.clear_button);

        dotView.setOnDotViewPressListener(this);
        btnRandom.setOnClickListener(this);
        btnClear.setOnClickListener(this);

    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());
        int color = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
        Dot newDot = new Dot(centerX, centerY, 50, color);
        dots.addDot(newDot);
    }

    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }

    public void onClearDot(View view){
        dots.clearDots();
    }

    public void onDotViewPressed(int x, int y){
        int dotPos = dots.findDot(x, y);
        if(dotPos == -1){
            Random random = new Random();
            int color = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
            Dot newDot = new Dot(x, y, 50, color);
            dots.addDot(newDot);
        }
        else {
            dots.removeDot(dotPos);
        }
    }

    public void onDotViewLongPressed(int x, int y){
        int dotPos = dots.findDot(x, y);
        if(dots.findDot(x, y) != -1){
            Dot dot = dots.getAllDot().get(dotPos);
            new EditFragment();
            Fragment editFragment = EditFragment.newInstance(dot);
            editFragment.setTargetFragment(this, 0);
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentContainer, editFragment)
                    .commit();
            this.dotView.invalidate();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.clear_button:
                onClearDot(view);
                break;
            case R.id.random_button:
                onRandomDot(view);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        this.dotView.invalidate();
    }
}
