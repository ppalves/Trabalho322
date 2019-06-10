package Equipiada.Components.PCA_Analysis.PCA_Analysis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Equipiada.Components.PCA_Analysis.IPCA_Analysis.IPCA_Analysis;

public class PCA_Analysis implements IPCA_Analysis {
	//variaveis para armazenar os PATHs 
	private String pythonPATH;
	private String scriptPATH;
	private String Dataset;
	
	//construtor: recebe o PATHs, respectivamente, de execucao do python3, de execucao do script e do Database
	public PCA_Analysis(String py, String sc, String d){
		pythonPATH = py;
		scriptPATH = sc;
		Dataset = d;
	}
	
	//realiza a Analise
	public void pca () {
		String dataset = Dataset;
		ProcessBuilder builder = new ProcessBuilder();
		builder.command(pythonPATH, 
						scriptPATH,
						dataset);
		try {
			Process p = builder.start();
			BufferedReader reader =
	                    new BufferedReader(new InputStreamReader(p.getInputStream()));
			 String pyOut = reader.readLine();
			 if (pyOut != null) {
				 System.out.println("Script executado com sucesso.");
			 }
			 else {
				 System.out.println("Falha ao executar o script. Verifique o DatasetPATH e o scriptP");
			 }
			 reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro de I/O.s");
		}
	}
	
	//mostra o grafico salvo pelo script
	public void showPlot(){
		JFrame frame = new JFrame();
		String imagePATH = Dataset.substring(0, (Dataset.length()-4))+".jpg";
		System.out.println(imagePATH);
		JLabel label = new JLabel(new ImageIcon(imagePATH));
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true); 
	}
	
	//sobrescreve o toString para retornar o DatasetPath
	public String toString() {
		return Dataset;
	}	
}