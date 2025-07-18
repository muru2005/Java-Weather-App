import com.sun.tools.javac.Main;
import org.example.Main3;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.time.format.DateTimeFormatter;
import org.example.*;

class GraphPanel extends JPanel {
    private double[] yCoordinates = new double[10];


    // Function to set the y-coordinates
    public void setYCoordinates(double[] yCoordinates) {
        if (yCoordinates.length == 6) {
            this.yCoordinates = yCoordinates;
        } else {
            throw new IllegalArgumentException("You must provide exactly 6 y-coordinates.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set the background color to blue
        this.setBackground(Color.BLUE);

        this.setSize(350, 150);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Create arrays to store points for the polygons
        int[] xPoints = new int[yCoordinates.length + 2];
        int[] yPointsAbove = new int[yCoordinates.length + 2];
        int[] yPointsBelow = new int[yCoordinates.length + 2];

        

        // Set the color for the lines to red
        g2d.setColor(Color.RED);

        // Set the stroke thickness to 3
        g2d.setStroke(new BasicStroke(2));

        // Set the graph's width and calculate the spacing for x-coordinates
        int graphWidth = 350;
        int graphHeight = 150;
        int spacing = graphWidth / (yCoordinates.length-1);

        double sum=0;
        for (double num:yCoordinates){
            sum+=num;
        }

        int yMid=(int)sum/yCoordinates.length;
        int x1,x2,y1,y2;

        // Find the polygon to color
        for (int i = 0; i < yCoordinates.length - 1; i++) {
            x1 = i * spacing;
            x2 = (i + 1) * spacing;

            if (yCoordinates[i]>=yMid){
                y1=75-(int)(yCoordinates[i]-yMid)*10;
            }
            else{
                y1=75+(int)(yMid-yCoordinates[i])*10;
            }

            if (yCoordinates[i+1]>=yMid){
                y2=75-(int)(yCoordinates[i+1]-yMid)*10;
            }
            else{
                y2=75+(int)(yMid-yCoordinates[i+1])*10;
            }

            xPoints[i]=x1;
            yPointsAbove[i]=y1-1;
            yPointsBelow[i]=y1+1;
            
        }

        int ycl=yCoordinates.length;

        xPoints[ycl-1]=350;
        xPoints[ycl]=350;
        xPoints[ycl+1]=0;

        if (yCoordinates[ycl-1]>=yMid){
            y2=75-(int)(yCoordinates[ycl-1]-yMid)*10;
        }
        else{
            y2=75+(int)(yMid-yCoordinates[ycl-1])*10;
        }

        yPointsAbove[ycl-1]=y2-1;
        yPointsAbove[ycl]=0;
        yPointsAbove[ycl+1]=0;
        
        yPointsBelow[ycl-1]=y2+1;
        yPointsBelow[ycl]=150;
        yPointsBelow[ycl+1]=150;

        // Now draw and fill the area above the line
        g2d.setColor(new Color(154, 200, 237));
        g2d.fillPolygon(xPoints, yPointsAbove, yCoordinates.length + 2);

        // Draw and fill the area below the line
        g2d.setColor(new Color(222, 222, 134));
        g2d.fillPolygon(xPoints, yPointsBelow, yCoordinates.length + 2);

        g2d.setColor(new Color(53, 53, 53));


        // Draw the lines connecting the points based on evenly spaced x-coordinates and y-coordinates
        for (int i = 0; i < yCoordinates.length - 1; i++) {
            x1 = i * spacing;
            x2 = (i + 1) * spacing;


            if (yCoordinates[i]>=yMid){
                y1=75-(int)(yCoordinates[i]-yMid)*10;
            }
            else{
                y1=75+(int)(yMid-yCoordinates[i])*10;
            }

            if (yCoordinates[i+1]>=yMid){
                y2=75-(int)(yCoordinates[i+1]-yMid)*10;
            }
            else{
                y2=75+(int)(yMid-yCoordinates[i+1])*10;
            }
            
            g2d.drawLine(x1, y1, x2, y2);

            // Draw the y-value above each point
            g2d.drawString(String.valueOf((int)yCoordinates[i]), x1, y1 - 5);
        }

        


       
    }
}



class RightPanel extends JPanel{
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawLine(90, 48, 340, 48);
        g.drawLine(90,85,340,85);

        g.drawOval(369,44,43,43);

        g.drawArc(72,48,37,37,90,180);
        g.drawArc(322,48,37,37,-90,180);
        
    }
}

public class HomePage{

    public static void main(String args[]){

        //Create frame for homepage
        HomeFrame myHomeFrame= new HomeFrame();
        myHomeFrame.setLayout(null);
        AccuWeatherAPI a=new AccuWeatherAPI();
        String homecity=args[1];

        //Border 
        Border border= BorderFactory.createLineBorder(new Color(0x87CEEB));


        //Weather Conditions

        AccuWeatherAPI m=new AccuWeatherAPI();
        String[] details;
        System.out.println("city:"+homecity);
        try {
            details=m.run(homecity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon cloudyWeatherIcon=new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\WeatherIcons\\partly_cloudy.png");
        ImageIcon sunnyWeatherIcon=new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\WeatherIcons\\sunny.png");
        ImageIcon snowyWeatherIcon=new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\snow.png");
        ImageIcon rainyWeatherIcon=new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\WeatherIcons\\rain_light.png");

        JLabel weatherIconImage=new JLabel();
        weatherIconImage.setBounds(20, 70, 110, 160);









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
            SportsPage.main(example2);
            myHomeFrame.dispose();});
        

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
        


        //Create RightPanel
        RightPanel rightPanel= new RightPanel();
        rightPanel.setBackground(new Color(0x87CEEB));
        rightPanel.setBounds(201,0,450,425);
        rightPanel.setLayout(null);


        //Labels for RightPanel

        String temp=details[1];
        String weather=details[0];
        weather=weather.toLowerCase(Locale.ROOT);

        System.out.println("W: "+weather);


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



        JLabel temperatureLabel=new JLabel(temp+"°C");
        temperatureLabel.setFont(new Font("Arial", Font.PLAIN, 32));
        temperatureLabel.setBounds(115, 75, 145, 127);

        JLabel weatherLabel=new JLabel(weather);
        weatherLabel.setFont(new Font("Arial", Font.BOLD, 24));
        weatherLabel.setBounds(320, 80, 450, 97);

        JLabel dayLabel=new JLabel(dayOfWeek);
        dayLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        dayLabel.setBounds(320, 97, 450, 120);



        //Create textbox for right panel
        JTextField searchBar = new JTextField("City");
        searchBar.setFont(new Font("Arial", Font.PLAIN, 20));
        searchBar.setBackground(new Color(0x87CEEB));
        searchBar.setBounds(90, 50, 250, 30);
        searchBar.setBorder(border);


        //Create button for right panel
        JButton searchButton= new JButton(searchLogoImage);
        searchButton.setBackground(new Color(0x87CEEB));
        searchButton.setBounds(375, 50, 30, 30);
        searchButton.setBorder(border);
        searchButton.addActionListener(e->{
            String c=searchBar.getText();
            String[] p={args[0],c};
            HomePage.main(p);
            myHomeFrame.dispose();});

        //Making graph for temperature
        GraphPanel temperatureChart= new GraphPanel();
        double[] temps;
        try {
            temps = m.run3(homecity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        temperatureChart.setYCoordinates(temps);
        temperatureChart.setBounds(50,220,350,150);
        

        //Labels for time

        LocalTime now=LocalTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("h a");
        int x0=now.getMinute()+50;
        System.out.println(x0);

        JLabel h0=new JLabel(now.format(formatter));
        h0.setBounds(x0, 375, 50, 20);

        now=now.plusHours(1);
        JLabel h1=new JLabel(now.format(formatter));
        h1.setBounds(x0+60, 375, 50, 20);

        now=now.plusHours(1);
        JLabel h2=new JLabel(now.format(formatter));
        h2.setBounds(x0+120, 375, 50, 20);

        now=now.plusHours(1);
        JLabel h3=new JLabel(now.format(formatter));
        h3.setBounds(x0+180, 375, 50, 20);

        now=now.plusHours(1);
        JLabel h4=new JLabel(now.format(formatter));
        h4.setBounds(x0+240, 375, 50, 20);

        now=now.plusHours(1);
        JLabel h5=new JLabel(now.format(formatter));
        h5.setBounds(x0+300, 375, 50, 20);

        

        

        
        






        //Add elements to right panel
        rightPanel.add(searchBar);
        rightPanel.add(searchButton);
        rightPanel.add(weatherIconImage);
        rightPanel.add(temperatureLabel);
        rightPanel.add(dayLabel);
        rightPanel.add(weatherLabel);
        rightPanel.add(temperatureChart);
        rightPanel.add(h0);
        rightPanel.add(h1);
        rightPanel.add(h2);
        rightPanel.add(h3);
        rightPanel.add(h4);
        rightPanel.add(h5);







        //Add to main home frame
        myHomeFrame.add(leftMenuPanel);
        myHomeFrame.add(rightPanel);

    }
}