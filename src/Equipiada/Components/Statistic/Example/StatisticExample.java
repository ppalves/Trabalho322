package Equipiada.Components.Statistic.Example;

import Equipiada.Components.Statistic.Statistic.Statistic;
import Equipiada.Components.Statistic.StatisticFactory.StatisticFactory;
import Equipiada.Templates.DataSetComponent.DataSetComponent;
import Equipiada.Templates.IDataSet.IDataSet;

public class StatisticExample {

    private static void printMatrix(String[][] s) {
        for (String[] strings : s) {
            for (int b = 0; b < s[0].length; b++)
                System.out.print(strings[b] + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String tablePath = "src/Equipiada/Templates/Tables/zombie-health-spreadsheet-ml-training.csv";
        // Seja bem vindo! Vamos aprender a utilizar o Statistic!
        IDataSet dataset = new DataSetComponent();
        dataset.setDataSource(tablePath);
        // Aqui estou criando o DataSet (o template do Santanche), utilize o endereço da sua tabela

        Statistic s = StatisticFactory.createStatistic();
        s.connect(dataset);

        // Aqui criei meu componente Statistic e conectei ele no dataset
        // Vamos começar pegando as doenças únicas e o número de ocorrências na tabela:
        System.out.println("============ Unicas ============");
        printMatrix(s.findUnique());
        // Agora eu quero ver o número de vezes que cada sintoma aparece em cada doença. Veja que cada coluna
        // é um sintoma, igual a tabela;
        System.out.println("============ Frequencias ============");
        printMatrix(s.findFrequency());

        // Agora vamos ver as porcentagens, absolutas e relativas
        System.out.println("============ % Relativa ============");
        printMatrix(s.relativePercentage());

        System.out.println("============ % Absolute ============");
        printMatrix(s.absolutePercentage());

        //Agora quero achar as probabilidades da pessoa ter X doenças dependendo do sintoma S:
        System.out.println("============ Diagnóstico ============");
        System.out.println("severe_anger");
        printMatrix(s.diagnose("severe_anger"));

        System.out.println("============ Diagnóstico ============");
        System.out.println("yellow_tong");
        printMatrix(s.diagnose("yellow_tong"));

        System.out.println("============ MultDiagnóstico ============");
        System.out.println("history_bacteria e chest_pain");
        String[] sintomas = {"history_bacteria", "chest_pain"};
        printMatrix(s.diagnose(sintomas));
    }
}
