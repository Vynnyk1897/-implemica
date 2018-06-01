package task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of Dijkstra Algorithm to find the paths of minimum cost 
 */

public class Task2 {
	// list of all cities
	private final Map<Integer, City> cities;
	// list of all roads
	private final Set<Road> roads;
	// cities that were settled
	private Set<City> settledCities;
	// unsettled cities
	private Set<City> unSettledCities;
	// previous city, so we could get total path
	private Map<City, City> predecessors;
	// current minimal transportation cost from source to specific city
	private Map<City, Integer> distance;

	public Task2(Graph graph) {
		// create a copy of the array so that we can operate on this array
		
		this.cities = new HashMap<Integer, City>(graph.getCities());
		this.roads = new HashSet<Road>(graph.getRoads());
	}

	public int execute(City source, City target ) {

		settledCities = new HashSet<City>();
		unSettledCities = new HashSet<City>();
		distance = new HashMap<City, Integer>();
		predecessors = new HashMap<City, City>();
		// set transportation cost to zero for our initial city
		distance.put(source, 0);
		// at the beginning all cities are unvisited
		unSettledCities.add(source);
		
		while (unSettledCities.size() > 0) {

			 
			// move to the next unvisited city with the smallest transportation cost
			City city = getMinimum(unSettledCities);
			 
			 
			// current city is visited remove it from the unvisited set.
			settledCities.add(city);

			// remove current city from the unvisited set. A visited city will never be
			// checked again
			unSettledCities.remove(city);

			findMinimalDistances(city);
			
			if(city.equals(target)) {
 				 
				return distance.get(city);
			}
			 
		}
		return 0;
	}

	private void findMinimalDistances(City sourceCity) {

		// get its unvisited neighbors
		List<City> adjacentCities = getNeighbors(sourceCity);

		for (City target : adjacentCities) {
			// Compare the newly calculated tentative transportation cost to the current
			// assigned value and assign the smaller one.
			if (getShortestDistance(target) > getShortestDistance(sourceCity) + getDistance(sourceCity, target)) {
				distance.put(target, getShortestDistance(sourceCity) + getDistance(sourceCity, target));
				// saves info on what was the city you came from
				predecessors.put(target, sourceCity);
				// add neighbors as unsettled
				unSettledCities.add(target);
			}
		}

	}

	private int getDistance(City sourceCity, City target) {
		for (Road road : roads) {
			if (road.getSource().equals(sourceCity) && road.getDestination().equals(target)) {
				return road.getWeight();
			}
		}
		throw new RuntimeException("Should not happen");
	}

	private List<City> getNeighbors(City node) {
		List<City> neighbors = new ArrayList<City>();
		for (Road road : roads) {
			if (road.getSource().equals(node) && !isSettled(road.getDestination())) {
				neighbors.add(road.getDestination());
			}
		}
		return neighbors;
	}

	private City getMinimum(Set<City> cities) {
		City minimum = null;
		 
		for (City city : cities) {
			 
			// any first unvisited city it is min
			if (minimum == null) {
				minimum = city;
			}
			// if there are more then one city
			// find city with the smallest current distance
			else {
				if (getShortestDistance(city) < getShortestDistance(minimum)) {
					minimum = city;
				}
			}
		}
		// return city with smallest current distance
		 
		return minimum;
	}

	private boolean isSettled(City city) {
		return settledCities.contains(city);
	}

	private int getShortestDistance(City destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			// assign to every city a tentative distance value: infinity for other cities
			// that are not source.
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	/*
	 * Method returns the path from the source to the selected target and NULL
	 * if no path exists
	 */
	public LinkedList<City> getPath(City target) {

		LinkedList<City> path = new LinkedList<City>();
 		City step = target;
		// check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		return path;
	}
 
}