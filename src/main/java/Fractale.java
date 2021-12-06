import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Fractale {
    private boolean test;
    private int iteration;
    private int tailleImageX;
    private int tailleImageY;
    private double x, y;
    JuliaTest julia = null;

    public Fractale(double x, double y, int tailleImageX, int tailleImageY, int iteration){
        this.x = x;
        this.y = y;
        this.tailleImageX = tailleImageX;
        this.tailleImageY = tailleImageY;
        this.iteration = iteration;
        julia = new JuliaTest(x, y);
    }

    public void afficheTest(){
        var img = new BufferedImage(tailleImageX, tailleImageY, BufferedImage.TYPE_INT_RGB);
        int r = 64;
        int g = 64;
        int b = 208;
        int col = (r<<16) | (g<<8) | b;
        for (int i = 0; i < tailleImageX; i++){
            for (int j = 0; j < tailleImageY; j++){
                julia.complex(i,j);
                this.test = julia.verifCood(julia.getX2(), julia.getY2(), iteration);
                if(test) img.setRGB(i,j, col);
            }
        }
        File f = new File("./ABCDE.png");
        try{
            ImageIO.write(img, "PNG", f);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
