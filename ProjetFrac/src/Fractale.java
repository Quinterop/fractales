
import org.apache.commons.math3.complex.Complex;
import java.math.*;

public class Fractale {
	private final Complex comp;
	private final int[] polynome;
	private final int maxIter;
	private final double radius;
	private final Complex[][] graph;
	private final Complex graphOrigin;
	private final double step;
	private final double planSize;
	private final int col;

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
		private int col;

		public Builder() { //champs obligatoires
			this.maxIter = 100000;
			this.comp = new Complex(-0.7269, 0.1889);
			this.step = 0.0;
			this.planSize = 0.0;
			this.radius = 2.0;
			this.col = 1;
		}
//methodes facultatives
		public Builder size(int x, int y) {
			this.tabX = x;
			this.tabY = y;
			return this;
		}
		

		public Builder plan(double x) {
			this.planSize = x;
			return this;
		}

		public Builder step(double x) {
			this.step = x;
			return this;
		}

		public Builder maxIter(int x) {
			this.maxIter = x;
			return this;
		}

		public Builder graphOrigin(Complex x) {
			this.graphOrigin = x;
			return this;
		}

		public Builder comp(Complex x) {
			this.comp = x;
			return this;
		}

		public Builder polynome(int[] x) {
			this.polynome = x;
			return this;
		}

		public Builder radius(double x) {
			this.radius = x;
			return this;
		}

		public Builder col(int col) {
			this.col = col;
			return this;
		}

		Complex[][] fillGraph() { //remplit le tableau de complexes 
			for (int i = 0; i < tabX + 1; i++) {
				for (int j = 0; j < tabY + 1; j++) {
					graph[i][j] = new Complex(graphOrigin.getReal() - planSize + j * step,
							graphOrigin.getImaginary() + planSize - i * step);
				}
			}

			return graph;
		}

		public Fractale build() { //initialisation auto des champs

			if (tabX == 0.0) {
				tabX = (int) (planSize / step) * 2;
				tabY = (int) (planSize / step) * 2;
				// TODO: utiliser l'autre val de planSize
			}
			if (planSize == 0.0) {
				planSize = step * tabX / 2;
			}
			if (step == 0.0) {
				step = planSize / tabX * 2;
			}
			if (graphOrigin == null)
				this.graphOrigin = new Complex(0, 0);
			graph = new Complex[tabX + 1][tabY + 1];
			fillGraph();
			return new Fractale(this);

		}
	}
	// valeurs obligatoires : tabsizes et step ou tabsizes et planSize ou planSize et step

	public Fractale(Builder builder) {
		comp = builder.comp;
		polynome = builder.polynome;
		maxIter = builder.maxIter;
		radius = builder.radius;
		graphOrigin = builder.graphOrigin;
		step = builder.step;
		graph = builder.graph;
		planSize = builder.planSize;
		col = builder.col;
	}


	static double module(Complex z) {
		return Math.sqrt(z.getReal() * z.getReal() + z.getImaginary() * z.getImaginary());
	}

	int divergenceIndex(Complex z0) { //calcule l'index (aka la couleur) d'un complexe
		int ite = 0;
		Complex zn = z0;
		// sortie de boucle si divergence
		while (ite < maxIter && (module(zn)) <= radius) {
			zn = zn.multiply(zn);
			zn = zn.add(comp);

			ite++;
		}

		return ite;
	}

	Complex[][] getGraph() {
		return graph; 
	}

	public int getX() {
		return graph.length;
	}

	public int getY() {
		return graph[0].length;
	}

	public Complex getOrigin() {
		return graphOrigin;
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

	public int getCol() {
		return col;
	}
}
