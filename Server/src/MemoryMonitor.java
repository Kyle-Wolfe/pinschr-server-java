import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;


public class MemoryMonitor implements Jsonable{
    private Sigar sigar;
    private Mem mem;

    public MemoryMonitor(Sigar sigar) {
        this.sigar = sigar;
        this.mem = new Mem();
    }

    public JsonObject getJson() {
        try {update();}
        catch (Exception e) {e.printStackTrace();}

        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObject value = factory.createObjectBuilder()
            .add("memory", factory.createObjectBuilder()
                .add("free", this.getFree())
                .add("used", this.getUsed()))
            .build();
        return value;
    }

    private void update() throws SigarException {
        mem.gather(sigar);
    }

    private long getFree() {
        return mem.getActualFree();
    }

    private long getUsed() {
        return mem.getActualUsed();
    }
}
