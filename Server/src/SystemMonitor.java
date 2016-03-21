import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hyperic.sigar.Sigar;
import java.util.HashMap;

public class SystemMonitor {
    private Sigar sigar;
    private MemoryMonitor memory;
    private NvidiaGraphicsMonitor gpu;
    private HashMap<String, Monitorable> monitors = new HashMap<String, Monitorable>();
    private Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public SystemMonitor() {
        sigar = new Sigar();
        memory = new MemoryMonitor(this.sigar);
        gpu = new NvidiaGraphicsMonitor();

        monitors.put("gpu", gpu);
        monitors.put("memory", memory);
    }

    public void update() {
        for (Monitorable value : monitors.values()) {
            value.update();
        }
    }

    
    public String toString() {
        return gson.toJson(this.monitors);
    }
}
