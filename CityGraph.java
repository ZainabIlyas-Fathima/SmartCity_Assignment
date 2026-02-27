import java.util.*;

/*CityGraph Class - Managed by Member 1(Zainab)
 This class handles the core data structure (Graph) for the Smart City.
 It maps locations and creates routes using different traversal algorithms.
 */
public class CityGraph {
    
    /* I chose a HashMap for the Adjacency List because it's highly efficient.
     The 'Key' is the city name, and the 'Value' is a List of its neighbors.
      This allows us to find any city's connections in O(1) average time.
     */
    private Map<String, List<String>> adjList;

    public CityGraph() {
        this.adjList = new HashMap<>();
    }

    public void addLocation(String name) {
        // Validation: We check if the city exists to prevent duplicate entries
        if (!adjList.containsKey(name)) {
            adjList.put(name, new ArrayList<>());
            System.out.println("Success: " + name + " added to the map.");
        } 
        else {
            System.out.println("Notice: This location already exists.");
        }
    }

    /*
      Removing a location is a two-step process:
     1. We must remove the city from the lists of all other cities it was connected to.
     2. We then remove the city's own entry from the Map.
     */
    public void removeLocation(String name) {
        if (adjList.containsKey(name)) {
            // Iterating through all cities to clean up any roads leading to the deleted city
            for (String otherCity : adjList.keySet()) {
                adjList.get(otherCity).remove(name);
            }
            adjList.remove(name);
            System.out.println("Success: " + name + " and its roads were removed.");
        } 
        else {
            System.out.println("Error: Location not found.");
        }
    }

    public void addRoad(String source, String destination) {
        // Validation: Ensuring the graph only connects cities that actually exist
        if (adjList.containsKey(source) && adjList.containsKey(destination)) {
            // Using a two-way connection because roads in our Smart City are undirected
            if (!adjList.get(source).contains(destination)) {
                adjList.get(source).add(destination);
                adjList.get(destination).add(source); 
                System.out.println("Road built between " + source + " and " + destination);
            }
             else {
                System.out.println("Notice: These two are already connected.");
            }
        } 
        else {
            System.out.println("Error: One or both cities are missing from the map.");
        }
    }

    public void removeRoad(String source, String destination) {
        if (adjList.containsKey(source) && adjList.containsKey(destination)) {
            // To remove a road, we just remove the names from each other's lists
            adjList.get(source).remove(destination);
            adjList.get(destination).remove(source);
            System.out.println("Road removed between " + source + " and " + destination);
        } 
        else {
            System.out.println("Error: Road connection not found.");
        }
    }

    public void displayGraph() {
        if (adjList.isEmpty()) {
            System.out.println("The map is currently empty.");
            return;
        }
        System.out.println("\n Current City Map ");
        // Printing each city and its neighbors for easy visualization
        for (String city : adjList.keySet()) {
            System.out.println(city + " -> " + adjList.get(city));
        }
    }

    // TRAVERSAL SECTION 

    /*  BFS (Breadth-First Search) implementation using a Queue.
      I chose this because it explores the map layer-by-layer, which is the best way to find the shortest path in an unweighted graph.
     */
    
    public void searchQueue(String start) {
        if (!adjList.containsKey(start)) return;

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>(); // Keeps track of where we've been

        queue.add(start);
        visited.add(start);

        System.out.print("Route (Queue/BFS): ");
        while (!queue.isEmpty()) {
            String current = queue.poll(); // Remove the front city
            System.out.print(current + "  ");
            
            // Look at all neighboring cities
            for (String neighbor : adjList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    /* DFS (Depth-First Search) implementation using a Stack.
     This explores as deep as possible down one branch before backtracking.
     */
    
    public void searchStack(String start) {
        if (!adjList.containsKey(start)) return;

        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();

        stack.push(start);

        System.out.print("Route (Stack/DFS): ");
        while (!stack.isEmpty()) {
            String current = stack.pop(); // Take the top city
            
            if (!visited.contains(current)) {
                System.out.print(current + "  ");
                visited.add(current);
                
                // Push all neighbors onto the stack to visit them next
                for (String neighbor : adjList.get(current)) {
                    stack.push(neighbor);
                }
            }
        }
        System.out.println();
    }
}