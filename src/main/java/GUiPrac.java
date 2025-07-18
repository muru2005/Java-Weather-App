import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
public class GUiPrac{
     String[] login(String username,String password) throws IOException {

        String csvFile = "D:\\java project\\untitled2\\src\\main\\java\\login.csv";
        String line = "";
        String csvSplitBy = ",";
        String arr1[]=new String[3];
        arr1[0]="5";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int c = 0;
            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] data = line.split(csvSplitBy);
                if (data[2].equals(username) && data[3].equals(password)) {

                    System.out.println("login sucessfull");
                    c = 1;
                    arr1[0]="1";
                    arr1[1]=data[0];
                    arr1[2]=data[6];


                }

            }
            if (c == 0) {
                System.out.println("login unsuccessfull");

                arr1[0]="0";


            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return arr1;
     }

    static JButton buttonLogin;

    public static void main(String args[]){
        
        LoginFrame myLoginFrame= new LoginFrame();  //Create login frame
        myLoginFrame.setLayout(null);

        //Importing Wallpaper
        ImageIcon loginwallpaper= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Login\\loginWallpaper.jpg");
        ImageIcon usernameLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Login\\username.png");
        ImageIcon padlockLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Login\\padlock.png");
        ImageIcon weatherMeImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Login\\weatherMe3.png");

        JLabel loginLabel = new JLabel(loginwallpaper); //Importing image to label
        loginLabel.setBounds(0, 0, 250, 425);  //Set Image label position and bounds

        JLabel usernameLogo = new JLabel(usernameLogoImage);
        usernameLogo.setBounds(55, 120, 30, 30);

        JLabel padlockLogo = new JLabel(padlockLogoImage);
        padlockLogo.setBounds(55, 160, 30, 30);

        JLabel weatherMe= new JLabel(weatherMeImage);
        weatherMe.setBounds(120, 0, 200, 100);
        
        
        
        myLoginFrame.add(loginLabel);

        Border border= BorderFactory.createLineBorder(new Color(0x87CEEB));




        JPanel p1= new JPanel();
        p1.setBackground(new Color(0x87CEEB));
        p1.setBounds(250,0,400,425);
        p1.setLayout(null);

        // Create the text fields
        JTextField textField1 = new JTextField("Username");
        textField1.setBackground(new Color(0x87CEEB));
        textField1.setBounds(90, 120, 250, 30);
        textField1.setBorder(border);

        JPasswordField textField2 = new JPasswordField("Password");
        textField2.setBackground(new Color(0x87CEEB));
        textField2.setBounds(90, 160, 250, 30);
        textField2.setBorder(border);
        textField2.setEchoChar('*');
        // Create the button
        buttonLogin = new JButton("Login");
        buttonLogin.setForeground(Color.BLACK);
        buttonLogin.setFont(new Font("Arial", Font.PLAIN, 13));
        buttonLogin.setBackground(new Color(0x87CEEB));
        buttonLogin.setBounds(90, 200, 250, 30);
        GUiPrac g=new GUiPrac();
        buttonLogin.addActionListener(e -> {
            String user=textField1.getText();
            String password=textField2.getText();
            try {
                String[] reply=g.login(user,password);
                if (reply[0].equals("1")){
                    System.out.println(user);
                    System.out.println(password);
                    System.out.println(reply[0]);
                    String[] passer={reply[1],reply[2]};
                    HomePage.main(passer);myLoginFrame.dispose();
                }
                else{
                    System.out.println("Fail");
                    System.out.println(user);
                    System.out.println(password);
                    System.out.println(reply[0]);
                }

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


            });
        

        JButton buttonRegister = new JButton("Register here");
        buttonRegister.setForeground(Color.BLACK);
        buttonRegister.setFont(new Font("Arial", Font.PLAIN, 13));
        buttonRegister.setBackground(new Color(0x87CEEB));
        buttonRegister.setBounds(90,355,250,30);
        buttonRegister.addActionListener(e -> {RegistrationPage.main(args);myLoginFrame.dispose();});

        JButton buttonForgotPassword = new JButton("Retrieve password");
        buttonForgotPassword.setForeground(Color.BLACK);
        buttonForgotPassword.setFont(new Font("Arial", Font.PLAIN, 13));
        buttonForgotPassword.setBackground(new Color(0x87CEEB));
        buttonForgotPassword.setBounds(90,275,250,30);
        buttonForgotPassword.addActionListener(e -> {ForgotPassword.main(args);myLoginFrame.dispose();});



         // Create the labels
         JLabel createAcountLabel = new JLabel("First time user?");
         createAcountLabel.setForeground(Color.black);
         createAcountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
         createAcountLabel.setBounds(125, 334, 150, 14); // Position and size of the username label

         // Create the labels
         JLabel forgotPasswordLabel = new JLabel("Forgot password?");
         forgotPasswordLabel.setForeground(Color.black);
         forgotPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
         forgotPasswordLabel.setBounds(125, 255, 150, 14);

         

        // Add the components to the panel
        p1.add(textField1);
        p1.add(textField2);
        p1.add(buttonLogin);
        p1.add(buttonRegister);
        p1.add(createAcountLabel);
        p1.add(usernameLogo);
        p1.add(padlockLogo);
        p1.add(weatherMe);
        p1.add(forgotPasswordLabel);
        p1.add(buttonForgotPassword);

        myLoginFrame.add(p1);
        
        
    }

}
