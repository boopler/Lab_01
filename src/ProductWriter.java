import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ProductWriter
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        List<String> recs = new ArrayList<>();

        System.out.println("== Product Writer (NIO style) ==");

        boolean more;
        do
        {
            String id   = SafeInput.getRegExString(in, "ID (exactly 6 digits): ", "\\d{6}").trim();
            String name = SafeInput.getNonZeroLenString(in, "Name: ").trim();
            String desc = SafeInput.getNonZeroLenString(in, "Description (short sentence): ").trim();

            double cost;
            do
            {
                cost = SafeInput.getDouble(in, "Cost (>= 0): ");
                if (cost < 0.0) System.out.println("Enter a number >= 0");
            } while (cost < 0.0);

            String line = String.format("%s, %s, %s, %.2f", id, name, desc, cost);
            recs.add(line);
            System.out.println("Added: " + line);

            more = SafeInput.getYNConfirm(in, "Add another product?");
        }
        while (more);

        String fileName = SafeInput.getNonZeroLenString(in, "Output file name (e.g., ProductTestData.txt): ").trim();
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