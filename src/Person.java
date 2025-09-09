import java.util.Calendar;

@SuppressWarnings("unused")
public class Person {
    private String firstName;
    private String lastName;
    private final String id;
    private String title;
    private int yob;

    public Person(String id, String firstName, String lastName, String title, int yob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        setYob(yob);
    }

    public Person(String id, String firstName, String lastName, int yob) {
        this(id, firstName, lastName, "", yob);
    }

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName()  { return lastName; }
    public String getId()        { return id; }
    public String getTitle()     { return title; }
    public int getYob()          { return yob; }

    // Setters
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName)   { this.lastName = lastName; }
    public void setTitle(String title)         { this.title = title; }
    public void setYob(int yob) {
        if (yob < 1940 || yob > 2010) throw new IllegalArgumentException("YOB must be 1940–2010");
        this.yob = yob;
    }

    public String fullName()    { return firstName + " " + lastName; }
    public String formalName()  { return (title == null || title.isBlank()) ? fullName() : title + " " + fullName(); }

    public int getAge() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return year - yob;
    }

    public int getAge(int year) {
        return year - yob;
    }

    @Override
    public String toString() {
        return String.format("%s, %s (%s) %s YOB:%d", lastName, firstName, id, title, yob);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person p)) return false;
        return this.id != null && this.id.equals(p.id);
    }

    public String toCSV()  { return String.format("%s, %s, %s, %s, %d", id, firstName, lastName, title, yob); }
    public String toJSON() {
        return String.format("{\"id\":\"%s\",\"firstName\":\"%s\",\"lastName\":\"%s\",\"title\":\"%s\",\"yob\":%d}",
                esc(id), esc(firstName), esc(lastName), esc(title), yob);
    }
    public String toXML()  {
        return String.format("<person><id>%s</id><firstName>%s</firstName><lastName>%s</lastName><title>%s</title><yob>%d</yob></person>",
                esc(id), esc(firstName), esc(lastName), esc(title), yob);
    }

    private String esc(String s) { return s == null ? "" : s.replace("\"", "\\\""); }
}
