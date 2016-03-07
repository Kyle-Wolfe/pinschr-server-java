import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Mem;
import java.awt.*;

public class MemoryMonitor extends CircularGraph {
    private Sigar sigar;
    private Mem mem;

    public MemoryMonitor(Sigar sigar, double x, double y, double radius) {
        super(x, y, radius);
        this.sigar = sigar;
        mem = new Mem();
    }

    public void update() {
        double memActualUsed = 0;
        double memAmount = 0;
        double memPercentUsed = 0;

        try {
            mem.gather(sigar);
            memActualUsed = mem.getActualUsed();
            memAmount = mem.getTotal();
            memPercentUsed = (memActualUsed / memAmount) * 100;
        } catch (SigarException e) {
            System.err.println(e.getMessage());
        }

        // Convert kilobytes to gigabytes
        memActualUsed /= Math.pow(1024, 3);
        memAmount /= Math.pow(1024, 3);

        // Create human readable label
        String label = String.format("%.1f GB used out of %.1f GB", memActualUsed, memAmount);

        super.setPercentage(memPercentUsed);
        super.setLabel(label);
    }

    public void draw(Graphics2D g2) {
        update();
        super.animate(g2);
    }
}
