import java.io.*;
import java.util.*;

public class Hill {
  public static void main(String[] args) {

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
      // fileContent = fileContent.toUpperCase();
      return fileContent;
  }
}
