import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Hypothesis2 {
    public static void Q1(){
        XYSeries gauss = new XYSeries("Gauss");
        XYSeries partGauss = new XYSeries("PartGauss");
        int index = 0;
        for(int i = 10; i < 10000; i += 100){
            Randomizer.resetRandomizer();
            MyMatrix matrix = MatrixGenerator.prepareMatrix(20);
            matrix.gauss();
            double er = IteratedMethod.getError(matrix.vector.vector, MonteCarlo.getSimulationVector(20, i));
            gauss.add(i, er);

            Randomizer.resetRandomizer();
            MyMatrix matrix2 = MatrixGenerator.prepareMatrix(20);
            matrix2.partChoiceGauss();
            double er2 = IteratedMethod.getError(matrix.vector.vector, MonteCarlo.getSimulationVector(20, i));
            partGauss.add(i, er2);


            System.out.print("*");
            if(index % 10 == 0){
                System.out.print("|");
            }
            index++;
        }
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection(gauss);
        xySeriesCollection.addSeries(partGauss);
        ChartUtilis.printChart(
                xySeriesCollection,
                "Q1",
                "Q1: Błąd w zależności od ilości symulacji (rozmiar 20)",
                "Ilość symulacji",
                "Blad (Norma nieskonczonosc)"
        );
    }
}
