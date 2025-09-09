import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProductReader {
    public static void main(String[] args) {
        System.out.println("== Product Reader ==");

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select a Product data file");
        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file selected. Exiting.");
            return;
        }
        Path file = chooser.getSelectedFile().toPath();

        List<Product> items = new ArrayList<>();

        try (BufferedReader in = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length < 4) continue;

                String id   = parts[0].trim();
                String name = parts[1].trim();
                String desc = parts[2].trim();
                double cost = Double.parseDouble(parts[3].trim());

                items.add(new Product(id, name, desc, cost));
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return;
        }

        String headerFmt = "%-8s %-18s %-36s %10s%n";
        String rowFmt    = "%-8s %-18s %-36s %10.2f%n";
        System.out.printf(headerFmt, "ID", "Name", "Description", "Cost");
        System.out.println("===================================================================");
        for (Product p : items) {
            System.out.printf(rowFmt, p.getId(), p.getName(), p.getDescription(), p.getCost());
        }
    }
}
