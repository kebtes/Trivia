package models;

import java.util.List;
import java.util.Objects;

public abstract class Questions {
    private String questionText;
    private String questionCategory;
    private String questionType;
    
    public Questions(String questionText, String questionCategory, String questionType){
        this.questionText = questionText;
        this.questionCategory = questionCategory;
        this.questionType = questionType;
    }

    public String getQuestionText(){
        return questionText;
    }

    public String getQuestionCategory(){
        return questionCategory;
    }

    public String getQuestionAskType(){
        return questionType;
    }

    public abstract List<String> getCorrectAnswer();
    public abstract boolean validateUserAnswer(List<String> userChoice);

    @Override
    public boolean equals(Object obj) {
        // if the object is literally this one
        if (this == obj) return true; 

        // if the object we comparing this instance with is either null,
        // or isn't from the same class this one is from
        if (obj == null || getClass() != obj.getClass()) return false;
        Questions questions = (Questions) obj;
        return Objects.equals(questionText, questions.getQuestionText());
    }

    @Override
    // this hashes the object, specifically by its name
    // so that we ensure equal objects have the same hash code
    public int hashCode() {
        return Objects.hash(questionText);
    }
}
