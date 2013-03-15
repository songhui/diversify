package diversify;

import java.util.Comparator;

/** configures the simulation */
public class Configuration {
  /** the number of apps in the system */
  int NAPPS = 400;

  /** the number of platforms in the system */
  int NPLATFORMS = 25;

  /** the number of services in the systems*/
  int NSERVICES = 15;

  /** platforms have [1,NMAXSERVICESPLATFORMS] services 
   * with a uniform distribution */
  int NMAXSERVICESPLATFORMS = 10;

  /** Applications have [1,NMAXSERVICESAPP] services 
   * with a uniform distribution */
  int NMAXSERVICESAPP = 3;

  /** orders platforms for sake of ordered platform failures */
  PlatformOrdering ordering = PlatformOrdering.LEAST_CONNECTED_FIRST;

  
  @Override
  public String toString() {
    return "NAPPS=" + NAPPS + " - "
        + "NPLATFORMS=" + NPLATFORMS + " - "
        + "NSERVICES=" + NSERVICES + " - "
        + "NMAXSERVICESPLATFORMS=" + NMAXSERVICESPLATFORMS + " - "
        + "NMAXSERVICESAPP=" + NMAXSERVICESAPP;
  }
}

abstract class PlatformOrdering implements Comparator<Platform>{
  private PlatformOrdering() {
  }

  public static final PlatformOrdering MOST_CONNECTED_FIRST = new PlatformOrdering() {
    @Override
    public int compare(Platform o, Platform thi) {
      return -(o.setapps.size() - thi.setapps.size());
    }
  };

  public static final PlatformOrdering LEAST_CONNECTED_FIRST = new PlatformOrdering() {
    @Override
    public int compare(Platform o, Platform thi) {
      return (o.setapps.size() - thi.setapps.size());
    }
  };

  public static final PlatformOrdering MOST_SERVING_FIRST = new PlatformOrdering() {
    @Override
    public int compare(Platform o, Platform thi) {
      return -(o.providedServices.size() - thi.providedServices.size());
    }
  };

  public static final PlatformOrdering LEAST_SERVING_FIRST = new PlatformOrdering() {
    @Override
    public int compare(Platform o, Platform thi) {
      return (o.providedServices.size() - thi.providedServices.size());
    }
  };

}
