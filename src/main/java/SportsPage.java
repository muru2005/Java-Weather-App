import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.Border;

import org.example.AccuWeatherAPI;
import org.example.Main3;
import org.example.Main1;
import org.example.Main4;


public class SportsPage {

    public static void main(String args[]){

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


     Main1 m1=new Main1();

     JButton buttonCrops = new JButton("AgriAdvisor");
     buttonCrops.setForeground(Color.BLACK);
     buttonCrops.setFont(new Font("Arial", Font.PLAIN, 13));
     buttonCrops.setBackground(new Color(0x87CEEB));
     buttonCrops.setBounds(45, 200, 150, 30);
     buttonCrops.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
     buttonCrops.addActionListener(e->{
      try {
       String[] example=m1.run("Chennai");
       String[] example2=new String[example.length+1];
       example2[0]=args[0];
       System.arraycopy(example,0,example2,1,example.length);
       AgriPage.main(example2);
      } catch (IOException ex) {
       throw new RuntimeException(ex);
      }
      myHomeFrame.dispose();});

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
     buttonExit.addActionListener(e->{GUiPrac.main(args);myHomeFrame.dispose();});

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
     rightTopTourism.setBounds(201,0,455,170);
     rightTopTourism.setLayout(null);

     //Textfileds
     JTextField textField1 = new JTextField();
     textField1.setBackground(Color.white);
     textField1.setBounds(155, 120, 150, 24);
     textField1.setBorder(border);


     //Labels for Right Top Tourism

     JLabel selectCityLabel = new JLabel("Select city:");
     selectCityLabel.setFont(new Font("Arial",Font.PLAIN,22));
     selectCityLabel.setBounds(15, 15, 120, 24);

     JLabel selectDateLabel = new JLabel("Select date:");
     selectDateLabel.setFont(new Font("Arial",Font.PLAIN,22));
     selectDateLabel.setBounds(15, 50, 120, 24);

     JLabel selectSportLabel = new JLabel("Select Sport:");
     selectSportLabel.setFont(new Font("Arial",Font.PLAIN,22));
     selectSportLabel.setBounds(15, 85, 140, 24);

     JLabel minCapacityLabel = new JLabel("Min capacity:");
     minCapacityLabel.setFont(new Font("Arial",Font.PLAIN,22));
     minCapacityLabel.setBounds(15, 120, 140, 24);

     //ComboBox

     String[] citiesOptions={"Chennai","Mumbai","Delhi"};

     JComboBox<String> citiesBox = new JComboBox<>(citiesOptions);
     citiesBox.setSelectedIndex(0); //Making Chennai default option
     citiesBox.setBounds(135, 15, 125, 24);
     citiesBox.setFont(new Font("Arial",Font.PLAIN,18));

     // Define the date format
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

     // Initialize the array to hold dates
     String[] dates = new String[5];

     // Get today's date
     LocalDate today = LocalDate.now();

     // Fill the array with dates from today to the next four days
     for (int i = 0; i < 5; i++) {
         dates[i] = today.plusDays(i).format(formatter);
     }


     JComboBox<String> datesBox = new JComboBox<>(dates);
     datesBox.setSelectedIndex(0); //Making Chennai default option
     datesBox.setBounds(135, 50, 125, 24);
     datesBox.setFont(new Font("Arial",Font.PLAIN,18));

     String[] sportOptions={"Hockey","Cricket","Football"};

     JComboBox<String> sportBox = new JComboBox<>(sportOptions);
     sportBox.setSelectedIndex(0); //Making Chennai default option
     sportBox.setBounds(155, 85, 125, 24);
     sportBox.setFont(new Font("Arial",Font.PLAIN,18));

     //Search Button for Right top

     JPanel libRightBottom=new JPanel();
     libRightBottom.setBackground(Color.GREEN);
     libRightBottom.setLayout(new BoxLayout(libRightBottom, BoxLayout.Y_AXIS));
     Main3 m3=new Main3();

     String[] example=args;

     int i=2;

     while (example[i]!=null){
      JPanel eachCityPanel= new JPanel();
      eachCityPanel.setBackground(new Color(0x87CEEB));
      eachCityPanel.setPreferredSize(new Dimension(430,70));
      eachCityPanel.setLayout(null);
      eachCityPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));


      JLabel activityName=new JLabel(example[i]);
      activityName.setFont(new Font("Arial", Font.BOLD, 20));
      activityName.setBounds(10, 10, 320, 25);
      activityName.setHorizontalAlignment(SwingConstants.LEFT);

      JLabel placeName=new JLabel(example[i+1]);
      placeName.setFont(new Font("Arial", Font.PLAIN, 18));
      placeName.setBounds(10, 40, 150, 25);
      placeName.setHorizontalAlignment(SwingConstants.LEFT);

      JLabel placeTemp=new JLabel(example[i+2]+"°C");
      placeTemp.setFont(new Font("Arial", Font.PLAIN, 18));
      placeTemp.setBounds(370, 5, 60, 25);
      placeTemp.setHorizontalAlignment(SwingConstants.RIGHT);

      JLabel placeWeather=new JLabel(example[i+3]);
      placeWeather.setFont(new Font("Arial", Font.PLAIN, 18));
      placeWeather.setBounds(330, 35, 100, 25);
      placeWeather.setHorizontalAlignment(SwingConstants.RIGHT);

      JButton bookButton=new JButton("Book");
      bookButton.setFont(new Font("Arial", Font.PLAIN, 18));
      bookButton.setBounds(170, 35, 100, 25);
      bookButton.setHorizontalAlignment(SwingConstants.LEFT);
      bookButton.addActionListener(f->{
       String city=args[1];
       int d=datesBox.getSelectedIndex();
       System.out.println("frontend name:"+args[0]+"Activity name:"+activityName.getText());
       m3.run3(d,activityName.getText(),city);
       bookButton.setText("Booked");
       bookButton.setEnabled(false);

       System.out.println(activityName.getText());});

      eachCityPanel.add(activityName);
      eachCityPanel.add(placeName);
      eachCityPanel.add(placeTemp);
      eachCityPanel.add(placeWeather);
      eachCityPanel.add(bookButton);

      libRightBottom.add(eachCityPanel);

      i+=4;

     }

     //Scrollbar for base panel
     JScrollPane scroll= new JScrollPane(libRightBottom);
     scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
     scroll.setBounds(201,170,455,255);
     myHomeFrame.add(scroll);

     JButton searchButton=new JButton("Search",searchLogoImage);
     searchButton.setFont(new Font("Arial",Font.PLAIN,24));
     searchButton.setBounds(295, 25, 150, 35);
     searchButton.addActionListener(e->{
      //RIGTH BOTTOM PANEL
      if (textField1.getText().equals("")){
       String city= citiesOptions[citiesBox.getSelectedIndex()];
       String sport=sportOptions[sportBox.getSelectedIndex()];
       int datenum=datesBox.getSelectedIndex();
       Main3 m=new Main3();
       String[] example2=m.run1(city,datenum,sport);
       String[] example1=new String[example2.length+2];
       example1[1]=city;
       example1[0]=args[0];
       System.arraycopy(example2,0,example1,2,example2.length);
       for(int j=0;j<example1.length;j++){
        System.out.println("Example:"+example1[j]);
       }
       SportsPage.main(example1);
       myHomeFrame.dispose();

      }
      else{
       String city= citiesOptions[citiesBox.getSelectedIndex()];
       String sport=sportOptions[sportBox.getSelectedIndex()];
       int datenum=datesBox.getSelectedIndex();
       String capString=textField1.getText();

       int capnum=Integer.parseInt(capString);
       Main3 m=new Main3();
       String[] example2=m.run2(city,datenum,sport,capnum);
       String[] example1=new String[example2.length+2];
       example1[1]=city;
       example1[0]=args[0];
       System.arraycopy(example2,0,example1,2,example2.length);
       SportsPage.main(example1);
       myHomeFrame.dispose();
      }


     });









     //Adding elements to Right top
     rightTopTourism.add(selectCityLabel);
     rightTopTourism.add(selectDateLabel);
     rightTopTourism.add(selectSportLabel);
     rightTopTourism.add(minCapacityLabel);

     rightTopTourism.add(textField1);


     rightTopTourism.add(citiesBox);
     rightTopTourism.add(datesBox);
     rightTopTourism.add(sportBox);

     rightTopTourism.add(searchButton);







     //Add elements to homeFrame
     myHomeFrame.add(leftMenuPanel);
     myHomeFrame.add(rightTopTourism);



    }
     
}
