import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
    //start of program
    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src/input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        LinkedList<Character> list = new LinkedList<>();
        String Test1Line = scanner.nextLine();
        String input1 = scanner.nextLine();
        for (int i = 0; i < input1.length(); i++) {
            list.add(input1.charAt(i));
        }
        System.out.println(list.toString());

    }
}
