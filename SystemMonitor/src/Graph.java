import java.awt.*;

public abstract class Graph
{
    private double x;
    private double y;
    private double w;
    private double h;
    private String label;
    
    public Graph(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.label = "String String";
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public double getWidth() {
        return w;
    }
    
    public double getHeight() {
        return h;
    }

    public void setLabel(String text) {
        this.label = text;
    }

    public String getLabel() {
        return this.label;
    }

    abstract void drawLabel(Graphics2D g2);
    
    abstract void update();
}
