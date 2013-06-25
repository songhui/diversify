package diversify;

import java.util.ArrayList;
import java.util.List;

/** is provided platforms and used by applications. */
public class Service {
	public List<Service> dep = new ArrayList<Service>();
	public boolean dead = false;
}
