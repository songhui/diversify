package diversify;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import diversify.Configuration;
import diversify.Platform;
import diversify.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class ServicesDependencies {
  public static ServicesDependencies INSTANCE = null;
  
  public static ServicesDependencies setInstance(final ServicesDependencies instance) {
    ServicesDependencies _INSTANCE = ServicesDependencies.INSTANCE = instance;
    return _INSTANCE;
  }
  
  private Configuration config = null;
  
  public ServicesDependencies(final Configuration config, final Procedure1<ServicesDependencies> initializer) {
    this.config = config;
    if (initializer!=null) initializer.apply(this);
  }
  
  public int NDEP = 10;
  
  public List<Service> getAllDeps(final Service service) {
    List<Service> _xblockexpression = null;
    {
      boolean _isNullOrEmpty = IterableExtensions.isNullOrEmpty(service.dep);
      if (_isNullOrEmpty) {
        ArrayList<Service> _arrayList = new ArrayList<Service>();
        return _arrayList;
      }
      final Function1<Service,List<Service>> _function = new Function1<Service,List<Service>>() {
          public List<Service> apply(final Service e) {
            List<Service> _allDeps = ServicesDependencies.this.getAllDeps(e);
            return _allDeps;
          }
        };
      List<List<Service>> _map = ListExtensions.<Service, List<Service>>map(service.dep, _function);
      final Function2<List<Service>,List<Service>,List<Service>> _function_1 = new Function2<List<Service>,List<Service>,List<Service>>() {
          public List<Service> apply(final List<Service> a, final List<Service> b) {
            Iterable<Service> _plus = Iterables.<Service>concat(a, b);
            List<Service> _list = IterableExtensions.<Service>toList(_plus);
            return _list;
          }
        };
      final List<Service> res = IterableExtensions.<List<Service>>reduce(_map, _function_1);
      Set<Service> _set = IterableExtensions.<Service>toSet(res);
      List<Service> _list = IterableExtensions.<Service>toList(_set);
      _xblockexpression = (_list);
    }
    return _xblockexpression;
  }
  
  public boolean addToUnique(final Service s, final List<Service> dep) {
    boolean _xifexpression = false;
    boolean _contains = dep.contains(s);
    if (_contains) {
      _xifexpression = false;
    } else {
      boolean _xblockexpression = false;
      {
        dep.add(s);
        _xblockexpression = (true);
      }
      _xifexpression = _xblockexpression;
    }
    return _xifexpression;
  }
  
  public void generateDep(final List<Service> services) {
    final int ssize = services.size();
    Random _random = new Random();
    final Random random = _random;
    int left = this.NDEP;
    boolean _greaterThan = (left > 0);
    boolean _while = _greaterThan;
    while (_while) {
      {
        int _nextInt = random.nextInt(ssize);
        final Service sfrom = services.get(_nextInt);
        int _nextInt_1 = random.nextInt(ssize);
        final Service sto = services.get(_nextInt_1);
        boolean _and = false;
        boolean _notEquals = (!Objects.equal(sfrom, sto));
        if (!_notEquals) {
          _and = false;
        } else {
          boolean _contains = sto.dep.contains(sfrom);
          boolean _not = (!_contains);
          _and = (_notEquals && _not);
        }
        if (_and) {
          this.addToUnique(sto, sfrom.dep);
          for (final Service s : sto.dep) {
            this.addToUnique(s, sfrom.dep);
          }
          final Function1<Service,Boolean> _function = new Function1<Service,Boolean>() {
              public Boolean apply(final Service e) {
                boolean _contains = e.dep.contains(sfrom);
                return Boolean.valueOf(_contains);
              }
            };
          Iterable<Service> _filter = IterableExtensions.<Service>filter(services, _function);
          for (final Service grands : _filter) {
            for (final Service s_1 : sto.dep) {
              this.addToUnique(s_1, grands.dep);
            }
          }
          int _minus = (left - 1);
          left = _minus;
        }
      }
      boolean _greaterThan_1 = (left > 0);
      _while = _greaterThan_1;
    }
  }
  
  public void reCreatePlatforms(final List<Platform> platforms, final List<Service> services) {
    this.generateDep(services);
    Random _random = new Random();
    final Random random = _random;
    for (final Platform p : platforms) {
      {
        ArrayList<Service> _arrayList = new ArrayList<Service>(p.providedServices);
        final ArrayList<Service> original = _arrayList;
        p.providedServices.clear();
        int _size = original.size();
        int tries = (_size * 10);
        boolean _and = false;
        int _size_1 = p.providedServices.size();
        boolean _lessThan = (_size_1 < this.config.NMAXSERVICESPLATFORMS);
        if (!_lessThan) {
          _and = false;
        } else {
          boolean _greaterThan = (tries > 0);
          _and = (_lessThan && _greaterThan);
        }
        boolean _while = _and;
        while (_while) {
          {
            int _size_2 = original.size();
            int _nextInt = random.nextInt(_size_2);
            final Service seed = original.get(_nextInt);
            boolean _contains = p.providedServices.contains(seed);
            boolean _not = (!_contains);
            if (_not) {
              int _size_3 = p.providedServices.size();
              int _size_4 = seed.dep.size();
              int _plus = (_size_3 + _size_4);
              boolean _lessThan_1 = (_plus < this.config.NMAXSERVICESPLATFORMS);
              if (_lessThan_1) {
                p.providedServices.add(seed);
                p.providedServices.addAll(seed.dep);
              }
            }
            int _minus = (tries - 1);
            tries = _minus;
          }
          boolean _and_1 = false;
          int _size_2 = p.providedServices.size();
          boolean _lessThan_1 = (_size_2 < this.config.NMAXSERVICESPLATFORMS);
          if (!_lessThan_1) {
            _and_1 = false;
          } else {
            boolean _greaterThan_1 = (tries > 0);
            _and_1 = (_lessThan_1 && _greaterThan_1);
          }
          _while = _and_1;
        }
      }
    }
  }
}
