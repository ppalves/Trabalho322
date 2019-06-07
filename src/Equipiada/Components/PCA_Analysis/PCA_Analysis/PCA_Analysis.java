package Equipiada.Components.PCA_Analysis.PCA_Analysis;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Equipiada.Components.PCA_Analysis.IPCA_Analysis.IPCA_Analysis;

public class PCA_Analysis implements IPCA_Analysis {
	private String pythonPATH;
	private String scriptPATH;
	private String Dataset;
	
	public PCA_Analysis(String py, String sc, String d){
		pythonPATH = py;
		scriptPATH = sc;
		Dataset = d;
	}
	
	public void pca () {
		String dataset = Dataset;
		ProcessBuilder builder = new ProcessBuilder();
		builder.command(pythonPATH, 
						scriptPATH,
						dataset);
		try {
			builder.start();
			System.out.println("Grafico criado.");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Nao foi possivel abrir o Dataset.");
		}
	}

	public void showPlot(){
		JFrame frame = new JFrame();
		JLabel label = new JLabel(new ImageIcon(Dataset.substring(0, (Dataset.length()-4))+".jpeg"));
		frame.add(label);
		frame.setDefaultCloseOperation
		(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true); 
	}
}