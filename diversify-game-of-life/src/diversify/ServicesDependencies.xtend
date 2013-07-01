package diversify

import java.util.ArrayList
import java.util.List
import java.util.Random
import org.eclipse.xtext.xbase.lib.Procedures$Procedure1



class ServicesDependencies {
	
	public static ServicesDependencies INSTANCE = null
	
	def static setInstance(ServicesDependencies instance){
		INSTANCE = instance
	}
	
	Configuration config = null
	
	new(Configuration config, Procedure1<ServicesDependencies> initializer){
		this.config = config
		initializer?.apply(this)
	}
	
	public int NDEP	= 10
	
	def List<Service> getAllDeps(Service service){
		if(service.dep.nullOrEmpty)
			return new ArrayList<Service>()
		val res = service.dep.map(e | e.allDeps).reduce(a,b| (a + b).toList)
		res.toSet.toList
	}
	
	def addToUnique(Service s, List<Service> dep){
		if(dep.contains(s)) 	
			false
		else{
			dep.add(s)
			true
		}
	}
	
	def generateDep(List<Service> services){		
		
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
				for(grands : services.filter(e | e.dep.contains(sfrom)))
					for(s : sto.dep)
						s.addToUnique(grands.dep)
				left = left - 1
			}
		}		
	}
	
	def reCreatePlatforms(List<Platform> platforms, List<Service> services){
		generateDep(services)
		val random = new Random()
		for(p : platforms){
			
			val original = new ArrayList<Service>(p.providedServices)
			val ceiling = random.nextInt(config.NMAXSERVICESPLATFORMS) + 9
			//val ceiling = config.NMAXSERVICESPLATFORMS
			p.providedServices.clear
			var tries = original.size() * 10
			while(p.providedServices.size < ceiling && tries > 0){
				val seed = original.get(random.nextInt(original.size()))
				if(!p.providedServices.contains(seed)){
					if(p.providedServices.size + seed.dep.size < ceiling){
						p.providedServices.add(seed)
						p.providedServices.addAll(seed.dep)
					}
				}
				tries = tries - 1
			}
		}
	}
	
}