import org.apache.commons.math3.complex.Complex;
import java.math.*;

public class Calcul {
	private final Complex comp;
	private final int[] polynome;
	private final int maxIter;
	private final double radius;
	private final Complex[][] graph;
	private final Complex graphOrigin;
	private final double step;
	private final double planSize;

	public static class Builder {
		
		private double planSize;
		private Complex comp;
		private int[] polynome;
		private int maxIter;
		private double radius;
		private Complex[][] graph;
		private Complex graphOrigin;
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
		public Builder plan(double x) {
			this.planSize=x;
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
		public Builder graphOrigin(Complex x) {
			this.graphOrigin=x;
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
					graph[i][j] = new Complex(graphOrigin.getReal() - planSize+ j * step,
							graphOrigin.getImaginary() +planSize - i * step);
				}
			}
			System.out.println(graph[0][0]);
			return graph;
		}
		
		public Calcul build() {
			//TODO : Check illegal argument
			if (tabX==0.0) {
				tabX= (int) (planSize/step)*2;
				tabY = (int) (planSize/step)*2;
				//System.out.println(tabX);
				//TODO: utiliser l'autre val de planSize	
			}
			if (planSize==0.0) {
				planSize = step*tabX/2;
				//System.out.println("planSize: " + planSize);
			}
			if (step==0.0){
				step=planSize/tabX*2;
				//System.out.println("step: " + step);
			}
			if(graphOrigin==null)
				this.graphOrigin = new Complex(0,0);
			graph = new Complex[tabX][tabY];
			fillGraph();
			System.out.println(graphOrigin);
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
		graphOrigin = builder.graphOrigin;
		step = builder.step;
		graph = builder.graph;
		planSize = builder.planSize;
		// colors= new int[builder.tabX][builder.tabY];
	}

	/*
	 * public Calcul(int x, int y, double h, Complex c) { this.colors = new
	 * int[x][y]; this.graph = new Complex[x][y]; this.step = h; this.graphOrigin
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
	//RENVOYER COPIES ?
	Complex[][] getGraph(){
		return graph; //renvoyer une copie ?
	}

	public int getX() {
		return graph.length;
	}

	public int getY() {
		return graph[0].length;
	}

public double getPlan() {
		return planSize;
	}
	
	public int getSize() {
		return graph.length;
	}
	
	public double getStep() {
		return step;
	}

	public int getMaxIter() {
		return maxIter;
	}
}
