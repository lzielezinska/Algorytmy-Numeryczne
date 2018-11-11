
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import static java.lang.System.*;

public class Main {
    public static void main(String[] args){
        out.println("Lucyna Zielezińska & Kacper Dondziak");
       // Tests.testAllTypes();
        Hypothesis.E1();
        XYSeries d1 = new XYSeries("LEWO BEZPOSRENIO");
        d1.add(1,2);
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection(d1);
        ChartUtilis.printChart(xySeriesCollection, "H3","A","B","C");
    }
}
