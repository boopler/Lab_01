import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;



public class PersonReader
{
    public static void main(String[] args)
    {
        System.out.println("== Person Reader (NIO style) ==");

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select a Person data file (e.g., PersonTestData.txt)");
        int result = chooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION)
        {
            System.out.println("No file selected. Exiting.");
            return;
        }

        Path file = chooser.getSelectedFile().toPath();
        System.out.println("Reading: " + file.toAbsolutePath());

        String headerFmt = "%-8s %-15s %-15s %-8s %4s%n";
        String rowFmt    = "%-8s %-15s %-15s %-8s %4s%n";
        System.out.printf(headerFmt, "ID#", "Firstname", "Lastname", "Title", "YOB");
        System.out.println("===============================================================");

        try (BufferedReader in = Files.newBufferedReader(file, StandardCharsets.UTF_8))
        {
            String line;
            while ((line = in.readLine()) != null)
            {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 5)
                {
                    System.out.println("[SKIP] bad record: " + line);
                    continue;
                }

                String id    = parts[0].trim();
                String first = parts[1].trim();
                String last  = parts[2].trim();
                String title = parts[3].trim();
                String yob   = parts[4].trim();

                System.out.printf(rowFmt, id, first, last, title, yob);
            }
        }
        catch (Exception e)
        {
            System.out.println("ERROR reading file: " + e.getMessage());
        }
    }
}