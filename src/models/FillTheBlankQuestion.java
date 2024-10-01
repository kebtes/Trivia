package models;

import java.util.List;
import java.util.Arrays;

public class FillTheBlankQuestion extends Questions {
    private String correctAnswer;

    public FillTheBlankQuestion(String questionText, String questionCategory, String questionType,
            String correctAnswer) {
        super(questionText, questionCategory, questionType);
        this.correctAnswer = correctAnswer;
    }

    public List<String> getCorrectAnswer() {
        return Arrays.asList(correctAnswer);
    }

    public boolean validateUserAnswer(List<String> userChoice) {
        String userAnswer = userChoice.get(0).toLowerCase().strip();
        String correctAnswer = this.correctAnswer.toLowerCase().strip();

        return userAnswer.equals(correctAnswer);
    }

}
