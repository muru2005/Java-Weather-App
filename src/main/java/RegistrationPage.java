import java.awt.*;
import java.io.FileWriter;
import javax.swing.*;
import javax.swing.border.Border;

class RegisterPanel extends JPanel{
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawLine(90, 151, 340, 151);
        g.drawLine(90, 191, 340, 191);
        g.drawLine(90, 231, 340, 231);
        g.drawLine(90, 271, 340, 271);
        g.drawLine(90, 311, 340, 311);
        g.drawLine(90, 351, 340, 351);
    }
}




public class RegistrationPage {
    void register(String name, String username, String pwd, String phoneno, String email, String address) {

        String id = phoneno + name.substring(0, 5);
        String csvfile = "D:\\java project\\untitled2\\src\\main\\java\\login.csv";
        String file="D:\\java project\\untitled2\\src\\main\\java\\org\\example\\favourites.csv";
        try (FileWriter writer = new FileWriter(csvfile, true)) {
            writer.append(id + "," + name + "," + username + "," + pwd + "," + phoneno + "," + email + "," + address + "\n");
            System.out.println("your id is :" + id);
            System.out.println("Registration is succesfully completed");
            try(FileWriter writer1 = new FileWriter(file, true)){
                writer1.append(id+","+"NULL"+",");
            }
            catch (Exception e){
                System.out.println(e);
            }


        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public static void main(String[] args) {
        
        RegistrationFrame myRegistrationFrame= new RegistrationFrame();
        myRegistrationFrame.setLayout(null);


        ImageIcon usernameLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Login\\username.png");
        ImageIcon padlockLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Login\\padlock.png");
        ImageIcon weatherMeImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Login\\weatherMe3.png");

        ImageIcon RegistrationWallpaper= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Login\\loginWallpaper.jpg");

        ImageIcon homeLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Registration\\home.png");
        ImageIcon emailLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Registration\\email.png");
        ImageIcon phoneLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Registration\\phone.png");
        ImageIcon userLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Registration\\user.png");


        
        //Creating Labels
        JLabel RegistrationLabel = new JLabel(RegistrationWallpaper); //Importing image to label
        RegistrationLabel.setBounds(0, 0, 250, 425);  //Set Image label position and bounds

        JLabel usernameLogo = new JLabel(usernameLogoImage);
        usernameLogo.setBounds(45, 160, 30, 30);

        JLabel padlockLogo = new JLabel(padlockLogoImage);
        padlockLogo.setBounds(45, 200, 30, 30);

        JLabel weatherMe= new JLabel(weatherMeImage);
        weatherMe.setBounds(100, 0, 200, 100);

        JLabel homeLogo = new JLabel(homeLogoImage);
        homeLogo.setBounds(45, 320, 30, 30);

        JLabel emailLogo = new JLabel(emailLogoImage);
        emailLogo.setBounds(45, 240, 30, 30);

        JLabel phoneLogo = new JLabel(phoneLogoImage);
        phoneLogo.setBounds(45, 280, 30, 30);

        JLabel userLogo= new JLabel(userLogoImage);
        userLogo.setBounds(45, 120, 30, 30);


        myRegistrationFrame.add(RegistrationLabel);

        Border border= BorderFactory.createLineBorder(new Color(0x87CEEB));
        
        //Creating Panel
        RegisterPanel p1= new RegisterPanel();
        p1.setBackground(new Color(0x87CEEB));
        p1.setBounds(250,0,400,425);
        p1.setLayout(null);


        //Creating Text fields

        JTextField textField11 = new JTextField("Username");
        textField11.setBackground(new Color(0x87CEEB));
        textField11.setBounds(90, 160, 250, 30);
        textField11.setBorder(border);
 
        JTextField textField12 = new JTextField("Password");
        textField12.setBackground(new Color(0x87CEEB));
        textField12.setBounds(90, 200, 250, 30);
        textField12.setBorder(border);

        JTextField textField13 = new JTextField("Email");
        textField13.setBackground(new Color(0x87CEEB));
        textField13.setBounds(90, 240, 250, 30);
        textField13.setBorder(border);

        JTextField textField14 = new JTextField("Phone number");
        textField14.setBackground(new Color(0x87CEEB));
        textField14.setBounds(90, 280, 250, 30);
        textField14.setBorder(border);

        JTextField textField15 = new JTextField("Home city");
        textField15.setBackground(new Color(0x87CEEB));
        textField15.setBounds(90, 320, 250, 30);
        textField15.setBorder(border);
 
        JTextField textField16 = new JTextField("Name");
        textField16.setBackground(new Color(0x87CEEB));
        textField16.setBounds(90, 120, 250, 30);
        textField16.setBorder(border);

        RegistrationPage rf=new RegistrationPage();

         // Create the button
         JButton buttonRegister = new JButton("Create Account");
         buttonRegister.setForeground(Color.BLACK);
         buttonRegister.setFont(new Font("Arial", Font.PLAIN, 13));
         buttonRegister.setBackground(new Color(0x87CEEB));
         buttonRegister.setBounds(90, 370, 250, 30);
         buttonRegister.addActionListener(e -> {
             String name=textField16.getText();
             System.out.println(name);
             String username=textField11.getText();
             String password=textField12.getText();
             String email=textField13.getText();
             String phone=textField14.getText();
             System.out.println(phone);
             String city=textField15.getText();
             rf.register(name, username, password, phone, email, city);
             GUiPrac.main(args);myRegistrationFrame.dispose();});
        
        // Add the components to the panel
        p1.add(textField11);
        p1.add(textField12);
        p1.add(textField13);
        p1.add(textField14);
        p1.add(textField15);
        p1.add(textField16);

        p1.add(usernameLogo);
        p1.add(padlockLogo);
        p1.add(weatherMe);
        p1.add(homeLogo);
        p1.add(userLogo);
        p1.add(emailLogo);
        p1.add(phoneLogo);

        p1.add(buttonRegister);

        myRegistrationFrame.add(p1);

    }
    
}
