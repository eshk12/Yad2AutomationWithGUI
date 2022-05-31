package com.Yad2AutomationWithGUI.bouncer;

import com.Yad2AutomationWithGUI.bouncer.Frames.DashboardJFrame;
import com.Yad2AutomationWithGUI.bouncer.Frames.LoginJFrame;
import com.Yad2AutomationWithGUI.bouncer.Listeners.EventListener;
import com.Yad2AutomationWithGUI.bouncer.Services.FlowControl;

import javax.swing.*;
import java.awt.*;

import com.formdev.flatlaf.FlatDarculaLaf;

public class Main {
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        LoginJFrame loginJFrame = new LoginJFrame(screenSize);
        DashboardJFrame dashboardJFrame = new DashboardJFrame(screenSize);
        new EventListener(loginJFrame, dashboardJFrame);
        FlowControl.getInstance().setDashboardJFrame(dashboardJFrame);
    }
}
