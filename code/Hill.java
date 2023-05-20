import java.io.*;
import java.util.*;

public class Hill {
  public static void main(String[] args) {
    encode(args[0], args[1]);
  }

  public static String encode(String fileToEncode, String keyFile) {
    String key = readFile(keyFile);
    String file = readFile(fileToEncode);
    StringBuilder encodedMessage = new StringBuilder();

    Matrix k;
    int l = key.length();
    if ((int)Math.sqrt(l) * Math.sqrt(l) == l) {
        double[][] mat = new double[(int)Math.sqrt(l)][(int)Math.sqrt(l)];
        for (int i = 0; i < mat[0].length; i++) {
            for (int j = 0; j < mat.length; j++) {
                char c = key.charAt(i*3 + j);
                mat[i][j] = (double)(c-65);
            }
        }
        k = new Matrix(mat);
        System.out.println(k.toString());
    }
    else {
        System.out.println("Key is not sufficient to create an n by n matrix.");
        System.exit(0);
    }

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
