import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;

import java.io.File;

public class ChartUtilis {
    public static void printChart(
            XYDataset xyDataset,
            String fileName,
            String chartTitle,
            String xAxisLabel,
            String yAxisLabel) {
        // tworzenie wykresu
        JFreeChart lineGraph = ChartFactory.createScatterPlot(
                chartTitle,  // Title
                xAxisLabel,           // X-Axis label
                yAxisLabel,           // Y-Axis label
                xyDataset,          // Dataset
                PlotOrientation.VERTICAL,        //Plot orientation
                true,                //show legend
                true,                // Show tooltips
                false               //url show
        );
        // Zapisywanie wykresu do pliku JPG:
        try {
            ChartUtilities.saveChartAsJPEG(new File(fileName + ".jpg"), lineGraph, 1600, 900);
        } catch (Exception e) {
            System.out.println("Problem z zapisem wykresu do pliku");
        }
    }
}
