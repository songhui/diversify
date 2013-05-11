package diversify;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** represents the relationships application/platforms */
public class MutualisticGraph {
	
  ServicesDependencies sd = new ServicesDependencies(this);

  List<Application> applications = new ArrayList<Application>();
  List<Platform> platforms = new ArrayList<Platform>();
  List<Service> services = new ArrayList<Service>();

  Configuration config = new Configuration();  
  
  public MutualisticGraph() {
    init();
  }
  
  private void init(){
	  for (int i = 0; i < config.NSERVICES; i++) {
	      services.add(new Service());
	    }
	  
	  sd.generateDep();

	    for (int i = 0; i < config.NPLATFORMS; i++) {
	      platforms.add(createPlatform("Platform" + i));
	    }

	    for (int i = 0; i < config.NAPPS; i++) {
	      applications.add(createApp());
	    }

	    setLinks();
	    
	  
  }
  
  public MutualisticGraph(Configuration config){
	  this.config=config;
	  init();
  }
  
  public void changeConfig(Configuration config){
	  services.clear();
	  platforms.clear();
	  applications.clear();
	  this.config=config;
	  
	  init();
  }


  Random r = new Random();

  private Platform createPlatform(String s) {
    Platform p = new Platform(s);
    int nservices = r.nextInt(config.NMAXSERVICESPLATFORMS - 1) + 1;
    
    while(p.providedServices.size()<nservices){
    	Service service = services.get(r.nextInt(services.size()));
    	p.addService(service);
    	for(Service dep : service.dep)
    		p.addService(dep);
    }
    
    return p;
  }

  private Application createApp() {
    Application a = new Application();
    int nservices = r.nextInt(config.NMAXSERVICESAPP - 1) + 1;
    for (int i = 0; i < nservices; i++) {
      a.addService(services.get(r.nextInt(services.size())));
    }
    return a;
  }

  public void setLinks() {
    for (Platform p : platforms) {
      p.setNumberSupportedApplications(applications);
    }
  }

}
