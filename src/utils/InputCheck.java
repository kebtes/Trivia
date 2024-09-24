package utils;

public class InputCheck {
    public static boolean validateInputName(String name){
        if (name.length() <= 3){
            return false;
        }

        for (int i = 0; i < name.length(); i++){
            char ch  = name.charAt(i);
            
            if (!Character.isLetter(ch) && ch != ' '){
                return false;
            }
        }
        
        return true;
    }
}
