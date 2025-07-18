import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class RegistrationFrame extends JFrame{  //Jframe is GUI window. Extending a subclass for easy declaration in main class.
    RegistrationFrame(){

        ImageIcon logoImage = new ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Desktop\\WeatherApp\\Images\\Login\\Logo.jpeg");  //Logo icon

        this.setTitle("Weather Me!");  //Frame title
        this.setSize(670,470);  //Frame X x Y dimension
        this.getContentPane().setBackground(new Color(0x87CEEB));
        this.setIconImage(logoImage.getImage()); //Change logo image
        this.setVisible(true);  //Making frame visible
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);  //Closes application on X button
    }
}


