import org.example.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.time.format.DateTimeFormatter;

class leftPanel extends JPanel{
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawLine(0, 110, 200, 110);
        g.drawLine(0, 155, 200, 155);
        g.drawLine(0, 195, 200, 195);
        g.drawLine(0,375,200,375);
        g.drawLine(199,0,199,425);
    }
}

class RightLibraryTopPanel extends JPanel{
    /*@Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawLine(90, 48, 340, 48);
        g.drawLine(90,85,340,85);

        g.drawOval(369,44,43,43);

        g.drawArc(72,48,37,37,90,180);
        g.drawArc(322,48,37,37,-90,180);
        
    }*/


}

public class LibraryPage{

    public static void main(String args[]){

        //Create frame for homepage
        HomeFrame myHomeFrame= new HomeFrame();
        myHomeFrame.setLayout(null);

        //Border 
        Border border= BorderFactory.createLineBorder(new Color(0x87CEEB));

        //Importing Images

        ImageIcon homeLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Home\\home.png");
        ImageIcon libraryLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Home\\library.png");
        ImageIcon weatherMeImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Login\\weatherMe3.png");
        ImageIcon exitLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Home\\logout.png");
        ImageIcon sportsLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Home\\cricket.png");
        ImageIcon cropsLogoImage=new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Home\\wheat-grain.png");
        ImageIcon tourismLogoImage=new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Home\\location.png");
        ImageIcon searchLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Home\\search.png");
        ImageIcon addLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\Home\\addition.png");
        ImageIcon cloudyWeatherIcon=new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\WeatherIcons\\partly_cloudy.png");
        ImageIcon sunnyWeatherIcon=new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\WeatherIcons\\sunny.png");
        ImageIcon snowyWeatherIcon=new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\snow.png");
        ImageIcon rainyWeatherIcon=new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\WeatherIcons\\rain_light.png");





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
        AccuWeatherAPI b=new AccuWeatherAPI();

        String[] p={args[0],b.homecity(args[0])};
        buttonHome.addActionListener(e->{HomePage.main(p);myHomeFrame.dispose();});

        JButton buttonLibrary = new JButton("Library");
        buttonLibrary.setForeground(Color.BLACK);
        buttonLibrary.setFont(new Font("Arial", Font.PLAIN, 13));
        buttonLibrary.setBackground(new Color(0x87CEEB));
        buttonLibrary.setBounds(45, 160, 150, 30);
        buttonLibrary.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
        

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
        buttonCrops.addActionListener(e->{
            try {
                Main1 m1=new Main1();
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

        
        //Top Right panel to accept new cities.
        JPanel libRightTop=new JPanel();
        libRightTop.setBackground(new Color(0x87CEEB));
        libRightTop.setBounds(201,0,455,170);
        libRightTop.setLayout(null);

        AccuWeatherAPI m=new AccuWeatherAPI();

        String[] cities;
        System.out.println("ID0: "+args[0]);

        try {
            cities=m.run1(args[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //Labels for Top right
        JLabel addingText=new JLabel("Add new city:");
        addingText.setFont(new Font("Arial",Font.PLAIN,24));
        addingText.setBounds(30, 40, 250, 35);

        JLabel savedCitiesText=new JLabel("Saved Cities:");
        savedCitiesText.setFont(new Font("Arial",Font.PLAIN,24));
        savedCitiesText.setBounds(30,140, 150, 30);


        //Textbox for accepting city name
        JTextField cityName= new JTextField("City...");
        cityName.setFont(new Font("Arial", Font.PLAIN, 20));
        cityName.setPreferredSize(new Dimension(300, 40));
        cityName.setBounds(30, 80, 250, 40);


        //Add button
        AccuWeatherAPI a=new AccuWeatherAPI();
        JButton addButton= new JButton(addLogoImage);
        addButton.setBackground(new Color(0x87CEEB));
        addButton.setBounds(285, 80, 40, 40);
        addButton.addActionListener(e->{
            a.addcity(args[0],cityName.getText());
            String[] p1={args[0]};
            LibraryPage.main(p1);
            myHomeFrame.dispose();
        });


        //Right bottom base panel
        JPanel libRightBottom=new JPanel();
        libRightBottom.setBackground(Color.red);
        libRightBottom.setLayout(new BoxLayout(libRightBottom, BoxLayout.Y_AXIS));

         
 

        String[] placeNames=cities;

        for (String i: cities){
            System.out.println("i: "+i);
        }

        int i=0;
        while (!cities[i].equals("NULL")){



            JPanel eachCityPanel= new JPanel();
            eachCityPanel.setBackground(new Color(0x87CEEB));
            eachCityPanel.setPreferredSize(new Dimension(430,70));
            eachCityPanel.setLayout(null);
            eachCityPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

            String[] details;

            try {
                System.out.println("ID: "+i);
                details=m.run(cities[i]);
                System.out.println("Details: "+details[0]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String temp=details[1];
            String weather=details[0];
            weather=weather.toLowerCase(Locale.ROOT);

            System.out.println("W: "+weather);
            JLabel weatherIconImage=new JLabel();


            if (weather.contains("snow")){
                weatherIconImage.setIcon(snowyWeatherIcon);
                weather="Snowing";
            }
            else if(weather.contains("rain") | weather.contains("storm")){
                weatherIconImage.setIcon(rainyWeatherIcon);
                weather="Rainy";
            }
            else if(weather.contains("cloud")){
                weatherIconImage.setIcon(cloudyWeatherIcon);
                weather="Cloudy";
            }
            else{
                weatherIconImage.setIcon(sunnyWeatherIcon);
                weather="Sunny";
            }

            weatherIconImage.setBounds(300, 2, 70, 70);
            eachCityPanel.add(weatherIconImage);



            JLabel placeName=new JLabel(cities[i]);
            placeName.setFont(new Font("Arial", Font.PLAIN, 24));
            placeName.setBounds(10, 10, 200, 50);
            placeName.setHorizontalAlignment(SwingConstants.LEFT);

            JLabel placeTemp=new JLabel(temp+"°C");
            placeTemp.setFont(new Font("Arial", Font.PLAIN, 18));
            placeTemp.setBounds(370, 5, 60, 25);
            placeTemp.setHorizontalAlignment(SwingConstants.RIGHT);

            JLabel placeWeather=new JLabel(weather);
            placeWeather.setFont(new Font("Arial", Font.PLAIN, 18));
            placeWeather.setBounds(370, 35, 60, 25);
            placeWeather.setHorizontalAlignment(SwingConstants.RIGHT);


            eachCityPanel.add(placeName);
            eachCityPanel.add(placeTemp);
            eachCityPanel.add(placeWeather);

            libRightBottom.add(eachCityPanel);
            i++;
        }


        //Scrollbar for base panel
        JScrollPane scroll= new JScrollPane(libRightBottom);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(201,175,455,250);
       


        





        
        //Adding components in LibRIghtTOP
        libRightTop.add(addingText);
        libRightTop.add(savedCitiesText);
        libRightTop.add(cityName);
        libRightTop.add(addButton);

        //Add to main home frame
        myHomeFrame.add(leftMenuPanel);
        myHomeFrame.add(libRightTop);
        myHomeFrame.add(scroll);

    }
}