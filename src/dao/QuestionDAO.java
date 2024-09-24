package dao;

import models.Category;
import models.Questions;

public interface QuestionDAO {
    Questions getQuestionByCategory(Category category);
}
