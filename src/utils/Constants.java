package utils;

import java.awt.Color;
import java.awt.Font;

public class Constants{
    // file source constants
    public static String BACKGROUND_IMAGE_SRC = "src\\images\\background.jpg";
    public static String LOGO_SRC = "src\\images\\logo.png";
    public static String CATEGORY_IMG_SRC = "src\\images\\icons\\category.png";
    public static String LIST_IMG_SRC = "src\\images\\icons\\list.png";
    public static String UNCHECKED_ICON = "src\\images\\icons\\unchecked.png";
    public static String CHECKED_ICON = "src\\images\\icons\\checkbox.png";
    
    // color constants
    public static Color LIGHT_CREAM_COLOR = new Color(249, 247, 232);
    public static Color CREAM_COLOR = new Color(243, 239, 212);
    public static Color LIGHT_GREEN_COLOR = new Color(179, 190, 98);
    public static Color GREEN_COLOR = new Color(48, 84, 80);
    public static Color GREEN_COLOR_2 = new Color(82, 121, 117);
    public static Color RED_COLOR = new Color(255, 111, 111);
    public static Color RED_COLOR_2 = new Color(255, 169, 111);


    public static Font getRegularFont(int size){
        return new Font("Poppins SemiBold", Font.PLAIN, size);
    }

    public static Font getBoldFont(int size){
        return new Font("Poppins ExtraBold", Font.BOLD, size);
    }

    public static Font getArialFont(int size){
        return new Font("Arial", Font.BOLD, size);
    }

    // public static Font getLighFont(int size){
    //     return new Font("Rowdies-Light", Font.PLAIN, size);
    // }

    // public static Font getExtraBoldFont(int size){
    //     return new Font("Rowdies-Light", Font.PLAIN, size);
    // }


}