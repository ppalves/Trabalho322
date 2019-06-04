public interface IStatistic extends ITableProducerReceptacle{
    public void connect(ITableProducer producer);
    public String[][] findUnique();
    public String[][] findFrequency();
    public String[][] relativePercentage();
    public String[][] absolutePercentage();
    public String[][] simpleDiagnose(String symptom);
}