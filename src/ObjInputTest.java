public class ObjInputTest {
    public static void main(String[] args) {
        SafeInputObj in = new SafeInputObj();

        String personId = in.getRegExString("Person ID (6 digits)", "\\d{6}");
        String first    = in.getNonZeroLenString("First name");
        int yob         = in.getRangedInt("Year of birth", 1940, 2010);

        double cost     = in.getRangedDouble("Product cost", 0, 10000);

        boolean ok      = in.getYNConfirm("Is this correct?");

        System.out.println("\nSummary:");
        System.out.println("ID=" + personId + ", First=" + first + ", YOB=" + yob +
                ", Cost=" + String.format("%.2f", cost) + ", Confirm=" + ok);
    }
}
