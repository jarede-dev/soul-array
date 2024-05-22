package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
//put everything in main menu (play, buy, sell, quit) in choice handler. Handle the control flow there
public class soulArray {

    JFrame window;
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, storyContinuePanel, menuPanel, menuButton, coinsPanel;
    JLabel titleTextLabel, menuTitle, coinsText;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    JButton startButton, storyButton, buy, sell, play, quit;
    JTextArea mainTextArea;

    TitleScreenHandler tsHandler = new TitleScreenHandler();
    ContinueButtonHandler cbHandler = new ContinueButtonHandler();
    PlayHandler playHandler = new PlayHandler();
    SellHandler sellHandler = new SellHandler();
    QuitHandler quitHandler = new QuitHandler();
    BuyHandler buyHandler = new BuyHandler();

    private int coins = 5000;


    public static void main(String[] args){
        new soulArray();
    }

    public soulArray(){

        ArrayList<String> mainArray = new ArrayList<>(Arrays.asList("BRAIN", "GUTS", "HANDS", "HEART", "LEGS"));
        ArrayList<String> sellArray = new ArrayList<>(Arrays.asList("EYES", "LUNGS", "KIDNEY", "TEETH", "SKIN"));
        ArrayList<Integer> mainArrayPrice = new ArrayList<>(Arrays.asList(2000, 1800, 1500, 3500, 2300));
        ArrayList<Integer> sellArrayPrice = new ArrayList<>(Arrays.asList(800, 1250, 1000, 750, 900));
        
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.black);
        titleTextLabel = new JLabel("SOUL ARRAY");
        titleTextLabel.setForeground(Color.white);
        titleTextLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);

        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);
        startButton.setFocusPainted(false);
        startButton.addActionListener(tsHandler);

        titleNamePanel.add(titleTextLabel);
        startButtonPanel.add(startButton);

        window.add(titleNamePanel);
        window.add(startButtonPanel);

        window.setVisible(true);
    }

    public void storyScreen(){
        
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(200, 200, 600, 150);
        mainTextPanel.setBackground(Color.black);
        window.add(mainTextPanel);

        mainTextArea = new JTextArea("You've sold your soul to me");
        mainTextArea.setBounds(200, 200, 600, 150);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);

        storyContinuePanel = new JPanel();
        storyContinuePanel.setBounds(225, 350, 300, 150);
        storyContinuePanel.setBackground(Color.black);
        window.add(storyContinuePanel); 
    
        storyButton = new JButton("continue");
        storyButton.setBackground(Color.black);
        storyButton.setForeground(Color.white);
        storyButton.setFont(normalFont);
        storyButton.setFocusPainted(false);
        storyButton.addActionListener(cbHandler);
        storyContinuePanel.add(storyButton);
    }

    public void mainMenu(){

        storyContinuePanel.setVisible(false);
        mainTextPanel.setVisible(false);

        menuPanel = new JPanel();
        menuPanel.setBounds(100, 100, 600, 150);
        menuPanel.setBackground(Color.black);
        menuTitle = new JLabel("MAIN MENU");
        menuTitle.setForeground(Color.white);
        menuTitle.setFont(titleFont);

        menuPanel.add(menuTitle);
        window.add(menuPanel);

        menuButton = new JPanel();
        menuButton.setBounds(225, 350, 300, 150);
        menuButton.setBackground(Color.black);
        menuButton.setLayout(new GridLayout(2, 2));
        window.add(menuButton); 
    
        play = new JButton("Play");
        play.setBackground(Color.black);
        play.setForeground(Color.green);
        play.setFont(normalFont);
        play.addActionListener(playHandler);
        play.setFocusPainted(false);

        quit = new JButton("Quit");
        quit.setBackground(Color.black);
        quit.setForeground(Color.red);
        quit.setFont(normalFont);
        quit.addActionListener(quitHandler);
        quit.setFocusPainted(false);

        buy = new JButton("Buy");
        buy.setBackground(Color.black);
        buy.setForeground(Color.white);
        buy.setFont(normalFont);
        buy.addActionListener(buyHandler);
        buy.setFocusPainted(false);

        sell = new JButton("Sell");
        sell.setBackground(Color.black);
        sell.setForeground(Color.white);
        sell.setFont(normalFont);
        sell.addActionListener(sellHandler);
        sell.setFocusPainted(false);

        menuButton.add(play);
        menuButton.add(quit);
        menuButton.add(buy);
        menuButton.add(sell);

    }

    public void choiceHandler(){
        //detect if a certain button is clicked
        //do the functionality of that button

        /*if buy button is clicked, buy() */
    }

    public void games(){
        menuPanel.setVisible(false);
        menuButton.setVisible(false);
        System.out.println("games");
    }

    public void buy(){
        menuPanel.setVisible(false);
        menuButton.setVisible(false);
        System.out.println("buy");
    }

    public void sell(){
        menuPanel.setVisible(false);
        menuButton.setVisible(false);
        System.out.println("sell");
    }

    public void quit(){
        menuPanel.setVisible(false);
        menuButton.setVisible(false);
        mainTextPanel.setVisible(true);
        mainTextArea.setText("quitting...");
        // Create a timer to delay the exit by 3000ms (3 seconds)
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                window.dispose();
            }
        });
        timer.setRepeats(false); // Make sure the timer only runs once
        timer.start();
    }

    public class TitleScreenHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            storyScreen();
        } 
    }

    public class ContinueButtonHandler implements ActionListener {
        private int clickCount = 0;
        public void actionPerformed(ActionEvent event) {
            clickCount++;
            if (clickCount == 1) {
                mainTextArea.setText("And now you regret it?");
            } else if (clickCount == 2) {
                mainTextArea.setText("Fine, you can have it back");
            } else if (clickCount == 3) {
                mainTextArea.setText("If you can beat me in a little game of course");
            } else if (clickCount == 4) {
                mainMenu();
            }
        }
    }

    public class PlayHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            games();
        } 
    }

    public class SellHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            sell();
        } 
    }

    public class QuitHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            quit();
        } 
    }

    public class BuyHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            buy();
        } 
    }

}
