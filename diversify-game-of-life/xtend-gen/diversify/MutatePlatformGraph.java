package diversify;

import com.google.common.collect.Iterables;
import diversify.Configuration;
import diversify.Platform;
import diversify.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class MutatePlatformGraph {
  public static MutatePlatformGraph INSTANCE = null;
  
  public static List<Double> dissims = new Function0<List<Double>>() {
    public List<Double> apply() {
      ArrayList<Double> _arrayList = new ArrayList<Double>();
      return _arrayList;
    }
  }.apply();
  
  public static List<Integer> size = new Function0<List<Integer>>() {
    public List<Integer> apply() {
      ArrayList<Integer> _arrayList = new ArrayList<Integer>();
      return _arrayList;
    }
  }.apply();
  
  public static MutatePlatformGraph setInstance(final MutatePlatformGraph instance) {
    MutatePlatformGraph _INSTANCE = MutatePlatformGraph.INSTANCE = instance;
    return _INSTANCE;
  }
  
  public int NSEED = 3;
  
  public int NADDNEW = 3;
  
  public int NREMOVE = 3;
  
  public int NMUTATION = 3;
  
  private Random random = new Function0<Random>() {
    public Random apply() {
      Random _random = new Random();
      return _random;
    }
  }.apply();
  
  private Configuration config = null;
  
  public MutatePlatformGraph(final Configuration config, final Procedure1<MutatePlatformGraph> initializer) {
    this.config = config;
    if (initializer!=null) initializer.apply(this);
  }
  
  public static double getOnePairPltfDiversity(final Platform a, final Platform b) {
    double _xblockexpression = (double) 0;
    {
      final Set<Service> serva1 = IterableExtensions.<Service>toSet(a.providedServices);
      final Set<Service> serva2 = IterableExtensions.<Service>toSet(a.providedServices);
      final Set<Service> servb = IterableExtensions.<Service>toSet(b.providedServices);
      serva1.retainAll(servb);
      Iterables.<Service>addAll(serva2, servb);
      double _xifexpression = (double) 0;
      int _size = serva2.size();
      boolean _equals = (_size == 0);
      if (_equals) {
        _xifexpression = 0;
      } else {
        int _size_1 = serva1.size();
        double _doubleValue = Integer.valueOf(_size_1).doubleValue();
        int _size_2 = serva2.size();
        double _divide = (_doubleValue / _size_2);
        double _minus = (1 - _divide);
        _xifexpression = _minus;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  public static double getPltfDiversity(final List<Platform> ps) {
    double _xblockexpression = (double) 0;
    {
      double total = 0;
      for (final Platform p1 : ps) {
        for (final Platform p2 : ps) {
          double _onePairPltfDiversity = MutatePlatformGraph.getOnePairPltfDiversity(p1, p2);
          double _plus = (total + _onePairPltfDiversity);
          total = _plus;
        }
      }
      int _size = ps.size();
      int _size_1 = ps.size();
      int _minus = (_size_1 - 1);
      int _multiply = (_size * _minus);
      double _divide = (total / _multiply);
      _xblockexpression = (_divide);
    }
    return _xblockexpression;
  }
  
  public void reCreatePlatforms(final List<Platform> platforms, final List<Service> services) {
    List<Platform> _subList = platforms.subList(0, this.NSEED);
    ArrayList<Platform> _arrayList = new ArrayList<Platform>(_subList);
    final ArrayList<Platform> left = _arrayList;
    platforms.clear();
    platforms.addAll(left);
    int _minus = (this.config.NPLATFORMS - 1);
    IntegerRange _upTo = new IntegerRange(this.NSEED, _minus);
    for (final Integer x : _upTo) {
      {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("Platform ");
        _builder.append(x, "");
        _builder.append("  ");
        Platform _platform = new Platform(_builder.toString());
        final Platform np = _platform;
        int _size = platforms.size();
        int _nextInt = this.random.nextInt(_size);
        final Platform seed = platforms.get(_nextInt);
        ArrayList<Service> _arrayList_1 = new ArrayList<Service>(seed.providedServices);
        np.providedServices = _arrayList_1;
        final int mutateTimes = this.random.nextInt(this.NMUTATION);
        boolean _greaterThan = (mutateTimes > 0);
        if (_greaterThan) {
          IntegerRange _upTo_1 = new IntegerRange(1, mutateTimes);
          for (final Integer i : _upTo_1) {
            {
              final int existSize = np.providedServices.size();
              int _plus = (existSize + this.NADDNEW);
              int _plus_1 = (_plus + this.NREMOVE);
              int _nextInt_1 = this.random.nextInt(_plus_1);
              final int place = (_nextInt_1 - this.NREMOVE);
              boolean _matched = false;
              if (!_matched) {
                boolean _lessThan = (place < 0);
                if (_lessThan) {
                  _matched=true;
                  int _size_1 = np.providedServices.size();
                  boolean _greaterThan_1 = (_size_1 > 1);
                  if (_greaterThan_1) {
                    np.providedServices.remove(0);
                  }
                }
              }
              if (!_matched) {
                boolean _greaterEqualsThan = (place >= existSize);
                if (_greaterEqualsThan) {
                  _matched=true;
                  boolean _lessThan_1 = (existSize < this.config.NMAXSERVICESPLATFORMS);
                  if (_lessThan_1) {
                    int _size_2 = services.size();
                    int _nextInt_2 = this.random.nextInt(_size_2);
                    Service _get = services.get(_nextInt_2);
                    np.providedServices.add(_get);
                  }
                }
              }
              if (!_matched) {
                {
                  int _size_3 = services.size();
                  int _nextInt_3 = this.random.nextInt(_size_3);
                  final Service service = services.get(_nextInt_3);
                  np.providedServices.set(place, service);
                }
              }
            }
          }
        }
        platforms.add(np);
      }
    }
  }
}
