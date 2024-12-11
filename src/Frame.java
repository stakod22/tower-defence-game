import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    public static final int WINDOWS_WIDTH = 600;
    public static final int WINDOWS_HEIGHT = 800;

    private PaintArea2d paintArea = new PaintArea2d();

    public Frame(){

        setTitle("Simple 2d Game");
        setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(paintArea);

        Timer timer = new Timer(20, (e) ->
                paintArea.repaint()
        );
        timer.start();
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }
}