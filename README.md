![Alt text](Equipiada.jpg?raw=true "Equipiada Inc.")

Equipiada é a ponta de indústria do desenvolvimento de novas tecnologias para componentes de ZombieHealth para a matéria de MC322 - Programação Orientada a Objeto. Nossa visão e valores está voltada para componentes simples e fáceis de usar, que ajudem de forma sistêmica o código dos outros.
 Em resumo:  
**Statistic:** Cria tabelas que permite uma fácil análise de probabilidades. Capaz também de te indicar as probabilidades de doenças baseadas em sintomas.  
**PCA Analysis:** Cria uns gráficos bonitos que ajudam a visualizar distância entre sintomas.  
**Dialogue:** Uma máquina de estados randômica com "pseudo" linguagem natural. Serve para deixar as conversas bonitas, sem exagerar muito.  
**Debugger:** Utiliza metaclasses para encontrar atributos, métodos e o diagnóstico dentro do paciente e doutor. Indica tudo em um log.

Professor: André Santanché

RA | Nome
----- | -----
213095 | Andreis Gustavo Malta Purim
221525 | Marcos Gabriel Barboza Dure Diaz
204729 | Pedro Pupo Alves
217295 | Guilherme Ramirez

# Main

Campo | Valor
----- | -----
Classe | Equipiada.Main.Main
Autores | Pedro Pupo
Objetivo | Diagnosticar de forma rápida e eficiente a doença do paciente

Com auxilio da Interface do componente `DataOraganizer` do grupo "Clube do Hardware" a main do Equipiada Inc. busca diagnosticar
o paciente da forma mais eficiente possivel, fazendo o menor numero de perguntas. Além disso graças ao componente `Dialogue` tudo isso é feito de forma divertida!

### Interface `ITreeDoctor`

~~~
public interface ITreeDoctor extends IResponderReceptacle, ITableProducerReceptacle{
    public String getNome();
    public String askPatiente(String attribute);
}
~~~

ITreeDoctor é uma adaptação da Interface `IDoctor` visando a melhor utilização e compatibilidade com a `Main`

Método | Objetivo
----- | -----
getNome | Retorna o nome do médico
askPatiente | Pergunta para o paciente se ele tem determinado sintoma, retorna "yes" ou "no"


# Componente `Statistic`

Campo | Valor
----- | -----
Classe | Equipiada.Components.Statistic.Statistic
Autores | Andreis Purim
Objetivo | Fazer uma análise estatísitca do DataSet. Utilizar probabilidades combinadas para encontrar os sintomas e doenças mais prováveis.
Interface | IStatistics

~~~
 public interface IStatistic extends ITableProducerReceptacle{
    public void connect(ITableProducer producer);
    public String[][] findUnique();
    public String[][] findFrequency();
    public String[][] relativePercentage();
    public String[][] absolutePercentage();
    public String[][] diagnose(String symptom);
    public String[][] diagnose(String[] symptons);
}
~~~

### Interface `IStatiscs`
Avalia frequências na tabela e cria uma tabela própria com os valores, depois calcula probabilidades e probabilidades combinadas.

Método | Objetivo
-------| --------
connect | Apenas conecta com um ponteiro para o produtor da tabela (padrão fornecido pelo professor), nas próximas atualizações não será preciso utilizar o ITableProducer
findUnique | Encontra as doenças únicas (ou seja, extrai repetições) e suas frequências
findFrequency | Encontra a relação de atributos (sintomas) por doença em uma tabela. Exemplo: Paralysis em dois diagnósticos de Bacterial_Infection
relativePercentage | Encontra a relação de porcentagem relativa dos sintomas (100% de paralysis em bacterial_infection);
absolutePercentage | Acha a porcentagem absoluta dos sintomas (11% dos diagnosticos possuem paralysis em bacterial_infection
diagnose | Dado um/vários sintomas, retorna uma matriz com as doenças e as probabilidades. Utilizando os valores acima, se você tem paralysis, teria [(1/19)/(2/19)] = 50% de ter Bacterial Infection.

# Componente `PCA_Analysis`

Campo | Valor
----- | -----
Classe | Equipiada.Components.PCA_Analysis.PCA_Analysis
Autores | Marcos Diaz
Objetivo | Interface para o uso de Principal Component Analysis, um método que proporciona a redução da dimensão dos dados do ZombieHealth, de modo a permitir melhor visualizacao deles. O principal objetivo de proporcionar melhor visualizacao é facilitar a busca por padroes entre as doencas. Assim, o gráfico das duas dimensões pode ser interpretado como uma representação da proximidade de sintomas entre os casos de doenças, ou seja, doenças diferentes com agrupamentos mais próximos tem maior chance de serem diagnosicados trocadas.
Interface | IPCA_Analysis 

~~~
public interface IPCA_Analysis {
    public void pca ();
    public void showPlot();
}
~~~
﻿![Alt text](pca_annotation.jpg?raw=true "pca_annotation")
###### AVISO ######
O componente só funciona em Linux.
O uso do script de Python3 que realiza o PCA depende de algumas dependências que podem ser instaladas digitando, numa Shell Linux: 
~~~~
chmod u+x python3_configure.sh
./python3_configure.sh
~~~~
O scritp também fornece o PATH de sua instalação do python3.
As dependências estão disponíveis no script caso o usuário queira instalá-las manualmente.
 
### Interface `PCA_Analysis`
Reduz as dimensões do conjunto de dados e desenha um gŕafico dos componentes principais (Dimensões ciradas nas quais o conjunto de dados apresenta maior variação).

Método | Objetivo
-------| --------
pca | Realiza a Análise de Componentes Principais (PCA) e salva um gráfico das duas principais componentes
showPlot | Abre o gráfico numa janela


# Componente `Debbuger`

Campo | Valor
----- | -----
Classe | Equipiada.Components.Debbuger.Debbuger
Autores | Guilherme Ramirez
Objetivo | IDebugger é uma interface que irá verificar o diagnóstico do paciente e do doutor, verificar possíveis incongruências e cria um log de debug de forma .txt.
Interface | IDebbuger

~~~
public interface IDebugger{
    public void connect(IDoctor doc, IPatient pat);
    // Conecta ao medico e ao doutor
    public String[] debugPatient();
    // Retorna string de debug do paciente;
    public String[] debugDoctor();
    // Retorna string de debug do doutor;
}
~~~


### Interface `IDebbuger`
Basicamente ajuda a fazer o debbuging, mostrando algumas variáveis e métodos pertencentes ao Doutor e Paciente, mesmo que sejam privados, gera um log de debug

Método | Objetivo
-------| --------
void connect | Conecta ao medico e ao doutor
debugPatient | Retorna string de debug do paciente
debugDoctor | Retorna string de debug do doutor


# Componente `Dialogue`

Campo | Valor
----- | -----
Classe | Equipiada.Components.Dialogue.Dialogue
Autores | Andreis Purim
Implementação | Pedro Pupo
Objetivo | Faz dialogos estúpidos, mas melhores.
Interface | IRealistic_Dialogue

~~~
public interface IRealistic_Dialogue{
    public void connect(Patient p, Doctor d);
    public void additional(boolean additional);
    public void virose(boolean virose);
    public void identifiers(boolean additional);
    public void start();
    public String questions(String question);
    public String answer(String answer);
    
}
~~~

### Interface `IRealistic_Dialogue`
Avalia frequências na tabela e cria uma tabela própria com os valores, depois calcula probabilidades e probabilidades combinadas`. EXIGE QUE AS INTERFACES POSSUAM A FUNÇÃO getNome();

Método | Objetivo
-------| --------
connect | Conecta a interface com os ponteiros do paciente e doutor.
additional | Aqui irá adicionar um vetor [N] de novos componentes para a conversa. Quando a entrevista coemçar, por exemplo, irá simular uma conversa entre o doutor e o paciente: "Olá, tudo bem?" "Sim, doutor"...
virose | Simplesmente troca todas as doenças conhecidas por algum tipo de virose (Paravirosis, Virose bacteriana) e irá tentar fazer para doenças genéricas (virose __)
identifiers | Faz as conversas como um script de teatro, começando com o Casting, os nomes identificados [Doutor]: e adiciona eventuais Ações como *Tosse*. 
start | Começa a conversa, impede que você inicie sem paciente e doutor.
questions | Função padrão para utilizar o componente, basta colocar a pergunta na String e ele irá interpretar (pode ser apenas o sintoma, pode ser uma frase, etc...
answer | Função padrão para resposta, basta colocar a resposta no campo da String e ele irá interpretar e transformar. Ambos questions e answers já imprimem por padrão e te retornam apenas a versão simplificada da resposta.
