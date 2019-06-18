package gui.controller;


import gui.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import static data.dataSupport.*;

public class MainFrameController {

    private MainFrame mainFrame;
    private JButton routeButton;
    private JTextArea routeTextArea;
    private JList routeList;
    private JScrollPane routeScrollPane;
    private JScrollPane answerScrollPane;


    public MainFrameController() {
        initComponents();
        initListeners();
    }

    public void showMainFrameWindow() {
        mainFrame.setVisible(true);
    }

    private void initComponents() {
        mainFrame = new MainFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        routeButton = mainFrame.getRouteButton();
        routeTextArea = mainFrame.getRouteTextArea();
        routeList = mainFrame.getRouteList();
        routeScrollPane = mainFrame.getRouteScrollPane();
        answerScrollPane = mainFrame.getAnswerScrollPane();


        routeList.setListData(getDirectories("data"));
        routeScrollPane.setViewportView(routeList);
        answerScrollPane.setViewportView(routeTextArea);


    }

    private void initListeners() {
        routeButton.addActionListener(new RouteButtonLister());
    }

    private class RouteButtonLister implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            List getIdAttribute = null;
            List getNameAttribute = null;
            List getHours = null;

            try {
                getNameAttribute = getAttributeValue(String.valueOf(routeList.getSelectedValue()), "nazwa");
                getIdAttribute = getAttributeValue(String.valueOf(routeList.getSelectedValue()), "id");
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            try {
                getHours = getHours(String.valueOf(routeList.getSelectedValue()), String.valueOf(getIdAttribute.get(0)));
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            routeTextArea.setText("");

            for (int i = 0; i < getNameAttribute.size(); i++) {
                routeTextArea.append(String.valueOf(getNameAttribute.get(i)) + "\n");


                for (int j = 0; j < getHours.size(); j++) {
                    routeTextArea.append(String.valueOf(getHours.get(j)) + ":00 ");
                }
                routeTextArea.append("\n");
            }

        }

    }
}
