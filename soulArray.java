package GUI;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class soulArray {
    private static final String coinsPath = "coinsTrack.csv";
    JFrame window;
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, storyContinuePanel, menuPanel, menuButton, coinsPanel, goBackToMainPanel, userHpLabel, oppHpLabel, bodyTextPanel, fighPassPanel, turnPanel, elementalPanel;
    JLabel titleTextLabel, menuTitle, coinsText, userHp, oppHp, turnText;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    Font subFont = new Font("Times New Roman", Font.PLAIN, 60);
    JButton startButton, storyButton, buy, sell, play, quit, goBackToMainB, fight, pass, cont;
    JTextArea mainTextArea, messageTextArea, bodyTextArea, elementalArea;

    //organs
    JPanel organsButtons;
    JButton brain, guts, hands, heart, legs, eyes, lungs, kidney, teeth, skin;

    // horses
    JPanel horses;
    JButton cerberus, persephone, hades, voskopoulos, tom;
    // horse racing panels
    JLabel hrTitle, hrSubtitle, hrEnterBet;
    JPanel hrTitlePanel, hrSubPanel, hrBetPanel;

    // rps panels
    JLabel choice;

    // rps elements
    JButton fire, water, earth;
    //games
    JPanel gamesButtons;
    JButton danceWdevilB, horseRacingB, rockPaperScissorsB;

    TitleScreenHandler tsHandler = new TitleScreenHandler();
    ContinueButtonHandler cbHandler = new ContinueButtonHandler();
    PlayHandler playHandler = new PlayHandler();
    SellHandler sellHandler = new SellHandler();
    QuitHandler quitHandler = new QuitHandler();
    BuyHandler buyHandler = new BuyHandler();
    GoBackHandler goBackHandler = new GoBackHandler();
    DanceWDevilHandler dwdHandler = new DanceWDevilHandler();
    HorseRacingHandler hrHandler = new HorseRacingHandler();
    ElementalHandler eHandler = new ElementalHandler();

    ArrayList<String> mainArray = new ArrayList<>(Arrays.asList("BRAIN", "GUTS", "HANDS", "HEART", "LEGS"));
    ArrayList<String> sellArray = new ArrayList<>(Arrays.asList("EYES", "LUNGS", "KIDNEY", "TEETH", "SKIN"));
    ArrayList<Integer> mainArrayPrice = new ArrayList<>(Arrays.asList(2000, 1800, 1500, 3500, 2300));
    ArrayList<Integer> sellArrayPrice = new ArrayList<>(Arrays.asList(800, 1250, 1000, 750, 900));

    //dance with the dimonyo variables
    private int coins = 10000;
    private boolean playerTurn = true;
    private boolean hasChosen = false;
    private int userDmg = 100;
    private int comDmg = 50;
    private int userHpAmount = 1000;
    private int oppHpAmount = 1000;
    private Random random = new Random();

    private Timer endGameTimer;

    ArrayList<String> boughtOrgans = new ArrayList<>();
    ArrayList<String> soldOrgans = new ArrayList<>();

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

        //go back to main menu button
        goBackToMainPanel = new JPanel();
        goBackToMainPanel.setBounds(10, 10, 100, 50);
        goBackToMainPanel.setBackground(Color.black);
        goBackToMainB = new JButton("Menu");
        goBackToMainB.setBackground(Color.black);
        goBackToMainB.setForeground(Color.white);
        goBackToMainB.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        goBackToMainB.setFocusPainted(false);
        goBackToMainB.addActionListener(goBackHandler);
        goBackToMainPanel.add(goBackToMainB);
        goBackToMainPanel.setVisible(false); // Initially hidden

        window.add(goBackToMainPanel);
        window.setVisible(true);
    }

    public void storyScreen(){
        
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(200, 200, 600, 150);
        mainTextPanel.setBackground(Color.black);
        mainTextPanel.setLayout(new BorderLayout());
        window.add(mainTextPanel);

        mainTextArea = new JTextArea("You've sold your soul to me");
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea, BorderLayout.NORTH);

        messageTextArea = new JTextArea();
        messageTextArea.setBackground(Color.black);
        messageTextArea.setForeground(Color.green);
        messageTextArea.setFont(normalFont);
        messageTextArea.setLineWrap(true);
        mainTextPanel.add(messageTextArea, BorderLayout.CENTER);

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
        if (storyContinuePanel != null) storyContinuePanel.setVisible(false);
        if (mainTextPanel != null) mainTextPanel.setVisible(false);
        if (coinsPanel != null) coinsPanel.setVisible(false);
        if (organsButtons != null) organsButtons.setVisible(false);
        if (gamesButtons != null) gamesButtons.setVisible(false);
        if (userHpLabel != null) userHpLabel.setVisible(false);
        if (oppHpLabel != null) oppHpLabel.setVisible(false);
        if (bodyTextPanel != null)bodyTextPanel.setVisible(false);
        if (fighPassPanel != null) fighPassPanel.setVisible(false);
        if (turnPanel != null) turnPanel.setVisible(false);
        if(horses != null) horses.setVisible(false);
        if(hrTitlePanel != null) hrTitlePanel.setVisible(false);
        if(hrSubPanel != null) hrSubPanel.setVisible(false);
        if(hrBetPanel != null) hrBetPanel.setVisible(false);
        if(choice != null) choice.setVisible(false);
        if(elementalPanel != null) elementalPanel.setVisible(false);

        if (messageTextArea != null) messageTextArea.setText("");

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

        goBackToMainPanel.setVisible(false);
    }

    public void games(){
        goBackToMainPanel.setVisible(true);
        if (menuPanel != null) menuPanel.setVisible(false);
        if (menuButton != null) menuButton.setVisible(false);
        
        mainTextPanel.setVisible(true);
        mainTextArea.setText("Which game do you want to play?");

        coinsPanel = new JPanel();
        coinsPanel.setBounds(80, 100, 600, 150);
        coinsPanel.setBackground(Color.black);
        coinsText = new JLabel();
        coinsText.setText("Coins: " + coins);
        coinsText.setForeground(Color.yellow);
        coinsText.setFont(normalFont);
        coinsPanel.add(coinsText);

        gamesButtons = new JPanel();
        gamesButtons.setBounds(225, 350, 300, 150);
        gamesButtons.setBackground(Color.black);
        gamesButtons.setLayout(new GridLayout(3, 3));
        window.add(gamesButtons);

        danceWdevilB = choiceButton("Dance with the Devil");
        danceWdevilB.addActionListener(dwdHandler);
        horseRacingB = choiceButton("Horse Racing");
        horseRacingB.addActionListener(hrHandler);
        rockPaperScissorsB = choiceButton("Elemental Exchange");
        rockPaperScissorsB.addActionListener(eHandler);
        gamesButtons.add(danceWdevilB);
        gamesButtons.add(horseRacingB);
        gamesButtons.add(rockPaperScissorsB);

        window.add(coinsPanel);

    }

    public void danceWdevil(){
        playerTurn = true;

        hasChosen = false;
        userDmg = 100;
        comDmg = 50;
        userHpAmount = 1000;
        oppHpAmount = 1000;
        if (gamesButtons != null) gamesButtons.setVisible(false);
        if (menuButton != null) menuButton.setVisible(false);
        if (mainTextPanel != null) mainTextPanel.setVisible(false);
        if(coins < 2000){
            mainTextPanel.setVisible(true);
            mainTextArea.setText("Not enough coins.\nYou need 2000 coins \nto play this.");
        } else{

        coinsPanel.setBounds(270, 80, 200, 50);

        userHpLabel = new JPanel();
        userHpLabel.setBounds(20, 120, 250, 50);
        userHpLabel.setBackground(Color.green);

        userHp = new JLabel("Your HP: " + userHpAmount);
        userHp.setForeground(Color.white);
        userHp.setFont(normalFont);

        userHpLabel.add(userHp);
        window.add(userHpLabel);

        oppHpLabel = new JPanel();
        oppHpLabel.setBounds(500, 120, 250, 50);
        oppHpLabel.setBackground(Color.red);

        oppHp = new JLabel("Devil's HP: " + oppHpAmount);
        oppHp.setForeground(Color.white);
        oppHp.setFont(normalFont);

        oppHpLabel.add(oppHp);
        window.add(oppHpLabel);

        turnPanel = new JPanel();
        turnPanel.setBounds(280, 170, 200, 50);
        turnPanel.setBackground(Color.black);

        String userTurn = "Your turn";

        turnText = new JLabel(userTurn);
        turnText.setForeground(Color.red);
        turnText.setFont(normalFont);

        turnPanel.add(turnText);
        window.add(turnPanel);

        bodyTextPanel = new JPanel();
        bodyTextPanel.setBounds(20, 250, 730, 170);
        bodyTextPanel.setBackground(Color.red);
        bodyTextPanel.setLayout(new BorderLayout());
        window.add(bodyTextPanel);

        bodyTextArea = new JTextArea("Welcome to Dance with the Devil");
        bodyTextArea.setBackground(Color.black);
        bodyTextArea.setForeground(Color.white);
        bodyTextArea.setFont(normalFont);
        bodyTextArea.setLineWrap(true);
        bodyTextPanel.add(bodyTextArea, BorderLayout.CENTER);
        
        fighPassPanel = new JPanel();
        fighPassPanel.setBounds(150, 450, 500, 50);
        fighPassPanel.setBackground(Color.pink);
        fighPassPanel.setLayout(new GridLayout(1, 3));
        JButton fight = new JButton("FIGHT");
        fight.setBackground(Color.green);
        fight.setForeground(Color.white);
        fight.setFont(normalFont);
        fight.setFocusPainted(false);
        try {
                Image img = ImageIO.read(getClass().getResource("img/punch.png"));
                fight.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                System.out.println(ex);
            }

        JButton pass = new JButton("Pass");
        pass.setBackground(Color.red);
        pass.setForeground(Color.white);
        pass.setFont(normalFont);
        pass.setFocusPainted(false);

        try {
            Image img = ImageIO.read(getClass().getResource("img/passPic.png"));
            pass.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        JButton cont = new JButton(">");
        cont.setBackground(Color.orange);
        cont.setForeground(Color.white);
        cont.setFont(normalFont);
        cont.setFocusPainted(false);

        fighPassPanel.add(fight);
        fighPassPanel.add(cont);
        fighPassPanel.add(pass);
        window.add(fighPassPanel);

        updateTurnUI(playerTurn);
        fight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(hasChosen == false){
                // Code to execute when button1 is clicked
                oppHpAmount -= userDmg;
                oppHp.setText("Devil's HP: " + oppHpAmount);
                turnText.setText(userTurn);
                bodyTextArea.setText("You attacked the opponent, he took " + userDmg + " damage!");
                hasChosen = true;
           
                checkForWinOrLose();
          
                }
            }
        });
        
        
        cont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(hasChosen == true){
                System.out.println("continue clicked");
                hasChosen = false;
                playerTurn = false;
                updateTurnUI(playerTurn);
                } else if(hasChosen == false){
                    playerTurn = true;
                    turnText.setText("Your Turn");
                    updateTurnUI(playerTurn);
                }
            }
        });
        

        // Add ActionListener to button2
        
        pass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(hasChosen == false){
                turnText.setText(userTurn);
                hasChosen = true;
                int chance = random.nextInt(5);
                if(chance == 0){
                    userDmg += 150;
                    
                    bodyTextArea.setText("[GOOD EVENT]\nYou got lucky! +150 basic attack dmg");
                } else if(chance == 1){
                    comDmg += 100;
                    
                    bodyTextArea.setText("[BAD EVENT]\nYou're unlucky, you just increased your \nopponents basic attack damage.\nDevil's basic attack damage: +100");
                } else if(chance == 2){
                    oppHpAmount -= 200;
                    
                    bodyTextArea.setText("[GOOD EVENT]\nInstant 200 damage inflicted on enemy");
                    oppHp.setText("Devil's HP: " + (oppHpAmount));
                } else if(chance == 3){
                    userHpAmount -= 150;
                    bodyTextArea.setText("[BAD EVENT]\nYour enemy instant inflicted 150 damage on you");
                    userHp.setText("Your HP: " + (userHpAmount));
                } else if(chance == 4){
                    oppHpAmount -= 500;
                    
                    bodyTextArea.setText("[GOOD EVENT]\nYou used your ULTIMATE SKILL\nSPECIAL SLASHH!!!!\n500 dmg inflicted on the devil");
                    oppHp.setText("Devil's HP: " + (oppHpAmount));
                }
        
                  checkForWinOrLose();
            
                }
            }
        });
        
        }
    }

    private void updateTurnUI(boolean turn) {
        if (turn) {
            bodyTextArea.setText("What's will you do?\nFIGHT - Damage dealing\nPASS - Skip turn | Good Event Chance: 50% |\n                            | Bad Event Chance: 50%  |");
        } else {
            int rng = random.nextInt(9);
            turnText.setText("Devil's Turn");
            if(rng == 0 || rng == 1 || rng == 2 || rng == 3){
                userHpAmount -= comDmg;
                bodyTextArea.setText("Devil used basic attack. -" + comDmg + "hp");
                userHp.setText("Your HP: " + (userHpAmount));
                rng = random.nextInt(9);
            } else if(rng == 4 || rng == 5 || rng == 6 || rng == 7){
                userHpAmount -= 150;
                bodyTextArea.setText("Devil used fireball. -150hp");
                userHp.setText("Your HP: " + (userHpAmount));
                rng = random.nextInt(9);
            
            } else if(rng == 8){
                userHpAmount -= 400;
                bodyTextArea.setText("Devil used his [ULTIMATE SKILL]\n[BLACK HOLE EXPLOSION]\nA black hole consumed you\n-400HP");
                rng = random.nextInt(9);
                userHp.setText("Your HP: " + (userHpAmount));
            }
            
            checkForWinOrLose();
            hasChosen = false;

        }
    }

    private void showWinScreen() {
            bodyTextArea.setText("You Win! +2000 coins");
            coins += 2000;
            if (endGameTimer != null) {
                endGameTimer.stop();
            }
        
            endGameTimer = new Timer(6000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainMenu();
                    resetGame();
                }
            });
            endGameTimer.setRepeats(false);
            endGameTimer.start();
}

private void showLoseScreen() {
        bodyTextArea.setText("You Lose! -2000 coins");
        coins += 2000;
        if (endGameTimer != null) {
            endGameTimer.stop();
        }
    
        endGameTimer = new Timer(6000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu();
                resetGame();
            }
        });
        endGameTimer.setRepeats(false);
        endGameTimer.start();
}

private void resetGame() {
    // Hide all panels used in danceWdevil
    if (gamesButtons != null) gamesButtons.setVisible(false);
    if (menuButton != null) menuButton.setVisible(false);
    if (mainTextPanel != null) mainTextPanel.setVisible(false);
    if (coinsPanel != null) coinsPanel.setVisible(false);
    if (userHpLabel != null) userHpLabel.setVisible(false);
    if (oppHpLabel != null) oppHpLabel.setVisible(false);
    if (turnPanel != null) turnPanel.setVisible(false);
    if (bodyTextPanel != null) bodyTextPanel.setVisible(false);
    if (fighPassPanel != null) fighPassPanel.setVisible(false);
    menuButton.setVisible(true);
    // Reset any other necessary game state here
}

private void checkForWinOrLose() {
    if (userHpAmount <= 0) {
        userHpAmount = 0;
        userHp.setText("Your HP: " + userHpAmount);
        Timer timer = new Timer(6000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoseScreen();
            }
        });
        timer.setRepeats(false);
        timer.start();
    } else if (oppHpAmount <= 0) {
        oppHpAmount = 0;
        oppHp.setText("Devil's HP: " + oppHpAmount);
        Timer timer = new Timer(6000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showWinScreen();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}

public void horseRacing(){
    if (gamesButtons != null) gamesButtons.setVisible(false);
    if (menuButton != null) menuButton.setVisible(false);
    if (mainTextPanel != null) mainTextPanel.setVisible(false);
    coinsPanel.setBounds(300, 80, 200, 50);

    hrTitlePanel = new JPanel();
    hrTitlePanel.setBounds(150, 150, 500, 75);
    hrTitlePanel.setBackground(Color.black);

    hrTitle = new JLabel("Horse Racing");
    hrTitle.setForeground(Color.red);
    hrTitle.setFont(subFont);

    hrTitlePanel.add(hrTitle);
    window.add(hrTitlePanel);

    hrSubPanel = new JPanel();
    hrSubPanel.setBounds(150, 240, 500, 80);
    hrSubPanel.setBackground(Color.black);

    hrSubtitle = new JLabel("Pick your horse");
    hrSubtitle.setForeground(Color.white);
    hrSubtitle.setFont(normalFont);

    hrSubPanel.add(hrSubtitle);
    window.add(hrSubPanel);

    horses = new JPanel();
    horses.setBounds(250, 350, 300, 150);
    horses.setBackground(Color.black);
    horses.setLayout(new GridLayout(5, 1));
    window.add(horses); 

    final String[] selectedHorse = {""};

    cerberus = choiceButton("Cerberus");
    persephone = choiceButton("Persephone");
    hades = choiceButton("Hades");
    voskopoulos = choiceButton("Voskopoulos");
    tom = choiceButton("Tom");

    cerberus.addActionListener(e -> {
        selectedHorse[0] = "Cerberus";
        horses.setVisible(false);
        hrSubPanel.setVisible(false);
    });
    
    persephone.addActionListener(e -> {
        selectedHorse[0] = "Persephone";
        horses.setVisible(false);
        hrSubPanel.setVisible(false);
    });
    
    hades.addActionListener(e -> {
        selectedHorse[0] = "Hades";
        horses.setVisible(false);
        hrSubPanel.setVisible(false);
    });
    
    voskopoulos.addActionListener(e -> {
        selectedHorse[0] = "Voskopoulos";
        horses.setVisible(false);
        hrSubPanel.setVisible(false);
    });
    
    tom.addActionListener(e -> {
        selectedHorse[0] = "Tom";
        horses.setVisible(false);
        hrSubPanel.setVisible(false);
    });

    horses.add(cerberus);
    horses.add(persephone);
    horses.add(hades);
    horses.add(voskopoulos);
    horses.add(tom);

    hrBetPanel = new JPanel();
    hrBetPanel.setBounds(150, 240, 500, 80);
    hrBetPanel.setBackground(Color.black);

    hrEnterBet = new JLabel("Enter amount to bet.");
    hrEnterBet.setForeground(Color.white);
    hrEnterBet.setFont(normalFont);

    hrBetPanel.add(hrEnterBet);
    window.add(hrBetPanel);

    // add a text field here that only accepts integers. there is also a button underneath it entitled "ENTER". if the user inputs an int, and clicks ENTER, it will be stored in a variable. 
    // else, if the field is blank or the input isn't an integer or the input equals 0, display an error message and wait until the user inputs a valid input again.

}

public void Elemental() {
    elementalPanel = new JPanel();
        elementalPanel.setBounds(150, 150, 500, 150);
        elementalPanel.setBackground(Color.white);
        elementalArea = new JTextArea("Fire beats Earth\nEarth beats Water\nWater beats Fire");
        elementalArea.setForeground(Color.black);
        elementalArea.setFont(normalFont);

        elementalPanel.add(elementalArea);
        window.add(elementalPanel);

    if (gamesButtons != null)
        gamesButtons.setVisible(false);
    if (menuButton != null)
        menuButton.setVisible(false);
    if (mainTextPanel != null)
        mainTextPanel.setVisible(false);
    coinsPanel.setBounds(300, 80, 200, 50);

    choice = new JLabel();
    choice.setBounds(150, 350, 500, 100);
    choice.setBackground(Color.black);
    choice.setLayout(new GridLayout(1, 3));
    window.add(choice);

    fire = choiceButton("Fire");
    water = choiceButton("Water");
    earth = choiceButton("Earth");

    choice.add(fire);
    fire.setBackground(Color.red);
    choice.add(water);
    water.setBackground(Color.blue);
    choice.add(earth);
    earth.setBackground(Color.gray);

    fire.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            playGame("Fire");
        }
    });

    water.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            playGame("Water");
        }
    });

    earth.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            playGame("Earth");
        }
    });
}

private void playGame(String userChoice) {
    String[] choices = { "Fire", "Water", "Earth" };
    Random random = new Random();
    String computerChoice = choices[random.nextInt(choices.length)];

    String result;
    if (userChoice.equals(computerChoice)) {
        result = "It's a draw!";
    } else if ((userChoice.equals("Fire") && computerChoice.equals("Earth")) ||
            (userChoice.equals("Water") && computerChoice.equals("Fire")) ||
            (userChoice.equals("Earth") && computerChoice.equals("Water"))) {
        result = "you win! +50!";
        coins += 50;
        coinsText.setText("Coins: " + coins);
    } else {
        result = "You lose! -50";
        coins -= 50;
        coinsText.setText("Coins: " + coins);
    }

    elementalArea.setText("You chose " + userChoice + "\nComputer chose " + computerChoice + "\n" + result);
}

    public JButton choiceButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setFont(normalFont);
        button.setFocusPainted(false);
        return button;
    }

    public void quitMethod() {
        // Check if file exists
        File file = new File(coinsPath);
        if (file.exists()) {
            System.out.println("File exists: " + coinsPath);
        } else {
            System.out.println("File does not exist: " + coinsPath);
        }

        // Read and print the current contents of the CSV file
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(coinsPath))) {
            System.out.println("Reading file: " + coinsPath);
            while ((line = reader.readLine()) != null) {
                System.out.println("Current contents: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write the new value to the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(coinsPath))) {
            System.out.println("Writing to file: " + coinsPath);
            writer.write(String.valueOf(coins));
            System.out.println("Coins are: " + coins);
        } catch (IOException f) {
            f.printStackTrace();
        }

        System.exit(0);
    }
    

    public void printCoins() {
        try (BufferedReader reader = new BufferedReader(new FileReader(coinsPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void buy(){
        goBackToMainPanel.setVisible(true);
        if (menuPanel != null) menuPanel.setVisible(false);
        if (menuButton != null) menuButton.setVisible(false);
        
        mainTextPanel.setVisible(true);
        mainTextArea.setText("Which organ do you want to buy?");
        messageTextArea.setText(""); // Clear the message area
    
        coinsPanel = new JPanel();
        coinsPanel.setBounds(80, 100, 600, 150);
        coinsPanel.setBackground(Color.black);
        coinsText = new JLabel();
        coinsText.setText("Coins: " + coins);
        coinsText.setForeground(Color.yellow);
        coinsText.setFont(normalFont);
        coinsPanel.add(coinsText);
    
        organsButtons = new JPanel();
        organsButtons.setBounds(225, 350, 300, 150);
        organsButtons.setBackground(Color.black);
        organsButtons.setLayout(new GridLayout(5, 1));
        window.add(organsButtons); 
    
        brain = choiceButton("Brain" + ": " + mainArrayPrice.get(0));
        brain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buyOrgan("BRAIN", mainArray, mainArrayPrice);
            }
        });
    
        guts = choiceButton("Guts" + ": " + mainArrayPrice.get(1));
        guts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buyOrgan("GUTS", mainArray, mainArrayPrice);
            }
        });
    
        hands = choiceButton("Hands" + ": " + mainArrayPrice.get(2));
        hands.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buyOrgan("HANDS", mainArray, mainArrayPrice);
            }
        });
    
        heart = choiceButton("Heart" + ": " + mainArrayPrice.get(3));
        heart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buyOrgan("HEART", mainArray, mainArrayPrice);
            }
        });
    
        legs = choiceButton("Legs" + ": " + mainArrayPrice.get(4));
        legs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buyOrgan("LEGS", mainArray, mainArrayPrice);
            }
        });
    
        organsButtons.add(brain);
        organsButtons.add(guts);
        organsButtons.add(hands);
        organsButtons.add(heart);
        organsButtons.add(legs);
        window.add(coinsPanel);

        if(mainArray.isEmpty()){
            organsButtons.setVisible(false);
            coinsPanel.setVisible(false);
            messageTextArea.setVisible(false);
            mainTextArea.setText("You win! You bought \nall your organs".toUpperCase());

        }
    }

    public void sell() {
        goBackToMainPanel.setVisible(true);
        if (menuPanel != null) menuPanel.setVisible(false);
        if (menuButton != null) menuButton.setVisible(false);
        
        mainTextPanel.setVisible(true);
        mainTextArea.setText("Which organ do you want to sell?");
        messageTextArea.setText(""); // Clear the message area
    
        coinsPanel = new JPanel();
        coinsPanel.setBounds(80, 100, 600, 150);
        coinsPanel.setBackground(Color.black);
        coinsText = new JLabel();
        coinsText.setText("Coins: " + coins);
        coinsText.setForeground(Color.yellow);
        coinsText.setFont(normalFont);
        coinsPanel.add(coinsText);
    
        organsButtons = new JPanel();
        organsButtons.setBounds(225, 350, 300, 150);
        organsButtons.setBackground(Color.black);
        organsButtons.setLayout(new GridLayout(5, 1));
        window.add(organsButtons); 
    
        eyes = choiceButton("Eyes" + ": " + sellArrayPrice.get(0));
        eyes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sellOrgan("EYES", sellArray, sellArrayPrice);
            }
        });

        lungs = choiceButton("Lungs" + ": " + sellArrayPrice.get(1));
        lungs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sellOrgan("LUNGS", sellArray, sellArrayPrice);
            }
        });

        kidney = choiceButton("Kidney" + ": " + sellArrayPrice.get(2));
        kidney.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sellOrgan("KIDNEY", sellArray, sellArrayPrice);
            }
        });

        teeth = choiceButton("Teeth" + ": " + sellArrayPrice.get(3));
        teeth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sellOrgan("TEETH", sellArray, sellArrayPrice);
            }
        });

        skin = choiceButton("Skin" + ": " + sellArrayPrice.get(4));
        skin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sellOrgan("SKIN", sellArray, sellArrayPrice);
            }
        });
    
        organsButtons.add(eyes);
        organsButtons.add(lungs);
        organsButtons.add(kidney);
        organsButtons.add(teeth);
        organsButtons.add(skin);
        window.add(coinsPanel);

        if(sellArray.isEmpty()){
            organsButtons.setVisible(false);
            coinsPanel.setVisible(false);
            messageTextArea.setVisible(false);
            mainTextArea.setText("You've sold all \nyour available organs".toUpperCase());
        }
    }

    private void buyOrgan(String organ, ArrayList<String> organsList, ArrayList<Integer> pricesList) {
        int index = organsList.indexOf(organ);
        if (index != -1) {
            int price = pricesList.get(index);
            if (coins >= price) {
                coins -= price;
                coinsText.setText("Coins: " + coins);
                organsList.remove(index);
                pricesList.remove(index);
                // Inform the user
                messageTextArea.setText("You've bought " + organ);
                boughtOrgans.add(organ);
                switch (organ) {
                    case "BRAIN":
                        brain.setForeground(Color.gray);
                        break;
                    case "GUTS":
                        guts.setForeground(Color.gray);
                        break;
                    case "HANDS":
                        hands.setForeground(Color.gray);
                        break;
                    case "HEART":
                        heart.setForeground(Color.gray);
                        break;
                    case "LEGS":
                        legs.setForeground(Color.gray);
                        break;
                }
            } else {
                messageTextArea.setText("Not enough coins to buy " + organ);
            }
        }
        if(organsList.isEmpty()){
            organsButtons.setVisible(false);
            coinsPanel.setVisible(false);
            messageTextArea.setVisible(false);
            mainTextArea.setText("You WIN, you've bought \nall your available organs back".toUpperCase());
            goBackToMainPanel.setVisible(false);
        }
    }

    private void sellOrgan(String organ, ArrayList<String> organsList, ArrayList<Integer> pricesList) {
        int index = organsList.indexOf(organ);
        if (index != -1) {
            int price = pricesList.get(index);
            coins += price;
            coinsText.setText("Coins: " + coins);
            organsList.remove(index);
            pricesList.remove(index);
            // Inform the user
            messageTextArea.setText("You've sold " + organ);
            soldOrgans.add(organ);
            switch (organ) {
                case "EYES":
                    eyes.setForeground(Color.gray);
                    break;
                case "LUNGS":
                    lungs.setForeground(Color.gray);
                    break;
                case "KIDNEY":
                    kidney.setForeground(Color.gray);
                    break;
                case "TEETH":
                    teeth.setForeground(Color.gray);
                    break;
                case "SKIN":
                    skin.setForeground(Color.gray);
                    break;
            }
        }
        if(organsList.isEmpty()){
            organsButtons.setVisible(false);
            coinsPanel.setVisible(false);
            messageTextArea.setVisible(false);
            mainTextArea.setText("You've sold all \nyour available organs".toUpperCase());
        }
    }

    public class TitleScreenHandler implements ActionListener {
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

    public class PlayHandler implements ActionListener {
        public void actionPerformed(ActionEvent event){
            games();
        }
    }

    public class BuyHandler implements ActionListener {
        public void actionPerformed(ActionEvent event){
            buy();
        }
    }

    public class SellHandler implements ActionListener {
        public void actionPerformed(ActionEvent event){
            sell();
        }
    }

    public class QuitHandler implements ActionListener {
        public void actionPerformed(ActionEvent event){
            //printCoins();
            quitMethod();
        }
    }

    public class GoBackHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            mainMenu();
        }
    }

    public class DanceWDevilHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            danceWdevil();
        }
    }

    public class HorseRacingHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            horseRacing();
        }
    }

    public class ElementalHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Elemental();
        }
    }
}
