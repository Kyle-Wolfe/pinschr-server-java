import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

public abstract class DiskGraph extends Graph
{
    private Rectangle2D.Double border;
    Color color = Color.BLUE;

    public DiskGraph(double x, double y, double height) {
        super(x, y, height/2, height);
    }

    public void drawBorder(Graphics2D g2) {
        double borderSize = 4;
        g2.setStroke(new BasicStroke((int)borderSize));
        border = new Rectangle2D.Double(super.getX(), super.getY(), super.getWidth(), super.getHeight());
        g2.draw(border);
    }

    public void drawFill(Graphics2D g2) {
        double startAngle = 90;
        double extent = getPercentage() * -3.6;
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

}
