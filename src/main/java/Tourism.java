import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;

import org.example.AccuWeatherAPI;
import org.example.Main1;
import org.example.Main3;
import org.example.Main4;


public class Tourism {
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
        ImageIcon starLogoImage= new ImageIcon("D:\\java project\\untitled2\\src\\main\\java\\Images\\star.png");




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
        rightTopTourism.setBounds(201,0,455,248);
        rightTopTourism.setLayout(null);


        //Labels for Right Top Tourism

        JLabel selectCityLabel = new JLabel("Select city:");
        selectCityLabel.setFont(new Font("Arial",Font.PLAIN,22));
        selectCityLabel.setBounds(15, 15, 120, 24);

        JLabel selectDateLabel = new JLabel("Select date:");
        selectDateLabel.setFont(new Font("Arial",Font.PLAIN,22));
        selectDateLabel.setBounds(15, 50, 120, 24);

        JLabel selectWheelchairLabel = new JLabel("Wheelchair:");
        selectWheelchairLabel.setFont(new Font("Arial",Font.PLAIN,22));
        selectWheelchairLabel.setBounds(15, 85, 120, 24);

        JLabel selectPetLabel = new JLabel("Pets:");
        selectPetLabel.setFont(new Font("Arial",Font.PLAIN,22));
        selectPetLabel.setBounds(15, 120, 120, 24);

        JLabel selectCostLabel = new JLabel("Cost:");
        selectCostLabel.setFont(new Font("Arial",Font.PLAIN,22));
        selectCostLabel.setBounds(15, 155, 120, 24);

        JLabel minRatingLabel = new JLabel("Minimum Rating:");
        minRatingLabel.setFont(new Font("Arial",Font.PLAIN,22));
        minRatingLabel.setBounds(15, 190, 170, 24);

        JTextField minRating=new JTextField();
        minRating.setFont(new Font("Arial",Font.PLAIN,22));
        minRating.setBounds(210, 190, 170, 24);


        // Create a JSlider
        /*JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 5, 0); // Orientation, min, max, initial value

        // Customize the JSlider
        slider.setMajorTickSpacing(1); // Major tick spacing
        slider.setPaintTicks(true);     // Show ticks
        slider.setPaintLabels(true);    // Show labels
        slider.setBounds(200,190,240,50);
        slider.setBackground(new Color(0x87CEEB));

        // Add ChangeListener to handle slider value changes
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                int value = source.getValue();
                System.out.println("Slider Value: " + value);
            }
        });*/


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


        // Create the radio buttons for the first pair
        JRadioButton wheelYesButton = new JRadioButton("Yes");
        wheelYesButton.setFont(new Font("Arial",Font.PLAIN,18));
        wheelYesButton.setBackground(new Color(0x87CEEB));
        JRadioButton wheelNoButton = new JRadioButton("No");
        wheelNoButton.setFont(new Font("Arial",Font.PLAIN,18));
        wheelNoButton.setBackground(new Color(0x87CEEB));

        wheelNoButton.setSelected(true);

        wheelYesButton.setBounds(160, 85, 80, 24);
        wheelNoButton.setBounds(300, 85, 80, 24);


        // Group the first pair of radio buttons
        ButtonGroup wheelRequirement = new ButtonGroup();
        wheelRequirement.add(wheelYesButton);
        wheelRequirement.add(wheelNoButton);

        // Create the radio buttons for the second pair
        JRadioButton petYesButton = new JRadioButton("Yes");
        petYesButton.setFont(new Font("Arial",Font.PLAIN,18));
        petYesButton.setBackground(new Color(0x87CEEB));
        JRadioButton petNoButton = new JRadioButton("No");
        petNoButton.setFont(new Font("Arial",Font.PLAIN,18));
        petNoButton.setBackground(new Color(0x87CEEB));
        petNoButton.setSelected(true);

        petYesButton.setBounds(160, 120, 80, 24);
        petNoButton.setBounds(300, 120, 80, 24);

        // Group the second pair of radio buttons
        ButtonGroup petRequirement = new ButtonGroup();
        petRequirement.add(petYesButton);
        petRequirement.add(petNoButton);

        // Create the radio buttons for the third pair
        JRadioButton costFreeButton = new JRadioButton("Free");
        costFreeButton.setFont(new Font("Arial",Font.PLAIN,18));
        costFreeButton.setBackground(new Color(0x87CEEB));
        JRadioButton costPaidButton = new JRadioButton("Paid");
        costPaidButton.setFont(new Font("Arial",Font.PLAIN,18));
        costPaidButton.setBackground(new Color(0x87CEEB));
        costPaidButton.setSelected(true);

        costFreeButton.setBounds(160, 155, 80, 24);
        costPaidButton.setBounds(300, 155, 80, 24);

        // Group the third pair of radio buttons
        ButtonGroup costRequirement = new ButtonGroup();
        costRequirement.add(costFreeButton);
        costRequirement.add(costPaidButton);

        //Search Button for Right top

        JButton searchButton=new JButton("Search",searchLogoImage);
        searchButton.setFont(new Font("Arial",Font.PLAIN,24));
        searchButton.setBounds(295, 25, 150, 35);
        searchButton.addActionListener(e->{
            Main4 m4=new Main4();
            String wheel="No";
            String cost="Paid",pet="No";
            JRadioButton[] buttonsWheel={wheelYesButton,wheelNoButton};
            JRadioButton[] buttonsPet={petNoButton,petYesButton};
            JRadioButton[] buttonsCost={costFreeButton,costPaidButton};
            for (JRadioButton b: buttonsWheel){
                if (b.isSelected()){
                    wheel=b.getText();
                }
            }
            for (JRadioButton b: buttonsPet){
                if (b.isSelected()){
                    pet=b.getText();
                }
            }
            for (JRadioButton b: buttonsCost){
                if (b.isSelected()){
                    cost=b.getText();
                }
            }
            double rating;
            try {
                rating = Double.parseDouble(minRating.getText());
            }
            catch (Exception f){
                rating=0.0;
            }
            String[] example= m4.run1(citiesOptions[citiesBox.getSelectedIndex()],wheel,rating,datesBox.getSelectedIndex(),pet,cost);
            String [] example2=new String[example.length+1];
            example2[0]=args[0];
            System.arraycopy(example,0,example2,1,example.length);
            Tourism.main(example2);myHomeFrame.dispose();});


        




        //Adding elements to Right top
        rightTopTourism.add(selectCityLabel);
        rightTopTourism.add(selectDateLabel);
        rightTopTourism.add(selectPetLabel);
        rightTopTourism.add(selectWheelchairLabel);
        rightTopTourism.add(selectCostLabel);
        rightTopTourism.add(minRatingLabel);

        rightTopTourism.add(citiesBox);
        rightTopTourism.add(datesBox);

        rightTopTourism.add(wheelYesButton);
        rightTopTourism.add(wheelNoButton);
        rightTopTourism.add(petYesButton);
        rightTopTourism.add(petNoButton);
        rightTopTourism.add(costFreeButton);
        rightTopTourism.add(costPaidButton);
        rightTopTourism.add(searchButton);

        rightTopTourism.add(minRating);


        //RIGHT BOTTOM PAGE

        //RIGTH BOTTOM PANEL
    
     JPanel libRightBottom=new JPanel();
     libRightBottom.setBackground(Color.GREEN);
     libRightBottom.setLayout(new BoxLayout(libRightBottom, BoxLayout.Y_AXIS));

     String[] example=args;
     int i=1;

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

         JLabel rating=new JLabel(example[i+4]);
         rating.setFont(new Font("Arial", Font.PLAIN, 18));
        rating.setBounds(210, 40, 50, 25);
        rating.setHorizontalAlignment(SwingConstants.LEFT);
         JLabel starimage=new JLabel(starLogoImage);
         starimage.setFont(new Font("Arial", Font.PLAIN, 18));
         starimage.setBounds(170, 40, 30, 30);

        i+=5;



        eachCityPanel.add(activityName);
        eachCityPanel.add(placeName);
        eachCityPanel.add(placeTemp);
        eachCityPanel.add(placeWeather);
        eachCityPanel.add(rating);
        eachCityPanel.add(starimage);

        libRightBottom.add(eachCityPanel);

     }



     //Scrollbar for base panel
     JScrollPane scroll= new JScrollPane(libRightBottom);
     scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
     scroll.setBounds(201,250,455,175);
        






        //Add elements to homeFrame
        myHomeFrame.add(leftMenuPanel);
        myHomeFrame.add(rightTopTourism);
        myHomeFrame.add(scroll);
    }
        

}


/*City
Wheelchair
Pet 
Cost */

