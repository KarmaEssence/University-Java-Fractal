package utils.config;

import utils.complex.ComplexRectangle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ImageConfig {
    private BufferedImage image;
    private Double discretizationStape;
    private Integer imageLength;
    private Integer imageHeight;

    public ImageConfig(ComplexRectangle complexRectangle, Double discretizationStape){
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
        System.out.println("Generating image ...");
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

    /**
     * Verifie que le repertoire existe.
     */
    /*private static void directoryExist(){
        String path = System.getProperty("user.dir");
        path+= "/data/fractal_image";
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdir();
        }
    }

    private String giveNewFilename(){
        File dir = new File("./data/fractal_image");
        File[] files = dir.listFiles();
        int max = 0;
        for(int i = 0; i < Objects.requireNonNull(files).length; i++){
            String temp = files[i].getName().split("\\.")[0];
            int valueFile = Integer.parseInt(temp);
            if(valueFile > max)
                max = valueFile;
        }
        return String.valueOf(max + 1);
    }*/
}
