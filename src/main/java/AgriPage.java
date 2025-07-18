import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReferenceArray;
import javax.swing.*;
import javax.swing.border.Border;

import org.example.AccuWeatherAPI;
import org.example.Main1;
import org.example.Main3;
import org.example.Main4;

public class AgriPage {

    public static int flag=10;
    public static void main(String args[]){
        Main1 m= new Main1();

        //Create frame for homepage
        HomeFrame myHomeFrame= new HomeFrame();
        myHomeFrame.setLayout(null);

        //Border 
        Border border= BorderFactory.createLineBorder(new Color(0x87CEEB));


        //Weather Conditions

        double temp=29.5;
        String weather="Cloudy";

        LocalDate currentDate= LocalDate.now();
        String dayOfWeek=currentDate.getDayOfWeek().getDisplayName(TextStyle.FULL,Locale.ENGLISH);



        //Import images
        ImageIcon homeLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Home\\home.png");
        ImageIcon libraryLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Home\\library.png");
        ImageIcon weatherMeImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Login\\weatherMe3.png");
        ImageIcon exitLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Home\\logout.png");
        ImageIcon sportsLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Home\\cricket.png");
        ImageIcon cropsLogoImage=new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Home\\wheat-grain.png");
        ImageIcon tourismLogoImage=new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Home\\location.png");
        ImageIcon searchLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Home\\search.png");




        //Create left side menu panel
        leftPanel leftMenuPanel= new leftPanel();
        leftMenuPanel.setBackground(new Color(0x87CEEB));
        leftMenuPanel.setBounds(0,0,200,425);
        leftMenuPanel.setLayout(null);

        //Labels for Left Menu
        JLabel weatherMe= new JLabel(weatherMeImage);
        weatherMe.setBounds(0, 0, 200, 100);

        JLabel homeLogo= new JLabel(homeLogoImage);
        homeLogo.setBounds(10,120,30,30);

        JLabel libraryLogo= new JLabel(libraryLogoImage);
        libraryLogo.setBounds(10,160,30,30);

        JLabel cropsLogo= new JLabel(cropsLogoImage);
        cropsLogo.setBounds(10,200,30,30);

        JLabel tourismLogo= new JLabel(tourismLogoImage);
        tourismLogo.setBounds(10,240,30,30);

        JLabel sportsLogo= new JLabel(sportsLogoImage);
        sportsLogo.setBounds(10,280,30,30);

        JLabel exitLogo= new JLabel(exitLogoImage);
        exitLogo.setBounds(10,380,30,30);

        
        

        //Buttons for Left Menu
        JButton buttonHome = new JButton("Home");
        buttonHome.setForeground(Color.BLACK);
        buttonHome.setFont(new Font("Arial", Font.PLAIN, 13));
        buttonHome.setBackground(new Color(0x87CEEB));
        buttonHome.setBounds(45, 120, 150, 30);
        buttonHome.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left

        buttonHome.addActionListener(e->{
            AccuWeatherAPI b=new AccuWeatherAPI();

            String[] p={args[0],b.homecity(args[0])};
            HomePage.main(p);myHomeFrame.dispose();});

        JButton buttonLibrary = new JButton("Library");
        buttonLibrary.setForeground(Color.BLACK);
        buttonLibrary.setFont(new Font("Arial", Font.PLAIN, 13));
        buttonLibrary.setBackground(new Color(0x87CEEB));
        buttonLibrary.setBounds(45, 160, 150, 30);
        buttonLibrary.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
        buttonLibrary.addActionListener(e->{
            String[] p={args[0]};
            LibraryPage.main(p);
            myHomeFrame.dispose();});

        JButton buttonSports = new JButton("Venue Finder");
        buttonSports.setForeground(Color.BLACK);
        buttonSports.setFont(new Font("Arial", Font.PLAIN, 13));
        buttonSports.setBackground(new Color(0x87CEEB));
        buttonSports.setBounds(45, 280, 150, 30);
        buttonSports.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
        buttonSports.addActionListener(e->{
            Main3 m3=new Main3();
            String[] example=m3.run1("Chennai",0,"Hockey");
            String[] example2=new String[example.length+2];
            example2[0]=args[0];
            example2[1]="Chennai";
            System.arraycopy(example,0,example2,2,example.length);
            System.out.println(example2);
            SportsPage.main(example2);myHomeFrame.dispose();});

        JButton buttonCrops = new JButton("AgriAdvisor");
        buttonCrops.setForeground(Color.BLACK);
        buttonCrops.setFont(new Font("Arial", Font.PLAIN, 13));
        buttonCrops.setBackground(new Color(0x87CEEB));
        buttonCrops.setBounds(45, 200, 150, 30);
        buttonCrops.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left

        JButton buttonTourism = new JButton("Explore city");
        buttonTourism.setForeground(Color.BLACK);
        buttonTourism.setFont(new Font("Arial", Font.PLAIN, 13));
        buttonTourism.setBackground(new Color(0x87CEEB));
        buttonTourism.setBounds(45, 240, 150, 30);
        buttonTourism.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
        buttonTourism.addActionListener(e -> {
            Main4 m4=new Main4();
            String[] example=m4.run1("Chennai","No",0.0,0,"No","Paid");
            System.out.println(example[0]);
            String[] example2= new String[example.length+1];
            example2[0]=args[0];
            System.arraycopy(example,0,example2,1,example.length);
            Tourism.main(example2)
            ;myHomeFrame.dispose();});

        JButton buttonExit = new JButton("Log out");
        buttonExit.setForeground(Color.BLACK);
        buttonExit.setFont(new Font("Arial", Font.PLAIN, 13));
        buttonExit.setBackground(new Color(0x87CEEB));
        buttonExit.setBounds(45, 380, 150, 30);
        buttonExit.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
        buttonExit.addActionListener(f->{GUiPrac.main(args);myHomeFrame.dispose();});

        //Add elements to LeftMenuPanel

        leftMenuPanel.add(weatherMe);
        leftMenuPanel.add(homeLogo);
        leftMenuPanel.add(libraryLogo);
        leftMenuPanel.add(exitLogo);
        leftMenuPanel.add(sportsLogo);
        leftMenuPanel.add(cropsLogo);
        leftMenuPanel.add(tourismLogo);

        leftMenuPanel.add(buttonHome);
        leftMenuPanel.add(buttonExit);
        leftMenuPanel.add(buttonLibrary);
        leftMenuPanel.add(buttonCrops);
        leftMenuPanel.add(buttonSports);
        leftMenuPanel.add(buttonTourism);


            //Right top panel
     JPanel rightTopTourism=new JPanel();
     rightTopTourism.setBackground(new Color(0x87CEEB));
     
     rightTopTourism.setBounds(201,0,455,120);
     rightTopTourism.setLayout(null);


     //Labels for Right Top Tourism

     JLabel selectCityLabel = new JLabel("Select city:");
     selectCityLabel.setFont(new Font("Arial",Font.PLAIN,24));
     selectCityLabel.setBounds(15, 15, 120, 28);

     //ComboBox

     String[] citiesOptions={"Chennai","Mumbai","Delhi"};

     JComboBox<String> citiesBox = new JComboBox<>(citiesOptions);
     citiesBox.setSelectedIndex(0); //Making Chennai default option
     citiesBox.setBounds(15, 52, 175, 30);
     citiesBox.setFont(new Font("Arial",Font.PLAIN,22));




     //Search Button for Right top
        String[] example;
        example = args;





     JButton searchButton=new JButton("Search",searchLogoImage);
     searchButton.setFont(new Font("Arial",Font.PLAIN,20));
     searchButton.setBounds(225, 52, 150, 28);
      searchButton.addActionListener(h->{
          try {
              String[] example1=m.run(citiesOptions[citiesBox.getSelectedIndex()]);
              String[] example2=new String[example1.length+1];
              example2[0]=args[0];
              System.arraycopy(example1,0,example2,1,example1.length);
              AgriPage.main(example2);

          } catch (IOException ex) {
              throw new RuntimeException(ex);
          }
          System.out.println(citiesOptions[citiesBox.getSelectedIndex()]);
            myHomeFrame.dispose();
            ;});

        //RIGTH BOTTOM PANEL

        JPanel libRightBottom=new JPanel();
        libRightBottom.setBackground(new Color(0x87CEEB));
        libRightBottom.setLayout(new BoxLayout(libRightBottom, BoxLayout.Y_AXIS));

        int i =1;
        while (example[i]!=null) {
            JPanel eachCityPanel= new JPanel();
            eachCityPanel.setBackground(new Color(0x87CEEB));
            eachCityPanel.setPreferredSize(new Dimension(430,80));
            eachCityPanel.setLayout(null);
            eachCityPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));


            JLabel cropName=new JLabel(example[i]);
            cropName.setFont(new Font("Arial", Font.BOLD, 20));
            cropName.setBounds(10, 10, 320, 25);
            cropName.setHorizontalAlignment(SwingConstants.LEFT);
            System.out.println("Crop "+example[i]);

            JLabel marketPrice=new JLabel("Market price: ₹"+ example[i+1]);
            marketPrice.setFont(new Font("Arial", Font.PLAIN, 18));
            marketPrice.setBounds(10, 40, 270, 25);
            marketPrice.setHorizontalAlignment(SwingConstants.LEFT);
            System.out.println("Market "+example[i+1]);

            ImageIcon cropImage=new ImageIcon(example[i+2]);


            JLabel cropImageLabel=new JLabel(cropImage);
            cropImageLabel.setBounds(335, 5, 65, 65);

            eachCityPanel.add(cropName);
            eachCityPanel.add(marketPrice);
            eachCityPanel.add(cropImageLabel);

            libRightBottom.add(eachCityPanel);



            i+=3;

        }
        //Scrollbar for base panel
        JScrollPane scroll= new JScrollPane(libRightBottom);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(201,120,455,305);

        myHomeFrame.add(scroll);




     //Adding elements to Right top
     rightTopTourism.add(selectCityLabel);

     rightTopTourism.add(citiesBox);

     rightTopTourism.add(searchButton);

















     //Add elements to homeFrame
     myHomeFrame.add(leftMenuPanel);
     myHomeFrame.add(rightTopTourism);

}
}
