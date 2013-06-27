package diversify;

import diversify.MutatePlatformGraph;
import diversify.Platform;
import diversify.Service;
import diversify.TheMatrix;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.IntegerRange;

@SuppressWarnings("all")
public class Test {
  public static void main(final String[] args) {
    int _minus = (-1);
    IntegerRange _upTo = new IntegerRange(1, _minus);
    for (final Integer x : _upTo) {
      System.out.println(x);
    }
    ArrayList<Platform> _arrayList = new ArrayList<Platform>();
    final ArrayList<Platform> p = _arrayList;
    IntegerRange _upTo_1 = new IntegerRange(1, 10);
    for (final Integer i : _upTo_1) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("p");
      _builder.append(i, "");
      Platform _platform = new Platform(_builder.toString());
      p.add(_platform);
    }
    ArrayList<Service> _arrayList_1 = new ArrayList<Service>();
    final ArrayList<Service> s = _arrayList_1;
    IntegerRange _upTo_2 = new IntegerRange(1, 10);
    for (final Integer i_1 : _upTo_2) {
      Service _service = new Service();
      s.add(_service);
    }
    Platform _get = p.get(0);
    List<Service> _subList = s.subList(0, 6);
    _get.providedServices.addAll(_subList);
    TheMatrix _theMatrix = new TheMatrix(null, null);
    final TheMatrix matrix = _theMatrix;
    double _pltfDiversity = MutatePlatformGraph.getPltfDiversity(p);
    System.out.println(_pltfDiversity);
  }
}
