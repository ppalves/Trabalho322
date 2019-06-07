package Equipiada.Components.Statistic.Statistic;

import Equipiada.Components.Statistic.IStatistic.IStatistic;
import Equipiada.Templates.ITableProducer.ITableProducer;

import java.util.Arrays;

/**
 * Classe do componente estatístico da Equipiada, MC322.
 * Leia o readme em https://github.com/ppalves/Trabalho322
 *
 * @author Andreis Purim
 * @version 1.0.1
 */

/* ****************************************** STATISTIC ************************************ */
public class Statistic implements IStatistic {
    private String[] attributes;    // Parâmetros da tabela de doenças
    private String[][] instances;   //

    private int[][] frequency;          // Frequências
    private int[] counter;              // Contador
    private float[][] relPercentage;    // Porcentagem relativa
    private float[][] absPercentage;    // Porcentagem absoluta
    private String[] unique;            // Doenças Únicas

    /* ************************** Connect ************************** */
    /* Conecta com a tabela e trata dados                            */
    public void connect(ITableProducer producer) {
        attributes = producer.requestAttributes(); // Pega atributos
        instances = producer.requestInstances();   //
        this.pfindUnique();      // Começa a tratar as tabelas
        this.pfindFrequencies(); //
        this.percentage();       //
    }

    /* ************************** FindUnique ************************** */
    /* Acha o vetor de doenças únicas (não inclui "total"               */
    private void pfindUnique() {
        int size = attributes.length;           //
        String[] ordenado = new String[size];   //

        // Acha vetor de diagnosticos
        for (int a = 0; a < size; a++)
            ordenado[a] = instances[a][attributes.length - 1];

        // Acha unicos
        unique = Arrays.stream(ordenado).distinct().toArray(String[]::new);

        // Contador de N diagnósticos para cada doença
        // Inicia com 0 (por precaução)
        counter = new int[unique.length + 1];
        for (int a = 0; a < counter.length; a++)
            counter[a] = 0;
        // Começa a fazer a contagem total e de cada doença
        for (int a = 0; a < unique.length; a++)
            for (String[] instance : instances)
                if (unique[a].equals(instance[attributes.length - 1])) {
                    counter[0]++;
                    counter[a + 1]++;
                }
    }
    // Transforma Uniques em String
    public String[][] findUnique() {
        // Inicia
        String[][] uniques = new String[unique.length + 1][2];
        uniques[0][0] = "total";
        // Preenche coluna 1, e depois coluna 2
        for (int a = 0; a < unique.length; a++)
            uniques[a + 1][0] = unique[a];
        for (int a = 0; a < counter.length; a++)
            uniques[a][1] = Integer.toString(counter[a]);
        return uniques;
    }

    /* ************************** FindFrequencies ************************** */
    /* Conta na tabela a frequência de doenças por diagnóstico               */
    private void pfindFrequencies() {
        frequency = new int[unique.length + 1][attributes.length - 1];

        // Zerando tudo, por precaução;
        for (int a = 0; a < (unique.length + 1); a++)
            for (int b = 0; b < attributes.length - 1; b++)
                frequency[a][b] = 0;

        // Para cada doença no unique, procura na tabela, se achar, incrementa na matriz de frequencia;
        for (int a = 0; a < unique.length; a++)
            for (String[] instance : instances)
                if (instance[attributes.length - 1].equals(unique[a]))
                    for (int c = 0; c < attributes.length - 1; c++)
                        if (instance[c].equals("t")) {
                            frequency[0][c]++;
                            frequency[a + 1][c]++;
                        }
    }
    // Frequências para String
    public String[][] findFrequency() {
        // Inicia
        String[][] frequencies = new String[unique.length + 1][attributes.length];
        frequencies[0][attributes.length - 1] = "total";
        // Coluna 1 e 2;
        for (int a = 0; a < (unique.length + 1); a++) {
            for (int b = 0; b < attributes.length - 1; b++)
                frequencies[a][b] = Integer.toString(frequency[a][b]);
            if (a > 0)
                frequencies[a][attributes.length - 1] = unique[a - 1];
        }
        return frequencies;
    }

    /* ************************** FindPercentage ************************** */
    /* Calcula porcentagem relativa e absoluta                              */
    private void percentage() {
        // Cria tabelas e divide elas pelo total;
        absPercentage = new float[frequency.length][(frequency[0]).length];
        relPercentage = new float[unique.length + 1][attributes.length];
        for (int a = 0; a < (unique.length + 1); a++)
            for (int b = 0; b < attributes.length - 1; b++) {
                absPercentage[a][b] = ((float) frequency[a][b] / counter[0]);
                relPercentage[a][b] = ((float) frequency[a][b] / frequency[0][b]);
            }
    }
    // Transforma absPercentage em String
    public String[][] absolutePercentage() {
        String[][] aper = new String[unique.length + 1][attributes.length];
        aper[0][attributes.length - 1] = "total";
        for (int a = 0; a < (unique.length + 1); a++) {
            for (int b = 0; b < attributes.length - 1; b++)
                aper[a][b] = String.format("%.2f", absPercentage[a][b]);
            if (a > 0)
                aper[a][attributes.length - 1] = unique[a - 1];
        }
        return aper;
    }

    // Transforma relPercentage em String
    public String[][] relativePercentage() {
        String[][] rper = new String[unique.length + 1][attributes.length];
        rper[0][attributes.length - 1] = "total";
        for (int a = 0; a < (unique.length + 1); a++) {
            for (int b = 0; b < attributes.length - 1; b++)
                rper[a][b] = String.format("%.2f", relPercentage[a][b]);
            if (a > 0)
                rper[a][attributes.length - 1] = unique[a - 1];
        }
        return rper;
    }

    /* *************************** Diagnósticos *************************** */
    /* Faz o diagnóstico para uma doença simples, mais eficiente            */
    public String[][] diagnose(String symptom) {
        float[] combined = new float[unique.length + 1];

        // Percorre a percentagem relativa, olha a coluna dele.
        for (int a = 0; a < attributes.length; a++)
            if (symptom.equals(attributes[a])) {
                for (int b = 1; b < (unique.length + 1); b++)
                    combined[b - 1] = absPercentage[b][a] / absPercentage[0][a];
                break;
            }

        // Combina em uma string
        String[][] stringCombined = new String[unique.length + 1][2];
        stringCombined[0][0] = "total";
        stringCombined[0][1] = "1,00";
        for (int a = 0; a < unique.length; a++)
            stringCombined[a + 1][0] = unique[a];
        for (int a = 0; a < combined.length - 1; a++)
            stringCombined[a + 1][1] = String.format("%.2f", combined[a]);

        return stringCombined;
    }
    /* Faz o diagnóstico para um vetor de doenças, mais eficiente            */
    public String[][] diagnose(String[] symptoms) {
        int[] combined = new int[unique.length + 1];
        int[] posTab = new int[symptoms.length];

        // Acha a posição dos sintomas nos atributos
        for (int a = 0; a < symptoms.length; a++) {
            for (int b = 0; b < attributes.length; b++)
                if (symptoms[a].equalsIgnoreCase(attributes[b]))
                    posTab[a] = b;
        }

        // Vai procurar uma tabela onde eles são verdadeiros
        for (String[] instance : instances) {
            boolean linha = true;
            for (int b : posTab)
                if (instance[b].equals("f"))
                    linha = false;
            if (linha) {
                // Se achou, incrementa o total e o da doença no combined
                combined[0]++;
                for (int b = 0; b < unique.length; b++)
                    if (unique[b].equalsIgnoreCase(instance[attributes.length - 1]))
                        combined[b + 1]++;
            }
        }
        // Junta em uma String
        String[][] stringCombined = new String[unique.length + 1][2];
        stringCombined[0][0] = "total";
        stringCombined[0][1] = "1,00";
        for (int a = 0; a < unique.length; a++)
            stringCombined[a + 1][0] = unique[a];
        for (int a = 0; a < combined.length - 1; a++)
            stringCombined[a + 1][1] = String.format("%.2f", (float) combined[a + 1] / combined[0]);
        return stringCombined;
    }
}