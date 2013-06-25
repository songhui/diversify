package diversify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** creates a mutualistic graph and simulates the failure of platforms */
public class PlatformFailureSimulation implements ISimulation<int[][]> {

  int[][] data;

  Configuration config = Configuration.INSTANCE;  

  @Override
  public ISimulation<int[][]> run() {
    MutualisticGraph g = new MutualisticGraph();
    g.config = config;
    
    //improve similarity
    MutatePlatformGraph mutator = MutatePlatformGraph.INSTANCE;
    if(mutator !=null){
	    System.out.print("Before: "+mutator.getPltfDiversity(g.platforms));
	    mutator.reCreatePlatforms(g.platforms, g.services);
	    System.out.println("After: "+mutator.getPltfDiversity(g.platforms));
    }
    
    ServicesDependencies depender = ServicesDependencies.INSTANCE;
    if(depender != null){
    	depender.reCreatePlatforms(g.platforms, g.services);
    }
    
    data = new int[g.platforms.size()+1][2];
    Collections.sort(g.platforms, config.ordering);
    List<Platform> p1 = new ArrayList<Platform>();
    Set<Application> res = new HashSet<Application>();
    data[0][0] = (g.platforms.size() +1);
    data[0][1] = res.size();
    
    for (int k = g.platforms.size(); k > 0; k--) {
      Platform p = g.platforms.get(k - 1);
      p1.add(p);
      List<Application> suppapps = p.getSupportedApplications(g.applications);
      res.addAll(suppapps);
      int index = k;
      data[index][0] = (k - 1);
      data[index][1] = res.size();
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
