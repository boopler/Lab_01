import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PersonReader {
    public static void main(String[] args) {
        System.out.println("== Person Reader ==");

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select a Person data file");
        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file selected. Exiting.");
            return;
        }
        Path file = chooser.getSelectedFile().toPath();

        List<Person> people = new ArrayList<>();

        try (BufferedReader in = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length < 5) continue;

                String id    = parts[0].trim();
                String first = parts[1].trim();
                String last  = parts[2].trim();
                String title = parts[3].trim();
                int yob      = Integer.parseInt(parts[4].trim());

                people.add(new Person(id, first, last, title, yob));
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return;
        }

        String headerFmt = "%-8s %-12s %-12s %-8s %4s%n";
        String rowFmt    = "%-8s %-12s %-12s %-8s %4d%n";
        System.out.printf(headerFmt, "ID", "First", "Last", "Title", "YOB");
        System.out.println("================================================");
        for (Person p : people) {
            System.out.printf(rowFmt, p.getId(), p.getFirstName(), p.getLastName(), p.getTitle(), p.getYob());
        }
    }
}
