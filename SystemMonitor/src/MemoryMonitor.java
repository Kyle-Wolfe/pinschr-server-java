import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Mem;
import java.awt.*;

public class MemoryMonitor extends CircularGraph
{
    private Sigar sigar;
    private Mem mem;
    
    public MemoryMonitor(double x, double y, double radius){
        super(x, y, radius);
        sigar = new Sigar();
        mem = new Mem();
    }
    
    public void update(){
        double memPercentUsed = 0;
        
        try {
            memPercentUsed = getMem();
        } catch(SigarException e) {
            memPercentUsed = 0;
        }
        super.setPercentage(memPercentUsed);
    }
    
    public void draw(Graphics2D g2) {
        update();
        super.animate(g2);
    }
    
    public double getMem() throws SigarException {
        mem.gather(sigar);
        return (double) mem.getUsedPercent();
    }
    
    
}
