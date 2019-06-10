package Equipiada.Components.PCA_Analysis.PCA_Example;
import Equipiada.Components.PCA_Analysis.PCA_Analysis.PCA_Analysis;

//Exemplo de uso do Componente
public class PCA_Example {
	public static void main (String[] args) {
		//PATH do seu interpretador de python3 (saida do comando "which python3" no terminal)
		String pythonPATH = "/usr/bin/python3";
		//PATH do script de python3 da Analise PCA
		String scriptPATH = "/home/marcos/Documents/3SEM/MC322/GIT/Trabalho322/src/Equipiada/Components/PCA_Analysis/PCA_Analysis/pca_prototype_scatter.py";
		//PATH do seu Dataset
		String datasetPATH = "/home/marcos/Documents/3SEM/MC322/Trabalho322/PCA-Component/zombieData/zombie-health-new-cases500.csv";
		
		//Objeto base do componente. Crie um por cada Dataset que deseje analizar.
		PCA_Analysis pca1 = new PCA_Analysis(pythonPATH, scriptPATH, datasetPATH);
		
		//Faz a analise e salva um grafico dos 2 componentes principais
		pca1.pca();
		
		//Mostra o grafico 
		pca1.showPlot();
		
		//repeticao como outro dataSet
		datasetPATH = "/home/marcos/Documents/3SEM/MC322/Trabalho322/PCA-Component/zombieData/zombie-health-spreadsheet-ml-training.csv";
		PCA_Analysis pca2 = new PCA_Analysis(pythonPATH, scriptPATH, datasetPATH);
		pca2.pca();
	}
}	
