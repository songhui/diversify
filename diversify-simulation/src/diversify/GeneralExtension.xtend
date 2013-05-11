package diversify

import org.eclipse.xtext.xbase.lib.Procedures$Procedure1

class GeneralExtension {
	def createConfiguration(MutualisticGraph graph, Procedure1<Configuration> initializer){
		val config = new Configuration()
		initializer?.apply(config)
		graph.changeConfig(config)
		
	}
	
}