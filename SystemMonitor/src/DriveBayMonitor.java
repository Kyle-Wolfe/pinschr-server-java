import org.hyperic.sigar.Sigar;

public class DriveBayMonitor
{
    public DriveBayMonitor() {
        DiskMonitor disk0 = new DiskMonitor(new Sigar(), 300, 300, 50);
    }
    
    
}
