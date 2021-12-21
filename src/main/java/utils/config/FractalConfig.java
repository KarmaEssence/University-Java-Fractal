package utils.config;

import fractal.Fractal;
import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;
import utils.json.JsonWriter;
import utils.json.annotation.JsonSerializable;

/**
 * Les attributs de la classe sont
 * public pour que le JsonReader
 * puisse y ecrire les donnees du
 * fichier de configuration choisi
 */
@JsonSerializable
public class FractalConfig {

    @JsonSerializable
    public String setChoice;

    @JsonSerializable
    public String colorChoice;

    @JsonSerializable
    public Double constantX;

    @JsonSerializable
    public Double constantY;

    @JsonSerializable
    public Double pointAX;

    @JsonSerializable
    public Double pointAY;

    @JsonSerializable
    public Double pointBX;

    @JsonSerializable
    public Double pointBY;

    @JsonSerializable
    public Double discretizationStape;

    /**
     * On utilise un builder
     * pour creer et sauvegarder une image
     * et sa configuration
     */
    public static class Builder {
        private final String setChoice;
        private final String colorChoice;
        private final Double constantX;
        private final Double constantY;
        private final Double pointAX;
        private final Double pointAY;
        private final Double pointBX;
        private final Double pointBY;
        private final Double discretizationStape;

        public Builder(String setChoice, String colorChoice, Complex c,
                       ComplexRectangle rectangle, Double discretizationStape){

            this.constantX = c.getReal();
            this.constantY = c.getImaginary();
            this.pointAX = rectangle.getPointA().getReal();
            this.pointAY = rectangle.getPointA().getImaginary();
            this.pointBX = rectangle.getPointB().getReal();
            this.pointBY = rectangle.getPointB().getImaginary();
            this.discretizationStape = discretizationStape;
            this.setChoice = setChoice;
            this.colorChoice = colorChoice;
        }

        public Builder(Fractal fractal){
            this(fractal.getSetChoice(), fractal.getColorChoice(),
                    fractal.getConstant(), fractal.getComplexRectangle(),
                    fractal.getDiscretizationStape());
        }

        /**
         * Construit une configuration
         * @return une configuration
         */
        public FractalConfig build() {
            return new FractalConfig(this);
        }

        /**
         * Construit et sauvegarde la configuration
         */
        public void buildAndSave(){
            FractalConfig fractalConfig = build();
            FileData.directoryExist("/data/fractal_config");
            JsonWriter jsonWriter = new JsonWriter("./data/fractal_config/" +
                    FileData.giveNewFilename("./data/fractal_config"));
            jsonWriter.serializeObject(fractalConfig);
        }

    }

    private FractalConfig(Builder builder){
        this.constantX = builder.constantX;
        this.constantY = builder.constantY;
        this.pointAX = builder.pointAX;
        this.pointAY = builder.pointAY;
        this.pointBX = builder.pointBX;
        this.pointBY = builder.pointBY;
        this.discretizationStape = builder.discretizationStape;
        this.setChoice = builder.setChoice;
        this.colorChoice = builder.colorChoice;
    }

    /**
     * Notre JsonReader a besoin d un
     * constructeur vide pour construire l object
     */
    public FractalConfig(){}
    
}
