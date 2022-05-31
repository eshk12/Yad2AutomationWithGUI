package com.Yad2AutomationWithGUI.bouncer.Listeners;

import com.Yad2AutomationWithGUI.bouncer.Frames.DashboardJFrame;
import com.Yad2AutomationWithGUI.bouncer.Frames.LoginJFrame;
import com.Yad2AutomationWithGUI.bouncer.Services.FlowControl;
import com.Yad2AutomationWithGUI.bouncer.Utils.Definitions;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EventListener {
    private LoginJFrame loginJFrame;
    private DashboardJFrame dashboardJFrame;

    public EventListener(LoginJFrame loginJFrame, DashboardJFrame dashboardJFrame) {
        this.loginJFrame = loginJFrame;
        this.dashboardJFrame = dashboardJFrame;
        loginJFrame.getLoginButton().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String email = loginJFrame.getEmailTextField().getText();
                String password = String.valueOf(loginJFrame.getPasswordField().getPassword());
                if (email != null && password != null) {
                    if (email.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "ישנם פרטים חסרים בטופס.");
                    } else {
                        try {
                            if (FlowControl.getInstance().performLogin(email, password)) {
                                loginJFrame.setVisible(false);
                                dashboardJFrame.setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(null, "הפרטים שהוזנו אינם נכונים.");
                            }
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "אירעה שגיאה אנא נסה מאוחר יותר");
                }

            }
        });

        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                FlowControl flowControlSingleton = FlowControl.getInstance();
                if (flowControlSingleton.isAbleToBounce()) {
                    Instant timeForNextBounce = (flowControlSingleton.getLastBounced().plus(Definitions.bounceTime));
                    Duration timeRemaining = Duration.between(Instant.now(), timeForNextBounce);

                    long totalSecs = timeRemaining.getSeconds();

                    long hours = totalSecs / 3600;
                    long minutes = (totalSecs % 3600) / 60;
                    long seconds = totalSecs % 60;

                    if(flowControlSingleton.getAds().size() > 0 && !flowControlSingleton.isAdsShown()){
                        dashboardJFrame.getAdJList().setListData(flowControlSingleton.getAds().toArray());
                    }
                    if (timeRemaining.isNegative()) {
                        dashboardJFrame.getTimeRemain().setText("מקפיץ");
                        try {
                            flowControlSingleton.performLogin();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else {
                        dashboardJFrame.getTimeRemain().setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
                    }

                }

            }

        }, 0, 1, TimeUnit.SECONDS);
    }

}
