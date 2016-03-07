import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.*;

public abstract class CircularGraph extends Graph
{
    private double radius;
    private double percentage;
    private Ellipse2D border;
    private Color color = Color.BLUE;
    
    public CircularGraph(double x, double y, double radius){
        super(x, y, radius*2, radius*2);
        this.radius = radius;
        percentage = 0;
        border = new Ellipse2D.Double(super.getX(), super.getY(), radius*2, radius*2);
    }
    
    public void drawBorder(Graphics2D g2) {
        double borderSize = 4;
        g2.setStroke(new BasicStroke((int)borderSize));
        g2.draw(border);
    }
    
    public void drawFill(Graphics2D g2) {
        double startAngle = 90;
        double extent = percentage * -3.6;
        Arc2D arc = new Arc2D.Double(border.getBounds2D(), startAngle, extent, Arc2D.PIE);
        g2.fill(arc);
    }
    
    public void animate(Graphics2D g2) {
        g2.setColor(color);
        drawBorder(g2);
        drawFill(g2);
        drawLabel(g2);
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public void setPercentage(double percentage) {
        if(percentage > 100)
            percentage = 100;
        else if (percentage < 0)
            percentage = 0;
            
        this.percentage = percentage;
    }

    public void drawLabel(Graphics2D g2) {
        FontRenderContext frContext = g2.getFontRenderContext();
        Font font = g2.getFont();
        TextLayout layout = new TextLayout(getLabel(), font, frContext);

        /* Reposition the text to the center */
        double vertPadding = 6.0;
        Rectangle2D bounds = layout.getBounds();
        float x = (float) (border.getX() + border.getWidth()/2 - bounds.getWidth()/2);
        float y = (float) (border.getY() + border.getHeight() + bounds.getHeight() + vertPadding);

        // Finally draw it
        layout.draw(g2, x, y);
    }
    
    
}
