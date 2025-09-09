@SuppressWarnings("unused")
public class Product {
    private String name;
    private String description;
    private final String id;
    private double cost;

    public Product(String id, String name, String description, double cost) {
        this.id = id;
        this.name = name;
        this.description = description;
        setCost(cost);
    }

    // Getters
    public String getName()        { return name; }
    public String getDescription() { return description; }
    public String getId()          { return id; }
    public double getCost()        { return cost; }

    // Setters
    public void setName(String name)               { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setCost(double cost) {
        if (cost < 0) throw new IllegalArgumentException("cost must be >= 0");
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("%s (%s): %s $%.2f", name, id, description, cost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product p)) return false;
        return this.id != null && this.id.equals(p.id);
    }

    public String toCSV()  { return String.format("%s, %s, %s, %.2f", id, name, description, cost); }
    public String toJSON() { return String.format("{\"id\":\"%s\",\"name\":\"%s\",\"description\":\"%s\",\"cost\":%.2f}",
            esc(id), esc(name), esc(description), cost); }
    public String toXML()  { return String.format("<product><id>%s</id><name>%s</name><description>%s</description><cost>%.2f</cost></product>",
            esc(id), esc(name), esc(description), cost); }

    private String esc(String s) { return s == null ? "" : s.replace("\"", "\\\""); }
}
