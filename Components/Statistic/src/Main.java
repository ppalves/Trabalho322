public class Main {

    public static void printMatrix(String[][] s){
        for(int a = 0; a < s.length; a++) {
            for (int b = 0; b < s[0].length; b++) {
                System.out.print(s[a][b] + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args)
    {
        String tablePath = "C:\\Users\\Micro\\IdeaProjects\\Statistic\\src\\zombie-health-spreadsheet-ml-training.csv";
        // Seja bem vindo! Vamos aprender a utilizar o Statistic!
        IDataSet dataset = new DataSetComponent();
        dataset.setDataSource(tablePath);
        // Aqui estou criando o DataSet (o template do Santanche), utilize o endereço da sua tabela
        Statistic s = new Statistic();
        s.connect(dataset);
        // Aqui criei meu componente Statistic e conectei ele no dataset
        // Vamos começar pegando as doenças únicas e o número de ocorrências na tabela:
        String result[][] = s.findUnique();
        System.out.println("============ Unicas ============");
        printMatrix(result);
        // Agora eu quero ver o número de vezes que cada sintoma aparece em cada doença. Veja que cada coluna
        // é um sintoma, igual a tabela;
        result = s.findFrequency();
        System.out.println("============ Unicas ============");
        printMatrix(result);
        // Agora vamos ver as porcentagens, absolutas e relativas
        System.out.println("============ % Relativa ============");
        result = s.relativePercentage();
        printMatrix(result);
        System.out.println("============ % Absolute ============");
        result = s.absolutePercentage();
        printMatrix(result);
        //Agora quero achar as probabilidades da pessoa ter X doenças dependendo do sintoma S:
        System.out.println("============ Diagnóstico ============");
        System.out.println("severe_anger");
        result = s.simpleDiagnose("severe_anger");
        printMatrix(result);
        System.out.println("\nparalysis");
        result = s.simpleDiagnose("paralysis");
        printMatrix(result);
    }
}
