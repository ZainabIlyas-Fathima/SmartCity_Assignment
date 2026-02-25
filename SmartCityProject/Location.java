/*
  Location Class - Managed by Member 1(Zainab)
  This class acts as the 'Node' for our Smart City Graph.
  It's a simple wrapper for city names, but with extra logic to make data handling much smoother and error-proof.
 */
public class Location {
    private String name;

    public Location(String name) {
        /*  I used .trim() here as a basic form of input validation.
         It removes any accidental leading or trailing spaces if the user types " Colombo " instead of "Colombo".
         */
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    /* Overriding toString() is important for debugging and UI.
      When we print our Graph, Java will now show the actual city name 
      instead of a confusing memory address like 'Location@5f37561'.
     */
    @Override
    public String toString() {
        return name; 
    }

    /* EQUALS OVERRIDE:
     This is crucial for our Graph logic. By using 'equalsIgnoreCase', 
     I ensure that the system treats "Kandy" and "kandy" as the same place.
     This prevents duplicate nodes in our map and makes searching more user-friendly.
     */
    @Override
    public boolean equals(Object obj) { 
        if (obj instanceof Location) {
            return ((Location) obj).name.equalsIgnoreCase(this.name);
        }
        return false;
    }
}