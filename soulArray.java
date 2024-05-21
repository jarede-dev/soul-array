package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class soulArray {

    JFrame window;
    JPanel titleNamePanel, startButtonPanel, storyTextPanel, storyContinuePanel, menuPanel, menuButton;
    JLabel titleTextLabel, menuTitle;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    JButton startButton, storyButton, buy, sell, play, quit;
    JTextArea storyTextArea;

    TitleScreenHandler tsHandler = new TitleScreenHandler();
    ContinueButtonHandler cbHandler = new ContinueButtonHandler();

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

        storyTextPanel = new JPanel();
        storyTextPanel.setBounds(200, 200, 600, 150);
        storyTextPanel.setBackground(Color.black);
        window.add(storyTextPanel);

        storyTextArea = new JTextArea("You've sold your soul to me");
        storyTextArea.setBounds(200, 200, 600, 150);
        storyTextArea.setBackground(Color.black);
        storyTextArea.setForeground(Color.white);
        storyTextArea.setFont(normalFont);
        storyTextArea.setLineWrap(true);
        storyTextPanel.add(storyTextArea);

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
        storyTextPanel.setVisible(false);

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
        play.setFocusPainted(false);

        quit = new JButton("Quit");
        quit.setBackground(Color.black);
        quit.setForeground(Color.red);
        quit.setFont(normalFont);
        quit.setFocusPainted(false);

        buy = new JButton("Buy");
        buy.setBackground(Color.black);
        buy.setForeground(Color.white);
        buy.setFont(normalFont);
        buy.setFocusPainted(false);

        sell = new JButton("Sell");
        sell.setBackground(Color.black);
        sell.setForeground(Color.white);
        sell.setFont(normalFont);
        sell.setFocusPainted(false);

        menuButton.add(play);
        menuButton.add(quit);
        menuButton.add(buy);
        menuButton.add(sell);

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
                storyTextArea.setText("And now you regret it?");
            } else if (clickCount == 2) {
                storyTextArea.setText("Fine, you can have it back");
            } else if (clickCount == 3) {
                storyTextArea.setText("If you can beat me in a little game of course");
            } else if (clickCount == 4) {
                mainMenu();
            }
        }
        
    }

}
