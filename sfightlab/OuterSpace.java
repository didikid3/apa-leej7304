//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OuterSpace extends Canvas implements KeyListener, Runnable
{
  private Ship ship;
  private Alien alienOne;
  private Alien alienTwo;

  /* uncomment once you are ready for this part
   **/
   private AlienHorde horde;
   private Bullets shots;
  

  private boolean[] keys;
  private BufferedImage back;

  public OuterSpace()
  {
    setBackground(Color.black);

    keys = new boolean[5];

    //instantiate other instance variables
    //Ship, Alien

    ship = new Ship(100,100,30,30,5);
    // alienOne = new Alien(100,100,50,50,5);
    // alienTwo = new Alien(200,100,50,50,5);
    horde = new AlienHorde(5);
    shots = new Bullets();

    this.addKeyListener(this);
    new Thread(this).start();

    setVisible(true);
  }

  public void update(Graphics window)
  {
    paint(window);
  }

  public void paint( Graphics window )
  {
    //set up the double buffering to make the game animation nice and smooth
    Graphics2D twoDGraph = (Graphics2D)window;

    //take a snap shop of the current screen and same it as an image
    //that is the exact same width and height as the current screen
    if(back==null)
      back = (BufferedImage)(createImage(getWidth(),getHeight()));

    //create a graphics reference to the back ground image
    //we will draw all changes on the background image
    Graphics graphToBack = back.createGraphics();

    graphToBack.setColor(Color.BLACK);
    graphToBack.fillRect(0,0,800,600);
    graphToBack.setColor(Color.BLUE);
    graphToBack.drawString("StarFighter ", 25, 50 );
    graphToBack.setColor(Color.YELLOW); // for ammo

    ship.draw(graphToBack);
    // alienOne.draw(graphToBack);
    // alienTwo.draw(graphToBack);

    if(keys[0])
    {
      ship.move("LEFT");
    }
    if(keys[1])
    {
      ship.move("RIGHT");
    }
    if(keys[2])
    {
      ship.move("UP");
    }
    if(keys[3])
    {
      ship.move("DOWN");
    }
    if(keys[4])
    {
      shots.add(new Ammo((ship.getX() + ship.getWidth() / 2) - 5, ship.getY() - 5, 5));
    }
    /*if(keys[4] == true)
    {
      bullet.setX(ship.getX());
      bullet.setY(ship.getY());
      bullet.draw(graphToBack);
    } // FIRE

    if(bullet.getY()>0)
    {
      bullet.move("UP");
      bullet.draw(graphToBack);
    }
    else
    {
      bullet.setDraw(false);
      bullet.setX(ship.getX());
      bullet.setY(ship.getY());
    }*/
    //once i create bullets, i will change this to be in Ammo
    shots.moveEmAll();
    shots.drawEmAll(graphToBack);
    // shots.cleanEmUp();
    horde.removeDeadOnes(shots.getList());
    horde.drawEmAll(graphToBack);

    //add code to move Ship, Alien, etc.


    //add in collision detection to see if Bullets hit the Aliens and if Bullets hit the Ship


    twoDGraph.drawImage(back, null, 0, 0);
  }


  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_LEFT)
    {
      keys[0] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      keys[1] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP)
    {
      keys[2] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN)
    {
      keys[3] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
      keys[4] = true;
    }
    repaint();
  }

  public void keyReleased(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_LEFT)
    {
      keys[0] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      keys[1] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP)
    {
      keys[2] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN)
    {
      keys[3] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
      keys[4] = false;
    }
    repaint();
  }

  public void keyTyped(KeyEvent e)
  {
  }

  public void run()
  {
    try
    {
      while(true)
      {
        Thread.currentThread().sleep(5);
        repaint();
      }
    }catch(Exception e)
    {
    }
  }
}

