package diversify;

import diversify.Configuration;
import diversify.GameOfLifeSimulation;
import diversify.ISimulation;
import diversify.MutatePlatformGraph;
import diversify.PlatformOrdering;
import diversify.Plot;
import diversify.RepeatedSimulation;
import diversify.ServicesDependencies;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.DoubleExtensions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class NewMain {
  public static Object NoMutate() {
    return null;
  }
  
  public static void main(final String[] args) {
    try {
      final Procedure1<Configuration> _function = new Procedure1<Configuration>() {
          public void apply(final Configuration it) {
            it.NPLATFORMS = 50;
            it.NSERVICES = 200;
            it.NAPPS = 1000;
            it.NMAXSERVICESAPP = 7;
            it.NMAXSERVICESPLATFORMS = 30;
            it.ordering = PlatformOrdering.HASHCODE_ORDER;
          }
        };
      final Configuration config = NewMain.createConfiguration(_function);
      final Procedure1<MutatePlatformGraph> _function_1 = new Procedure1<MutatePlatformGraph>() {
          public void apply(final MutatePlatformGraph it) {
            it.NSEED = 1;
            it.NMUTATION = 50;
            it.NADDNEW = 3;
            it.NREMOVE = 3;
          }
        };
      MutatePlatformGraph _mutatePlatformGraph = new MutatePlatformGraph(config, _function_1);
      MutatePlatformGraph mutator = _mutatePlatformGraph;
      final Procedure1<ServicesDependencies> _function_2 = new Procedure1<ServicesDependencies>() {
          public void apply(final ServicesDependencies it) {
            it.NDEP = 100;
          }
        };
      ServicesDependencies _servicesDependencies = new ServicesDependencies(config, _function_2);
      ServicesDependencies depender = _servicesDependencies;
      MutatePlatformGraph.setInstance(mutator);
      RepeatedSimulation _repeatedSimulation = new RepeatedSimulation(20);
      final RepeatedSimulation sim = _repeatedSimulation;
      final Procedure1<Configuration> _function_3 = new Procedure1<Configuration>() {
          public void apply(final Configuration it) {
            it.NTOTALLIFE = 1000;
            it.NMAXREBORN = 10;
            it.NPERCENTDOWN = 10;
          }
        };
      GameOfLifeSimulation _gameOfLifeSimulation = new GameOfLifeSimulation(_function_3);
      sim.sim = _gameOfLifeSimulation;
      ISimulation<List<int[][]>> _run = sim.run();
      final List<int[][]> result = _run.getSimulationResult();
      Plot _plot = new Plot();
      String _description = sim.description();
      _plot.run(result, _description);
      final ArrayList<ArrayList<Integer>> plain = NewMain.getPlainResult(result);
      final List<Double> average = NewMain.getAverage(plain);
      int i = 0;
      final List<Double> dissims = MutatePlatformGraph.dissims;
      InputOutput.<List<Double>>println(dissims);
      final Function2<Double,Double,Double> _function_4 = new Function2<Double,Double,Double>() {
          public Double apply(final Double x, final Double y) {
            double _plus = DoubleExtensions.operator_plus(x, y);
            return Double.valueOf(_plus);
          }
        };
      Double _reduce = IterableExtensions.<Double>reduce(dissims, _function_4);
      int _size = dissims.size();
      double _divide = ((_reduce).doubleValue() / _size);
      InputOutput.<Double>println(Double.valueOf(_divide));
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("general/data/gol-");
      _builder.append(mutator.NMUTATION, "");
      _builder.append(".data");
      String _string = _builder.toString();
      PrintWriter _printWriter = new PrintWriter(_string, "UTF-8");
      final PrintWriter writer = _printWriter;
      for (final Double x : average) {
        {
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append(i, "");
          _builder_1.append("\t");
          _builder_1.append(x, "");
          writer.println(_builder_1.toString());
          int _plus = (i + 1);
          i = _plus;
        }
      }
      writer.close();
      InputOutput.<String>println("finished");
    } catch (Exception _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public static Configuration createConfiguration(final Procedure1<Configuration> initializer) {
    Configuration _configuration = new Configuration();
    final Configuration config = _configuration;
    if (initializer!=null) initializer.apply(config);
    Configuration.setInstance(config);
    return config;
  }
  
  public static ArrayList<ArrayList<Integer>> getPlainResult(final List<int[][]> result) {
    ArrayList<ArrayList<Integer>> _xblockexpression = null;
    {
      ArrayList<ArrayList<Integer>> _arrayList = new ArrayList<ArrayList<Integer>>();
      final ArrayList<ArrayList<Integer>> plain = _arrayList;
      for (final int[][] e : result) {
        {
          ArrayList<Integer> _arrayList_1 = new ArrayList<Integer>();
          final ArrayList<Integer> x = _arrayList_1;
          int _size = ((List<int[]>)Conversions.doWrapArray(e)).size();
          int _minus = (_size - 1);
          IntegerRange _upTo = new IntegerRange(1, _minus);
          for (final Integer i : _upTo) {
            int[] _get = ((List<int[]>)Conversions.doWrapArray(e)).get((i).intValue());
            Integer _get_1 = ((List<Integer>)Conversions.doWrapArray(_get)).get(1);
            x.add(_get_1);
          }
          x.add(Integer.valueOf(0));
          plain.add(x);
        }
      }
      _xblockexpression = (plain);
    }
    return _xblockexpression;
  }
  
  public static List<Double> getAverage(final ArrayList<ArrayList<Integer>> result) {
    List<Double> _xblockexpression = null;
    {
      ArrayList<Integer> _get = result.get(0);
      ArrayList<Integer> _arrayList = new ArrayList<Integer>(_get);
      final ArrayList<Integer> total = _arrayList;
      int _size = result.size();
      int _minus = (_size - 1);
      IntegerRange _upTo = new IntegerRange(1, _minus);
      for (final Integer i : _upTo) {
        ArrayList<Integer> _get_1 = result.get((i).intValue());
        int _size_1 = _get_1.size();
        int _minus_1 = (_size_1 - 1);
        IntegerRange _upTo_1 = new IntegerRange(0, _minus_1);
        for (final Integer j : _upTo_1) {
          Integer _get_2 = total.get((j).intValue());
          ArrayList<Integer> _get_3 = result.get((i).intValue());
          Integer _get_4 = _get_3.get((j).intValue());
          int _plus = ((_get_2).intValue() + (_get_4).intValue());
          total.set((j).intValue(), Integer.valueOf(_plus));
        }
      }
      final Function1<Integer,Double> _function = new Function1<Integer,Double>() {
          public Double apply(final Integer e) {
            double _doubleValue = e.doubleValue();
            int _size = result.size();
            double _divide = (_doubleValue / _size);
            return Double.valueOf(_divide);
          }
        };
      List<Double> _map = ListExtensions.<Integer, Double>map(total, _function);
      _xblockexpression = (_map);
    }
    return _xblockexpression;
  }
}
