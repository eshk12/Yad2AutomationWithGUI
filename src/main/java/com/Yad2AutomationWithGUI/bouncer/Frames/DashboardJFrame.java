package com.Yad2AutomationWithGUI.bouncer.Frames;

import com.Yad2AutomationWithGUI.bouncer.Utils.Definitions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DashboardJFrame extends JFrame {
    protected int widthDimension = 800;
    protected int heightDimension = 650;
    private JList adJList;
    private ArrayList adsArrayList;
    private JLabel timeRemain;
    public DashboardJFrame(Dimension screenSize) {
        this.setBounds(
                (int) (screenSize.getWidth() - widthDimension) / 2,
                (int) (screenSize.getHeight() - heightDimension) / 2,
                widthDimension,
                heightDimension
        );

        JLabel exitJLabel = new JLabel("X");
        exitJLabel.setBounds(15, 15, 30, 30);
        exitJLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        exitJLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        exitJLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        JLabel timerTitle = new JLabel(Definitions.timerTitle);
        timerTitle.setBounds(350,50,350,100);
        timerTitle.setFont(new Font("Calibri", Font.BOLD, 30));

        timeRemain = new JLabel();
        timeRemain.setBounds(230,50,350,100);
        timeRemain.setFont(new Font("Calibri", Font.BOLD, 30));


        adJList = new JList();
        adJList.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);;

        JScrollPane adJScrollPane = new JScrollPane(adJList);
        adJScrollPane.setBounds(0,200,widthDimension,400);
        adJScrollPane.setPreferredSize(new Dimension(20, 20));

        JLabel copyrights = new JLabel(Definitions.copyrights);
        copyrights.setBounds((widthDimension-280)/2,610,280,30);
        copyrights.setFont(new Font("Calibri", Font.BOLD, 15));

        this.getContentPane().setLayout(null);
        this.setUndecorated(true);
        this.add(exitJLabel);
        this.add(timerTitle);
        this.add(timeRemain);
        this.add(adJScrollPane);
        this.add(copyrights);
    }

    public JList getAdJList() {
        return adJList;
    }

    public void setAdJList(JList adJList) {
        this.adJList = adJList;
    }

    public JLabel getTimeRemain() {
        return timeRemain;
    }

    public void setTimeRemain(JLabel timeRemain) {
        this.timeRemain = timeRemain;
    }
}
