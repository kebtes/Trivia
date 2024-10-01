package models;

import java.util.List;

public class MultipleAnswerQuestion extends Questions {
    private List<String> questionChoices;
    private List<String> correctAnswers;

    public MultipleAnswerQuestion(String questionText, String questionCategory, String questionType,
            List<String> questionChoices, List<String> correctAnswers) {
        super(questionText, questionCategory, questionType);
        this.correctAnswers = correctAnswers;
        this.questionChoices = questionChoices;
    }

    public List<String> getCorrectAnswer() {
        return correctAnswers;
    }

    public List<String> getQuestionChoices() {
        return questionChoices;
    }

    public boolean validateUserAnswer(List<String> userChoice) {
        String userAnswer = userChoice.get(0).toLowerCase().strip();
        String correctAnswer = correctAnswers.get(0).toLowerCase().strip();

        return userAnswer.equals(correctAnswer);
    }

}
