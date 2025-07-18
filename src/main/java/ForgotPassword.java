import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;

class ForgotPanel extends JPanel{
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawLine(90, 151, 340, 151);
        g.drawLine(90, 191, 340, 191);
        g.drawLine(90, 341, 340, 341);
    }
}

class OTPFrame extends JFrame{
    OTPFrame(int reply){
        
        ImageIcon logoImage = new ImageIcon("C:\\Users\\mahad\\OneDrive\\Desktop\\WeatherApp\\Images\\Login\\Logo.jpeg");  //Logo icon

        this.setTitle("Weather Me!");  //Frame title
        this.setSize(220,165);  //Frame X x Y dimension
        this.getContentPane().setBackground(new Color(0x87CEEB));
        this.setIconImage(logoImage.getImage()); //Change logo image
        this.setVisible(true);  //Making frame visible
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);  //Closes application on X button
        this.setLayout(null);

        JLabel replyLable=new JLabel();

        if (reply==0){
            replyLable.setText("<HTML>Verification<br>Failed</HTML>");
        }
        else{
            replyLable.setText("<HTML>Verification<br>Successful</HTML>");
        }

        replyLable.setFont(new Font("Arial", Font.PLAIN, 20));
        replyLable.setBounds(30,30,150,60);

        this.add(replyLable);

    }
}

public class ForgotPassword {

    static void OTPCheck(int r){
        OTPFrame frame1= new OTPFrame(r); //OTP checker

    }
    int verify(String username,String phoneno) throws IOException {
        String file = "D:\\java project\\untitled2\\src\\main\\java\\login.csv";
        String line = "";
        String csvSplitBy = ",";
        int c = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);

                if (data[2].equals(username) && data[4].equals(phoneno)) {
                    c = 1;
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return c;

    }
    void forgotpwd(String username, String  phoneno,String password) throws IOException {
        String file = "D:\\java project\\untitled2\\src\\main\\java\\login.csv";
        String line = "";
        String csvSplitBy = ",";

        java.util.List<java.util.List<String>> arr1 = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int c = 0;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                java.util.List<String> dataList = new ArrayList<>(java.util.List.of(data));
                if (data[2].equals(username) && data[4].equals(phoneno)) {

                    dataList.set(3, password);
                    c = 1;
                }
                arr1.add(dataList);
            }

            try (FileWriter writer = new FileWriter(file, false)) {
                for (List<String> row : arr1) {
                    writer.append(String.join(csvSplitBy, row));
                    writer.append(",");
                    writer.append("\n");
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        } catch (Exception e) {
            System.out.println(e);
        }


    }


    public static void main(String args[]){
        
        LoginFrame myForgotPasswordFrame= new LoginFrame();  //Create login frame
        myForgotPasswordFrame.setLayout(null);

        




        //Importing Wallpaper
        ImageIcon loginwallpaper= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Login\\loginWallpaper.jpg");
        ImageIcon usernameLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Login\\username.png");
        ImageIcon padlockLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Login\\padlock.png");
        ImageIcon weatherMeImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Login\\weatherMe3.png");

        ImageIcon phoneLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Registration\\phone.png");


        JLabel loginLabel = new JLabel(loginwallpaper); //Importing image to label
        loginLabel.setBounds(0, 0, 250, 425);  //Set Image label position and bounds

        JLabel usernameLogo = new JLabel(usernameLogoImage);
        usernameLogo.setBounds(55, 120, 30, 30);

        JLabel phoneLogo = new JLabel(phoneLogoImage);
        phoneLogo.setBounds(55, 160, 30, 30);

        JLabel weatherMe= new JLabel(weatherMeImage);
        weatherMe.setBounds(120, 0, 200, 100);

        JLabel passwordLogo= new JLabel(padlockLogoImage);
        passwordLogo.setBounds(55,310,30,30);

        Border border= BorderFactory.createLineBorder(new Color(0x87CEEB));

        // Create the text fields
        JTextField textField1 = new JTextField("Username");
        textField1.setBackground(new Color(0x87CEEB));
        textField1.setBounds(90, 120, 250, 30);
        textField1.setBorder(border);

        JTextField textField2 = new JTextField("Phone number");
        textField2.setBackground(new Color(0x87CEEB));
        textField2.setBounds(90, 160, 250, 30);
        textField2.setBorder(border);

        JTextField textField3 = new JTextField("New password");
        textField3.setBackground(new Color(0x87CEEB));
        textField3.setBounds(90, 310, 250, 30);
        textField3.setBorder(border);
        
        
        
        myForgotPasswordFrame.add(loginLabel);






        ForgotPanel p1= new ForgotPanel();
        p1.setBackground(new Color(0x87CEEB));
        p1.setBounds(250,0,400,425);
        p1.setLayout(null);

        ForgotPassword fg=new ForgotPassword();

        // Create the button
        JButton buttonOTP = new JButton("Verify");
        buttonOTP.setForeground(Color.BLACK);
        buttonOTP.setFont(new Font("Arial", Font.PLAIN, 13));
        buttonOTP.setBackground(new Color(0x87CEEB));
        buttonOTP.setBounds(90, 200, 250, 30);
        buttonOTP.addActionListener(e -> {
            String username=textField1.getText();
            String phoneno=textField2.getText();

            int r=0;
            try {
                r=fg.verify(username,phoneno);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            OTPCheck(r);});

        JButton buttonSetPassword = new JButton("Set new password");
        buttonSetPassword.setForeground(Color.BLACK);
        buttonSetPassword.setFont(new Font("Arial", Font.PLAIN, 13));
        buttonSetPassword.setBackground(new Color(0x87CEEB));
        buttonSetPassword.setBounds(90,355,250,30);

        buttonSetPassword.addActionListener(e -> {
            String username=textField1.getText();
            String phoneno=textField2.getText();
            String password=textField3.getText();
            try {
                fg.forgotpwd(username,phoneno,password);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            GUiPrac.main(args);myForgotPasswordFrame.dispose();});



         // Create the labels
         JLabel EnterPasswordLabel = new JLabel("Enter new password: ");
         EnterPasswordLabel.setForeground(Color.black);
         EnterPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
         EnterPasswordLabel.setBounds(90, 285, 150, 14);

         

        // Add the components to the panel
        p1.add(textField1);
        p1.add(textField2);
        p1.add(buttonOTP);
        p1.add(buttonSetPassword);
        p1.add(textField3);
        p1.add(usernameLogo);
        p1.add(phoneLogo);
        p1.add(weatherMe);
        p1.add(EnterPasswordLabel);
        p1.add(passwordLogo);
        
        myForgotPasswordFrame.add(p1);
        
        
    }
}
