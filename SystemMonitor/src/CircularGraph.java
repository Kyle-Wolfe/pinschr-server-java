import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.*;

public abstract class CircularGraph extends Graph
{
    private double radius;
    private Color color = Color.BLUE;
    
    public CircularGraph(double x, double y, double radius){
        super(x, y, radius*2, radius*2);
        this.radius = radius;
    }
    
    public void drawBorder(Graphics2D g2) {
        Ellipse2D border = new Ellipse2D.Double(super.getX(), super.getY(), super.getWidth(), super.getHeight());
        double borderSize = 4;
        g2.setStroke(new BasicStroke((int)borderSize));
        g2.draw(border);
    }
    
    public void drawFill(Graphics2D g2) {
        double startAngle = 90;
        double extent = getPercentage() * -3.6;
        Arc2D arc = new Arc2D.Double(super.getBounds2D(), startAngle, extent, Arc2D.PIE);
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
}
