package diversify;

import java.util.Comparator;

/** configures the simulation */
public class Configuration {
	
  public static Configuration INSTANCE = null;
  public static void setInstance(Configuration config){
	  INSTANCE = config;
  }
	
  int NTOTALLIFE = 1000;
  int NMAXREBORN = 10;
  int NPERCENTDOWN = 10;
  int NSICKTODIE = 3;
	
	
  /** the number of apps in the system */
  int NAPPS = 1000;

  /** the number of platforms in the system */
  int NPLATFORMS = 25;

  /** the number of services in the systems*/
  int NSERVICES = 100;

  /** platforms have [1,NMAXSERVICESPLATFORMS] services 
   * with a uniform distribution */
  int NMAXSERVICESPLATFORMS = 30;

  /** Applications have [1,NMAXSERVICESAPP] services 
   * with a uniform distribution */
  int NMAXSERVICESAPP = 10;
  
  int NDEP = 0;

  /** orders platforms for sake of ordered platform failures */
  PlatformOrdering ordering = PlatformOrdering.DOCUMENT_ORDER;

  
  @Override
  public String toString() {
    return "NAPPS=" + NAPPS + " - "
        + "NPLATFORMS=" + NPLATFORMS + " - "
        + "NSERVICES=" + NSERVICES + " - "
        + "NMAXSERVICESPLATFORMS=" + NMAXSERVICESPLATFORMS + " - "
        + "NMAXSERVICESAPP=" + NMAXSERVICESAPP + " - "
        + "NDEP=" + NDEP;
  }
  
  
}

