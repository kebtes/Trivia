package models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import utils.PreferencesManager;

import dao.impl.QuestionDAOImpl;

public class Quiz{
    QuestionDAOImpl questionDAO;
    PreferencesManager preference = new PreferencesManager();

    @SuppressWarnings("unused")
    private LocalDateTime quizDate = LocalDateTime.now();
    
    private List<Questions> quizQuestions = new ArrayList<>();
    private List<Player> quizPlayers = new ArrayList<>();
    private static int questionSizePerPerson;
    private static List<Category> availableCategories = new ArrayList<>();


    private int questionSizePerCategory;
    
    private void loadPreferences() {
        if (preference.getMovieCategory()) {
            availableCategories.add(new Category("Movie"));
        } 
        if (preference.getMusicCategory()) {
            availableCategories.add(new Category("Music"));
        } 
        if (preference.getGeneralCategory()) {
            availableCategories.add(new Category("General"));
        } 
        if (preference.getMathCategory()) {
            availableCategories.add(new Category("Math"));
        }

        questionSizePerPerson = preference.getQuestionSize();
    }
    
    public Quiz(List<Player> quizPlayers){
        this.quizPlayers = quizPlayers;
        
        // load categories from preferences
        loadPreferences();

        questionSizePerCategory = (questionSizePerPerson * quizPlayers.size()) / availableCategories.size();

        int categoryIdx = 0;
        for (int i = 0; i < questionSizePerPerson; i++){
            // System.out.println(questionSizePerPerson);

            for (int j = 0; j < questionSizePerCategory; j++) {
                questionDAO = new QuestionDAOImpl();
                Questions question = questionDAO.getQuestionByCategory(availableCategories.get(categoryIdx));
                
                // check if quesiton not null
                // check if new question not in list
                if (question != null && !quizQuestions.contains(question)) {
                    // System.out.println(question.getQuestionText());
                    quizQuestions.add(question);
                }

            }

            categoryIdx = (categoryIdx + 1) % availableCategories.size();
        }
    }

    public static void setQuestionSize(int newQuestionSize){
        questionSizePerPerson = newQuestionSize;
    }
    
    public static void removeFromCategory(Category category){
        availableCategories.remove(category);
    }

    public static void addToCategory(Category category){
        availableCategories.add(category);
    }

    public List<Questions> getQuizQuestions(){
        return quizQuestions;
    }

    public List<Player> getQuizPlayers(){
        return quizPlayers;
    }

}
