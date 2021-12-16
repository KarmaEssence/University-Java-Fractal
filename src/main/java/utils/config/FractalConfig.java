package utils.config;

import fractal.Fractal;
import org.apache.commons.math3.complex.Complex;
import utils.complex.ComplexRectangle;
import utils.json.JsonWriter;
import utils.json.annotation.JsonSerializable;

@JsonSerializable
public class FractalConfig {
    
    @JsonSerializable
    public final Double constantX;

    @JsonSerializable
    public final Double constantY;

    @JsonSerializable
    public final Double pointAX;

    @JsonSerializable
    public final Double pointAY;

    @JsonSerializable
    public final Double pointBX;

    @JsonSerializable
    public final Double pointBY;

    @JsonSerializable
    public final Double discretizationStape;

    public static class Builder {
        private final Double constantX;
        private final Double constantY;
        private final Double pointAX;
        private final Double pointAY;
        private final Double pointBX;
        private final Double pointBY;
        private final Double discretizationStape;

        public Builder(Complex c, ComplexRectangle rectangle, Double discretizationStape){

            this.constantX = c.getReal();
            this.constantY = c.getImaginary();
            this.pointAX = rectangle.getPointA().getReal();
            this.pointAY = rectangle.getPointA().getImaginary();
            this.pointBX = rectangle.getPointB().getReal();
            this.pointBY = rectangle.getPointB().getImaginary();
            this.discretizationStape = discretizationStape;
        }

        public Builder(Fractal fractal){
            this(fractal.getConstant(), fractal.getComplexRectangle(),
                    fractal.getDiscretizationStape());
        }

        public FractalConfig build() {
            return new FractalConfig(this);
        }
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
    }
    
}
