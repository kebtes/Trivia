package ui;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import java.util.List;

import models.Player;
import models.Quiz;
import utils.Constants;
import utils.ImagePanel;

public class MainUI{
    private CardLayout cardLayout;
    private JPanel cardPanel;
    Quiz quiz;

    // overloaded methods to show component from the cardlayout
    public void showComponent(String comp){
        cardLayout.show(cardPanel, comp);
    }

    public void showComponent(String comp, List<Player> players) {
        if (comp.equals("QuestionUI")) {
            cardPanel.add(new QuestionUI(players, this), "QuestionUI");
        }
        cardLayout.show(cardPanel, comp);
    }

    public void showComponent(String comp, Quiz quiz){
        if (comp.equals("ResultUI")) {
            cardPanel.add(new ResultsUI(this, quiz), "ResultUI");
        }
        cardLayout.show(cardPanel, comp);
    }

    public MainUI(){
        JFrame mainFrame = new JFrame();
        mainFrame.setSize(785, 530);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);

        // set fram logo
        ImageIcon icon = new ImageIcon(Constants.LOGO_SRC);
        mainFrame.setIconImage(icon.getImage());
        mainFrame.setTitle("Trivia");
        
        // this panel for the background image
        ImagePanel backgroundPanel = new ImagePanel(utils.Constants.BACKGROUND_IMAGE_SRC);
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 785, 530);

        // content and cardlayout section
        cardLayout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        // cardPanel.setBackground(utils.Constants.GREEN_COLOR);
        cardPanel.setBounds(
            (mainFrame.getWidth() - 340) / 2, // Center horizontally
            25, // Center vertically (subtract footer height)
            340, // Width
            396  // Height
        );

        // PAGES
        cardPanel.setLayout(cardLayout);
        cardPanel.add(new AddPlayerSectionUI(this), "AddPlayerUI");
        cardPanel.add(new SettingsUI(), "SettingsUI");
        cardPanel.add(new HistoryUI(), "HistoryUI");
        
        // panel for the buttons at the footer
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(null);
        footerPanel.setBackground(utils.Constants.LIGHT_CREAM_COLOR);
        footerPanel.setBounds(0, 483, 785, 40);
        
        // buttons
        JButton historyButton = new JButton("HISTORY");
        historyButton.setBackground(utils.Constants.LIGHT_GREEN_COLOR);
        historyButton.setForeground(utils.Constants.LIGHT_CREAM_COLOR);
        historyButton.setFont(utils.Constants.getBoldFont(18));
        historyButton.setBounds(173, 0, 146, 40);
        historyButton.setLayout(null);
        historyButton.setHorizontalAlignment(SwingConstants.CENTER);
        historyButton.setVerticalAlignment(SwingConstants.CENTER);
        historyButton.setBorderPainted(false);
        historyButton.setFocusPainted(false);
        // historyButton.setContentAreaFilled(false);

        JButton gameButton = new JButton("GAME");
        gameButton.setBackground(utils.Constants.GREEN_COLOR);
        gameButton.setForeground(utils.Constants.LIGHT_CREAM_COLOR);
        gameButton.setFont(utils.Constants.getBoldFont(18));
        gameButton.setBounds(319, 0, 146, 40);
        gameButton.setLayout(null);
        gameButton.setHorizontalAlignment(SwingConstants.CENTER);
        gameButton.setVerticalAlignment(SwingConstants.CENTER);
        gameButton.setBorderPainted(false);
        gameButton.setFocusPainted(false);
        // gameButton.setContentAreaFilled(false);

        JButton settingsButton = new JButton("SETTINGS");
        settingsButton.setBackground(utils.Constants.LIGHT_GREEN_COLOR);
        settingsButton.setForeground(utils.Constants.LIGHT_CREAM_COLOR);
        settingsButton.setFont(utils.Constants.getBoldFont(18));
        settingsButton.setBounds(465, 0, 146, 40);
        settingsButton.setLayout(null);
        settingsButton.setHorizontalAlignment(SwingConstants.CENTER);
        settingsButton.setVerticalAlignment(SwingConstants.CENTER);
        settingsButton.setBorderPainted(false);
        settingsButton.setFocusPainted(false);
        // settingsButton.setContentAreaFilled(false);


        // ALL ACTION LISTNERS
        historyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                historyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        
            // @Override
            // public void mouseExited(MouseEvent e) {
            //     if (!historyButton.getBackground().equals(utils.Constants.GREEN_COLOR)) {
            //         historyButton.setBackground(utils.Constants.LIGHT_GREEN_COLOR);
            //     }
            // }
        });

        historyButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "HistoryUI");
            ((HistoryUI) cardPanel.getComponent(2)).updateHistory();
            gameButton.setBackground(utils.Constants.LIGHT_GREEN_COLOR);
            historyButton.setBackground(utils.Constants.GREEN_COLOR);
            settingsButton.setBackground(utils.Constants.LIGHT_GREEN_COLOR);
        });

        gameButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "AddPlayerUI");
            // Set selected state for the game button
            gameButton.setBackground(utils.Constants.GREEN_COLOR);
            historyButton.setBackground(utils.Constants.LIGHT_GREEN_COLOR);
            settingsButton.setBackground(utils.Constants.LIGHT_GREEN_COLOR);
        });
        
        
        gameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                gameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                // gameButton.setBackground(utils.Constants.GREEN_COLOR);
            }
        
            // @Override
            // public void mouseExited(MouseEvent e) {
            //     if (!gameButton.getBackground().equals(utils.Constants.GREEN_COLOR)) {
            //         gameButton.setBackground(utils.Constants.LIGHT_GREEN_COLOR);
            //     }
            // }
        });

        settingsButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "SettingsUI");
            gameButton.setBackground(utils.Constants.LIGHT_GREEN_COLOR);
            historyButton.setBackground(utils.Constants.LIGHT_GREEN_COLOR);
            settingsButton.setBackground(utils.Constants.GREEN_COLOR);
        });

        
        
        settingsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                settingsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                // settingsButton.setBackground(utils.Constants.GREEN_COLOR);
            }
        
            // @Override
            // public void mouseExited(MouseEvent e) {
            //     if (!settingsButton.getBackground().equals(utils.Constants.GREEN_COLOR)) {
            //         settingsButton.setBackground(utils.Constants.LIGHT_GREEN_COLOR);
            //     }
            // }
        });

        footerPanel.add(historyButton);
        footerPanel.add(gameButton);
        footerPanel.add(settingsButton);

        mainFrame.add(backgroundPanel);
        mainFrame.add(footerPanel);
        backgroundPanel.add(cardPanel);

        mainFrame.setComponentZOrder(footerPanel, 0);
        mainFrame.setComponentZOrder(backgroundPanel, 1);
        // mainFrame.setComponentZOrder(cardPanel, 0);
        mainFrame.setVisible(true);
    }
}
