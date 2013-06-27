package diversify

import java.util.HashSet
import java.util.ArrayList

class Test {
	def static void main(String[] args){
		
		for(x : 1..-1){
			System::out.println(x)
		}
		
		val p = new ArrayList<Platform>();
		for(i:1..10)
			p.add(new Platform('''p«i»'''));
		
		val s = new ArrayList<Service>()
		
		for(i:1..10){
			s.add(new Service())
		}
		
		p.get(0).providedServices.addAll(s.subList(0,6))
		//p2.providedServices.addAll(s.subList(5,10))
		
		
		val matrix = new TheMatrix(null,null)
		System::out.println(MutatePlatformGraph::getPltfDiversity(p))
	}
}