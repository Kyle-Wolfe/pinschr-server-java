import com.google.gson.annotations.Expose;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class MemoryMonitor implements Monitorable {
    @Expose private long free;
    @Expose private long used;
    private Sigar sigar;
    private Mem mem;

    public MemoryMonitor(Sigar sigar) {
        this.sigar = sigar;
        this.mem = new Mem();
        update();
    }

    @Override
    public void update() {
        try {
            mem.gather(sigar);
        }
        catch (SigarException e) {
            e.printStackTrace();
        }

        this.used = mem.getActualUsed();
        this.free = mem.getActualFree();
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
