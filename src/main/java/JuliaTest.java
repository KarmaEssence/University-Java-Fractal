public class JuliaTest {
	private double x1, x2, y1, y2, currentX, currentY;
	private int iteration;
	protected boolean test;

	public JuliaTest(double x, double y){
		x1 = x;
		y1 = y;
	}

	public void complex(double i, double j){
		currentX = (((double)i*2)/800)-1;
		currentY = (((double)j*2)/800)-1;
	}

	public boolean verifCood(double x, double y, int iteration){
		int compteur = 0;
		x = x1;
		y = y1;
		while(iteration-- > 0){
			double newX = (currentX * currentX) - (currentY * currentY) + x;
			double newY = (2 * currentX * currentY) + y;
			currentX = newX;
			currentY = newY;
			compteur++;
			if(currentX * currentX + currentX * currentY > 4 ) return test = false;
		}
		return true;
	}

	public double getX2(){
		return x2;
	}

	public double getY2(){
		return y2;
	}

	public int getIteration(){
		return iteration;
	}
	
	/*public void testJulia(double constante1, double constante2){
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
	}*/

}
