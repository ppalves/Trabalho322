//Interface para o uso de Principal Component Analysis para reducao da dimensao dos dados do ZombieHealth, de modo a permitir melhor visualizacao deles. O principal objetivo de proporcionar melhor visualizacao eh facilitar a busca por padroes entre as doencas.

public interface PCA_Analysis extends IDataSet, ITableProducerReceptacle{
	//retorna um vetor com a variacao dos componentes principais em ordem decrescente
	public float[] principal_components (int nDimensions);
	//nDimesnions = 1: mostra a distribuicao das doencas ao longo do componente de maior variacao
	//nDimensions = 2: mostra um plano com a distribuicao das doencas ao longo dois dois componentes de maoir variacao
	public void visualize (int nDimensions);
}
