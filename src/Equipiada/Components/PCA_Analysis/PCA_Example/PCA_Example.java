package Equipiada.Components.PCA_Analysis.PCA_Example;
import Equipiada.Components.PCA_Analysis.PCA_Analysis.PCA_Analysis;

public class PCA_Example {
	public static void main (String[] args) {
		PCA_Analysis pca1 = new PCA_Analysis("/usr/bin/python3", 
				"/home/marcos/Documents/3SEM/MC322/Trabalho322/PCA-Component/pca_prototype_scatter.py",
				"zombie-health-cases500.csv");
		pca1.pca();
		pca1.showPlot();
	}
}
