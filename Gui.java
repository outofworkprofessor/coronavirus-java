import javax.swing.*;
import java.awt.*;

public class Gui {
   public static void main(String[] args) {
      int height = 600;
      int width = 800;
      JFrame f = new JFrame("corona");
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      AnimPanel ap = new AnimPanel(height, width);
      f.getContentPane().add(ap);
      f.pack();
      f.show();
   }
}