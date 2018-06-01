package task2;

 
import java.util.Map;
import java.util.Set;
 

public class Graph {
    private final Map <Integer,City> cities;
    private final Set <Road> roads;

    public Graph(Map <Integer, City> cities, Set <Road> roads) {
        this.cities = cities;
        this.roads = roads;
    }

    public Map <Integer, City> getCities() {
        return cities;
    }

    public Set <Road> getRoads() {
        return roads;
    }



}