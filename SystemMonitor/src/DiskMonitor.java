import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemMap;
import org.hyperic.sigar.FileSystemUsage;
import java.awt.*;

public class DiskMonitor extends DiskGraph {

    private Sigar sigar;
    private FileSystemUsage fsUsage;

    public DiskMonitor(Sigar sigar, double x, double y, double height) {
        super(x, y, height);
        this.sigar = sigar;
        fsUsage = new FileSystemUsage();
    }

    public void update() {
        FileSystem filesystem = null;
        String fsName = null;

        try {
            FileSystem[] fslist = sigar.getFileSystemList();
            filesystem = fslist[0];
            fsName = filesystem.getDevName();
            this.fsUsage.gather(this.sigar, fsName);

        } catch (SigarException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        double diskPercentUsed = fsUsage.getUsePercent();

        // Put percent used out of 100.
        diskPercentUsed *= 100;

        System.out.println(diskPercentUsed);
        super.setPercentage(diskPercentUsed);
        super.setLabel("Disk 0 :)");
    }

    public void draw(Graphics2D g2) {
        update();
        super.animate(g2);
    }
}









    /* OLD MAIN
        public static void main(String[] args) throws SigarException{
        Sigar sigar = new Sigar();
        FileSystem[] fslist = sigar.getFileSystemList();
        FileSystemMap mounts = sigar.getFileSystemMap();
        FileSystemUsage fsUsage = new FileSystemUsage();

        for (FileSystem fs : fslist) {
            if (fs.getType() == fs.TYPE_LOCAL_DISK) {
                System.out.println(fs.getDevName());
                fsUsage.gather(sigar, fs.getDevName());
                System.out.println(fsUsage.getUsePercent());
            }
        }

    }
     */
