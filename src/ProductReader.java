import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


public class ProductReader
{
    public static void main(String[] args)
    {
        System.out.println("== Product Reader (NIO style) ==");

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select a Product data file (e.g., ProductTestData.txt)");
        int result = chooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION)
        {
            System.out.println("No file selected. Exiting.");
            return;
        }

        Path file = chooser.getSelectedFile().toPath();
        System.out.println("Reading: " + file.toAbsolutePath());

        String headerFmt = "%-8s %-20s %-36s %10s%n";
        String rowFmt    = "%-8s %-20s %-36s %10.2f%n";
        System.out.printf(headerFmt, "ID#", "Name", "Description", "Cost");
        System.out.println("================================================================================");

        try (BufferedReader in = Files.newBufferedReader(file, StandardCharsets.UTF_8))
        {
            String line;
            while ((line = in.readLine()) != null)
            {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 4)
                {
                    System.out.println("[SKIP] bad record: " + line);
                    continue;
                }

                String id   = parts[0].trim();
                String name = parts[1].trim();
                String desc = parts[2].trim();
                double cost = Double.parseDouble(parts[3].trim());

                System.out.printf(rowFmt, id, name, desc, cost);
            }
        }
        catch (Exception e)
        {
            System.out.println("ERROR reading file: " + e.getMessage());
        }
    }
}
