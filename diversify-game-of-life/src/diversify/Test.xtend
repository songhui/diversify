package diversify

import java.util.HashSet
import java.util.ArrayList

class Test {
	def static void main(String[] args){
		
		for(x : 1..-1){
			System::out.println(x)
		}
		
		val p1 = new Platform("p1")
		val p2 = new Platform("p2")
		val s = new ArrayList<Service>()
		var i = 10
		while(i > 0){
			s.add(new Service())
			i = i-1
		}
		
		p1.providedServices.addAll(s.subList(0,6))
		p2.providedServices.addAll(s.subList(5,10))
		
		val matrix = new TheMatrix(null,null)
		System::out.println(MutatePlatformGraph::getOnePairPltfDiversity(p1,p1))
	}
}