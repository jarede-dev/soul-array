package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class soulArray {

    JFrame window;
    JPanel titleNamePanel, startButtonPanel, storyTextPanel, storyContinuePanel;
    JLabel titleTextLabel;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    JButton startButton, storyButton, buy, sell, play, quit;
    JTextArea storyTextArea;

    TitleScreenHandler tsHandler = new TitleScreenHandler();
    ContinueButtonHandler cbHandler = new ContinueButtonHandler();

    public static void main(String[] args){
        new soulArray();
    }

    public soulArray(){
        
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
                //mainMenu();
            }
        }
        
    }

}
