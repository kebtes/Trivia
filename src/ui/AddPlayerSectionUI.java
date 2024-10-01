package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import exceptions.InvalidPlayerNameException;
import exceptions.NotEnoughPlayersException;
import exceptions.PlayerAlreadyExistException;
import models.Player;
import utils.Constants;
import utils.InputCheck;
import dao.impl.PlayerDAOImpl;

import java.util.List;
import java.util.ArrayList;

public class AddPlayerSectionUI extends JPanel {
    JPanel playerCardDisplay = new JPanel();
    List<Player> currentPlayers;
    MainUI mainUI;
    PlayerDAOImpl playerDB = new PlayerDAOImpl();

    public List<Player> getAllCurrentPlayers() {
        return currentPlayers;
    }

    private JPanel createAndAddPlayerCardToPanel(Player player) {
        JPanel outputPanel = new JPanel();
        outputPanel.setPreferredSize(new java.awt.Dimension(261, 23));
        outputPanel.setBackground(Constants.GREEN_COLOR);
        outputPanel.setLayout(null);

        String player_name = player.getPlayerName();

        JLabel nameLabel = new JLabel();
        nameLabel.setText(player_name);
        nameLabel.setBackground(Constants.LIGHT_CREAM_COLOR);
        nameLabel.setBorder(null);
        nameLabel.setFont(Constants.getRegularFont(10));
        nameLabel.setBounds(0, 0, 223, 23);
        nameLabel.setOpaque(true);
        nameLabel.setForeground(Constants.GREEN_COLOR);
        nameLabel.setBorder(new EmptyBorder(0, 5, 0, 0));

        JButton removeButton = new JButton("-");
        removeButton.setFont(Constants.getRegularFont(16));
        removeButton.setBackground(Constants.GREEN_COLOR);
        removeButton.setSize(23, 23);
        removeButton.setBounds(240, 0, 23, 23);
        removeButton.setBorder(null);
        removeButton.setForeground(Constants.LIGHT_CREAM_COLOR);
        removeButton.setFocusPainted(false);
        removeButton.setBorder(null);
        // removeButton.setContentAreaFilled(false);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerCardDisplay.remove(outputPanel);
                playerCardDisplay.revalidate();
                playerCardDisplay.repaint();
                currentPlayers.remove(player);
            }
        });

        removeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                removeButton.setFont(Constants.getBoldFont(16));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                removeButton.setFont(Constants.getRegularFont(16));
            }
        });

        outputPanel.add(nameLabel);
        outputPanel.add(removeButton);

        return outputPanel;
    }

    public AddPlayerSectionUI(MainUI mainUI) {
        removeAll();
        revalidate();
        repaint();

        this.mainUI = mainUI;
        setLayout(null);
        setBackground(utils.Constants.GREEN_COLOR);

        // add players header text
        JLabel addPlayersText = new JLabel();
        addPlayersText.setText("Add Players");
        addPlayersText.setFont(Constants.getBoldFont(16));
        addPlayersText.setForeground(Constants.LIGHT_GREEN_COLOR);
        addPlayersText.setBounds(45, 47, 119, 24);

        // max of three players label
        JLabel msg = new JLabel();
        msg.setText("A MAXIMUM OF 3 PLAYERS");
        msg.setFont(utils.Constants.getRegularFont(10));
        msg.setForeground(Constants.LIGHT_CREAM_COLOR);
        msg.setBounds(45, 71, 145, 15);

        // label to show error message and successful verdicts
        JLabel verdictLabel = new JLabel();
        verdictLabel.setText("");
        verdictLabel.setFont(Constants.getRegularFont(8));
        verdictLabel.setBounds(45, 354, 150, 15);
        verdictLabel.setForeground(Constants.RED_COLOR);

        JTextField nameInputField = new JTextField("Type player name here...", 140);
        nameInputField.setBackground(Constants.LIGHT_CREAM_COLOR);
        nameInputField.setForeground(Constants.GREEN_COLOR);
        nameInputField.setFont(Constants.getRegularFont(10));
        nameInputField.setBounds(45, 103, 223, 23);
        nameInputField.setBorder(new EmptyBorder(0, 5, 0, 0));

        // this removes the default text in the text area when clicked
        nameInputField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nameInputField.setText("");
            }
        });

        JButton addPlayerButton = new JButton();
        addPlayerButton.setText("+");
        addPlayerButton.setFont(Constants.getRegularFont(16));
        addPlayerButton.setBounds(280, 103, 40, 23);
        addPlayerButton.setForeground(Constants.LIGHT_CREAM_COLOR);
        addPlayerButton.setBackground(Constants.GREEN_COLOR);
        addPlayerButton.setOpaque(true);
        addPlayerButton.setBorder(null);
        addPlayerButton.setFocusPainted(false);
        // addPlayerButton.setContentAreaFilled(false);

        // list containing currentPlayers
        currentPlayers = new ArrayList<>();
        playerCardDisplay.setBounds(45, 163, 261, 159);
        playerCardDisplay.setBackground(Constants.GREEN_COLOR);

        playerCardDisplay.removeAll();
        playerCardDisplay.revalidate();
        playerCardDisplay.repaint();

        // bolds the + sign when mouse hovers over it
        addPlayerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                addPlayerButton.setFont(Constants.getBoldFont(16));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addPlayerButton.setFont(Constants.getRegularFont(16));
            }
        });

        addPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verdictLabel.setText("");
                try {
                    // Check if the maximum number of players has been reached
                    if (currentPlayers.size() >= 3) {
                        verdictLabel.setText("Maximum of 3 players reached!");
                        verdictLabel.setForeground(Color.RED);
                        return;
                    }

                    String inputName = nameInputField.getText().strip();

                    // validate input name
                    if (!InputCheck.validateInputName(inputName)) {
                        throw new InvalidPlayerNameException();
                    }

                    Player new_player = new Player(inputName);

                    // check if player already exists
                    if (currentPlayers.contains(new_player)) {
                        throw new PlayerAlreadyExistException();
                    }

                    currentPlayers.add(new_player);
                    JPanel newPlayerPanel = createAndAddPlayerCardToPanel(new_player);
                    playerCardDisplay.add(newPlayerPanel);

                    playerCardDisplay.revalidate();
                    playerCardDisplay.repaint();

                    verdictLabel.setText("Player added successfuly");
                    verdictLabel.setForeground(Constants.LIGHT_GREEN_COLOR);

                } catch (InvalidPlayerNameException exc) {
                    verdictLabel.setText("Invalid player name, try again!");
                    verdictLabel.setForeground(Color.RED);

                } catch (PlayerAlreadyExistException exc) {
                    verdictLabel.setText("Player already in game!");
                    verdictLabel.setForeground(Color.RED);

                } finally {
                    nameInputField.setText("Type player name here...");

                }
            }
        });

        JButton startGameButton = new JButton("START");
        startGameButton.setFont(Constants.getRegularFont(16));
        startGameButton.setForeground(Constants.LIGHT_CREAM_COLOR);
        startGameButton.setBackground(Constants.LIGHT_GREEN_COLOR);
        startGameButton.setFocusPainted(false);
        startGameButton.setBorder(null);
        startGameButton.setBounds(207, 349, 100, 25);
        // startGameButton.setContentAreaFilled(false);

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (currentPlayers.size() == 0) {
                        throw new NotEnoughPlayersException();
                    }

                    // WHAT A SHITTTTYY BUG THIS WAS OMFG
                    // for (Player p: currentPlayers)
                    // playerDB.addPlayer(p);

                    mainUI.showComponent("QuestionUI", currentPlayers);

                } catch (NotEnoughPlayersException exc) {
                    verdictLabel.setText("Not enough players...");
                    verdictLabel.setForeground(Color.RED);

                }

            }
        });

        startGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startGameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Border outerBorder = BorderFactory.createLineBorder(Constants.CREAM_COLOR, 1);
                startGameButton.setBorder(outerBorder);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startGameButton.setBorder(null);
            }
        });

        JSeparator horizontalSeparator = new JSeparator();
        horizontalSeparator.setBounds(45, 146, 262, 1);
        horizontalSeparator.setForeground(Constants.LIGHT_GREEN_COLOR);

        add(addPlayersText);
        add(nameInputField);
        add(msg);
        add(verdictLabel);
        add(horizontalSeparator);
        add(addPlayerButton);
        add(playerCardDisplay);
        add(startGameButton);
    }
}
