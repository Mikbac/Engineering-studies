import java.awt.*;
import javax.swing.JPanel;
public class MonteCarloJPanel extends JPanel
{



    private int numExperiments = 1000;
    private int numInCircle;
    private Boolean visibleLine;

    public MonteCarloJPanel() {
          this.visibleLine = false;
    }

    public void setVisibleLine(Boolean visible) {
        this.visibleLine = visible;
    }

    public Boolean getVisibleLine() {
        return visibleLine;
    }

    private final static int OFFSET = 5;
    private final static int NUM_PIXELS = 585;

    public void setNumExperiments(int numExperiments) {
        this.numExperiments = numExperiments;
    }

    @Override
    protected void paintComponent( Graphics graphics )
    {
             super.paintComponent( graphics );
        numInCircle =  0;     // initialize every time paintComponent method is invoked
        Color color;
        setBackground(Color.black);
        for ( int experimentNum = 0; experimentNum < numExperiments; experimentNum++ )
        {
            double x = Math.random();
            double y = Math.random();
            int xInt = (int) ( x * NUM_PIXELS );
            int yInt = (int) ( y * NUM_PIXELS );
            x = 2 * x - 1;
            y = 2 * y - 1;
            if ( x * x + y * y <= 1.0 )
            {
                numInCircle++;
                color = Color.orange;
            }
            else
            {
                color = Color.blue;
            }
            graphics.setColor( color );
            graphics.fillRect( OFFSET + xInt, OFFSET + yInt, 1, 1 );
        }

        if(visibleLine) {
            graphics.setColor(Color.white);
            graphics.drawLine(OFFSET, NUM_PIXELS / 2 + OFFSET, NUM_PIXELS + OFFSET, NUM_PIXELS / 2 + OFFSET);
            graphics.drawLine(NUM_PIXELS / 2  + OFFSET, 0 + OFFSET , NUM_PIXELS / 2  + OFFSET, NUM_PIXELS + OFFSET);
            graphics.drawOval(1+OFFSET ,0+OFFSET ,NUM_PIXELS,NUM_PIXELS);
        }


        graphics.setColor( Color.black );
        graphics.drawRect(OFFSET, OFFSET, NUM_PIXELS, NUM_PIXELS );
        graphics.drawOval(OFFSET, OFFSET, NUM_PIXELS, NUM_PIXELS);
        double pi = 4.0 * numInCircle / numExperiments;
        graphics.setColor(Color.white);
        graphics.drawString("Dla punktów: " +numExperiments+ " jest punktów w okregu: " + numInCircle +" daje to pi = " + pi, 2 * OFFSET, NUM_PIXELS + 5 * OFFSET );
        graphics.drawString("Autor: Mikołaj Bachorz - 426080", 2 * OFFSET, NUM_PIXELS + 5 * OFFSET +20 );
    }
    public void re()
    {
        repaint();
    }

}
