import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class PersonGenerator
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        List<String> recs = new ArrayList<>();

        System.out.println("== Person Generator (NIO style) ==");

        boolean more;
        do
        {
            String id    = SafeInput.getRegExString(in, "ID (exactly 6 digits)", "\\d{6}").trim();
            String first = SafeInput.getNonZeroLenString(in, "First name").trim();
            String last  = SafeInput.getNonZeroLenString(in, "Last name").trim();
            String title = SafeInput.getNonZeroLenString(in, "Title (Mr., Mrs., Ms., Dr., Esq., etc.)").trim();
            int yob      = SafeInput.getRangedInt(in, "Year of birth (0-9999)", 0, 9999);

            String line = String.format("%s, %s, %s, %s, %d", id, first, last, title, yob);
            recs.add(line);
            System.out.println("Added: " + line);

            more = SafeInput.getYNConfirm(in, "Add another person?");
        } while (more);

        String fileName = SafeInput.getNonZeroLenString(in, "Output file name (e.g., PersonTestData.txt)").trim();
        Path file = Paths.get(fileName);

        try (BufferedWriter out = Files.newBufferedWriter(file, StandardCharsets.UTF_8))
        {
            for (String s : recs)
            {
                out.write(s);
                out.newLine();
            }
            System.out.println("Saved " + recs.size() + " record(s) to: " + file.toAbsolutePath());
        }
        catch (Exception e)
        {
            System.out.println("ERROR writing file: " + e.getMessage());
        }
    }
}