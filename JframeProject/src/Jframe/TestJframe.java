package Jframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestJframe {
    // Global variables for User ID and Password
   // private static String globalUserId;
    //private static String globalPassword;
    
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Login Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setResizable(false);
        
        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Create the main panel with a GridBagLayout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create components
        JLabel urlLabel = new JLabel("URL:");
        JTextField urlField = new JTextField(30);

		/*
		 * JLabel userIdLabel = new JLabel("User ID:"); JTextField userIdField = new
		 * JTextField(20);
		 * 
		 * JLabel passwordLabel = new JLabel("Password:"); JPasswordField passwordField
		 * = new JPasswordField(20);
		 */

        JButton loginButton = new JButton("Login");
        JLabel messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Set layout constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to panel
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(urlLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(urlField, gbc);

		/*
		 * gbc.gridx = 0; gbc.gridy = 1; panel.add(userIdLabel, gbc); gbc.gridx = 1;
		 * gbc.gridy = 1; panel.add(userIdField, gbc);
		 * 
		 * gbc.gridx = 0; gbc.gridy = 2; panel.add(passwordLabel, gbc); gbc.gridx = 1;
		 * gbc.gridy = 2; panel.add(passwordField, gbc);
		 */
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        frame.add(messageLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        
        // Add action listener to login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = urlField.getText().trim();
				/*
				 * globalUserId = userIdField.getText().trim(); globalPassword = new
				 * String(passwordField.getPassword()).trim();
				 */
                boolean Urlvalid = isValidUrl(url);

                if (url.isEmpty()) {
                    messageLabel.setText("All fields are required!");
                    messageLabel.setForeground(Color.RED);
                }else if(Urlvalid == false) {
                	System.out.println("The URL format is Incorrect......!!");
                	  messageLabel.setText("The URL format is Incorrect!");
                      messageLabel.setForeground(Color.RED);
                }
                else {
                    try {
                        messageLabel.setText("Opening login page...");
                        messageLabel.setForeground(Color.BLACK);

                        // Open the login page and perform login using Selenium
                        performLoginWithSelenium(url);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    } catch (Exception ex) {
                        messageLabel.setText("Error: " + ex.getMessage());
                        messageLabel.setForeground(Color.RED);
                    }
                }
                
            }
        });

        // Make the frame visible
        frame.setVisible(true);

    }
    public static boolean isValidUrl(String url) {
        // Regular expression to match a valid URL format
        String urlPattern = "^(https?|ftp)://[^\s/$.?#].[^\s]*$";
        Pattern pattern = Pattern.compile(urlPattern);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }
    private static void performLoginWithSelenium(String url) {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver\\chromedriver.exe");

      WebDriver driver = new ChromeDriver();
      driver.navigate().to(url);
      
      driver.quit();
  
    }
}
