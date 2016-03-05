import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemMap;
import org.hyperic.sigar.FileSystemUsage;
import java.awt.*;

public class DiskMonitor extends DiskGraph
{
    public static void main(String[] args) throws SigarException{
        Sigar sigar = new Sigar();        
        FileSystem[] fslist = sigar.getFileSystemList();
        FileSystemMap mounts = sigar.getFileSystemMap();
        FileSystemUsage fsUsage = new FileSystemUsage();
        
        for (int i = 0; i < fslist.length; i++) {
            FileSystem fs = fslist[i];
            if (fs.getType() == fs.TYPE_LOCAL_DISK) {
                System.out.println(fs.getDevName());
                fsUsage.gather(sigar, fs.getDevName());
                System.out.println(fsUsage.getUsePercent());
            }
        }
        
    }
    
    public DiskMonitor(double x, double y, double height) {
        super(x, y, height);
    }
    
    public void update() {}
}
