import Frames.DashboardJFrame;
import Frames.LoginJFrame;
import Listeners.EventListener;
import Services.FlowControl;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;


public class Yad2AutomationWithGUI {
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
