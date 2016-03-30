import com.google.gson.annotations.Expose;

import javax.print.DocFlavor;
import java.io.File;
import java.util.Properties;

public class OSMonitor implements Monitorable {
    @Expose
    private String platform;

    @Expose
    private String version;

    @Expose
    private String user;

    @Expose
    private String home;

    public OSMonitor() {
        Properties properties = System.getProperties();
        this.platform = getPlatform(properties);
        this.version = properties.getProperty("os.version");
        this.user = properties.getProperty("user.name");
        this.home = properties.getProperty("user.home")
                .replace(File.separator,"/");
    }

    private String getPlatform(Properties properties) {
        final String WINDOWS_PREFIX = "windows";
        final String MAC_PREFIX = "mac";
        final String LINUX_PREFIX = "linux";

        String name = properties.getProperty("os.name");
        name = name.toLowerCase();

        if(name.startsWith(WINDOWS_PREFIX)) {
            return "Windows";
        }
        else if (name.startsWith(MAC_PREFIX)) {
            return "Mac";
        }
        else if (name.startsWith(LINUX_PREFIX)) {
            return "Linux";
        }
        else {
            return "-1";
        }
    }

    public void update() {
        // Nothing needs to be updated since it shouldn't change
    }
}
