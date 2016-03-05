import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.font.TextLayout;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class Label
{
    private double x;
    private double y;
    private Graphics2D g2;
    private Font font;
    private FontRenderContext frContext;
    private String text;
    private Rectangle2D bounds;
    private TextLayout layout;
    
    
    public Label(Graphics2D g2, String text, double x, double y) {
        this.g2 = g2;
        this.x = x;
        this.y = y;
        frContext = g2.getFontRenderContext();
        font = g2.getFont();
        bounds = font.getStringBounds(text, frContext);
        layout = new TextLayout(text, font, frContext);
    }
    
    public void draw() {
        layout.draw(g2, (float) x, (float) y);
    }
    
    public double getHeight() {
        return bounds.getHeight();
    }
    
    public double getWidth() {
        return bounds.getWidth();
    }
}
