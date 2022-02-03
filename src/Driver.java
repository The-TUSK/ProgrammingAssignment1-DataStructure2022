import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Driver {
    //start of program
    static RLE mainRLE = new RLE();
    public static void main(String[] args) {
        Scanner scanner = null;
        FileWriter writer = null;

        try {
            scanner = new Scanner(new File("src/input.txt"));
            writer = new FileWriter(new File("src/output.txt"));

            //--encoding tests--
            //Initialized lists
            LinkedList<Character> list = new LinkedList<>();
            LinkedList<Character> list2 = new LinkedList<>();
            LinkedList<Character> list3 = new LinkedList<>();

            String text = scanner.nextLine();
            String encodingInput1 = scanner.nextLine();
            String encodingInput2 = scanner.nextLine();
            String encodingInput3 = scanner.nextLine();


            for (int i = 0; i < encodingInput1.length(); i++) {
                list.add(encodingInput1.charAt(i));
            }
            for (int i = 0; i < encodingInput2.length(); i++) {
                list2.add(encodingInput2.charAt(i));
            }
            for (int i = 0; i < encodingInput3.length(); i++) {
                list3.add(encodingInput3.charAt(i));
            }

            writer.write("Test 1: Output for Encoding\n");
            encodingBuilder(list, mainRLE.encode(list), writer);
            encodingBuilder(list2, mainRLE.encode(list2), writer);
            encodingBuilder(list3, mainRLE.encode(list3), writer);


            //--decoding tests--
            String header = scanner.nextLine();
            String input1 = scanner.nextLine();
            String input2 = scanner.nextLine();
            String input3 = scanner.nextLine();

            writer.write("Test 2: Output for Decoding\n");
            decodingBuilder(input1, mainRLE.decode(input1), writer);
            decodingBuilder(input2, mainRLE.decode(input2), writer);
            decodingBuilder(input3, mainRLE.decode(input3), writer);


            //--equal tests--
            String header2 = scanner.nextLine();
            String eInput1 = scanner.next();
            String eInput12 = scanner.next();
            String eInput2 = scanner.next();
            String eInput22 = scanner.next();
            String eInput3 = scanner.next();
            String eInput32 = "";
            String eInput4 = scanner.next();
            String eInput42 = scanner.next();

            equalBuilder(eInput1, eInput12, writer);
            equalBuilder(eInput2, eInput22, writer);
            equalBuilder(eInput3, eInput32, writer);
            equalBuilder(eInput4, eInput42, writer);

            writer.close();




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    static void encodingBuilder(LinkedList list, String output, FileWriter fileWriter) throws IOException {
        Double compressedRatio = (double) list.getSize()/(double) output.length();
        fileWriter.write("[" + list.toString() + ":" + list.getSize() + "]" + " [" + output + ":"
                + output.length() + "]" + " [" + compressedRatio + "] \n");
    }

    static void decodingBuilder(String input, LinkedList output, FileWriter fileWriter) throws IOException {
        fileWriter.write( "[" + input + "] [" + output.toString() + "]\n");
    }

    static void equalBuilder(String input1, String input2, FileWriter fileWriter) throws  IOException{
        fileWriter.write("[" + input1 + "] [" + input2 + "] [" + mainRLE.equal(mainRLE.decode(input1), mainRLE.decode(input2)) + "]\n");
    }

}
