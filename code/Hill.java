import java.io.*;
import java.util.*;

public class Hill {
  public static void main(String[] args) {
    if (args.length == 0) {
        System.out.println(getMultModInverse(9, 26));
    }
    else if (args[0].equals("encode")) {
        System.out.println(encode(args[1], args[2]));
    }
    else if (args[0].equals("decode")) {
        System.out.println(decode(args[1], args[2]));
    }
  }

  public static String encode(String fileToEncode, String keyFile) {
    String key = readFile(keyFile);
    String contents = readFile(fileToEncode);
    StringBuilder encodedMessage = new StringBuilder();

    Matrix k = generateKeyMatrix(key);

    // pad with Z's
    while (contents.length() % k.r != 0) {
        contents += "Z";
    }
    
    for (int i = 0; i < contents.length(); i += k.r) {
        String temp = contents.substring(i, i + k.r);
        double[][] phrase = new double[k.r][1];
        for (int rows = 0; rows < phrase.length; rows++) {
            char c = temp.charAt(rows);
            phrase[rows][0] = (double)(c-65);
        }
        Matrix part = new Matrix(phrase);

        // System.out.println(part.toString());

        Matrix encodedPart = k.mult(part);
        encodedPart = encodedPart.matrixMod(26);

        encodedMessage.append(encodedPart.getAlpha());
    } 
    return encodedMessage.toString();
  }

  public static String decode(String fileToEncode, String keyFile) {
    StringBuilder decodedMessage = new StringBuilder();

    String key = readFile(keyFile);
    String contents = readFile(fileToEncode);

    Matrix k = generateKeyMatrix(key);

    /*
     * Steps to get the decrypting matrix
     * Check if matrix has modular multiplicative inverse
     * It does if the determinant has a modular inverse
     * This is the case if a * det = 1 (n mod 26)
     * That is that the gcd between det and 26 is 1 or that two the two numbers are coprime.
     * In that case we get the inverse of the matrix
     * Multiply every element with the modular inverse of the determinant.
     * Then mod each value by 26.
     */
    
     double det = k.getDeterminant();
     k = k.getInverse();
    //  System.out.println(k);
     k = k.scalarMult(det);
     int modular = getMultModInverse((int)det, 26);
     k = k.matrixMod(26);
     k = k.scalarMult((double)modular);
     k = k.matrixMod(26);

    //  System.out.println(k);


    while (contents.length() % k.r != 0) {
        contents += "Z";
    }
    
    for (int i = 0; i < contents.length(); i += k.r) {
        // possible edge case deal with later
        String temp = contents.substring(i, i + k.r);
        double[][] phrase = new double[k.r][1];
        for (int rows = 0; rows < phrase.length; rows++) {
            char c = temp.charAt(rows);
            phrase[rows][0] = (double)(c-65);
        }
        Matrix part = new Matrix(phrase);

        // System.out.println(part.toString());

        Matrix decodedPart = k.mult(part);
        decodedPart = decodedPart.matrixMod(26);

        decodedMessage.append(decodedPart.getAlpha());
    } 

    return decodedMessage.toString();
  }

  public static Matrix generateKeyMatrix(String key) {
    Matrix k = new Matrix();

    int l = key.length();
    // perfect square for key length
    if ((int)Math.sqrt(l) * Math.sqrt(l) == l) {
        double[][] mat = new double[(int)Math.sqrt(l)][(int)Math.sqrt(l)];
        for (int i = 0; i < mat[0].length; i++) {
            for (int j = 0; j < mat.length; j++) {
                char c = key.charAt(i*mat.length + j);
                mat[i][j] = (double)(c-65);
            }
        }
        k = new Matrix(mat);

        // System.out.println(k.toString());

    }
    else {
        System.out.println("Key is not sufficient to create an n by n matrix.");
        System.exit(0);
    }
    return k;
  }  

  // getMultModInverse(determinant, 26)
  public static int getMultModInverse(int a, int b) {
    return getMultModInverse_r(a, b, a/b, a%b, 1, 0, 1, 0, 1, -1*(a/b));
  }

  public static int getMultModInverse_r(int a, int b, int q, int r, int s1, int s2, int s3, int t1, int t2, int t3) {
    if (r == 0) {
        return s2;
    }
    else {
        int newA = b;
        int newB = r;
        int newQ = newA/newB;
        int newR = newA % newB;
        int newS3 = s2 - newQ * s3;
        int newT3 = t2 - newQ * t3;
        return getMultModInverse_r(newA, newB, newQ, newR, s2, s3, newS3, t2, t3, newT3);
    }
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
