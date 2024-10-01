package ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import java.awt.Image;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import utils.ImageLoadandResize;
import utils.PreferencesManager;
import utils.Constants;

import models.Category;
import models.Quiz;

public class SettingsUI extends JPanel {
    private ImageIcon uncheckedIcon = new ImageIcon(Constants.UNCHECKED_ICON);
    private ImageIcon checkedIcon = new ImageIcon(Constants.CHECKED_ICON);

    private Image scaledUnchecked = uncheckedIcon.getImage().getScaledInstance(13, 13, Image.SCALE_SMOOTH);
    private Image scaledChecked = checkedIcon.getImage().getScaledInstance(13, 13, Image.SCALE_SMOOTH);

    private JTextField sizeInputField = null;
    private JCheckBox mathCategoryCBox = null;
    private JCheckBox generalCategoryCBox = null;
    private JCheckBox moviesCategoryCBox = null;
    private JCheckBox musicCategoryCBox = null;

    private PreferencesManager preferencesManager = new PreferencesManager();

    // property states
    private boolean generalCategoryState = preferencesManager.getGeneralCategory();
    private boolean musicCategoryState = preferencesManager.getMusicCategory();
    private boolean movieCategoryState = preferencesManager.getMovieCategory();
    private boolean mathCategoryState = preferencesManager.getMathCategory();

    // reverts all changes made in the setting to their default value
    private void resetSettings() {
        // set question size back to 4
        sizeInputField.setText("4");
        // Quiz.setQuestionSize(4);

        // every category is now checked
        mathCategoryCBox.setSelected(true);
        generalCategoryCBox.setSelected(true);
        moviesCategoryCBox.setSelected(true);
        musicCategoryCBox.setSelected(true);

        // revert the properties file
        preferencesManager.setMathCategory(true);
        preferencesManager.setGeneralCategory(true);
        preferencesManager.setMusicCategory(true);
        preferencesManager.setMovieCategory(true);
        preferencesManager.setQuestionSize(4);
    }

    public SettingsUI() {
        setBackground(Constants.GREEN_COLOR);
        setLayout(null);

        // settings text
        JLabel settingTextLabel = new JLabel();
        settingTextLabel.setText("Settings");
        settingTextLabel.setFont(Constants.getBoldFont(20));
        settingTextLabel.setBounds(45, 44, 100, 30);
        settingTextLabel.setForeground(Constants.LIGHT_GREEN_COLOR);

        JSeparator horizontalSeparator = new JSeparator();
        horizontalSeparator.setBounds(41, 79, 269, 1);
        horizontalSeparator.setForeground(Constants.LIGHT_CREAM_COLOR);

        // available categories section
        JLabel availableCategoriesText = new JLabel();
        availableCategoriesText.setText("Available categories");
        availableCategoriesText.setForeground(Constants.LIGHT_CREAM_COLOR);
        availableCategoriesText.setBounds(42, 80, 100, 12);
        availableCategoriesText.setFont(Constants.getRegularFont(8));

        // image
        ImageIcon acImg = ImageLoadandResize.loadAndResizeIcon(Constants.CATEGORY_IMG_SRC, 32, 32);
        JLabel availableCategoriesIMG = new JLabel(acImg);
        availableCategoriesIMG.setBounds(35, 98, 50, 32);

        Cursor hoverCursor = new Cursor(Cursor.HAND_CURSOR);

        // checkboxes
        // general category
        generalCategoryCBox = new JCheckBox("General");
        generalCategoryCBox.setBounds(116, 99, 80, 15);
        generalCategoryCBox.setBackground(Constants.GREEN_COLOR);
        generalCategoryCBox.setForeground(Constants.LIGHT_CREAM_COLOR);
        generalCategoryCBox.setFont(Constants.getRegularFont(10));
        generalCategoryCBox.setCursor(hoverCursor);
        generalCategoryCBox.setFocusPainted(false);

        generalCategoryCBox.setIcon(new ImageIcon(scaledUnchecked));
        generalCategoryCBox.setSelectedIcon(new ImageIcon(scaledChecked));
        generalCategoryCBox.setRolloverEnabled(false);
        generalCategoryCBox.setSelected(generalCategoryState);

        generalCategoryCBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (generalCategoryCBox.isSelected())
                    preferencesManager.setGeneralCategory(true);
                else
                    preferencesManager.setGeneralCategory(false);
            }

        });

        // movies category
        moviesCategoryCBox = new JCheckBox("Movies");
        moviesCategoryCBox.setBounds(225, 99, 80, 15);
        moviesCategoryCBox.setBackground(Constants.GREEN_COLOR);
        moviesCategoryCBox.setForeground(Constants.LIGHT_CREAM_COLOR);
        moviesCategoryCBox.setFont(Constants.getRegularFont(10));
        moviesCategoryCBox.setCursor(hoverCursor);
        moviesCategoryCBox.setFocusPainted(false);

        moviesCategoryCBox.setIcon(new ImageIcon(scaledUnchecked));
        moviesCategoryCBox.setSelectedIcon(new ImageIcon(scaledChecked));
        moviesCategoryCBox.setRolloverEnabled(false);
        moviesCategoryCBox.setSelected(movieCategoryState);

        moviesCategoryCBox.addActionListener(new ActionListener() {
            Category obj = new Category("Movies");

            @Override
            public void actionPerformed(ActionEvent e) {
                if (moviesCategoryCBox.isSelected())
                    Quiz.addToCategory(obj);
                else
                    Quiz.removeFromCategory(obj);

            }
        });

        // math category
        mathCategoryCBox = new JCheckBox("Math");
        mathCategoryCBox.setBounds(116, 119, 80, 15);
        mathCategoryCBox.setBackground(Constants.GREEN_COLOR);
        mathCategoryCBox.setForeground(Constants.LIGHT_CREAM_COLOR);
        mathCategoryCBox.setFont(Constants.getRegularFont(10));
        mathCategoryCBox.setCursor(hoverCursor);
        mathCategoryCBox.setFocusPainted(false);
        mathCategoryCBox.setSelected(true);

        mathCategoryCBox.setIcon(new ImageIcon(scaledUnchecked));
        mathCategoryCBox.setSelectedIcon(new ImageIcon(scaledChecked));
        mathCategoryCBox.setRolloverEnabled(mathCategoryState);

        mathCategoryCBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (mathCategoryCBox.isSelected())
                    preferencesManager.setMathCategory(true);
                else
                    preferencesManager.setMathCategory(false);

            }
        });

        // movies category
        musicCategoryCBox = new JCheckBox("Music");
        musicCategoryCBox.setBounds(225, 119, 80, 15);
        musicCategoryCBox.setBackground(Constants.GREEN_COLOR);
        musicCategoryCBox.setForeground(Constants.LIGHT_CREAM_COLOR);
        musicCategoryCBox.setFont(Constants.getRegularFont(10));
        musicCategoryCBox.setCursor(hoverCursor);
        musicCategoryCBox.setFocusPainted(false);
        musicCategoryCBox.setSelected(true);

        musicCategoryCBox.setIcon(new ImageIcon(scaledUnchecked));
        musicCategoryCBox.setSelectedIcon(new ImageIcon(scaledChecked));
        musicCategoryCBox.setRolloverEnabled(musicCategoryState);

        musicCategoryCBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (musicCategoryCBox.isSelected())
                    preferencesManager.setMusicCategory(true);
                else
                    preferencesManager.setMusicCategory(false);

            }
        });

        // question size section
        JSeparator horizontalSeparator2 = new JSeparator();
        horizontalSeparator2.setBounds(41, 201, 269, 1);
        horizontalSeparator2.setForeground(Constants.LIGHT_CREAM_COLOR);

        // question size text
        JLabel questionSizeLabel = new JLabel();
        questionSizeLabel.setText("Question size per person (min 4, max 8)");
        questionSizeLabel.setForeground(Constants.LIGHT_CREAM_COLOR);
        questionSizeLabel.setBounds(44, 203, 160, 12);
        questionSizeLabel.setFont(Constants.getRegularFont(8));

        ImageIcon qsImg = ImageLoadandResize.loadAndResizeIcon(Constants.LIST_IMG_SRC, 32, 32);
        JLabel questionSizeImage = new JLabel(qsImg);
        questionSizeImage.setBounds(35, 224, 50, 32);

        sizeInputField = new JTextField();
        sizeInputField.setText("4");
        sizeInputField.setBackground(Constants.LIGHT_GREEN_COLOR);
        sizeInputField.setForeground(Constants.LIGHT_CREAM_COLOR);
        sizeInputField.setFont(Constants.getBoldFont(15));
        sizeInputField.setBounds(135, 227, 25, 25);
        sizeInputField.setHorizontalAlignment(JTextField.CENTER);
        sizeInputField.setBorder(null);

        JButton deductButton = new JButton();
        deductButton.setText("-");
        deductButton.setBackground(Constants.GREEN_COLOR);
        deductButton.setFont(Constants.getRegularFont(12));
        deductButton.setForeground(Constants.LIGHT_CREAM_COLOR);
        deductButton.setFocusPainted(false);
        deductButton.setBounds(116, 230, 12, 20);
        deductButton.setBorder(null);
        deductButton.setOpaque(true);
        // deductButton.setContentAreaFilled(false);

        deductButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                deductButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });

        deductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int inputValue = Integer.parseInt(sizeInputField.getText());
                int newValue = Math.max(inputValue - 1, 4);

                sizeInputField.setText(String.valueOf(newValue));
                Quiz.setQuestionSize(newValue);
            }
        });

        JButton addButton = new JButton();
        addButton.setText("+");
        addButton.setBackground(Constants.GREEN_COLOR);
        addButton.setFont(Constants.getRegularFont(12));
        addButton.setForeground(Constants.LIGHT_CREAM_COLOR);
        addButton.setFocusPainted(false);
        addButton.setBounds(163, 230, 12, 20);
        addButton.setBorder(null);
        addButton.setOpaque(true);
        // addButton.setContentAreaFilled(false);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int inputValue = Integer.parseInt(sizeInputField.getText());
                int newValue = Math.min(inputValue + 1, 8);

                sizeInputField.setText(String.valueOf(newValue));
                Quiz.setQuestionSize(newValue);
            }
        });

        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });

        JButton resetSettingButton = new JButton();
        resetSettingButton.setText("RESET SETTING");
        resetSettingButton.setFont(Constants.getBoldFont(12));
        resetSettingButton.setBackground(Constants.GREEN_COLOR);
        resetSettingButton.setForeground(Constants.CREAM_COLOR);
        resetSettingButton.setFocusPainted(false);
        resetSettingButton.setBorder(null);
        resetSettingButton.setBounds(120, 359, 120, 20);
        // deletePlayerButton.setContentAreaFilled(false);

        resetSettingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                resetSettingButton.setForeground(Constants.LIGHT_GREEN_COLOR);
                resetSettingButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                resetSettingButton.setForeground(Constants.LIGHT_CREAM_COLOR);
            }
        });

        resetSettingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetSettings();
            }
        });

        add(settingTextLabel);
        add(horizontalSeparator);
        add(availableCategoriesText);
        add(availableCategoriesIMG);

        add(generalCategoryCBox);
        add(moviesCategoryCBox);
        add(mathCategoryCBox);
        add(musicCategoryCBox);

        add(horizontalSeparator2);
        add(questionSizeLabel);
        add(questionSizeImage);
        add(deductButton);
        add(sizeInputField);
        add(addButton);
        add(resetSettingButton);

    }

}
