package utils.complex;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ComplexImg {
    private BufferedImage image;
    private Double discretizationStape;
    private Integer imageLength;
    private Integer imageHeight;

    public ComplexImg(ComplexRectangle complexRectangle, Double discretizationStape){
        this.discretizationStape = discretizationStape;

        //Calcul de la longueur et de la largeur du rectangle complexe :
        double lengthComplex = Math.abs(complexRectangle.getPointA().getReal() - complexRectangle.getPointB().getReal());
        double widthComplex = Math.abs(complexRectangle.getPointA().getImaginary() - complexRectangle.getPointB().getImaginary());

        //Calcul de la longueur et de la largeur pour les dimensions de l'image :
        imageLength = (int)(lengthComplex / discretizationStape);
        imageHeight = (int)(widthComplex / discretizationStape);
        image = new BufferedImage(imageLength, imageHeight, BufferedImage.TYPE_INT_RGB);
    }

    public BufferedImage getImage() {
        return image;
    }

    public Double getDiscretizationStape() {
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
