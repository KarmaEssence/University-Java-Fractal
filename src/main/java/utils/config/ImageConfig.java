package utils.config;

import utils.complex.ComplexRectangle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageConfig {
    private final BufferedImage image;
    private final Integer imageLength;
    private final Integer imageHeight;

    public ImageConfig(ComplexRectangle complexRectangle, Double discretizationStape){

        //Calcul de la longueur et de la largeur du rectangle complexe :
        double lengthComplex = Math.abs(complexRectangle.getPointA().getReal() - complexRectangle.getPointB().getReal());
        double widthComplex = Math.abs(complexRectangle.getPointA().getImaginary() - complexRectangle.getPointB().getImaginary());

        //Calcul de la longueur et de la largeur pour les dimensions de l'image :
        imageLength = (int)(lengthComplex / discretizationStape);
        imageHeight = (int)(widthComplex / discretizationStape);
        image = new BufferedImage(imageLength, imageHeight, BufferedImage.TYPE_INT_RGB);
    }

    /**
     * Recupere un BufferedImage
     * @return un BufferedImage
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Recupere la largeur de l'image
     * @return la largeur de l'image
     */
    public Integer getImageLength() {
        return imageLength;
    }

    /**
     * Recupere la hauteur de l'image
     * @return la hauteur de l'image
     */
    public Integer getImageHeight() {
        return imageHeight;
    }

    /**
     * Sauvegarde la fractal
     */
    public void saveFractal(){
        if(image == null) return;
        System.out.println("Generating image ...");
        FileData.directoryExist("/data");
        FileData.directoryExist("/data/fractal_config");
        FileData.directoryExist("/data/fractal_image");
        File file = new File("./data/fractal_image/" +
                FileData.giveNewFilename("./data/fractal_image") + ".png");
        try{
            ImageIO.write(image, "PNG", file);
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("The image has been generated");

    }
}
