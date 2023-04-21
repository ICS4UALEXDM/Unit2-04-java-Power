import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
* This program calculates amount of mean median and mode.
*
* @author  Alex De Meo
* @version 1.0
* @since   2023/02/08
*/

public final class Power {
    /**
    * This is a private constructor used to satisfy the
    * style checker.
    *
    * @exception IllegalStateException Utility Class
     */
    private Power() {
        throw new IllegalStateException("Utility Class");
    }
    /**
    * This is the main method.
    *
    * @param args Unused
    * @throws Exception if something goes wrong
    */

    public static void main(String[] args) throws Exception {
        // Initializing variables
        String line;
        final String err = "Error";
        try {
            // new file object
            final File baseFile = new File("base.txt");
            final File expFile = new File("exp.txt");

            // Creating the writer
            final FileWriter writer = new FileWriter("output.txt");

            try {
                // Creating the scanner.
                final Scanner scanner = new Scanner(baseFile);
                final Scanner scanner2 = new Scanner(expFile);

                // ArrayList to hold the lines
                final ArrayList<String> lines = new ArrayList<>();
                final ArrayList<String> lines2 = new ArrayList<>();
                // Getting the input from the first file
                while (scanner.hasNextLine()) {
                    // getting next line and putting it in the interim list
                    line = scanner.nextLine();
                    lines.add(line);
                }
                while (scanner2.hasNextLine()) {
                    line = scanner2.nextLine();
                    lines2.add(line);
                }

                // taking the data and manipulating it with a function
                for (int i = 0; i < lines.size(); i++) {
                    if (lines.get(i).length() == 0
                        || lines2.get(i).length() == 0) {
                        // writing to file
                        writer.write("ERROR: Empty line in one of the files\n");
                    } else {
                        try {
                            // converting to ints
                            final int base = Integer.parseInt(lines.get(i));
                            final int exp = Integer.parseInt(lines2.get(i));
                            if (exp < 0) {
                                writer.write("ERROR: For this program, the "
                                    + "exponent cannot be negative. \n");
                            } else {
                                final int power = recPower(base, exp);
                                writer.write(base + "^" + exp + " = " + power
                                    + "\n");
                            }
                        } catch (NumberFormatException error) {
                            writer.write("ERROR: Invalid input on a file\n");
                        }
                    }
                }
            } catch (IOException error) {
                System.out.println(err + error.getMessage());
            }
            // closes the writer
            writer.close();
        } catch (IOException error) {
            System.out.println(err + error.getMessage());
        }
    }
    /**
    * This is the method reverseRecs the string.
    *
    * @param base This is the base of the power
    * @param exp This is the exponent
    * @return the powered value
    */

    public static int recPower(int base, int exp) {
        if (exp == 0) {
            // Anything to the power of 0 is 1, even 0
            return 1;
        } else {
            // recursivity for the base
            return base * recPower(base, exp - 1);
        }
    }
}
