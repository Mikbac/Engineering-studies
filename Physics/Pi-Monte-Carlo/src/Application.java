import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Application extends JFrame implements ChangeListener, ActionListener {
    private JLabel lDes;
    private JButton bLines;
    private JSlider sDraw;
    private int maxNum = 50000;

    public Application() {
        setSize(1200, 200);
        setTitle("Monte Carlo - Pi");
        setLayout(null);

        lDes = new JLabel("Wybierz ilość punktów: ");
        lDes.setBounds(300,10, 200,20);
        add(lDes);

        sDraw = new JSlider(0, maxNum, 0);
        sDraw.setBounds(10, 50, 1150, 50);
        sDraw.setMajorTickSpacing(maxNum/4);
        sDraw.setMinorTickSpacing(20);
        sDraw.setPaintTicks(true);
        sDraw.setPaintLabels(true);
        add(sDraw);
        sDraw.addChangeListener(this);

        bLines = new JButton("Pokaż linie");
        bLines.setBounds(100,10,100,20);
        add(bLines);
        bLines.addActionListener(this);

    }

    public static void main(String[] args) {

        Application app = new Application();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);

    }

    MonteCarloJPanel jPanel = new MonteCarloJPanel();
    JFrame application = new JFrame("Monte Carlo Pi");

    @Override
    public void stateChanged(ChangeEvent e) {


        {

            jPanel.setNumExperiments(sDraw.getValue());
            application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            application.add(jPanel);
            application.setSize(612, 680);
            application.setVisible(true);
            jPanel.re();

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
            Object  source = e.getSource();
            if (source == bLines)
        {
         if(jPanel.getVisibleLine()==true)
            {
                jPanel.setVisibleLine(false);
                jPanel.setNumExperiments(sDraw.getValue());
                application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                application.add(jPanel);
                application.setSize(612, 680);
                application.setVisible(true);
                jPanel.re();
            }
            else
            {
                jPanel.setVisibleLine(true);
                jPanel.setNumExperiments(sDraw.getValue());
                application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                application.add(jPanel);
                application.setSize(612, 680);
                application.setVisible(true);
                jPanel.re();
            }
        }

    }
}