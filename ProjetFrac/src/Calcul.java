import org.apache.commons.math3.complex.Complex;
import java.math.*;

public class Calcul {
<<<<<<< ProjetFrac/src/Calcul.java
	private final Complex comp;
	private final int[] polynome;
	private final int maxIter;
	private final double radius;
	private final Complex[][] graph;
	private final Complex topLeftComplex;
	private final double step;

	public static class Builder {
		
		private double planSize;
		private Complex comp;
		private int[] polynome;
		private int maxIter;
		private double radius;
		private Complex[][] graph;
		private Complex topLeftComplex;
		private double step;
		private int tabX;
		private int tabY;

		public Builder() {
			this.maxIter = 1000;
			this.comp = new Complex(-0.7269, 0.1889);
			this.step = 0.0;
			this.planSize = 0.0;
			this.radius = 2.0;
		}
		public Builder size(int x,int y) {
			this.tabX= x;
			this.tabY= y;
			return this;
		}
		public Builder step(double x) {
			this.step=x;
			return this;
		}
		public Builder maxIter(int x) {
			this.maxIter=x;
			return this;
		}
		public Builder topLeftComplex(Complex x) {
			this.topLeftComplex=x;
			return this;
		}
		public Builder comp(Complex x) {
			this.comp=x;
			return this;
		}
		public Builder polynome(int[] x) {
			this.polynome=x;
			return this;
		}
		public Builder radius(double x) {
			this.radius=x;
			return this;
		}
		Complex[][] fillGraph() {
			for (int i = 0; i < tabX; i++) {
				for (int j = 0; j < tabY; j++) {
					graph[i][j] = new Complex(topLeftComplex.getReal() + j * step,
							topLeftComplex.getImaginary() - i * step);
				}
			}
			return graph;
		}
		public Calcul build() {
			//TODO : Check illegal argument
			if (tabX==0.0) {
				tabX= (int) (planSize/step);
				tabY = (int) (planSize/step);
				//TODO: utiliser l'autre val de planSize	
			}
			if (planSize==0.0) {
				planSize = step*tabX/2;
			}
			if (step==0.0){
				step=planSize/tabX;
			}
			if(topLeftComplex==null)
				this.topLeftComplex = new Complex(-planSize, planSize);
			graph = new Complex[tabX][tabY];
			fillGraph();
			System.out.println(topLeftComplex);
			return new Calcul(this);
		}
	}
	// valeurs obligatoires : tabsizes et step ou tabsizes et planSize ou planSize
	// et step

	public Calcul(Builder builder) {
		comp = builder.comp;
		polynome = builder.polynome;
		maxIter = builder.maxIter;
		radius = builder.radius;
		topLeftComplex = builder.topLeftComplex;
		step = builder.step;
		graph = builder.graph;
		// colors= new int[builder.tabX][builder.tabY];
	}

	/*
	 * public Calcul(int x, int y, double h, Complex c) { this.colors = new
	 * int[x][y]; this.graph = new Complex[x][y]; this.step = h; this.topLeftComplex
	 * = c; this.radius = 2.0; }
	 */

	static double module(Complex z) {
=======
	public Complex comp;
	public int[] polynom;
	public Function fun;
	public int[][] colors;
	private final int matIter = 1000;
	private final double radius = 2.0;
	public Complex[][] graph;
	public Complex topLeftComplex;
	public double step;

	public Calcul(int x, int y, double h, Complex c) {
		colors = new int[x][y];
		graph = new Complex[x][y];
		step = h;
		topLeftComplex = c;
	}

	public void setSize(int x, int y) {
		colors = new int[x][y];
		graph = new Complex[x][y];
	}

	public void setStep(int h) {
		step = h;
	}

	public void setTopLeftComplex(Complex c) {
		topLeftComplex = c;
	}

	double module(Complex z) {
>>>>>>> ProjetFrac/src/Calcul.java
		return Math.sqrt(z.getReal() * z.getReal() + z.getImaginary() * z.getImaginary());
	}

	int divergenceIndex(Complex z0) {
		//boolean test = true;

		int ite = 0;
		Complex zn = z0;
		// sortie de boucle si divergence
<<<<<<< ProjetFrac/src/Calcul.java
		while (ite < maxIter && (Math.abs(module(zn))) <= radius) {
			while (ite < maxIter && (module(zn)) <= radius) {
				zn = zn.multiply(zn);
				zn = zn.add(comp);

				ite++;
			}

		}
		//System.out.println(ite);
=======
		while (ite < matIter && (module(zn)) <= radius) {
			for (int i = 0; i < polynom.length; i++) {
				
				//Complex zx = zn.pow(i);
				//zx=zx.multiply(polynom[i]);
				//zn = zx.add(zn);
			}
			zn = zn.pow(2);	
			zn = zn.add(comp);
			
			
			//if (test) {
			//	System.out.println(zn);
			//	System.out.println(zn2);
				//ca devrait etre pareil
			//	test = false;
		//	}
			

			
			
			ite++;
		}
		// System.out.println(ite);
>>>>>>> ProjetFrac/src/Calcul.java
		return ite;
	}
	Complex[][] getGraph(){
		return graph; //renvoyer une copie ?
	}

<<<<<<< ProjetFrac/src/Calcul.java
	public int getX() {
		return graph.length;
=======
	Complex[][] fill() {
		for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < colors[0].length; j++) {
				graph[i][j] = new Complex(topLeftComplex.getReal() + j * step,
						topLeftComplex.getImaginary() - i * step);
			}
		}
		// System.out.println(graph[100][100].getReal()+"
		// "+graph[100][100].getImaginary());
		return graph;
>>>>>>> ProjetFrac/src/Calcul.java
	}

	public int getY() {
		return graph[0].length;
	}

}
