import java.util.Random;

public class Person {
   public int x;
   public int y;
   public boolean immune = false;
   public int infected = 0; // when infected, this will be positive and increase until the
                            // infectedDuration is reached, at which point the person
                            // will be immune
   private int velx = 0;
   private int vely = 0;
   private int infectedDuration = 100;  // how many steps in the animation the person
                                        // remains infected
   private int height = 600;
   private int width = 800;

   private Random gen = new Random();
      
   public Person(int x, int y) {
      this.x = x;
      this.y = y;
      // randomly set the Person's velocity- from -5 to 4 in both x and y directions
      velx = gen.nextInt(10) - 5;
      vely = gen.nextInt(10) - 5;
   }
   
   public void move() {
      x += velx;
      y += vely;
      // if they hit the walls, bounce
      if (x > width || x < 0) {
         velx = -velx;
      }
      if (y > height || y < 0) {
         vely = -vely;
      }
      // each time they move, if already infected they get closer to immunity
      if (infected > 0) {
         infected++;
      }
      // check to see if they've reached the immunity threshold
      if (infected > infectedDuration) {
         infected = 0;
         immune = true;
      }
   }
}