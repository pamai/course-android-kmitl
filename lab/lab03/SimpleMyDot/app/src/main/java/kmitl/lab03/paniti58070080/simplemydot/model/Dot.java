package kmitl.lab03.paniti58070080.simplemydot.model;

public class Dot {

    private int centerX;
    private int centerY;
    private int radius;
    private int color;

    public interface OnDotChangedListener{
        void onDotChanged(Dot dot);
    }

    public OnDotChangedListener listener;

    public void setListener(OnDotChangedListener listener) {
        this.listener = listener;
    }

    public Dot(OnDotChangedListener listener, int centerX, int centerY, int radius, int color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;

        this.listener = listener;
        this.listener.onDotChanged(this);
    }

    public Dot(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
        this.listener.onDotChanged(this);
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
        this.listener.onDotChanged(this);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

}
