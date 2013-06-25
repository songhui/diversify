package diversify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** provides n services */
public class Platform {

  String name;
  List<Service> providedServices = new ArrayList<Service>();
  List<Application> setapps;
  
  List<Application> supportedApps = new ArrayList<Application>();
  Map<Service,Integer> rebooting = new HashMap<Service,Integer>();
  int life = 0;
  

  public Platform(String s) {
    name = s;
  }

  public void addService(Service service) {
    providedServices.add(service);
  }

  public boolean supports(Application a) {
    return providedServices.containsAll(a.requiredServices);
  }

  public int setNumberSupportedApplications(List<Application> apps) {
    return getSupportedApplications(apps).size();
  }

  public List<Application> getSupportedApplications(List<Application> apps) {
    setapps = new ArrayList<Application>();
    for (Application a : apps) {
      if (this.supports(a)) {
        setapps.add(a);
      }
    }

    return setapps;
  }

  @Override
  public String toString() {
    return name + " "
        + "services:" + providedServices.size() + " "
        + "applications:" + setapps.size() + " ";
  };

}
