package diversify;

import java.util.Comparator;

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
	  
	  public static final PlatformOrdering DOCUMENT_ORDER = new PlatformOrdering() {
		    @Override
		    public int compare(Platform o, Platform thi) {
		      return o.name.compareTo(thi.name);
		    }
		  };

	}

