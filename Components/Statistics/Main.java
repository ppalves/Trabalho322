import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main{

    public interface IDataSource {
        public String getDataSource();
        public void setDataSource(String dataSource);
    }

    public interface ITableProducer {
        String[] requestAttributes();
        String[][] requestInstances();
    }

    public interface ITableProducerReceptacle {
        public void connect(ITableProducer producer);
    }

    public interface IDataSet extends IDataSource, ITableProducer {
    }

    public interface IStatistic extends ITableProducerReceptacle{
        public void connect(ITableProducer producer);
        // Conecta ao produtor de tabela;
        public String[][] findUnique();
        // Encontra doenças unicas na tabela, retorna doenças e frequencias
        public String[][] frequencyTableAttributes();
        // Encontra frequencia de atributos por doença;
        public String[][] frequencyTableDisease();
        // Encontra frequencia de doenças por atributo
        public String[][] percentageAttributes();
        // Porcentagem das frequencias
        public String[][] percentageDiseases();
        // Porcentagem dos atributos
        public String[][] likely(String[] symptoms);
        // Checa probabilidade dados os sintomas;
    }

    public class DataSetComponent implements IDataSet {
        private String dataSource = null;
        private String[] attributes = null;
        private String[][] instances = null;

        public DataSetComponent() {
            /* nothing */
        }

        public String getDataSource() {
            return dataSource;
        }

        public void setDataSource(String dataSource) {
            this.dataSource = dataSource;
            if (dataSource == null) {
                attributes = null;
                instances = null;
            } else
                readDS();
        }

        public String[] requestAttributes() {
            return attributes;
        }

        public String[][] requestInstances() {
            return instances;
        }

        private void readDS() {
            ArrayList<String[]> instArray = new ArrayList<String[]>();
            try {
                BufferedReader file = new BufferedReader(new FileReader(dataSource));

                String line = file.readLine();
                if (line != null) {
                    attributes = line.split(",");
                    line = file.readLine();
                    while (line != null) {
                        String[] instLine = line.split(",");
                        instArray.add(instLine);
                        line = file.readLine();
                    }
                    instances = instArray.toArray(new String[0][]);
                }

                file.close();
            } catch (IOException erro) {
                erro.printStackTrace();
            }
        }

    }

    public class Statistic {
        private ITableProducer producer;
        private String attributes[];
        private String instances[][];
        private String unique[];
        private int frequency[][];
        private int counter[];

        // Função de Conectar com o produtor da tabela;
        public void connect(ITableProducer producer){
            this.producer = producer;
        }
        /******************************* Acha Unicos *******************************/
        // Irá econtrar as doenças únicas e suas frequências
        public void findUnique(){
            int size = producer.requestInstances().length;
            attributes = producer.requestAttributes();
            instances = producer.requestInstances();
            String ordenado[] = new String[size];

            // Pega todas as strings do final, e depois acha as únicas;
            for(int a = 0; a < size; a++){
                ordenado[a] = instances[a][attributes.length - 1];
            }
            unique = Arrays.stream(ordenado).distinct().toArray(String[]::new);

            // Agora faz um contador para ver quantas vezes foi
            // Sim, isso não é efetivo ainda, está cheio de fors
            counter = new int[unique.length+1];
            for(int a = 0; a < counter.length; a++)
                counter[a] = 0;
            for(int a = 0; a < unique.length; a++){
                for(int b = 0; b < instances.length; b++){
                    if(unique[a].equals(instances[b][attributes.length - 1])){
                        counter[0]++;
                        counter[a+1]++;
                    }
                }
            }

            // Junta os dois em uma matriz uniques
            String uniques[][] = new String[unique.length+1][2];
            uniques[0][0] = "total";
            for(int a = 0; a < unique.length; a++)
                uniques[a+1][0] = unique[a];
            for(int a = 0; a < counter.length; a++)
                uniques[a][1] = Integer.toString(counter[a]);

            System.out.println("========= Unique Diseases =========");
            for(int a = 0; a < uniques.length; a++){
                System.out.println(uniques[a][0] + " " + uniques[a][1]);
            }
        }

        /******************************* Printa Tabela *******************************/
        // Faz a tabela ficar mais bonita de ver. Só isso.
        public void printTable(){
            System.out.println("========= Disease Table =========");
            for (int a = 0; a < instances.length; a++){
                for(int b = 0; b < attributes.length; b++){
                    if(!instances[a][b].equals("f"))
                        System.out.print(instances[a][b] + " ");
                    else
                        System.out.print("  ");
                }
                System.out.println("");
            }
        }

        /******************************* Acha frequências *******************************/
        public void findFrequencies(){
            frequency  = new int[unique.length+1][attributes.length-1];

            // Zera tudo
            for(int a = 0; a < (unique.length+1); a ++){
                for(int b = 0; b < attributes.length-1; b++){
                    frequency[a][b] = 0;
                }
            }

            // Para cada doença no unique, procura na tabela, se achar, incrementa na matriz de frequencia;
            for(int a = 0; a < unique.length; a ++){
                for(int b = 0; b < instances.length; b++){
                    if(instances[b][attributes.length-1].equals(unique[a])){
                        for(int c = 0; c < attributes.length-1; c++){
                            if(instances[b][c].equals("t")){
                                frequency[0][c]++;
                                frequency[a+1][c]++;
                            }
                        }
                    }
                }
            }

            // Printa frequencias
            System.out.println("========= Frequencies =========");
            for(int a = 0; a < (unique.length+1); a ++){
                for(int b = 0; b < attributes.length-1; b++){
                    System.out.printf("%3d ",frequency[a][b]);
                }
                if(a > 0)
                    System.out.print(unique[a-1]);
                else
                    System.out.print("total");
                System.out.println("");
            }
        }
        /******************************* Acha porcentagem *******************************/
        public void findPercentage(){
            System.out.println("========= Percentage =========");

            // Basicamente percorre nossas frequências e divide, formatação diferente de zeros.
            String percentage[][] = new String[unique.length][attributes.length];

            for(int a = 1; a < (unique.length+1); a ++){
                int b;
                for(b = 0; b < attributes.length-1; b++){
                    percentage[a-1][b] = String.format("%.2f",(float)frequency[a][b]/frequency[0][b]);
                    if(!percentage[a-1][b].equals("0.00"))
                        System.out.print(percentage[a-1][b] + " ");
                    else
                        System.out.print("0    ");
                }
                percentage[a-1][b] = unique[a-1];
                System.out.println(percentage[a-1][b]);
            }
        }

        public void staticDiagnose(String sintomas){
            float percentageB[][] = new float[frequency.length][(frequency[0]).length];
            System.out.println("");

            for(int a = 0; a < frequency.length; a++){
                for(int b = 0; b < frequency[0].length; b++){
                    percentageB[a][b] = (float)frequency[a][b]/counter[0];
                    System.out.print(String.format("%.2f ", percentageB[a][b]));
                }
                if(a == 0)
                    System.out.print("\n");
                System.out.println("");
            }
            System.out.println("");

            int i;
        }

    }

    IDataSet dataset = new DataSetComponent();
    dataset.setDataSource(".../table.csv");
    Statistic s = new Statistic();

    public static void main(String[] args){


        System.out.println("Hello");


        s.connect(dataset);
        s.findUnique();
        s.printTable();
        s.findFrequencies();
        s.findPercentage();
        s.staticDiagnose("yellow_tong");
    }
}