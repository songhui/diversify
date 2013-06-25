package diversify;

/** can be run n times before getting the result (for repeated randomized experiments) */
public interface ISimulation<T> {
  public ISimulation<T> run();
  public T getSimulationResult();
  public String description(); 
}
