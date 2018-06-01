package task2;

 
public class Road  {
    private  City source;
    private  City destination;
    private  int transportationCost;

    public Road( City source, City destination, int transportationCost) {
        
        this.source = source;
        this.destination = destination;
        this.transportationCost = transportationCost;
    }
 
    public City getDestination() {
        return destination;
    }

    public City getSource() {
        return source;
    }
    public int getWeight() {
        return transportationCost;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }


}