package diversify;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import diversify.Application;
import diversify.Configuration;
import diversify.MutatePlatformGraph;
import diversify.MutualisticGraph;
import diversify.Platform;
import diversify.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class TheMatrix {
  private static int round = 0;
  
  private Random random = new Function0<Random>() {
    public Random apply() {
      Random _random = new Random();
      return _random;
    }
  }.apply();
  
  private Configuration config = new Function0<Configuration>() {
    public Configuration apply() {
      Configuration _configuration = new Configuration();
      return _configuration;
    }
  }.apply();
  
  private MutualisticGraph graph = null;
  
  public TheMatrix(final MutualisticGraph graph, final Procedure1<Configuration> initializer) {
    this.graph = graph;
    if (initializer!=null) initializer.apply(this.config);
  }
  
  public boolean killServiceFromPlatform(final Platform p) {
    boolean _xblockexpression = false;
    {
      for (final Service s : p.providedServices) {
        int _nextInt = this.random.nextInt(100);
        boolean _lessThan = (_nextInt < this.config.NPERCENTDOWN);
        if (_lessThan) {
          int _nextInt_1 = this.random.nextInt(this.config.NMAXREBORN);
          p.rebooting.put(s, Integer.valueOf(_nextInt_1));
        }
      }
      Set<Service> _keySet = p.rebooting.keySet();
      boolean _removeAll = p.providedServices.removeAll(_keySet);
      _xblockexpression = (_removeAll);
    }
    return _xblockexpression;
  }
  
  public void reboot(final Platform p) {
    Set<Entry<Service,Integer>> _entrySet = p.rebooting.entrySet();
    final Procedure1<Entry<Service,Integer>> _function = new Procedure1<Entry<Service,Integer>>() {
        public void apply(final Entry<Service,Integer> e) {
          Integer _value = e.getValue();
          int _minus = ((_value).intValue() - 1);
          e.setValue(Integer.valueOf(_minus));
        }
      };
    IterableExtensions.<Entry<Service,Integer>>forEach(_entrySet, _function);
    Set<Entry<Service,Integer>> _entrySet_1 = p.rebooting.entrySet();
    final Function1<Entry<Service,Integer>,Boolean> _function_1 = new Function1<Entry<Service,Integer>,Boolean>() {
        public Boolean apply(final Entry<Service,Integer> e) {
          Integer _value = e.getValue();
          boolean _lessEqualsThan = ((_value).intValue() <= 0);
          return Boolean.valueOf(_lessEqualsThan);
        }
      };
    Iterable<Entry<Service,Integer>> _filter = IterableExtensions.<Entry<Service,Integer>>filter(_entrySet_1, _function_1);
    final Function1<Entry<Service,Integer>,Service> _function_2 = new Function1<Entry<Service,Integer>,Service>() {
        public Service apply(final Entry<Service,Integer> e) {
          Service _key = e.getKey();
          return _key;
        }
      };
    Iterable<Service> _map = IterableExtensions.<Entry<Service,Integer>, Service>map(_filter, _function_2);
    final List<Service> toLive = IterableExtensions.<Service>toList(_map);
    p.providedServices.addAll(toLive);
    for (final Service s : toLive) {
      p.rebooting.remove(s);
    }
  }
  
  public int updateSupportedApps(final Platform p, final List<Application> apps) {
    int _xblockexpression = (int) 0;
    {
      p.supportedApps.clear();
      final Function1<Application,Boolean> _function = new Function1<Application,Boolean>() {
          public Boolean apply(final Application app) {
            boolean _containsAll = p.providedServices.containsAll(app.requiredServices);
            return Boolean.valueOf(_containsAll);
          }
        };
      Iterable<Application> _filter = IterableExtensions.<Application>filter(apps, _function);
      Iterables.<Application>addAll(p.supportedApps, _filter);
      int _xifexpression = (int) 0;
      int _size = p.supportedApps.size();
      boolean _equals = (_size == 0);
      if (_equals) {
        int _xifexpression_1 = (int) 0;
        boolean _greaterThan = (p.life > this.config.NSICKTODIE);
        if (_greaterThan) {
          int _life = p.life = this.config.NSICKTODIE;
          _xifexpression_1 = _life;
        } else {
          int _minus = (p.life - 1);
          int _life_1 = p.life = _minus;
          _xifexpression_1 = _life_1;
        }
        _xifexpression = _xifexpression_1;
      } else {
        int _life_2 = p.life = 10000;
        _xifexpression = _life_2;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  public boolean killPlatforms(final List<Platform> ps) {
    final Function1<Platform,Boolean> _function = new Function1<Platform,Boolean>() {
        public Boolean apply(final Platform e) {
          boolean _lessEqualsThan = (e.life <= 0);
          return Boolean.valueOf(_lessEqualsThan);
        }
      };
    Iterable<Platform> _filter = IterableExtensions.<Platform>filter(ps, _function);
    List<Platform> _list = IterableExtensions.<Platform>toList(_filter);
    boolean _removeAll = ps.removeAll(_list);
    return _removeAll;
  }
  
  public boolean killApps(final List<Application> apps, final List<Platform> ps) {
    boolean _xblockexpression = false;
    {
      HashSet<Application> _hashSet = new HashSet<Application>();
      final HashSet<Application> active = _hashSet;
      final Function1<Platform,Set<Application>> _function = new Function1<Platform,Set<Application>>() {
          public Set<Application> apply(final Platform e) {
            Set<Application> _set = IterableExtensions.<Application>toSet(e.supportedApps);
            return _set;
          }
        };
      List<Set<Application>> _map = ListExtensions.<Platform, Set<Application>>map(ps, _function);
      final Procedure1<Set<Application>> _function_1 = new Procedure1<Set<Application>>() {
          public void apply(final Set<Application> e) {
            Iterables.<Application>addAll(active, e);
          }
        };
      IterableExtensions.<Set<Application>>forEach(_map, _function_1);
      apps.clear();
      boolean _addAll = apps.addAll(active);
      _xblockexpression = (_addAll);
    }
    return _xblockexpression;
  }
  
  public List<Integer> run() {
    List<Integer> _xblockexpression = null;
    {
      boolean _notEquals = (!Objects.equal(MutatePlatformGraph.INSTANCE, null));
      if (_notEquals) {
        MutatePlatformGraph.INSTANCE.reCreatePlatforms(this.graph.platforms, this.graph.services);
      }
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(TheMatrix.round, "");
      _builder.append(": ");
      double _pltfDiversity = MutatePlatformGraph.getPltfDiversity(this.graph.platforms);
      _builder.append(_pltfDiversity, "");
      System.out.println(_builder.toString());
      int _plus = (TheMatrix.round + 1);
      TheMatrix.round = _plus;
      int time = 0;
      ArrayList<Integer> _arrayList = new ArrayList<Integer>();
      final List<Integer> nApps = _arrayList;
      boolean _lessThan = (time < this.config.NTOTALLIFE);
      boolean _while = _lessThan;
      while (_while) {
        {
          final Procedure1<Platform> _function = new Procedure1<Platform>() {
              public void apply(final Platform p) {
                TheMatrix.this.reboot(p);
              }
            };
          IterableExtensions.<Platform>forEach(this.graph.platforms, _function);
          final Procedure1<Platform> _function_1 = new Procedure1<Platform>() {
              public void apply(final Platform p) {
                TheMatrix.this.killServiceFromPlatform(p);
              }
            };
          IterableExtensions.<Platform>forEach(this.graph.platforms, _function_1);
          final Procedure1<Platform> _function_2 = new Procedure1<Platform>() {
              public void apply(final Platform p) {
                TheMatrix.this.updateSupportedApps(p, TheMatrix.this.graph.applications);
              }
            };
          IterableExtensions.<Platform>forEach(this.graph.platforms, _function_2);
          this.killApps(this.graph.applications, this.graph.platforms);
          this.killPlatforms(this.graph.platforms);
          int _size = this.graph.applications.size();
          nApps.add(Integer.valueOf(_size));
          int _plus_1 = (time + 1);
          time = _plus_1;
        }
        boolean _lessThan_1 = (time < this.config.NTOTALLIFE);
        _while = _lessThan_1;
      }
      _xblockexpression = (nApps);
    }
    return _xblockexpression;
  }
}
