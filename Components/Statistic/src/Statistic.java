import java.util.*;

/**
 *  Statistic class for the Statistic Component, does the mathematical accounts necessary.
 * @author Andreis Purim
 * @version 1.0.0
 */
public class Statistic implements IStatistic {
    private ITableProducer producer;
    private String attributes[];
    private String instances[][];

    private int frequency[][];
    private int counter[];
    private float relPercentage[][];
    private float absPercentage[][];
    private String unique[];

    /**
     * Initializer funcion, connects with table producer and generates frequency and percentage tables
     * @param producer
     */
    public void connect(ITableProducer producer){
        this.producer = producer;
        this.pfindUnique();
        this.pfindFrequencies();
        this.prelativePercentage();
        this.pabsolutePercentage();
    }

    /**
     * Runs across the diagnostic table, finds unique diseases;
     */
    private void pfindUnique(){
        int size = producer.requestInstances().length;
        attributes = producer.requestAttributes();
        instances = producer.requestInstances();
        String ordenado[] = new String[size];

        // Acha vetor de diagnosticos
        for(int a = 0; a < size; a++)
            ordenado[a] = instances[a][attributes.length - 1];

        // Acha unicos
        unique = Arrays.stream(ordenado).distinct().toArray(String[]::new);

        // Contador de N diagnósticos para cada doença
        counter = new int[unique.length+1];
        for(int a = 0; a < counter.length; a++)
            counter[a] = 0;
        for(int a = 0; a < unique.length; a++)
            for(int b = 0; b < instances.length; b++)
                if(unique[a].equals(instances[b][attributes.length - 1])){
                    counter[0]++;
                    counter[a+1]++;
                }
    }

    /**
     * Finds simptoms frequencies by disease;
     */
    private void pfindFrequencies(){
        frequency  = new int[unique.length+1][attributes.length-1];

        // Zerando tudo, por precaução;
        for(int a = 0; a < (unique.length+1); a ++)
            for(int b = 0; b < attributes.length-1; b++)
                frequency[a][b] = 0;

        // Para cada doença no unique, procura na tabela, se achar, incrementa na matriz de frequencia;
        for(int a = 0; a < unique.length; a ++)
            for(int b = 0; b < instances.length; b++)
                if(instances[b][attributes.length-1].equals(unique[a]))
                    for(int c = 0; c < attributes.length-1; c++)
                        if(instances[b][c].equals("t")){
                            frequency[0][c]++;
                            frequency[a+1][c]++;
                        }
    }

    /**
     * Finds relative percentage by disease;
     */
    private void prelativePercentage(){
        relPercentage = new float[unique.length+1][attributes.length];
        for(int a = 0; a < (unique.length+1); a ++)
            for (int b = 0; b < attributes.length - 1; b++)
                relPercentage[a][b] = ((float) frequency[a][b] / frequency[0][b]);
    }

    /**
     * Finds absolute percentage (this is quite useful
     */
    private void pabsolutePercentage(){
        absPercentage = new float[frequency.length][(frequency[0]).length];
        for(int a = 0; a < (unique.length+1); a ++)
            for (int b = 0; b < attributes.length - 1; b++)
                absPercentage[a][b] = ((float) frequency[a][b] / counter[0]);
    }

    /**
     * Give me a symptom and I'll give you a probability table
     * @param String symptom
     * @return returns a String matrix with diseases and probabilities
     */
    public String[][] simpleDiagnose(String symptom){
        float combined[] = new float[unique.length+1];

        // Percorre e acha o sintoma, olha a coluna dele.
        for(int a = 0; a < attributes.length; a++)
            if(symptom.equals(attributes[a])) {
                for (int b = 1; b < (unique.length + 1); b++)
                    combined[b - 1] = absPercentage[b][a] / absPercentage[0][a];
                break;
            }

        // Combina em uma string
        String stringCombined[][] = new String[unique.length+1][2];
        stringCombined[0][0] = "total";
        stringCombined[0][1] = "1,00";
        for(int a = 0; a < unique.length; a++)
            stringCombined[a+1][0] = unique[a];
        for(int a = 0; a < combined.length-1; a++)
            stringCombined[a+1][1] = String.format("%.2f",combined[a]);

        return stringCombined;
    }

    // Transforma absPercentage em String
    public String[][] absolutePercentage(){
        String aper[][] = new String[unique.length+1][attributes.length];
        aper[0][attributes.length-1] = "total";
        for(int a = 0; a < (unique.length+1); a ++) {
            for (int b = 0; b < attributes.length - 1; b++)
                aper[a][b] = String.format("%.2f",absPercentage[a][b]);
            if (a > 0)
                aper[a][attributes.length-1] = unique[a-1];
        }
        return aper;
    }

    // Transforma relPercentage em String
    public String[][] relativePercentage(){
        String rper[][] = new String[unique.length+1][attributes.length];
        rper[0][attributes.length-1] = "total";
        for(int a = 0; a < (unique.length+1); a ++) {
            for (int b = 0; b < attributes.length - 1; b++)
                rper[a][b] = String.format("%.2f",relPercentage[a][b]);
            if (a > 0)
                rper[a][attributes.length-1] = unique[a-1];
        }
        return rper;
    }

    // Frequencies to string
    public String[][] findFrequency(){
        String frequencies[][] = new String[unique.length+1][attributes.length];
        frequencies[0][attributes.length-1] = "total";
        for(int a = 0; a < (unique.length+1); a ++) {
            for (int b = 0; b < attributes.length - 1; b++)
                frequencies[a][b] = Integer.toString(frequency[a][b]);
            if (a > 0)
                frequencies[a][attributes.length-1] = unique[a-1];
        }
        return frequencies;
    }

    // Unique to String
    public String[][] findUnique(){
        String uniques[][] = new String[unique.length+1][2];
        uniques[0][0] = "total";
        for(int a = 0; a < unique.length; a++)
            uniques[a+1][0] = unique[a];
        for(int a = 0; a < counter.length; a++)
            uniques[a][1] = Integer.toString(counter[a]);
        return uniques;
    }
}