import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DrawingPanel extends JPanel
{
    final MainFrame frame;
    final static int W = 800, H = 600;
    BufferedImage image;
    Graphics2D graphics;

    //the offscreen image
    //the "tools" needed to draw in the image
    public DrawingPanel(MainFrame frame)
    {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    private void createOffscreenImage()
    {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }

    public void resetScreenImage()
    {
        createOffscreenImage();
        repaint();
    }

    public void setImage(BufferedImage image)
    {
        this.image = image;
        graphics = image.createGraphics();
        repaint();
    }

    private void init()
    {
        setPreferredSize(new Dimension(W, H)); //don’t use setSize. Why?
        setBorder(BorderFactory.createEtchedBorder()); //for fun
        this.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                drawShape(e.getX(), e.getY());
                repaint();
            } //Can’t use lambdas, JavaFX does a better job in these cases
        });
    }

    private void drawShape(int x, int y)
    {
        // int radius = (int)(Math.random() * 100); //generate a random number
        int radius = frame.configPanel.getShapeSize();
        int sides = frame.configPanel.sidesNumber();

        //  facem managementul in functie de ceea ce selectam din panel

        String colorString = frame.configPanel.getShapeColor();
        Color color;
        if(colorString.equals("Black"))
            color = new Color(0, 0, 0);
        else {
            int red = (int)(Math.random()*256);
            int green = (int)(Math.random()*256);
            int blue = (int)(Math.random()*256);
            color = new Color(red, green, blue);
        }

        String shapeString = frame.configPanel.getShape();
        Shape shape;
        if(shapeString.equals("Regular Polygon"))
            shape = new RegularPolygon(x, y, radius, sides);
        else
            shape = new NodeShape(x, y, radius*2);


        graphics.setColor(color);
        graphics.fill(shape);
    }

    @Override
    public void update(Graphics g)
    {

    } //Why did I do that?

    @Override
    protected void paintComponent(Graphics g)
    {
        g.drawImage(image, 0, 0, this);
    }
}
