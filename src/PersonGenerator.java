import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonGenerator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Person> people = new ArrayList<>();

        System.out.println("== Person Generator ==");

        boolean more;
        do {
            String id    = SafeInput.getRegExString(in, "ID (6 digits)", "\\d{6}").trim();
            String first = SafeInput.getNonZeroLenString(in, "First name").trim();
            String last  = SafeInput.getNonZeroLenString(in, "Last name").trim();
            String title = SafeInput.getNonZeroLenString(in, "Title (Mr., Ms., Dr., etc.)").trim();
            int yob      = SafeInput.getRangedInt(in, "YOB (1940-2010)", 1940, 2010);

            people.add(new Person(id, first, last, title, yob));
            more = SafeInput.getYNConfirm(in, "Add another person?");
        } while (more);

        String fileName = SafeInput.getNonZeroLenString(in, "Output file name (e.g., PersonTestData.txt)").trim();
        Path file = Paths.get(fileName);

        try (BufferedWriter out = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            for (Person p : people) {
                out.write(p.toCSV());
                out.newLine();
            }
            System.out.println("Saved " + people.size() + " record(s) to: " + file.toAbsolutePath());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
