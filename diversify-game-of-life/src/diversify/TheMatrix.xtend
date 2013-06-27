package diversify

import java.util.Random
import org.eclipse.xtext.xbase.lib.Procedures$Procedure1
import java.util.List
import java.util.HashSet
import java.util.ArrayList

import static extension java.util.Collections.*
import static extension diversify.MutatePlatformGraph.*

class TheMatrix {
	static int round = 0
	Random random = new Random()
	Configuration config = new Configuration()
	MutualisticGraph graph = null
	
	new(MutualisticGraph graph, Procedure1<Configuration> initializer){
		this.graph = graph
		initializer?.apply(config)
	}
	
	def killServiceFromPlatform(Platform p){
		for(s : p.providedServices)
			if(random.nextInt(100) < config.NPERCENTDOWN)				
				p.rebooting.put(s,random.nextInt(config.NMAXREBORN))	
		
		p.providedServices.removeAll(p.rebooting.keySet)
	}
	
	def reboot(Platform p){
		p.rebooting.entrySet.forEach(e | e.value = e.value-1)
		val toLive = p.rebooting.entrySet.filter(e | e.value <= 0)
					  .map(e|e.key).toList()
		p.providedServices.addAll(toLive)
		for(s:toLive)
			p.rebooting.remove(s)		
	}
	
	def updateSupportedApps(Platform p, List<Application> apps){
		p.supportedApps.clear();
		p.supportedApps.addAll(apps.filter(
			app | p.providedServices.containsAll(app.requiredServices)	
		))
		
		if(p.supportedApps.size == 0){
			if(p.life > config.NSICKTODIE)
				p.life = config.NSICKTODIE
			else
				p.life = p.life - 1
		}
		else
			p.life = 10000
	}
	
	def killPlatforms(List<Platform> ps){
		ps.removeAll(ps.filter(e|e.life <= 0).toList)
	}
	
	def killApps(List<Application> apps, List<Platform> ps){
		val active = new HashSet<Application>()
		ps.map(e|e.supportedApps.toSet).forEach(e|active.addAll(e))
		
		apps.clear()
		apps.addAll(active)
	}
	
	
	
	
	def run(){
		
		if(MutatePlatformGraph::INSTANCE!=null)
			MutatePlatformGraph::INSTANCE.reCreatePlatforms(graph.platforms,graph.services)
		MutatePlatformGraph::dissims.add(getPltfDiversity(graph.platforms))
		//System::out.println('''«round»: «getPltfDiversity(graph.platforms)»''')
		round = round + 1
		var time = 0;
		val List<Integer> nApps = new ArrayList<Integer>()
		while(time < config.NTOTALLIFE){
			graph.platforms.forEach(p | p.reboot)
			graph.platforms.forEach(p | p.killServiceFromPlatform)
			graph.platforms.forEach(p | p.updateSupportedApps(graph.applications))
			killApps(graph.applications,graph.platforms)
			killPlatforms(graph.platforms)
			
			nApps.add(graph.applications.size)
			time = time + 1;
		}
		
		nApps
	}
	
}