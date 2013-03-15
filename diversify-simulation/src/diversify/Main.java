package diversify;

import java.util.List;

public class Main {

  public static void main(String[] args) {
    ISimulation<List<int[][]>> sim = new RepeatedSimulation();
    new Plot().run(sim.run().getSimulationResult(), sim.description());    
  }

}
