package gui.view;

import javax.swing.*;

public class MainFrame extends JFrame {

    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private JPanel mainPanel;
    private JButton routeButton;
    private JTextArea routeTextArea;
    private JList routeList;
    private JScrollPane routeScrollPane;
    private JScrollPane answerScrollPane;

    public MainFrame() {
        setSize(WIDTH, HEIGHT);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
    }

    public JButton getRouteButton() {
        return routeButton;
    }

    public JTextArea getRouteTextArea() {
        return routeTextArea;
    }

    public JList getRouteList() {
        return routeList;
    }

    public JScrollPane getRouteScrollPane() {
        return routeScrollPane;
    }

    public JScrollPane getAnswerScrollPane() {
        return answerScrollPane;
    }
}

