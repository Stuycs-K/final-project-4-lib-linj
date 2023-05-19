import java.io.*;
import java.util.*;

public class Hill {
  public static void main(String[] args) {
    // System.out.println(readFile(args[0]));
  }

  public static String encode(String fileToEncode, String keyFile) {
    String key = readFile(keyFile);
    String file = readFile(fileToEncode);
    StringBuilder encodedMessage = new StringBuilder();
    // Matrix k = new Matrix();
    /*
        i += n where n is elements in matrix, varied based on size of key
     */
    for (int i = 0; i < file.length(); i += 1) {
        // Matrix f = new Matrix();
        // convert to matrix

        // if (remaining file not enough pad with 27?) {
        //     pad matrix
        // }

        // f.mult(k);
        // encodedMessage.append("something");
    } 
    return encodedMessage.toString();
  }

  public static String readFile(String file) {
      String fileContent = "";
      try {
          File f = new File(file);
          Scanner in = new Scanner(f);
          while (in.hasNextLine()) {
              String text = in.nextLine();
              StringBuilder add = new StringBuilder();
              for (int i = 0; i < text.length(); i++) {
                  if (Character.isLetter(text.charAt(i))) {
                      add.append(text.charAt(i));
                  }
              }
              fileContent += add.toString();
          }
      }
      catch (FileNotFoundException ex) {
          ex.printStackTrace();
      }
      fileContent = fileContent.toUpperCase();
      return fileContent;
  }
}
