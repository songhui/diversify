package diversify


import org.eclipse.xtext.xbase.lib.Procedures$Procedure1
import java.util.Random
import java.util.ArrayList
import java.util.List

class MutatePlatformGraph{
	
	public static MutatePlatformGraph INSTANCE = null
	public static List<Double> dissims = new ArrayList<Double>()
	public static List<Integer> size = new ArrayList<Integer>()
	
	
	def static setInstance(MutatePlatformGraph instance){
		INSTANCE = instance
	}
	
	public int NSEED = 3
	public int NADDNEW = 3
	public int NREMOVE = 3
	public int NMUTATION = 3
	Random random = new Random()
	Configuration config = null
	
	new(Configuration config, Procedure1<MutatePlatformGraph> initializer){
		this.config = config
		initializer?.apply(this)
	}
	
	def static getOnePairPltfDiversity(Platform a, Platform b){
		val serva1 = a.providedServices.toSet()
		val serva2 = a.providedServices.toSet()
		val servb = b.providedServices.toSet()
		
		serva1.retainAll(servb)
		serva2.addAll(servb)
		if(serva2.size == 0)
			0
		else 
			1 - serva1.size().doubleValue/serva2.size()
	}
	
	def static getPltfDiversity(List<Platform> ps){
		var double total = 0
		for(p1:ps) for(p2:ps)
			total = total + MutatePlatformGraph::getOnePairPltfDiversity(p1,p2)
		total / (ps.size * (ps.size -1) )
	}
	
	def reCreatePlatforms(List<Platform> platforms, List<Service> services){
		
		val left = new ArrayList<Platform>(platforms.subList(0,NSEED))
		platforms.clear
		platforms.addAll(left)
		for(x : NSEED..config.NPLATFORMS-1){
			val np = new Platform('''Platform «x»  ''')
			val seed = platforms.get(random.nextInt(platforms.size))
			np.providedServices = new ArrayList<Service>(seed.providedServices)
			val mutateTimes = random.nextInt(NMUTATION)
			if(mutateTimes > 0) for(i : 1..mutateTimes){
				val existSize = np.providedServices.size
				val place = random.nextInt(existSize + NADDNEW + NREMOVE)-NREMOVE
				switch place{
					case place < 0: 
						if(np.providedServices.size > 1)
							np.providedServices.remove(0)
					case place >= existSize: 
						if(existSize < config.NMAXSERVICESPLATFORMS)
							np.providedServices.add(services.get(random.nextInt(services.size)))
					default:{
						val service = services.get(random.nextInt(services.size))
						np.providedServices.set(place,service)
					}
						
				}
			}
			platforms.add(np)
		}
	}
}