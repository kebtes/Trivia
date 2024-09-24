package utils;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;
import java.awt.Image;

public class ImageLoadandResize {
    public static ImageIcon loadAndResizeIcon(String IMG, int w, int h) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(IMG));
            Image image = bufferedImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);
            
            return new ImageIcon(image);

        } catch (IOException e) {
            e.printStackTrace();
            return null; 
        }
    }
}