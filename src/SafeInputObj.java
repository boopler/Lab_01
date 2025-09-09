import java.util.Scanner;

public class SafeInputObj {
    private Scanner pipe;

    public SafeInputObj() {
        this.pipe = new Scanner(System.in);
    }

    public SafeInputObj(Scanner pipe) {
        this.pipe = pipe;
    }

    public String getNonZeroLenString(String prompt) {
        String retString = "";
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.length() == 0);
        return retString;
    }

    public int getRangedInt(String prompt, int low, int high) {
        int retVal = 0;
        boolean done = false;
        String trash;

        do {
            System.out.print("\n" + prompt + " [" + low + "-" + high + "]: ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine(); // clear the rest of the line
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("You must enter a value in range!");
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter an integer: " + trash);
            }
        } while (!done);

        return retVal;
    }

    public double getDouble(String prompt) {
        double retVal = 0.0;
        boolean done = false;
        String trash;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine(); // clear the rest of the line
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a number: " + trash);
            }
        } while (!done);

        return retVal;
    }

    public double getRangedDouble(String prompt, int low, int high) {
        double retVal = 0.0;
        boolean done = false;
        String trash;

        do {
            System.out.print("\n" + prompt + " [" + low + "-" + high + "]: ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine(); // clear the rest of the line
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("You must enter a value in range!");
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a number: " + trash);
            }
        } while (!done);

        return retVal;
    }

    public boolean getYNConfirm(String prompt) {
        while (true) {
            System.out.print(prompt + " [Y/N]: ");
            String resp = pipe.nextLine().trim();
            if (resp.equalsIgnoreCase("Y")) return true;
            if (resp.equalsIgnoreCase("N")) return false;
            System.out.println("Please enter Y or N.");
        }
    }

    public String getRegExString(String prompt, String regExPattern) {
        String retVal = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            retVal = pipe.nextLine().trim();
            if (retVal.matches(regExPattern)) {
                done = true;
            } else {
                System.out.println("Input must match: " + regExPattern);
            }
        } while (!done);

        return retVal;
    }
}
