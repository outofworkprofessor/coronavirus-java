import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class AnimPanel extends JPanel implements ActionListener {
   // Timer is needed for animation
   private Timer tm = new Timer(200, this);
   // the number of people in the simulation
   private int numPoints = 200;
   // an array of Person objects
   private Person[] p = new Person[numPoints];
   // how large to draw each Person
   private int circleSize = 10;
   // how close two Persons need to be in order to infect one another
   private int infectDistance = 10;
   // height and width of the screen
   private int height = 600;
   private int width = 800;
   // random number generator to randomly place the people initially
   private Random gen = new Random();
   
   public AnimPanel(int h, int w) {
      width = w;
      height = h;
      setPreferredSize(new Dimension(width, height));
      // populate the Person array with randomly placed people
      for(int i=0;i<p.length;i++) {
         int x = gen.nextInt(width);
         int y = gen.nextInt(height);
         p[i] = new Person(x, y);
      }
      // set Patient 0- initially this is the only person infected
      p[0].infected = 1;
      // start the timer!
      tm.start();
   }
   
   public void actionPerformed(ActionEvent e) {
      // at each step in the animation, move all the Person objects
      for(int i=0;i<p.length;i++) {
         p[i].move();
      }
      // check to see if any of the people are close enough to infect someone
      handleCollisions();
      
      repaint();
   }
   
   public void handleCollisions() {
      // compare each point to all the other points
      for(int i=0;i<p.length;i++) {
         for(int j=i+1;j<p.length;j++) {
            int deltax = p[i].x - p[j].x;
            int deltay = p[i].y - p[j].y;
            double dist = Math.sqrt(deltax*deltax+deltay*deltay);
            // if the distance between 2 points is small enough, and one of
            // the Persons is infected, then infect the other Person
            if (dist < infectDistance) {
               if (p[i].infected > 0 && !p[j].immune && p[j].infected == 0) {
                  p[j].infected++;
               }
               if (p[j].infected > 0 && !p[i].immune && p[i].infected == 0) {
                  p[i].infected++;
               }

            }
         }
      }      
   }
   public void paintComponent(Graphics g) {
      // each time we paint the screen, set the color based on 
      // who is infected and who isn't, and who has recovered
      for(int i=0;i<p.length;i++) {
         if (p[i].infected > 0) {
            g.setColor(Color.red);
         } else if (p[i].immune) {
            g.setColor(Color.green);
         } else {
            g.setColor(Color.black);
         }
         g.fillOval(p[i].x, p[i].y, circleSize, circleSize);
      }
   }
}