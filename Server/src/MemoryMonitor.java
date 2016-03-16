import com.google.gson.annotations.Expose;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class MemoryMonitor{
    @Expose private long memoryFree;
    @Expose private long memoryUsed;
    private Sigar sigar;
    private Mem mem;

    public MemoryMonitor(Sigar sigar) {
        this.sigar = sigar;
        this.mem = new Mem();
    }

    private void update() {
        try {
            mem.gather(sigar);
        }
        catch (SigarException e) {
            e.printStackTrace();
        }
    }

    private long getFree() {
        return mem.getActualFree();
    }

    private long getUsed() {
        return mem.getActualUsed();
    }
}
