package ui;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import dao.impl.PlayerDAOImpl;
import dao.impl.PlayerQuizHistoryDAOImpl;

import models.Quiz;
import models.Player;
import utils.Constants;
import utils.PreferencesManager;

public class ResultsUI extends JPanel {
    private PlayerDAOImpl playerDAO;
    private PlayerQuizHistoryDAOImpl playerQuizHistoryDAO;
    PreferencesManager preferencesManager = new PreferencesManager();
    MainUI mainUI;

    public ResultsUI(MainUI mainUI, Quiz quiz) {
        this.mainUI = mainUI;

        setBackground(Constants.GREEN_COLOR);
        setLayout(null);

        // game over text
        JLabel gameOverLabel = new JLabel();
        gameOverLabel.setText("Game Over!");
        gameOverLabel.setFont(Constants.getBoldFont(20));
        gameOverLabel.setForeground(Constants.LIGHT_GREEN_COLOR);
        gameOverLabel.setBounds(106, 46, 130, 30);

        // game result text
        JLabel gameResultLable = new JLabel();
        gameResultLable.setText("GAME RESULTS");
        gameResultLable.setForeground(Constants.CREAM_COLOR);
        gameResultLable.setFont(Constants.getRegularFont(10));
        gameResultLable.setBounds(137, 76, 75, 15);

        JSeparator horizontalSeparator = new JSeparator();
        horizontalSeparator.setBounds(38, 103, 269, 1);
        horizontalSeparator.setForeground(Constants.LIGHT_CREAM_COLOR);

        // panel to hold player results
        JPanel resultPanel = new JPanel();
        resultPanel.setBackground(Constants.GREEN_COLOR);
        resultPanel.setBounds(38, 111, 269, 169);
        resultPanel.setLayout(new GridLayout());
        resultPanel.setLayout(new GridLayout(3, 2, 0, 8));

        // handling the players
        List<Player> quizPlayers = quiz.getQuizPlayers();
        System.out.println(quizPlayers);
        for (Player player : quizPlayers) {
            String playerName = player.getPlayerName();
            int questionSize = preferencesManager.getQuestionSize();
            int playerScore = player.getPlayerScore();

            JPanel playerPanel = new JPanel();
            playerPanel.setBackground(Constants.GREEN_COLOR_2);
            playerPanel.setLayout(null);

            JLabel playerNameLabel = new JLabel();
            playerNameLabel.setText(playerName);
            playerNameLabel.setFont(Constants.getBoldFont(13));
            playerNameLabel.setBackground(Constants.GREEN_COLOR_2);
            playerNameLabel.setForeground(Constants.LIGHT_CREAM_COLOR);
            playerNameLabel.setBounds(11, 11, 93, 27);

            String result = String.valueOf(playerScore) + "/" + String.valueOf(questionSize);
            JLabel playerResultLabel = new JLabel();
            playerResultLabel.setText(result);
            playerResultLabel.setFont(Constants.getBoldFont(13));
            playerResultLabel.setBackground(Constants.GREEN_COLOR_2);
            playerResultLabel.setForeground(Constants.LIGHT_CREAM_COLOR);
            playerResultLabel.setBounds(236, 11, 27, 23);

            if ((playerScore / (double) questionSize) >= 0.5) {
                playerResultLabel.setForeground(Constants.LIGHT_GREEN_COLOR);
            } else {
                playerResultLabel.setForeground(Constants.RED_COLOR_2);
            }

            playerDAO = new PlayerDAOImpl();
            playerQuizHistoryDAO = new PlayerQuizHistoryDAOImpl();

            // updating the database
            // add the player if not already in database
            if (!(playerDAO.playerExists(playerName))) {
                // update the player score if player already exist
                playerDAO.addPlayer(player, playerScore);
            } else {
                playerDAO.updatePlayerScore(player, player.getPlayerScore());
                System.out.println(playerScore);
            }

            // fetch player id
            int playerId = playerDAO.getPlayerIdByName(player);

            // add it to the relation table if player has never played
            if (!(playerQuizHistoryDAO.checkHistory(playerId))) {
                playerQuizHistoryDAO.addPlayerHistory(player, player.getPlayerScore());
            }
            // update the score in the relation table if player had a history
            else {
                playerQuizHistoryDAO.updatePlayerHistory(player, player.getPlayerScore());
            }

            player.restartScore();

            playerPanel.add(playerNameLabel);
            playerPanel.add(playerResultLabel);
            resultPanel.add(playerPanel);
        }

        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setBackground(Constants.GREEN_COLOR_2);
        playAgainButton.setForeground(Constants.LIGHT_CREAM_COLOR);
        playAgainButton.setFont(Constants.getRegularFont(15));
        playAgainButton.setBounds(110, 304, 126, 37);
        ;
        playAgainButton.setBorder(null);
        playAgainButton.setFocusPainted(false);
        // playAgainButton.setContentAreaFilled(false);

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainUI.showComponent("AddPlayerUI");
            }
        });

        playAgainButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                playAgainButton.setBackground(Constants.LIGHT_GREEN_COLOR);
                playAgainButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Border outerBorder = BorderFactory.createLineBorder(Constants.CREAM_COLOR, 1);
                playAgainButton.setBorder(outerBorder);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                playAgainButton.setBackground(Constants.GREEN_COLOR_2);
                playAgainButton.setBorder(null);
            }

        });

        add(gameResultLable);
        add(gameOverLabel);
        add(horizontalSeparator);
        add(playAgainButton);
        add(resultPanel);
    }

}
