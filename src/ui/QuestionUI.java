package ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Component;

import java.util.Arrays;
import java.util.List;

import models.FillTheBlankQuestion;
import models.MultipleAnswerQuestion;
import models.Player;
import models.Questions;
import models.Quiz;
import utils.Constants;
import utils.PreferencesManager;

public class QuestionUI extends JPanel {
    PreferencesManager preferencesManager = new PreferencesManager();
    private int currentPlayerIdx = 0;
    private int currentQuestionIdx = 0;
    private List<Player> allCurrentPlayers;
    private List<Questions> quizQuestions;
    private boolean isSinglePlayer;

    private Quiz quiz;
    private MainUI mainUI;

    public QuestionUI(List<Player> allCurrentPlayers, MainUI mainUI) {
        setBackground(utils.Constants.GREEN_COLOR);
        this.allCurrentPlayers = allCurrentPlayers;

        // make every players score 0
        for (Player player: allCurrentPlayers){
            player.restartScore();
        }

        isSinglePlayer = (allCurrentPlayers.size() == 1);

        quiz = new Quiz(allCurrentPlayers);
        quizQuestions = quiz.getQuizQuestions();

        this.mainUI = mainUI;

        setLayout(null);
        showNextQuestion();
    }

    private void showNextQuestion() {
        removeAll();

        if (currentQuestionIdx >= quizQuestions.size()) {
            mainUI.showComponent("ResultUI", quiz);
            return;
        }

        // Get current player and question
        Player currentPlayer = allCurrentPlayers.get(currentPlayerIdx);
        Questions currentQuestion = quizQuestions.get(currentQuestionIdx);
        
        String playerTurnLabelText = currentPlayer.getPlayerName() + "'s turn";
        playerTurnLabelText.toUpperCase();
       
        // Get the question category and create label
        String questionCategory = currentQuestion.getQuestionCategory();
        // if (questionCategory.equals("generalCategory")) questionCategory = "General";
        // else if (questionCategory.equals("mathCategory")) questionCategory = "Mathematics";
        // else if (questionCategory.equals("movieCategory")) questionCategory = "Movies";
        // else if (questionCategory.equals("musicCategory")) questionCategory = "Music";
        
        JLabel questionCategoryLabel = new JLabel();
        questionCategoryLabel.setText(questionCategory);
        questionCategoryLabel.setBackground(Constants.GREEN_COLOR);
        questionCategoryLabel.setForeground(Constants.LIGHT_GREEN_COLOR);
        questionCategoryLabel.setBorder(null);
        questionCategoryLabel.setFont(Constants.getBoldFont(10));
        questionCategoryLabel.setBounds(250, 34, 150, 30); // Adjusted width/height for better visibility
        
        // question textlabel
        JTextArea questionTextLabel = new JTextArea(2, 230);
        questionTextLabel.setText(currentQuestion.getQuestionText());
        questionTextLabel.setLineWrap(true);
        questionTextLabel.setWrapStyleWord(true);
        questionTextLabel.setBackground(Constants.GREEN_COLOR);
        questionTextLabel.setForeground(Constants.LIGHT_CREAM_COLOR);
        questionTextLabel.setBounds(40, 70, 260, 100);
        questionTextLabel.setFocusable(false);

        if (questionTextLabel.getText().length() >= 60) questionTextLabel.setFont(Constants.getRegularFont(17));
        else if (questionTextLabel.getText().length() >= 35) questionTextLabel.setFont(Constants.getRegularFont(18));
        else questionTextLabel.setFont(Constants.getRegularFont(20));

        JSeparator horizontalSeparator = new JSeparator();
        horizontalSeparator.setBounds(39, 170, 269, 1);
        horizontalSeparator.setForeground(Constants.LIGHT_GREEN_COLOR);

        // for multiple choice question
        if (currentQuestion.getQuestionAskType().equals("MCQ")){
            System.out.println((currentQuestion.getCorrectAnswer()));

            MultipleAnswerQuestion mcqQuestion = (MultipleAnswerQuestion) currentQuestion;
            List<String> questionChoices = mcqQuestion.getQuestionChoices();

            // buttons label
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setBounds(39, 190, 268, 152);
            buttonsPanel.setLayout(new GridLayout(2, 3, 8, 8));
            buttonsPanel.setBackground(Constants.GREEN_COLOR);

            for (String choice: questionChoices){
                // jbutton text doesnt allow text wrap
                // so we use this method
                JButton btn = new JButton("<html><center>" + choice + "</center></html>");

                
                if (choice.length() >= 15) btn.setFont(Constants.getRegularFont(13));
                else if (choice.length() >= 20) btn.setFont(Constants.getRegularFont(9));
                else btn.setFont(Constants.getRegularFont(16));

                btn.setBackground(Constants.GREEN_COLOR_2);
                btn.setForeground(Constants.LIGHT_CREAM_COLOR);
                btn.setSize(120, 37);
                btn.setBorder(null);
                btn.setFocusPainted(false);
                // btn.setContentAreaFilled(false);
                // btn.setHorizontalTextPosition(SwingConstants.CENTER);
                // btn.setVerticalTextPosition(SwingConstants.CENTER);
                
                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        Border outerBorder = BorderFactory.createLineBorder(Constants.CREAM_COLOR, 1);
                        btn.setBorder(outerBorder);
                    }
    
                    @Override
                    public void mouseExited(MouseEvent e) {
                        btn.setBorder(null);
                    }
                    
                });

                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // remove the html tags before validating
                        String buttonText = btn.getText().replaceAll("<[^>]*>", "");
                        
                        if (mcqQuestion.validateUserAnswer(Arrays.asList(buttonText))){
                            btn.setBackground(Constants.LIGHT_GREEN_COLOR);
                            currentPlayer.incrementScore();
                            

                        }else{
                            btn.setBackground(Constants.RED_COLOR_2);
                            
                            // indicate the correct choice by making the correct choice button green
                            for (Component comp : buttonsPanel.getComponents()){
                                JButton choiceBtn = (JButton) comp;
                                String choiceText = choiceBtn.getText().replaceAll("<[^>]*>", "");
                                
                                if (currentQuestion.validateUserAnswer(Arrays.asList(choiceText))){
                                    choiceBtn.setBackground(Constants.LIGHT_GREEN_COLOR);
                                    break;
                                }
                            }

                        }
                        
                        // schedule a delay before showing the next question
                        Timer timer = new Timer(500, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                currentPlayerIdx = (currentPlayerIdx + 1) % allCurrentPlayers.size();
                                currentQuestionIdx = currentQuestionIdx + 1;
                                showNextQuestion();
                            }
                        });
                        timer.setRepeats(false); // ensure the timer only runs once
                        timer.start();

                    }
                });
    
                buttonsPanel.add(btn);
    
            }

            add(buttonsPanel);
        
        }else if (currentQuestion.getQuestionAskType().equals("FBQ")) {
            FillTheBlankQuestion fbqQuestion = (FillTheBlankQuestion) currentQuestion;

            JTextField fillBlankInput = new JTextField();
            fillBlankInput.setBackground(Constants.CREAM_COLOR);
            fillBlankInput.setBorder(null);
            fillBlankInput.setBounds(39, 228, 269, 37);
            fillBlankInput.setBorder(new EmptyBorder(0, 5, 0, 0));
            fillBlankInput.setFont(Constants.getBoldFont(18));
            fillBlankInput.setHorizontalAlignment(JTextField.CENTER);

            fillBlankInput.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    // accept input when the user clicks enter key
                    if (e.getKeyCode() == KeyEvent.VK_ENTER){
                        // sends user input for validation
                        if (fbqQuestion.validateUserAnswer(Arrays.asList(fillBlankInput.getText()))){
                            fillBlankInput.setBackground(Constants.LIGHT_GREEN_COLOR);
                            currentPlayer.incrementScore();

                        }else{
                            fillBlankInput.setBackground(Constants.RED_COLOR_2);
                            fillBlankInput.setText(fbqQuestion.getCorrectAnswer().get(0));
                            
                            if (fbqQuestion.getCorrectAnswer().get(0).length() >= 15) fillBlankInput.setFont(Constants.getBoldFont(14));
                        }

                        // schedule a delay before showing the next question
                        Timer timer = new Timer(500, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                currentPlayerIdx = (currentPlayerIdx + 1) % allCurrentPlayers.size();
                                currentQuestionIdx += 1;
                                showNextQuestion();
                            }
                        });
                        timer.setRepeats(false); // ensure the timer only runs once
                        timer.start();

                    }
                }
            });
            add(fillBlankInput);
        }
        

        // label to show who's turn it is to play
        JLabel playerTurnLabel = new JLabel();
        playerTurnLabel.setText(playerTurnLabelText);
        playerTurnLabel.setFont(Constants.getRegularFont(10));
        playerTurnLabel.setForeground(Constants.RED_COLOR);
        playerTurnLabel.setBounds(39, 354, 269, 23);
        playerTurnLabel.setHorizontalAlignment(JLabel.CENTER);
        playerTurnLabel.setVerticalAlignment(JLabel.CENTER);
        
        if (!isSinglePlayer) add(playerTurnLabel);

        add(questionCategoryLabel);
        add(questionTextLabel);
        add(horizontalSeparator);

        // refresh panel to show new components
        revalidate();
        repaint();
    }
}
