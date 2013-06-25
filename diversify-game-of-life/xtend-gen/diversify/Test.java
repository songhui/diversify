package diversify;

import diversify.MutatePlatformGraph;
import diversify.Platform;
import diversify.Service;
import diversify.TheMatrix;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.IntegerRange;

@SuppressWarnings("all")
public class Test {
  public static void main(final String[] args) {
    int _minus = (-1);
    IntegerRange _upTo = new IntegerRange(1, _minus);
    for (final Integer x : _upTo) {
      System.out.println(x);
    }
    Platform _platform = new Platform("p1");
    final Platform p1 = _platform;
    Platform _platform_1 = new Platform("p2");
    final Platform p2 = _platform_1;
    ArrayList<Service> _arrayList = new ArrayList<Service>();
    final ArrayList<Service> s = _arrayList;
    int i = 10;
    boolean _greaterThan = (i > 0);
    boolean _while = _greaterThan;
    while (_while) {
      {
        Service _service = new Service();
        s.add(_service);
        int _minus_1 = (i - 1);
        i = _minus_1;
      }
      boolean _greaterThan_1 = (i > 0);
      _while = _greaterThan_1;
    }
    List<Service> _subList = s.subList(0, 6);
    p1.providedServices.addAll(_subList);
    List<Service> _subList_1 = s.subList(5, 10);
    p2.providedServices.addAll(_subList_1);
    TheMatrix _theMatrix = new TheMatrix(null, null);
    final TheMatrix matrix = _theMatrix;
    double _onePairPltfDiversity = MutatePlatformGraph.getOnePairPltfDiversity(p1, p1);
    System.out.println(_onePairPltfDiversity);
  }
}
