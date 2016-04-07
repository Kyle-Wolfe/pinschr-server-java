import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hyperic.sigar.Sigar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class SystemMonitor {

    private HashMap<String, Monitorable> monitors = new LinkedHashMap<String, Monitorable>();
    private Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public SystemMonitor() {
        Sigar sigar = new Sigar();

        monitors.put("memory", new MemoryMonitor(sigar));
        monitors.put("gpu", new NvidiaGraphicsMonitor());
        monitors.put("os", new OSMonitor());
        monitors.put("partitions", new PartitionMonitor(sigar));

        Iterator<Monitorable> it = monitors.values().iterator();
        while(it.hasNext()) {
            if(!it.next().isAvailable()) {
                it.remove();
            }
        }
    }

    public void update() {
        for (Monitorable value : monitors.values()) {
            value.update();
        }
    }

    public void printPretty() {
        Gson prettyGson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        System.out.println(prettyGson.toJson(this.monitors));
    }
    
    public String toString() {
        return gson.toJson(this.monitors);
    }
}
