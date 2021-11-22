import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class JuliaTest {
	private int coodX1, coodX2, coodY1, coodY2;
	private int tailleImageMax = 100;
	private int iteration_max = 100;
	private int tailleImageX = (coodX2-coodX1)*tailleImageMax;
	private int tailleImageY = (coodY2-coodY1)*tailleImageMax;

	public JuliaTest(){}
	
	public void testJulia(int constante1, int constante2){
		int compteur = 0;
		var img = new BufferedImage(tailleImageX, tailleImageY, BufferedImage.TYPE_INT_RGB);	
		int r = 64;
		int g = 64;
		int b = 208;
		int col = (r<<16) | (g<<8) | b;
		for (int i = 0; i < tailleImageX; i++){
			for (int j = 0; j < tailleImageY; j++){
				int tailleImageConstante1 = i/tailleImageX + coodX1;
				int tailleImageConstante2 = j/tailleImageY + coodX2;
				if((tailleImageConstante1*tailleImageConstante1 + tailleImageConstante2*tailleImageConstante2) < 4 && compteur < iteration_max){
					if(compteur == iteration_max){
						img.setRGB(i,j, col);
					}
					int tmp = tailleImageConstante1;
					tailleImageConstante1 = tailleImageConstante1*tailleImageConstante1 - tailleImageConstante2*tailleImageConstante2 + constante1;
					tailleImageConstante2 = 2*tailleImageConstante2*tmp + constante2;
					compteur++;
				}
			}
		}
		File f = new File("Test.pnj");

		try{
			ImageIO.write(img, "PNG", f);
		}catch (IOException e){
			e.printStackTrace();
		}

	}
}
