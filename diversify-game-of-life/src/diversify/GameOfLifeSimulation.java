package diversify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** creates a mutualistic graph and simulates the failure of platforms */
public class GameOfLifeSimulation implements ISimulation<int[][]> {

  int[][] data;

  Configuration config = new Configuration();  

  @Override
  public ISimulation<int[][]> run() {
    MutualisticGraph g = new MutualisticGraph();
    g.config = config;
    data = new int[config.NTOTALLIFE][2];
    
    TheMatrix matrix = new TheMatrix(g,null);
    List<Integer> res = matrix.run();
    int i = 0;
    for(Integer napps : res){
    	data[i][0]=i;
    	data[i][1]=napps;
    	i ++;
    }
    
    return this;
  }

  @Override
  public int[][] getSimulationResult() {
    return data;
  }

  @Override
  public String description() {
    return "Failure Sim ("+config.toString()+")";
  }

}
