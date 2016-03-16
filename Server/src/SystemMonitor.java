import org.hyperic.sigar.Sigar;

public class SystemMonitor {
    private Sigar sigar;
    private MemoryMonitor memory;
    private NvidiaGraphicsMonitor gpu;

    public SystemMonitor() {
        this.sigar = new Sigar();
        this.memory = new MemoryMonitor(this.sigar);
        this.gpu = new NvidiaGraphicsMonitor();
    }

    public String toString() {
        //return this.gpu.getJson().toString();
        return "";
    }
}
