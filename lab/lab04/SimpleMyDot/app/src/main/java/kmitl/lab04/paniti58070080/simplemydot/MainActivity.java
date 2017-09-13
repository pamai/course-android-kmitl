package kmitl.lab04.paniti58070080.simplemydot;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.util.Random;

import kmitl.lab04.paniti58070080.simplemydot.model.Dot;
import kmitl.lab04.paniti58070080.simplemydot.model.Dots;
import kmitl.lab04.paniti58070080.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dots.OnDotsChangeListener, DotView.OnDotViewPressListener{

    private DotView dotView;
    private Dots dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dotView = (DotView) findViewById(R.id.dotview);
        dots = new Dots();
        dots.setListener(this);
        dotView.setOnDotViewPressListener(this);
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

    public void onShare(View view){
        if(requestExternalStoragePermission()){
            View screenView = view.getRootView();
            screenView.setDrawingCacheEnabled(true);
            Bitmap screenBitmap = Bitmap.createBitmap(screenView.getDrawingCache());
            screenView.setDrawingCacheEnabled(false);
            Uri imageUri = getUri(getApplicationContext(), screenBitmap);
            share(imageUri);
        }
    }

    public Uri getUri(Context context, Bitmap bitmap){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "image", null);
        return Uri.parse(path);
    }

    public void share(Uri imageUri){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
        intent.setType("image/*");
        startActivity(Intent.createChooser(intent, "Share Screen"));
    }

    private boolean requestExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    100);
            return false;
        } else {
            return true;
        }
    }
}
