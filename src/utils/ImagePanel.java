package utils;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    private Image image;

    // constructor loads the image and loads the image using ImageIO
    public ImagePanel(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            // Draw the image on the panel, scaled to fit the size of the panel
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
