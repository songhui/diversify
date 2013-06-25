package diversify;

import java.util.ArrayList;
import java.util.List;

/** repeats a UnitSimulation n times */
public class RepeatedSimulation implements ISimulation<List<int[][]>> {

  int NUMBEROFRUNS = 20;
  List<int[][]> result = new ArrayList<int[][]>();
  
  public RepeatedSimulation(int runs){
	  this.NUMBEROFRUNS = runs;
  }
  
  /**
   * Original one
   */
  ISimulation<int[][]> sim = new GameOfLifeSimulation();
  //ISimulation<int[][]> sim = new PlatformFailureSimulation();
  
  @Override
  public ISimulation<List<int[][]>> run() {
    for (int l = 0; l < NUMBEROFRUNS; l++) {
      result.add(sim.run().getSimulationResult());
    }
    return this;
  }

  @Override
  public List<int[][]> getSimulationResult() {
    return result;
  }

  @Override
  public String description() {
    return sim.description()+ " repeated " + NUMBEROFRUNS+ " times";
  }

}
