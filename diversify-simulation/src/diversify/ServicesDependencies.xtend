package diversify

import java.util.ArrayList
import java.util.List
import java.util.Random



class ServicesDependencies {
	
	new(MutualisticGraph graph){
		this.graph = graph
		
	}
	
	int NDEP
	
	MutualisticGraph graph = null
	
	def List<Service> getAllDeps(Service service){
		if(service.dep.nullOrEmpty)
			return new ArrayList<Service>()
		val res = service.dep.map(e | e.allDeps).reduce(a,b| (a + b).toList)
		res.toSet.toList
	}
	
	def addToUnique(Service s, List<Service> dep){
		if(dep.contains(s))
			return false
		else
			dep.add(s)
		true
	}
	
	def generateDep(){
		
		NDEP = graph.config.NDEP
		
		val services = graph.services
		val ssize = services.size
		val random = new Random()
		
		var left = NDEP
		while(left > 0){
			val sfrom = services.get(random.nextInt(ssize))
			val sto = services.get(random.nextInt(ssize))
			
			if(sfrom != sto && !sto.dep.contains(sfrom)){
				sto.addToUnique(sfrom.dep)
				for(s : sto.dep)
					s.addToUnique(sfrom.dep)
				for(grands : graph.services.filter(e | e.dep.contains(sfrom)))
					for(s : sto.dep)
						s.addToUnique(grands.dep)
				left = left - 1
			}
		}
		
		services.forEach(e|System::out.println(e.dep.size))
		
	}
	
}