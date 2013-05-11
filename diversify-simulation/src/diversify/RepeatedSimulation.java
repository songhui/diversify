package diversify;

import java.util.ArrayList;
import java.util.List;

/** repeats a UnitSimulation n times */
public class RepeatedSimulation implements ISimulation<List<int[][]>> {

  final int NUMBEROFRUNS = 20;
  List<int[][]> result = new ArrayList<int[][]>();
  
  /**
   * Original one
   */
  ISimulation<int[][]> sim = new PlatformFailureSimulation();
  //ISimulation<int[][]> sim = new ServiceDirectedFailureSimulation();
  
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
