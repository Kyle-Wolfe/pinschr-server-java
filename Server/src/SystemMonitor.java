import org.hyperic.sigar.Sigar;

public class SystemMonitor {
    private Sigar sigar;
    private MemoryMonitor memory;

    public SystemMonitor() {
        this.sigar = new Sigar();
        this.memory = new MemoryMonitor(this.sigar);
    }

    public String toString() {
        return this.memory.getJson().toString();
    }
}
