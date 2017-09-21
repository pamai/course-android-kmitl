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
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import kmitl.lab05.paniti58070080.simplemydot.R;
import kmitl.lab05.paniti58070080.simplemydot.model.Dot;


public class EditFragment extends Fragment implements View.OnClickListener{

    Dot dot;
    View rootView;
    int colorReview;
    int reviewRed;
    int reviewGreen;
    int reviewBlue;

    public EditFragment() {
        // Required empty public constructor
    }

    public static EditFragment newInstance(Dot dot) {

        Bundle args = new Bundle();
        args.putParcelable("dot", dot);
        EditFragment fragment = new EditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.dot = getArguments().getParcelable("dot");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_edit, container, false);
        initView(rootView);
        // Inflate the layout for this fragment
        return rootView;
    }

    public void initView(View rootView){
        int radius = dot.getRadius();
        int red = Color.red(dot.getColor());
        int green = Color.green(dot.getColor());
        int blue = Color.blue(dot.getColor());
        final SeekBar barR = rootView.findViewById(R.id.seekBarR);
        SeekBar barG = rootView.findViewById(R.id.seekBarG);
        SeekBar barB = rootView.findViewById(R.id.seekBarB);
        EditText editRadius = rootView.findViewById(R.id.editRadius);
        final TextView showColor = rootView.findViewById(R.id.colorView);
        barR.setProgress(red);
        reviewRed = red;
        barG.setProgress(green);
        reviewGreen = green;
        barB.setProgress(blue);
        reviewBlue = blue;
        colorReview = Color.argb(255, reviewRed, reviewGreen, reviewBlue);
        showColor.setBackgroundColor(colorReview);

        barR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                reviewRed = i;
                colorReview = Color.argb(255, reviewRed, reviewGreen, reviewBlue);
                showColor.setBackgroundColor(colorReview);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        barG.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                reviewGreen = i;
                colorReview = Color.argb(255, reviewRed, reviewGreen, reviewBlue);
                showColor.setBackgroundColor(colorReview);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        barB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                reviewBlue = i;
                colorReview = Color.argb(255, reviewRed, reviewGreen, reviewBlue);
                showColor.setBackgroundColor(colorReview);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        editRadius.setText(Integer.toString(radius));

        Button btnApply = rootView.findViewById(R.id.apply_button);
        Button btnCancel = rootView.findViewById(R.id.cancel_button);

        btnApply.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.apply_button:
                onApply();
                break;
            case R.id.cancel_button:
                getFragmentManager().beginTransaction().remove(this).commit();
                break;
        }
    }

    public void onApply(){
        SeekBar barR = this.rootView.findViewById(R.id.seekBarR);
        SeekBar barG = this.rootView.findViewById(R.id.seekBarG);
        SeekBar barB = this.rootView.findViewById(R.id.seekBarB);
        EditText radius = this.rootView.findViewById(R.id.editRadius);
        int color = Color.argb(255, barR.getProgress(), barG.getProgress(), barB.getProgress());
        int radius_int = Integer.parseInt(radius.getText().toString());
        dot.setColor(color);
        dot.setRadius(radius_int);

        Intent intent = new Intent();
        intent.putExtra("centerX", dot.getCenterX());
        intent.putExtra("centerY", dot.getCenterY());
        intent.putExtra("color", color);
        intent.putExtra("radius", radius_int);

        getTargetFragment().onActivityResult(getTargetRequestCode(), 0, intent);
        getFragmentManager().beginTransaction().remove(this).commit();
    }
}
