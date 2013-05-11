package diversify

import diversify.ISimulation
import diversify.Configuration
import diversify.MutualisticGraph
import java.util.ArrayList

/**
 * killing services one by one, and after any service is killed, all the platforms
 * providing this service will be killed
 */
class ServiceDirectedFailureSimulation implements ISimulation<int[][]> {
		
	extension GeneralExtension ce = new GeneralExtension()
		
	int[][] data;

  	Configuration config = new Configuration(); 
	override description(){"Service-directed failure Sim ("+config.toString()+")"; }	
	override getSimulationResult(){ data }
	
	override run() {
		val g = new MutualisticGraph()
		g.createConfiguration()[
			NSERVICES=100
			NAPPS=600 ]
		val p = g.platforms.map(e|e);
		val da = newArrayList(newArrayList(0,0))
		da.remove(0)

		for(s : g.services){
			val killed = p.filter(e|e.providedServices.contains(s))			
			p.removeAll(killed.toList)				
			val supported = 
				if(p.size!=0) 
					p.map(e|e.getSupportedApplications(g.applications).size)
					 .reduce(a,b|a+b) 
				else 0							 
			da.add(newArrayList(da.size,supported))			
		}		
		data = Utility::get2DArrayFromList(da)
		this
	}
}


