import java.io.*;
import java.util.*;

public class Hill {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println(getMultModInverse(9, 26));
    }
    else if (args[0].equals("encode")) {
      String contents = readFile(args[1]);
      String key = readFile(args[2]);
      ArrayList<Character> skippedChars = getSkippedChars(args[1]);
      ArrayList<Integer> skippedIndices = getSkippedIndices(args[1]);
      System.out.println(encode(contents, key, skippedChars, skippedIndices));
    }
    else if (args[0].equals("decode")) {
      String contents = readFile(args[1]);
      String key = readFile(args[2]);
      ArrayList<Character> skippedChars = getSkippedChars(args[1]);
      ArrayList<Integer> skippedIndices = getSkippedIndices(args[1]);
      System.out.println(decode(contents, key, skippedChars, skippedIndices));
    }
    else if (args[0].equals("bruteforce")) {
      bruteforce(args[1]);
    }
  }

  public static void bruteforce(String filepath){
    String contents = readFile(filepath);
    ArrayList<Character> skippedChars = getSkippedChars(filepath);
    ArrayList<Integer> skippedIndices = getSkippedIndices(filepath);

    // iterate thru all possible keys (given coprime w/ 26)
    for (int i = 0; i < 26; i++){
      for (int j = 0; j < 26; j++){
        for (int k = 0; k < 26; k++){
          for (int l = 0; l < 26; l++){
            double[][] keyMatrix = {{i, j}, {k, l}};
            Matrix tempKey = new Matrix(keyMatrix);

            // optimization: key determinant must be coprime with 26
            if (tempKey.isCoprimeWith(26) && tempKey.getDeterminant() != 0){
              String key = matrixToText(tempKey);
              String decrypted = decode(contents, key, skippedChars, skippedIndices);
              System.out.println("Key: " + key);
              System.out.println("Decrypted: " + decrypted);
            }
          }
        }
      }
    }
    System.out.println();
  }

  public static String matrixToText(Matrix matrix){
    StringBuilder key = new StringBuilder();
    for (int i = 0; i < matrix.r; i++){
      for (int j = 0; j < matrix.c; j++){
        key.append((char)(matrix.m[i][j] + 65));
      }
    }
    return key.toString();
  }

  public static ArrayList<Character> getSkippedChars(String file){
    ArrayList<Character> skippedChars = new ArrayList<Character>();
    try {
        File f = new File(file);
        Scanner in = new Scanner(f);
        while (in.hasNextLine()) {
            String text = in.nextLine();
            for (int i = 0; i < text.length(); i++) {
                if (!Character.isLetter(text.charAt(i))) {
                    skippedChars.add(text.charAt(i));
                }
            }
        }
    }
    catch (FileNotFoundException ex) {
        ex.printStackTrace();
    }
    return skippedChars;
  }

  public static ArrayList<Integer> getSkippedIndices(String file){
    ArrayList<Integer> skippedIndices = new ArrayList<Integer>();
    int charCount = 0;
    try {
        File f = new File(file);
        Scanner in = new Scanner(f);
        while (in.hasNextLine()) {
            String text = in.nextLine();
            for (int i = 0; i < text.length(); i++) {
                if (!Character.isLetter(text.charAt(i))) {
                    skippedIndices.add(charCount);
                }
                charCount++;
            }
        }
    }
    catch (FileNotFoundException ex) {
        ex.printStackTrace();
    }
    return skippedIndices;
  }

  public static String encode(String contents, String key, ArrayList<Character> skippedChars, ArrayList<Integer> skippedIndices) {
    StringBuilder encodedMessage = new StringBuilder();

    Matrix k = generateKeyMatrix(key);
    System.out.println(k);

    // pad with Z's
    while (contents.length() % k.r != 0) {
        contents += "z";
    }

    for (int i = 0; i < contents.length(); i += k.r) {
        String temp = contents.substring(i, i + k.r);
        double[][] phrase = new double[k.r][1];
        for (int rows = 0; rows < phrase.length; rows++) {
            char c = temp.charAt(rows);
            if (Character.isUpperCase(c)) {
              phrase[rows][0] = (double)(c-65);
            }
            if (Character.isLowerCase(c)) {
              phrase[rows][0] = (double)(c-97);
            }
        }

        Matrix part = new Matrix(phrase);
        Matrix encodedPart = k.mult(part);
        encodedPart = encodedPart.matrixMod(26);
        String messagePart = matrixToText(encodedPart);
        for (int w = 0; w < temp.length(); w++) {
          if (Character.isLowerCase(temp.charAt(w))) {
            encodedMessage.append(Character.toLowerCase(messagePart.charAt(w)));
          }
          if (Character.isUpperCase(temp.charAt(w))) {
            encodedMessage.append(messagePart.charAt(w));
          }
        }
        // encodedMessage.append(matrixToText(encodedPart));
    }

    for (int i = 0; i < skippedChars.size(); i++){
      int index = skippedIndices.get(i);
      char c = skippedChars.get(i);
      encodedMessage.insert(index, c);
    }
    return encodedMessage.toString();
  }

  public static String decode(String contents, String key, ArrayList<Character> skippedChars, ArrayList<Integer> skippedIndices) {
    StringBuilder decodedMessage = new StringBuilder();

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
    //  System.out.println("Inverse\n" + k);
     k = k.scalarMult(Math.abs(det));
    //  System.out.println("Scalar Mult\n" + k);
     int modular = getMultModInverse((int)det, 26);
    //  System.out.println("Modular: " + modular);
     k = k.scalarMult((double)modular);
     k = k.matrixMod(26);

    while (contents.length() % k.r != 0) {
        contents += "z";
    }

    for (int i = 0; i < contents.length(); i += k.r) {
        String temp = contents.substring(i, i + k.r);
        double[][] phrase = new double[k.r][1];
        for (int rows = 0; rows < phrase.length; rows++) {
            char c = temp.charAt(rows);
            if (Character.isUpperCase(c)) {
              phrase[rows][0] = (double)(c-65);
            }
            if (Character.isLowerCase(c)) {
              phrase[rows][0] = (double)(c-97);
            }
        }

        Matrix part = new Matrix(phrase);
        Matrix decodedPart = k.mult(part);
        decodedPart = decodedPart.matrixMod(26);
        String messagePart = matrixToText(decodedPart);
        for (int w = 0; w < temp.length(); w++) {
          if (Character.isLowerCase(temp.charAt(w))) {
            decodedMessage.append(Character.toLowerCase(messagePart.charAt(w)));
          }
          if (Character.isUpperCase(temp.charAt(w))) {
            decodedMessage.append(messagePart.charAt(w));
          }
        }
        // decodedMessage.append(matrixToText(decodedPart));

    }

    for (int i = 0; i < skippedChars.size(); i++){
      int index = skippedIndices.get(i);
      char c = skippedChars.get(i);
      decodedMessage.insert(index, c);
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

        if (!k.isCoprimeWith(26) || k.getDeterminant()==0) {
            System.out.println("Invalid Key since determinant is a factor of 26 or determinant is 0.\n" + "Determinant: " + k.getDeterminant());
            System.exit(0);
        }
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
      // fileContent = fileContent.toUpperCase();
      return fileContent;
  }
}
