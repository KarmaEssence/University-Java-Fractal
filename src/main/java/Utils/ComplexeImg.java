package Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ComplexeImg {
    private BufferedImage image;
    private Integer discretizationStape;
    private Integer imageLength;
    private Integer imageHeight;

    public ComplexeImg(ComplexeRectangle complexeRectangle, Integer discretizationStape){
        this.discretizationStape = discretizationStape;

        //Calcul de la longueur et de la largeur du rectangle complexe :
        double lengthComplex = Math.abs(complexeRectangle.getPointA().getReal() - complexeRectangle.getPointB().getReal());
        double widthComplex = Math.abs(complexeRectangle.getPointA().getImaginary() - complexeRectangle.getPointB().getImaginary());

        //Calcul de la longueur et de la largeur pour les dimensions de l'image :
        imageLength = (int)(lengthComplex / discretizationStape);
        imageHeight = (int)(widthComplex / discretizationStape);
        image = new BufferedImage(imageLength, imageHeight, BufferedImage.TYPE_INT_RGB);
    }

    public BufferedImage getImage() {
        return image;
    }

    public Integer getDiscretizationStape() {
        return discretizationStape;
    }

    public Integer getImageLength() {
        return imageLength;
    }

    public Integer getImageHeight() {
        return imageHeight;
    }

    public void saveFractal(){
        if(image == null) return;

        File file = new File("./MyFile.png");
        try{
            ImageIO.write(image, "PNG", file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
