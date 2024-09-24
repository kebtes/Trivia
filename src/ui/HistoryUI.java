package ui;

import javax.swing.*;
import javax.swing.border.Border;

import dao.impl.PlayerDAOImpl;
import dao.impl.PlayerQuizHistoryDAOImpl;
import exceptions.InvalidPlayerNameException;
import exceptions.PlayerAlreadyExistException;
import models.Player;
// import models.Quiz;
import utils.Constants;
import utils.InputCheck;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import utils.PreferencesManager;

import java.util.List;

public class HistoryUI extends JPanel {
    PlayerQuizHistoryDAOImpl playerQuizHistoryDAO;
    PlayerDAOImpl playerDAO;
    PreferencesManager preferencesManager = new PreferencesManager();

    JPanel playerCards = new JPanel();
    JLabel playerTextLabel;

    public HistoryUI() {
        setLayout(null);
        setBackground(Constants.GREEN_COLOR);

        // Player history header
        JLabel playerHistoryLabel = new JLabel("Player History");
        playerHistoryLabel.setBackground(Constants.GREEN_COLOR);
        playerHistoryLabel.setForeground(Constants.LIGHT_GREEN_COLOR);
        playerHistoryLabel.setFont(Constants.getBoldFont(20));
        playerHistoryLabel.setBounds(45, 44, 170, 30);

        JSeparator horizontalSeparator = new JSeparator();
        horizontalSeparator.setBounds(41, 79, 269, 1);
        horizontalSeparator.setForeground(Constants.LIGHT_CREAM_COLOR);

        playerTextLabel = new JLabel("Player");
        playerTextLabel.setBackground(Constants.GREEN_COLOR);
        playerTextLabel.setForeground(Constants.LIGHT_CREAM_COLOR);
        playerTextLabel.setFont(Constants.getRegularFont(10));
        playerTextLabel.setBounds(41, 84, 32, 15);

        JLabel lastGameLabel = new JLabel("Last Game");
        lastGameLabel.setBackground(Constants.GREEN_COLOR);
        lastGameLabel.setForeground(Constants.LIGHT_CREAM_COLOR);
        lastGameLabel.setFont(Constants.getRegularFont(10));
        lastGameLabel.setBounds(160, 84, 55, 15);

        JLabel scoreLabel = new JLabel("Score");
        scoreLabel.setBackground(Constants.GREEN_COLOR);
        scoreLabel.setForeground(Constants.LIGHT_CREAM_COLOR);
        scoreLabel.setFont(Constants.getRegularFont(10));
        scoreLabel.setBounds(274, 84, 32, 15);

        // Set BoxLayout for vertical spacing
        playerCards.setBackground(Constants.GREEN_COLOR);
        // playerCards.setLayout(new BoxLayout(playerCards, BoxLayout.Y_AXIS));

        // scrollbar properties
        UIManager.put("ScrollBar.width", 0);
        
        JScrollPane scrollPane = new JScrollPane(playerCards);
        // // scrollPane.setBackground(Constants.GREEN_COLOR);
        // scrollPane.getViewport().setBackground(Constants.GREEN_COLOR);
        scrollPane.setBounds(41, 104, 270, 250);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(null);

        
        // Fetch history
        playerQuizHistoryDAO = new PlayerQuizHistoryDAOImpl();
        updateHistory();

        JButton clearHistoryButton = new JButton("CLEAR HISTORY");
        clearHistoryButton.setBackground(Constants.GREEN_COLOR);
        clearHistoryButton.setFont(Constants.getBoldFont(12));
        clearHistoryButton.setForeground(Constants.CREAM_COLOR);
        clearHistoryButton.setBounds(110, 360, 120, 20);
        clearHistoryButton.setFocusPainted(false);
        clearHistoryButton.setBorder(null);
        clearHistoryButton.setContentAreaFilled(false);

        clearHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerQuizHistoryDAO.clearHistory();
                playerCards.removeAll(); 
                updateHistory(); 
                scrollPane.revalidate();
                scrollPane.repaint(); 
            }
        });

        clearHistoryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                clearHistoryButton.setForeground(Constants.LIGHT_GREEN_COLOR);
                clearHistoryButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                clearHistoryButton.setForeground(Constants.LIGHT_CREAM_COLOR);
            }
        });

        add(playerHistoryLabel);
        add(horizontalSeparator);
        add(playerTextLabel);
        add(lastGameLabel);
        add(scoreLabel);
        add(scrollPane);
        add(clearHistoryButton);
    }

    public void updateHistory() {
        playerCards.removeAll();
        
        List<List<String>> playerRecords = playerQuizHistoryDAO.getHistory();
        int[] totalHeight = {0}; // To calculate total height for playerCards
        
        for (List<String> record : playerRecords) {
            JPanel singleRecordPanel = new JPanel();
            singleRecordPanel.setLayout(null);
            singleRecordPanel.setBackground(Constants.GREEN_COLOR_2);
            singleRecordPanel.setPreferredSize(new Dimension(270, 40));
            singleRecordPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
            
            // record infos
            String playerName = record.get(0);
            String quizDate = record.get(2);
            String score = record.get(3);
            int questionsAsked = Integer.parseInt(record.get(1)) * preferencesManager.getQuestionSize();
            
            JLabel[] playerNameLabel = {new JLabel(playerName)};

            boolean[] isExpanded = {false};
            singleRecordPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JSeparator horizontalSeparator;
                    JTextField updateNameTextField;
                    JButton updatePlayerButton;
                    JButton deletePlayerButton;

                    // toggle the expanded state
                    isExpanded[0] = !isExpanded[0];
                    
                    if (isExpanded[0]){
                        // expand the panel
                        singleRecordPanel.setPreferredSize(new Dimension(270, 100));
                        totalHeight[0] += 60;

                        // enlarged card section
                        horizontalSeparator = new JSeparator();
                        horizontalSeparator.setBounds(8, 42, 253, 1);
                        horizontalSeparator.setForeground(Constants.LIGHT_CREAM_COLOR);

                        updateNameTextField = new JTextField();
                        updateNameTextField.setText(playerName);
                        updateNameTextField.setFont(Constants.getRegularFont(11));
                        updateNameTextField.setHorizontalAlignment(JTextField.CENTER);
                        updateNameTextField.setBackground(Constants.LIGHT_GREEN_COLOR);
                        updateNameTextField.setForeground(Constants.LIGHT_CREAM_COLOR);
                        updateNameTextField.setBorder(null);
                        updateNameTextField.setBounds(75, 56, 120, 20);

                        updateNameTextField.addMouseListener(new MouseAdapter() {
                            public void mouseClicked(MouseEvent e) {
                                // System.out.println("clicked");
                                updateNameTextField.setText("");
                                updateNameTextField.setBackground(Constants.LIGHT_GREEN_COLOR);
                                updateNameTextField.setForeground(Constants.LIGHT_CREAM_COLOR);
                            };
                        });
                                                 
                        updatePlayerButton = new JButton();
                        updatePlayerButton.setText("UPDATE PLAYER");
                        updatePlayerButton.setForeground(Constants.LIGHT_CREAM_COLOR);
                        updatePlayerButton.setFocusPainted(false);
                        updatePlayerButton.setBorder(null);
                        updatePlayerButton.setFont(Constants.getRegularFont(9));
                        updatePlayerButton.setBounds(24, 82, 80, 15);
                        updatePlayerButton.setBackground(Constants.GREEN_COLOR_2);
                        updatePlayerButton.setContentAreaFilled(false);

                        
                        updatePlayerButton.addMouseListener(new MouseAdapter() {
                            public void mouseEntered(MouseEvent e) {
                                updatePlayerButton.setForeground(Constants.LIGHT_GREEN_COLOR);
                            };    

                            public void mouseExited(MouseEvent e) {
                                updatePlayerButton.setForeground(Constants.LIGHT_CREAM_COLOR);
                            };
                        });

                        updatePlayerButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    playerDAO = new PlayerDAOImpl();
                                    String updateName = updateNameTextField.getText().strip();
                                    
                                    // check if player by the update name already exists in database
                                    if (playerDAO.playerExists(updateName)) throw new PlayerAlreadyExistException();

                                    // validate new name
                                    if (!InputCheck.validateInputName(updateName)) throw new InvalidPlayerNameException();
                                    
                                    // update database
                                    PlayerDAOImpl playerDAO = new PlayerDAOImpl();
                                    playerDAO.updatePlayerName(new Player(playerName), updateName);

                                    // update name label
                                    updateNameTextField.setText(updateName);
                                    playerNameLabel[0].setText(updateName);
                                
                                } catch (PlayerAlreadyExistException exc) {
                                    updateNameTextField.setText("Player exists");
                                    updateNameTextField.setBackground(Constants.RED_COLOR);
                                
                                } catch (InvalidPlayerNameException exc) {
                                    updateNameTextField.setText("Invalid name");
                                    updateNameTextField.setBackground(Constants.RED_COLOR);
                                }
                                
                            };
                        });

                        deletePlayerButton = new JButton();
                        deletePlayerButton.setText("DELETE PLAYER");
                        deletePlayerButton.setForeground(Constants.LIGHT_CREAM_COLOR);
                        deletePlayerButton.setFocusPainted(false);
                        deletePlayerButton.setBorder(null);
                        deletePlayerButton.setFont(Constants.getRegularFont(9));
                        deletePlayerButton.setBounds(172, 82, 80, 15);
                        deletePlayerButton.setBackground(Constants.GREEN_COLOR_2);
                        deletePlayerButton.setContentAreaFilled(false);

                        deletePlayerButton.addMouseListener(new MouseAdapter() {
                            public void mouseEntered(MouseEvent e) {
                                deletePlayerButton.setForeground(Constants.RED_COLOR);
                            };

                            public void mouseExited(MouseEvent e) {
                                deletePlayerButton.setForeground(Constants.LIGHT_CREAM_COLOR);
                            };
                        });

                        deletePlayerButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                PlayerDAOImpl playerDAO = new PlayerDAOImpl();
                                
                                // fetch player ID and remove it from database
                                int playerId = playerDAO.getPlayerIdByName(new Player(playerName));
                                playerDAO.deletePlayer(playerId); 
                                    
                                // remove the player card
                                playerCards.remove(singleRecordPanel);
                                playerCards.revalidate();
                                playerCards.repaint();


                            };
                        });

                        singleRecordPanel.add(horizontalSeparator);
                        singleRecordPanel.add(updateNameTextField);
                        singleRecordPanel.add(updatePlayerButton);
                        singleRecordPanel.add(deletePlayerButton);


                    }else{
                        // singleRecordPanel.remove(1);
                        singleRecordPanel.setPreferredSize(new Dimension(270, 40));
                        totalHeight[0] -= 60;
                    }

                    playerCards.setPreferredSize(new Dimension(245, totalHeight[0]));
                    playerCards.revalidate();
                    playerCards.repaint();
                }


                @Override
                public void mouseEntered(MouseEvent e) {
                    singleRecordPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    Border outerBorder = BorderFactory.createLineBorder(Constants.LIGHT_GREEN_COLOR, 1);
                    singleRecordPanel.setBorder(outerBorder);
                }
    
                @Override
                public void mouseExited(MouseEvent e) {
                    singleRecordPanel.setBorder(null);
                }
            });
    
            playerNameLabel[0].setForeground(Constants.LIGHT_CREAM_COLOR);
            playerNameLabel[0].setFont(Constants.getRegularFont(13));
            playerNameLabel[0].setBounds(11, 11, 93, 20);
    
            JLabel playerLastGameDate = new JLabel(quizDate);
            playerLastGameDate.setForeground(Constants.LIGHT_CREAM_COLOR);
            playerLastGameDate.setFont(Constants.getRegularFont(13));
            playerLastGameDate.setBounds(115, 11, 93, 20);
    
            String scoreText = score + "/" + questionsAsked;
            JLabel playerScoreLabel = new JLabel(scoreText);
            playerScoreLabel.setForeground(Constants.LIGHT_CREAM_COLOR);
            playerScoreLabel.setFont(Constants.getRegularFont(13));
            playerScoreLabel.setBounds(230, 11, 40, 20);
    
            // Change color based on score
            if ((Integer.parseInt(record.get(3)) / (double) questionsAsked) >= 0.5) {
                playerScoreLabel.setForeground(Constants.LIGHT_GREEN_COLOR);
            } else {
                playerScoreLabel.setForeground(Constants.RED_COLOR_2);
            }
    
            singleRecordPanel.add(playerNameLabel[0]);
            singleRecordPanel.add(playerLastGameDate);
            singleRecordPanel.add(playerScoreLabel);
    
            playerCards.add(singleRecordPanel);

            // Calculate the total height
            totalHeight[0] += 50; // 40 for panel height + 10 for spacing
        }
    
        playerCards.setPreferredSize(new Dimension(245, totalHeight[0])); // Set preferred size based on total height
        playerCards.revalidate();
        playerCards.repaint();
    }
}