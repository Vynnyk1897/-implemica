package task2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import task2.Task2;

public class TestTask2 {

	private Map<Integer, City> cities;
	private Set<Road> roads;
	private List<String> tests;

	public int testExcute(City source, City target) {
		Graph graph = new Graph(cities, roads);
		Task2 task2 = new Task2(graph);
		int result = task2.execute(source, target);
		return result;
	}

	/**
	 * Reads file to get needed info
	 * First goes though to find cities 
	 * Second goes through file to add road(connect existing cities) and tests 
	 */

	public void getGraph(String path) {
		File file = new File (path);
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
			cities = new LinkedHashMap<Integer, City>();
			roads = new HashSet<Road>();
			tests = new ArrayList<>();
			int numberOfTests = Integer.valueOf(br.readLine());
			int numberOfCities = Integer.valueOf(br.readLine());
			String line = br.readLine();
			int id = 1;
			int skip;
	 		br.mark((int)file.length());
			for (int k = 1; k < numberOfCities + 1; k++) {
				checkCity(line);
				cities.put(k, new City(id++, line));
				skip = Integer.valueOf(br.readLine());
				for (int i = 0; i < skip; i++) {
					br.readLine();
				}
				line = br.readLine();
			}
			br.reset();
			String[] lines;

			for (int k = 1; k <= numberOfCities; k++) {
				skip = Integer.valueOf(br.readLine());
				for (int i = 0; i < skip; i++) {
					lines = br.readLine().split(" ");
					roads.add(
							new Road(cities.get(k), cities.get(Integer.valueOf(lines[0])), Integer.valueOf(lines[1])));
				}
				br.readLine();
			}

			for (int k = 0; k <= numberOfTests; k++) {
				tests.add(br.readLine());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Get city by name  
	 */
	private City getCity(String name) {

		for (Map.Entry<Integer, City> city : cities.entrySet()) {

			if (city.getValue().getName().equals(name)) {
				return city.getValue();
			}
		}
		throw new NullPointerException();
	}
	//The name of a city is a string containing characters a,...,z and is at most 10 characters long.
	private void checkCity(String city) {
		
		if(city.length()>10) {
			throw new IllegalArgumentException();
		}
		String pattern = "[a-z]+";
		if(!Pattern.matches(pattern, city)) {
			throw new IllegalArgumentException();
		} 
		
		
	}
	
	public static void main(String[] args) {

		TestTask2 testTask3 = new TestTask2();
		testTask3.getGraph("input.txt");
		String[] cities = null;
		StringBuilder sb = new StringBuilder();
 		for (String str : testTask3.tests) {
			cities = str.split(" ");
			sb.append(testTask3.testExcute(testTask3.getCity(cities[0]),
					testTask3.getCity(cities[1]))).append(" ");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());
	}
}