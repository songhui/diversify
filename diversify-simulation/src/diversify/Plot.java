package diversify;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/** plots some line series using jfreechart */
public class Plot {

  void run(List<int[][]> ldata, String title) {
      XYSeriesCollection dataset = new XYSeriesCollection();

      int n=0;
      for (int[][] data: ldata) {
      XYSeries xy = new XYSeries("run"+(n++));
      for (int i = 0; i < data.length; i++) {
          xy.add(data[i][0], data[i][1]);
      }
      dataset.addSeries(xy);
      }
      JFreeChart chart = ChartFactory.createXYLineChart(
          title, "# platforms down", "# running apps", dataset, PlotOrientation.VERTICAL, true, true, false);
      ChartFrame frame = new ChartFrame("First", chart);
      frame.pack();
      frame.setVisible(true);

      //frame.dispose();
  }

  
}
