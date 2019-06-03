![Alt text](Equipiada.jpg?raw=true "Equipiada Inc.")

# Componente `Statistics`

Campo | Valor
----- | -----
Classe | Components.Statistics
Autores | Andreis
Objetivo | Fazer uma análise estatísitca do DataSet. Utilizar probabilidades combinadas para encontrar os sintomas e doenças mais prováveis.
Interface | IStatistics

~~~
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
~~~

### Interface `IStatiscs`
Avalia frequências na tabela e cria uma tabela própria com os valores, depois calcula probabilidades e probabilidades combinadas.

Método | Objetivo
-------| --------
connect | Apenas conecta com um ponteiro para o produtor da tabela (padrão fornecido pelo professor), nas próximas atualizações não será preciso utilizar o ITableProducer
findUnique | Encontra as doenças únicas (ou seja, extrai repetições) e suas frequências
frequencyTableAttributes | Encontra a relação de atributos (sintomas) por doença em uma tabela. Exemplo: Paralysis em dois diagnósticos de Bacterial_Infection
frequencyTableDiseases | Encontra a relação de doenças por atributos. Exemplo: Bacterial_Infection encontrado duas vezes em Paralysis. Em teoria é a mesma tabela acima, apenas demonstrada de outro jeito para facilitar iterações dependendo da necessidade.
percentageAttributes | Acha a porcentagem total de atributos em valores absolutos. Ex: Duas aparições de Paralysis, 100% em Bacterial Infection.
percentageCombined | Faz uma tabela para utilizar probabilidades combinadas. Ex: Duas aparições de Paralysis em 19 diagnósticos (2/19) e uma aparição em Bacterial Infection (1/19).
likely | Dado um vetor de strings com o nome dos sintomas, retorna uma matriz com as doenças e as probabilidades. Utilizando os valores acima, se você tem paralysis, teria [(1/19)/(2/19)] = 50% de ter Bacterial Infection.

# Componente `PCA_Analysis`

Campo | Valor
----- | -----
Classe | Components.PCA_Analysis
Autores | Marcos
Objetivo | Interface para o uso de Principal Component Analysis para reducao da dimensao dos dados do ZombieHealth, de modo a permitir melhor visualizacao deles. O principal objetivo de proporcionar melhor visualizacao é facilitar a busca por padroes entre as doencas. >`

~~~
public interface PCA_Analysis extends IDataSet, ITableProducerReceptacle{
    //retorna um vetor com a variacao dos componentes principais em ordem decrescente
    public float[] principal_components (int nDimensions);
    //nDimesnions = 1: mostra a distribuicao das doencas ao longo do componente de maior variacao
    //nDimensions = 2: mostra um plano com a distribuicao das doencas ao longo dois dois componentes de maoir variacao
    public void visualize (int nDimensions);
}
~~~

### Interface `PCA_Analysis`
Reduz as dimensões do conjunto de dados e desenha um gŕafico dos componentes principais (Dimensões ciradas nas quais o conjunto de dados apresenta maior variação).

Método | Objetivo
-------| --------
principal_components | Retorna um vetor com a variacao dos componentes principais em ordem decrescente
visualize | Desenha um gŕafico da distribuição dos dados ao longo de uma ou duas das maiores componentes principais


# Componente `Debbuger`

Campo | Valor
----- | -----
Classe | Components.Debbuger
Autores | Andreis
Objetivo | IDebugger é uma interface que irá verificar o diagnóstico do paciente e do doutor, verificar possíveis incongruências e retornar strings de uma forma mais fácil de debuggar o seu código.
Interface | IDebbuger.java

~~~
public interface IDebugger{
    public void connect(IDoctor doc, IPatient pat);
    // Conecta ao medico e ao doutor
    public String[] debugPatient();
    // Retorna string de debug do paciente;
    public String[] debugDoctor();
    // Retorna string de debug do doutor;
    public String[] debugDiagnosis();
    // Retorna string de debug da diagnose;
}
~~~


### Interface `IDebbuger`
Basicamente ajuda a fazer o debbuging, mostrando algumas variáveis pertencentes ao Doutor e Paciente.

Método | Objetivo
-------| --------
void connect | Conecta ao medico e ao doutor
debugPatient | Retorna string de debug do paciente
debugDoctor | Retorna string de debug do doutor
debugDiagnosis | Retorna string de debug da diagnose


# Componente `Dialogue`

Campo | Valor
----- | -----
Classe | Components.Dialogue
Autores | Andreis
Objetivo | Faz dialogos estúpidos, mas melhores.
Interface | IRealistic_Dialogue

~~~
public interface IRealistic_Dialogue{
    public void connect(Patient p, Doctor d);
    public void additional(boolean additional);
    public void virose(boolean virose);
    public void identifiers(boolean additional);
    public String questions(String question);
    public String answers(String answer);
    
}
~~~

### Interface `IRealistic_Dialogue`
Avalia frequências na tabela e cria uma tabela própria com os valores, depois calcula probabilidades e probabilidades combinadas`.

Método | Objetivo
-------| --------
additional | Conecta a interface com os ponteiros do paciente e doutor.
additional | Aqui irá adicionar um vetor [N] de novos componentes para a conversa. Quando a entrevista coemçar, por exemplo, irá simular uma conversa entre o doutor e o paciente: "Olá, tudo bem?" "Sim, doutor"...
virose | Simplesmente troca todas as doenças conhecidas por algum tipo de virose (Paravirosis, Virose bacteriana) e irá tentar fazer para doenças genéricas (virose __)
identifiers | Faz as conversas como um script de teatro, começando com o Casting, os nomes identificados [Doutor]: e adiciona eventuais Ações.
questions | Função padrão para utilizar o componente, basta colocar a pergunta na String e ele irá interpretar (pode ser apenas o sintoma, pode ser uma frase, etc...
answers | Função padrão para resposta, basta colocar a resposta no campo da String e ele irá interpretar e transformar. Ambos questions e answers já imprimem por padrão e te retornam apenas a versão simplificada da resposta.
