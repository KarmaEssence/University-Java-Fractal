package Launcher;


import org.apache.commons.math3.complex.Complex;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Launcher {

    public static void main(String[] args){

        Complex c = new Complex(-(0.7269), 0.1889);
        
        var img= new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        int r = 64; int g = 224; int b = 208; //turquoise
        int col = (r << 16) | (g << 8) | b;
        img.setRGB((int) c.getReal(),(int) c.getImaginary(),col);
        File f = new File("./MyFile.png");
        try{
            ImageIO.write(img, "PNG", f);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
