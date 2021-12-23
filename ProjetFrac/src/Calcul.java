import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.util.MathArrays.Function;
import java.math.*;
import java.lang.Math;

public class Calcul {
	public Complex comp;
	public int[] polynome;
	public int[][] colors;
	private final int maxIter;
	private final double radius;
	public Complex[][] graph;
	public Complex topLeftComplex;
	public double step;

	public static class Builder {
		//valeurs obligatoires : tabsizes et step ou tabsizes et plansize ou plansize et step
		public Complex comp;
		public int[] polynome = {0,0,1};
		private int maxIter;
		private double radius;
		public Complex topLeftComplex;
		public double step;
		public int[] tabsizes = new int[2];
		public double plansize;//TODO : mettre 2 coords
		
		public Builder() {
			this.maxIter = 1000;
			this.comp = new Complex(-0.7269, 0.1889);
			this.step = 0.0;
			this.plansize=0.0;
			this.radius = 2.0;
		}
		public Builder size(int x,int y) {
			this.tabsizes[0]= x;
			this.tabsizes[1]= y;
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
		public Calcul build() {
			if (tabsizes==null) {
				tabsizes[0]= (int) (plansize/step);
				tabsizes[1] = (int) (plansize/step);//TODO: utiliser l'autre val de plansize	
			}
			if (plansize==0.0) {
				plansize = step*tabsizes[0];
			}
			if (step==0.0){
				step=plansize/tabsizes[0];
			}
			if(topLeftComplex==null)
				this.topLeftComplex = new Complex(-plansize, plansize);
			
			return new Calcul(this);
		}
	}
	public Calcul(Builder b) {
		comp=b.comp;
		polynome=b.polynome;
		maxIter=b.maxIter;
		radius=b.radius;
		topLeftComplex=b.topLeftComplex;
		step=b.step;
		graph = new Complex[b.tabsizes[0]][b.tabsizes[1]];
		colors= new int[b.tabsizes[0]][b.tabsizes[1]];
	}

	/*public Calcul(int x, int y, double h, Complex c) {
		this.colors = new int[x][y];
		this.graph = new Complex[x][y];
		this.step = h;
		this.topLeftComplex = c;
		this.radius = 2.0;
	}
*/
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
		System.out.println(ite);

		return ite;

	}

	Complex[][] fill() {
		for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < colors[0].length; j++) {
				graph[i][j] = new Complex(topLeftComplex.getReal() + j * step, topLeftComplex.getImaginary() - i * step);			}
		}
		return graph;
	}

	int[][] calculate() {
		for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < colors[0].length; j++) {
				colors[i][j] = divergenceIndex(graph[i][j]);
			}
		}
		return colors;
	}
}
