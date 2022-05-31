package com.Yad2AutomationWithGUI.bouncer.Frames;

import com.Yad2AutomationWithGUI.bouncer.Utils.Definitions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class LoginJFrame extends JFrame {
    protected int widthDimension = 450;
    protected int heightDimension = 300;
    private JButton loginButton;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    public LoginJFrame(Dimension screenSize) {
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
        JLabel loginJLabel = new JLabel(Definitions.loginJFrameTitle, SwingConstants.CENTER);
        loginJLabel.setBounds(50, 50, 360, 50);
        loginJLabel.setFont(new Font("Calibri", Font.BOLD, 20));

        JLabel emailLabel = new JLabel(Definitions.emailLabel);
        emailLabel.setBounds((widthDimension - 150), 5, 150, 200);
        emailLabel.setFont(new Font("Calibri", Font.PLAIN, 18));

        emailTextField = new JTextField();
        emailTextField.setBounds(90, 94, 200, 25);

        JLabel passwordLabel = new JLabel(Definitions.passwordLabel);
        passwordLabel.setBounds((widthDimension - 100), 10, 100, 250);
        passwordLabel.setFont(new Font("Calibri", Font.PLAIN, 18));

        passwordField = new JPasswordField();
        passwordField.setBounds(90, 124, 200, 25);

        loginButton = new JButton(Definitions.loginButton);
        loginButton.setBounds((widthDimension - 400) / 2, 175, 400, 50);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
        this.setUndecorated(true);
        this.setVisible(true);

        this.add(exitJLabel);
        this.add(loginJLabel);
        this.add(emailLabel);
        this.add(emailTextField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(loginButton);

    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JTextField getEmailTextField() {
        return emailTextField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }
}
