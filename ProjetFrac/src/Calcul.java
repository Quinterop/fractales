import org.apache.commons.math3.complex.Complex;
import java.math.*;

public class Calcul {
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
		return Math.sqrt(z.getReal() * z.getReal() + z.getImaginary() * z.getImaginary());
	}

	int divergenceIndex(Complex z0) {
		int ite = 0;
		Complex zn = z0;
		// sortie de boucle si divergence
		while (ite < maxIter && (Math.abs(module(zn))) <= radius) {
			while (ite < maxIter && (module(zn)) <= radius) {
				zn = zn.multiply(zn);
				zn = zn.add(comp);

				ite++;
			}

		}
		//System.out.println(ite);
		return ite;
	}
	Complex[][] getGraph(){
		return graph; //renvoyer une copie ?
	}

	public int getX() {
		return graph.length;
	}

	public int getY() {
		return graph[0].length;
	}
	
	public double getStep() {
		return step;
	}

	public int getMaxIter() {
		return maxIter;
	}
}
