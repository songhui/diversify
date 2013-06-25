package diversify;

import java.util.ArrayList;
import java.util.List;

/** requires some services to run */
public class Application {
  List<Service> requiredServices = new ArrayList<Service>();

  public void addService(Service service) {
    requiredServices.add(service);
  }

}
