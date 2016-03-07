import org.hyperic.sigar.Sigar;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel
{
    
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;
    
    public static final String TITLE = "System Monitor";

    Sigar sigar = new Sigar();
    MemoryMonitor mem = new MemoryMonitor(sigar, 5, 5, 100);
    DiskMonitor disk = new DiskMonitor(sigar, 5, 300, 100);
    //DriveBayMonitor drives = new DriveBayMonitor();

    
    
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        mem.draw(g2);
        disk.draw(g2);
    }
    
    
    public static void main(String[] args) {
        int MS_WIN_OFFSET_W = 16;
        int MS_WIN_OFFSET_H = 38;
        
        int windowWidth = CANVAS_WIDTH + MS_WIN_OFFSET_W;
        int windowHeight = CANVAS_HEIGHT + MS_WIN_OFFSET_H;
        
        JFrame window = new JFrame(TITLE);      
        window.setBounds(0, 0, windowWidth, windowHeight);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel canvas = new Main();
        canvas.setBackground(Color.WHITE);
        
        window.getContentPane().add(canvas);
        window.setVisible(true);
        
        while(true) {
            canvas.repaint();
            try {
                Thread.sleep(200);
            }
            catch(Exception ex) {}
        }
    }
}
