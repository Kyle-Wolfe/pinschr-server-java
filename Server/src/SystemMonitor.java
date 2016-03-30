import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hyperic.sigar.Sigar;
import java.util.HashMap;

public class SystemMonitor {

    private HashMap<String, Monitorable> monitors = new HashMap<String, Monitorable>();
    private Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public SystemMonitor() {
        Sigar sigar = new Sigar();

        monitors.put("gpu", new NvidiaGraphicsMonitor());
        monitors.put("memory", new MemoryMonitor(sigar));
        monitors.put("os", new OSMonitor());
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
