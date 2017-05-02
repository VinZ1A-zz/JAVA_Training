package CodingGame;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

/**
 * Auto-generated code below aims at helping you parse the standard input according to the problem
 * statement.
 **/
class PlayerMars {

  // debug stuff (when applicable)
  static private Vector<String> getData() {
    Vector<String> aInput = new Vector<String>();

    // land x/y 0 100
    // land x/y 1000 500
    // land x/y 1500 1500
    // land x/y 3000 1000
    // land x/y 4000 150
    // land x/y 5500 150
    // land x/y 6999 800
    // X/Y 2500 2700
    // h/v Speeds 0 0
    // fuel 550
    // rotate 0
    // power 0

    aInput.add("3");

    return aInput;
  }

  static boolean _debug = true;
  static boolean _fromEclipse = false;

  public static void main(String args[]) {
    if (args.length != 0) {
      _debug = true;
      _fromEclipse = true;
      String aInput = "";
      for (String data : getData()) {
        aInput += data + "\r\n";
      }
      System.setIn(new ByteArrayInputStream(aInput.getBytes()));
    }
    doIt();
  }

  static public final double G = 3.711f;
  static public final int MIN_POWER = 0;
  static public final int MAX_POWER = 4;
  static public final int LANDING_VSPEED = -40;

  static void doIt() {

    Scanner in = new Scanner(System.in);
    int surfaceN = in.nextInt(); // the number of points used to draw the
                                 // surface of Mars.
    int[] yPts = new int[surfaceN];
    int[] xPts = new int[surfaceN];
    int yGround = -1;
    int xGroundA = -1;
    int xGroundB = -1;
    for (int i = 0; i < surfaceN; i++) {
      int landX = in.nextInt(); // X coordinate of a surface point. (0 to 6999)
      int landY = in.nextInt(); // Y coordinate of a surface point. By linking
                                // all the points together in a sequential
                                // fashion, you form the surface of Mars.
      debugln("land x/y " + landX + " " + landY);
      yPts[i] = landY;
      xPts[i] = landX;
      if (i > 0 && yPts[i] == yPts[i - 1]) { // same altitude between 2 points
        yGround = yPts[i];
        // xGround = xPts[i - 1] + ((xPts[i] - xPts[i - 1]) / 2);
        xGroundA = xPts[i - 1];
        xGroundB = xPts[i];
        System.err.println("Flat ground of altitude " + yGround + " found between Xa " + xGroundA + " and " + xGroundB);
      }
    }

    // game loop
    int prevXPos = -1;
    int prevYPos = -1;
    while (true) {
      int X = in.nextInt();
      int Y = in.nextInt();

      debugln("X/Y " + X + " " + Y);
      int hSpeed = in.nextInt(); // the horizontal speed (in m/s), can be
                                 // negative. abs() < 500
      int vSpeed = in.nextInt(); // the vertical speed (in m/s), can be
                                 // negative. abs() < 500
      debugln("h/v Speeds " + hSpeed + " " + vSpeed);
      int fuel = in.nextInt(); // the quantity of remaining fuel in liters.
      debugln("fuel " + fuel); // < 2000
      int rotate = in.nextInt(); // the rotation angle in degrees (-90 to 90).
      debugln("rotate " + rotate); // abs() < 90
      int power = in.nextInt(); // the thrust power (0 to 4).
      debugln("power " + power);

      if (prevXPos == -1) {
        prevXPos = X;
        prevYPos = Y;
        System.out.println("0 4"); // will work only on dx/dy
        continue;
      }
      int dx = X - prevXPos;
      int dy = Y - prevYPos;

      double a = ((Math.pow(LANDING_VSPEED, 2) - Math.pow(vSpeed, 2)) / (yGround - Y)) / 2;
      double r = a + G;
      System.err.format("a: %.3f, r: %.3f\n", a, r);

      // calculate ideal power (hight school memories)
      power = r >= G ? MAX_POWER : MIN_POWER; // ideal power speed

      double speed = Math.sqrt(dx * dx + dy * dy);

      // first of all, position pod above the flat area
      int oppositeAngle = (int) Math.toDegrees(Math.asin(dx / speed));
      int angle = 0; // chosen angle
      if (xGroundA > X || X > xGroundB) {
        System.err.println("not over FLAT (dx,dy=" + dx + "," + dy + ")");
        // wrong direction
        if ((X < xGroundA && dx < 0) || (X > xGroundB && dx > 0) ||
        // or X speed is too fast
          (Math.abs(dx) > 2.8 * 20)
        // or we're lower than target ground
        /* || (Y < yGround + 500) */) {
          System.err.println("Wrong dir OR too fast (dx=" + dx + ")");
          angle = oppositeAngle;
          power = 4; // give some punch
        }
        // pod wil never make it as it's too slow
        else if (Math.abs(dx) < 2 * 20) { // arbitrary value
          System.err.println("TOO SLOW " + dx);
          angle = (int) Math.toDegrees(Math.acos(G / 4.0));
          if (X < xGroundA)
            angle = -angle;
          power = 4; // give some punch
        } else { // not wrong dir, not too fast/slow...
          // well well well... time to land? (already done)
          if (Math.abs(dy) > 40 - 15 // too fast vertically (more margin as
                                     // higher)
            || (Y < yGround + 600 && dy < 0)) // or we're lower than target
                                              // ground
          // TODO - add test "we're lower than a peak we haven't passed yet"
          // eg. between X and either xGroundA and xGroundB there is a yPts[i]
          // which is higher than Y + margin
          // xxxx
          {
            power = 4;
            System.err.println("OK(1) 4");
          } else {
            System.err.println("OK(1) ");
          }
        }

      } else// pod is over flat area
      {
        System.err.println("over FLAT (dx,dy=" + dx + "," + dy + ")");
        if (Math.abs(dy) > 40 - 5) { // still too fast vertically, adjust
          System.err.println("dy too fast");
          power = 4;
        }
        if (Y < yGround + 80) // unless we're almost landing (let's pray)
        {
          System.err.println("landing");
          power = 3;
        }
        // too much speed, reduce (opposite direction)
        else if (Math.abs(dx) > 20 - 15) {
          System.err.println("dx too fast");
          angle = oppositeAngle;
          power = 4; // give some punch
        } else {
          System.err.println("OK(2)?");
        }
      }

      // pod is travelling right > 20 m/s - should correct by apply thrust +
      // left angle
      // try to reach -7 deg eg. aply + 15 if final res. is > -7

      // distance is increasing : is angle too strong? eg. at this rate will
      // we be >20 at X ?
      // distance is decreasing : reaching stability? avoid reversing?

      // rotate power. rotate is the desired rotation angle. power is the
      // desired thrust power.
      // rotate can only change by +- 15 degrees
      // power can only change by +- 1
      System.out.println(angle + " " + power);

      prevXPos = X;
      prevYPos = Y;
    }

  }

  static void debugln(String iStr) {
    if (_debug) {
      System.err.println(iStr);
    }
  }
}