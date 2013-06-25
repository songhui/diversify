package diversify

import org.eclipse.xtext.xbase.lib.Procedures$Procedure1
import java.util.List
import java.util.ArrayList

class NewMain {
	
	def static NoMutate(){}

	def static void main(String[] args){
		val config = createConfiguration()[
			NPLATFORMS = 50
			NSERVICES = 200
			NAPPS = 1000
			NMAXSERVICESAPP = 7
			NMAXSERVICESPLATFORMS = 30
			ordering = PlatformOrdering::DOCUMENT_ORDER
		]
		var mutator = new MutatePlatformGraph(config)[
			NSEED = 5
			NMUTATION = 10
		]
		var depender = new ServicesDependencies(config)[
			NDEP = 400
		]
		
		
		//MutatePlatformGraph::setInstance(mutator)     //mute (not commented) or random (commented)
		
		ServicesDependencies::setInstance(depender)   //dependent or not (commented)
		
		
		val sim = new RepeatedSimulation(200)
		
		//which simulator
		sim.sim = new PlatformFailureSimulation()		
		//sim.sim = new GameOfLifeSimulation()
		
		val result = sim.run().simulationResult
		
    	//new Plot().run(result, sim.description())
    	val plain = getPlainResult(result)
    	val average = getAverage(plain)
    	var i = 0
    	
    	
    	val dissims = MutatePlatformGraph::dissims
    	
    	println(dissims)
    	println(dissims.reduce(x,y|x+y)/dissims.size)
    	
    	for(x : average){
    		System::out.println('''«i»	«x»''')
    		i = i + 1
    	}
    	
		
	}

	def static createConfiguration(Procedure1<Configuration> initializer){
		val config = new Configuration()
		initializer?.apply(config)
		Configuration::setInstance(config)
		return config
	}
	
	def static getPlainResult(List<int[][]> result){
		val plain = new ArrayList<ArrayList<Integer>>()
		for(e : result){
			val x = new ArrayList<Integer>()
			for(i:1..e.size-1)
				x.add(e.get(i).get(1))
			x.add(0)
			plain.add(x)
		}
		
		plain		
	}

	def static getAverage(ArrayList<ArrayList<Integer>> result){
		val total = new ArrayList<Integer>(result.get(0));
		for(i : 1..result.size-1)
			for(j:0..result.get(i).size-1)
				total.set(j, total.get(j) + result.get(i).get(j))
				
		total.map(e | e.doubleValue / result.size)
	}
}