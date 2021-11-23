import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class JuliaTest {
	private double coodX1 = -2.1, coodX2 = 0.6, coodY1 = -1.2, coodY2 = 1.2;
	private int tailleImageMax = 300;
	private int iteration_max = 500;
	private double tailleImageX = (coodX2-coodX1)*tailleImageMax;
	private double tailleImageY = (coodY2-coodY1)*tailleImageMax;

	public JuliaTest(){}
	
	public void testJulia(double constante1, double constante2){
		var img = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
		int r = 64;
		int g = 64;
		int b = 208;
		int col = (r<<16) | (g<<8) | b;
		for (int i = 0; i < tailleImageX; i++){
			for (int j = 0; j < tailleImageY; j++){
				double compteur = 0;
				double tailleImageConstante1 = i/tailleImageMax + coodX1;
				double tailleImageConstante2 = j/tailleImageMax + coodY1;
				do{
					double tmp = tailleImageConstante1;
					tailleImageConstante1 = tailleImageConstante1*tailleImageConstante1 - tailleImageConstante2*tailleImageConstante2 + constante1;
					tailleImageConstante2 = 2*tailleImageConstante2*tmp + constante2;
					compteur=compteur+1;
				}while(tailleImageConstante1*tailleImageConstante1 + tailleImageConstante2*tailleImageConstante2 < 4 && compteur < iteration_max);
				if(compteur == iteration_max){
					img.setRGB(i,j, col);
				}
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
