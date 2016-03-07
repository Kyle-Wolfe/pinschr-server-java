import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

public abstract class Graph
{
    private Rectangle2D bounds;
    private String label;
    private double percentage;
    
    public Graph(double x, double y, double w, double h) {
        bounds = new Rectangle2D.Double(x, y, w, h);
        this.label = "String String";
        this.percentage = 0;
    }
    
    public double getX() {
        return bounds.getX();
    }
    
    public double getY() {
        return bounds.getY();
    }
    
    public double getWidth() {
        return bounds.getWidth();
    }
    
    public double getHeight() {
        return bounds.getHeight();
    }

    public Rectangle2D getBounds2D() {
        return bounds.getBounds2D();
    }

    public void setLabel(String text) {
        this.label = text;
    }

    public String getLabel() {
        return this.label;
    }

    public void setPercentage(double percentage) {
        if(percentage > 100)
            percentage = 100;
        else if (percentage < 0)
            percentage = 0;
        this.percentage = percentage;
    }

    public double getPercentage() {
        return this.percentage;
    }

    abstract void update();

    public void drawLabel(Graphics2D g2) {
        FontRenderContext frContext = g2.getFontRenderContext();
        Font font = g2.getFont();
        TextLayout layout = new TextLayout(getLabel(), font, frContext);

        /* Reposition the text to the center */
        double vertPadding = 6.0;
        Rectangle2D textBounds = layout.getBounds();
        float x = (float) (this.bounds.getX() + this.bounds.getWidth()/2 - textBounds.getWidth()/2);
        float y = (float) (this.bounds.getY() + this.bounds.getHeight() + textBounds.getHeight() + vertPadding);

        // Finally draw it
        layout.draw(g2, x, y);
    }
}
