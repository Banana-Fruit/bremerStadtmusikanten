package control;


import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Testing
{
    public static void main (String[] args)
    {
        Frame frame = new Frame("Aspect Ratio Example");
        frame.setSize(800, 450); // Start with a 16:9 window size
        
        AspectRatioCanvas canvas = new AspectRatioCanvas();
        frame.add(canvas);
        
        frame.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized (ComponentEvent e)
            {
                canvas.revalidate();
                canvas.repaint();
            }
        });
        
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing (WindowEvent windowEvent)
            {
                System.exit(0);
            }
        });
    }
}


class AspectRatioCanvas extends Canvas
{
    @Override
    public void paint (Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        
        Dimension size = getSize();
        double aspectRatio = 16.0 / 9.0;
        int newWidth = size.width;
        int newHeight = (int) (newWidth / aspectRatio);
        
        if (newHeight > size.height)
        {
            newHeight = size.height;
            newWidth = (int) (newHeight * aspectRatio);
        }
        
        int x = (size.width - newWidth) / 2;
        int y = (size.height - newHeight) / 2;
        
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, size.width, size.height);
        
        g2d.setColor(Color.BLACK);
        g2d.fillRect(x, y, newWidth, newHeight);
        
        // Set the font size relative to the canvas size
        int fontSize = Math.min(newWidth, newHeight) / 10;
        Font font = new Font("Arial", Font.PLAIN, fontSize);
        g2d.setFont(font);
        
        // Draw a sample string centered
        String text = "Sample Text";
        FontMetrics metrics = g2d.getFontMetrics(font);
        int textX = x + (newWidth - metrics.stringWidth(text)) / 2;
        int textY = y + ((newHeight - metrics.getHeight()) / 2) + metrics.getAscent();
        
        // Enable anti-aliasing for smoother text
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, textX, textY);
    }
}
