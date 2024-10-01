package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.QuestionDAO;
import models.Category;
import models.FillTheBlankQuestion;
import models.MultipleAnswerQuestion;
import models.Questions;
import utils.DBConnection;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class QuestionDAOImpl implements QuestionDAO {
    @Override
    public Questions getQuestionByCategory(Category category) {
        List<Questions> questions = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String category_title = category.getCategoryTitle();
        String QUERY = "SELECT TOP 1 * FROM Questions WHERE category = ? ORDER BY NEWID()";

        try {
            Connection connection = DBConnection.getConnection();

            statement = connection.prepareStatement(QUERY);

            statement.setString(1, category_title);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // int questionId = resultSet.getInt("question_id");
                String questionText = resultSet.getString("question_text");
                String correctAnswer = resultSet.getString("correct_answer");
                String cate_title = resultSet.getString("category");
                String questionAskType = resultSet.getString("question_ask_type");
                String questionChoices = resultSet.getString("question_choices");

                Questions question = null;
                if (questionAskType.equals("MCQ")) {
                    List<String> questionChoicesToPass = Arrays.asList(questionChoices.split(","));
                    List<String> correctAnswerMCQ = Arrays.asList(correctAnswer.split(","));

                    question = new MultipleAnswerQuestion(
                            questionText,
                            cate_title,
                            questionAskType,
                            questionChoicesToPass,
                            correctAnswerMCQ);

                } else if (questionAskType.equals("FBQ")) {
                    question = new FillTheBlankQuestion(
                            questionText,
                            cate_title,
                            questionAskType,
                            correctAnswer);
                }

                questions.add(question);
            }

            // returns a question at the random index
            if (!questions.isEmpty()) {
                return questions.get(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;

    }
}
