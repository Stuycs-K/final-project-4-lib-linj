public class Matrix{
  public double[][] m;
  public int r, c;

  Matrix(){
    m = new double[2][2];
    r = 2; c = 2;
    ident();
  }

  Matrix(int row, int col){
    m = new double[row][col];
    r = row; c = col;
  }

  Matrix(double[][] a){
    r = a.length; c = a[0].length;
    m = new double[r][c];
    for (int i = 0; i < r; i++){
      for (int j = 0; j < c; j++){
        m[i][j] = a[i][j];
      }
    }
  }

  public void ident(){
    if (r != c){
      System.out.println("can't be done, not a square matrix");
      return;
    }
    for (int i = 0; i < r; i++){
      for (int j = 0; j < c; j++){
        if (i == j){
          m[i][j] = 1;
        } else{
          m[i][j] = 0;
        }
      }
    }
  }

  public void clear(){
    m = new double[r][c];
  }

  public Matrix mult(Matrix a){
    if (c != a.r){
      System.out.println("invalid dimensions for mult");
      System.exit(0);
    }
    double[][] result = new double[r][a.c];
    for (int i = 0; i < r; i++){
      for (int j = 0; j < a.c; j++){
        for (int k = 0; k < a.r; k++){
          result[i][j] += m[i][k] * a.m[k][j];
        }
      }
    }
    return new Matrix(result);
  }

  public String toString(){
    String whole = "";
    for (int r = 0; r < m.length; r++){
      String str = "";
      for (int c = 0; c < m[0].length; c++){
        str += m[r][c] + "\t";
      }
      whole += str + "\n";
    }
    return whole;
  }

  // determinant via sum of cofactors
  public double getDeterminant(){
    if (r != c){
      System.out.println("invalid dimensions");
      System.exit(0);
    }
    return getDeterminant_r(m);
  }

  public double getDeterminant_r(double[][] matrix){
    if (matrix.length == 1){ return matrix[0][0]; }
    double determinant = 0;
    int currSign = 1; // will alternate from 1 to -1 for cofactor sign

    // get cofactors of first row, multiply by original element, and sum for determinant
    for (int i = 0; i < matrix.length; i++){
      double[][] sub = getSub(matrix, 0, i);
      double minor = getDeterminant_r(sub);
      determinant += matrix[0][i] * currSign * minor;
      // flip sign for next val
      currSign *= -1;
    }

    return determinant;
  }

  public double[][] getSub(double[][] matrix, int excludedRow, int excludedCol){
    double[][] sub = new double[matrix.length - 1][matrix.length - 1];
    for (int i = 0, row = 0; i < matrix.length; i++){
      // skip excluded row and excluded col elements
      if (i != excludedRow){
        for (int j = 0, col = 0; j < matrix[i].length; j++){
          if (j != excludedCol){
            sub[row][col] = matrix[i][j];
            col++;
          }
        }
        row++;
      }
    }
    return sub;
  }

  public Matrix getInverse(){
    if (r != c || getDeterminant() == 0){
      System.out.println("impossible to get inverse");
      System.exit(0);
    }

    double[][] minorMatrix = new double[r][c];
    for (int i = 0; i < r; i++){
      for (int j = 0; j < c; j++){
        minorMatrix[i][j] = getMinor(m, i ,j);
      }
    }
    // System.out.println("minor matrix");
    // System.out.println(new Matrix(minorMatrix));

    double[][] cofactors = getCofactorMatrix(minorMatrix);
    // System.out.println("cofactor matrix");
    // System.out.println(new Matrix(cofactors));

    double[][] adjugate = transpose(cofactors);
    // System.out.println("adjugate matrix");
    // System.out.println(new Matrix(adjugate));

    // System.out.println("inverse matrix");
    return scalarMult(adjugate, 1/getDeterminant());
  }

  public Matrix getModularInverseMatrix(int modVal){
    int determinant = ((int)getDeterminant() % modVal + modVal) % modVal;
    int determinantInv = modInverse(determinant, modVal);
    if (determinantInv == -1){
      System.out.println("A modular inverse matrix doesn't exist for this plaintext crib.");
      System.exit(0);
    }

    double[][] minorMatrix = new double[r][c];
    for (int i = 0; i < r; i++){
      for (int j = 0; j < c; j++){
        minorMatrix[i][j] = getMinor(m, i ,j);
      }
    }
    double[][] cofactors = getCofactorMatrix(minorMatrix);
    double[][] adjugate = transpose(cofactors);

    double[][] inverseMatrix = new double[m.length][m.length];
    for (int i = 0; i < m.length; i++){
      for (int j = 0; j < m.length; j++){
        inverseMatrix[i][j] = (adjugate[i][j] * determinantInv) % modVal;
      }
    }

    return new Matrix(inverseMatrix);
  }

  public static int modInverse(int n, int modVal){
    n = n % modVal;
    for (int i = 1; i < modVal; i++){
      if ((n * i) % modVal == 1){
        return i;
      }
    }
    System.out.println("A modular inverse of val " + n + " doesn't exist.");
    return -1;
  }

  // modified version of getSub()
  public double getMinor(double[][] matrix, int excludedRow, int excludedCol){
    double[][] sub = new double[matrix.length - 1][matrix[0].length - 1];
    for (int i = 0, row = 0; i < matrix.length; i++){
      if (i != excludedRow){
        for (int j = 0, col = 0; j < matrix[0].length; j++){
          if (j != excludedCol){
            sub[row][col] = matrix[i][j];
            col++;
          }
        }
        row++;
      }
    }
    Matrix submatrix = new Matrix(sub);
    return submatrix.getDeterminant();
  }

  public double[][] getCofactorMatrix(double[][] matrix){
    double[][] cofactors = new double[matrix.length][matrix.length];
    for (int i = 0; i < matrix.length; i++){
      for (int j = 0; j < matrix.length; j++){
        // determines if it should be + or - (checkerboard pattern)
        double cofactor = Math.pow(-1, i+j) * matrix[i][j];
        cofactors[i][j] = cofactor;
      }
    }
    return cofactors;
  }

  public static double[][] transpose(double[][] matrix){
    // swaps elements about main diagonal
    double[][] transposed = new double[matrix.length][matrix[0].length];
    for (int i = 0; i < matrix.length; i++){
      for (int j = 0; j < matrix[0].length; j++){
        transposed[j][i] = matrix[i][j];
      }
    }
    return transposed;
  }

  public Matrix scalarMult(double[][] matrix, double scalar){
    double[][] scaled = new double[matrix.length][matrix[0].length];
    for (int i = 0; i < matrix.length; i++){
      for (int j = 0; j < matrix[0].length; j++){
        scaled[i][j] = matrix[i][j] * scalar;
      }
    }
    return new Matrix(scaled);
  }

  public Matrix scalarMult(double scalar){
    double[][] scaled = new double[r][c];
    for (int i = 0; i < r; i++){
      for (int j = 0; j < c; j++){
        scaled[i][j] = Math.round(m[i][j] * scalar);
      }
    }
    return new Matrix(scaled);
  }

  public Matrix matrixMod(int modVal){
    double[][] modded = new double[r][c];
    for (int i = 0; i < r; i++){
      for (int j = 0; j < c; j++){
        modded[i][j] = m[i][j] % modVal;
        // we want positive values for this
        if (modded[i][j] < 0){
          modded[i][j] += modVal;
        }
      }
    }
    return new Matrix(modded);
  }

  // check if matrix's determinant is coprime with a val
  public boolean isCoprimeWith(int val){
    int determinant = (int)getDeterminant();
    return (gcd(determinant, val) == 1);
  }

  // old algo from apcs
  public static int gcd(int n1, int n2){
    n1 = Math.abs(n1);
    n2 = Math.abs(n2);
    int smaller;
    int counter = 1;
    int accumulatedGCD = 1;
    if (n1 > n2){
      smaller = n2;
    } else{
      smaller = n1;
    }

    while (counter <= smaller){
      if (n1 % counter == 0 && n2 % counter == 0){
        accumulatedGCD = counter;
      }
      counter++;
    }
    return accumulatedGCD;
  }

  // public String getAlpha() {
  //   String str = "";
  //   for (int i = 0; i < r; i++) {
  //     for (int j = 0; j < c; j++) {
  //       str += (char)(int)((m[i][j] + 65));
  //     }
  //   }
  //   return str;
  // }

  // testing
  public static void main(String[] args) {
    Matrix test = new Matrix(4, 3);
    System.out.println(test);
    test.ident();
    System.out.println(test);

    double[][] testM1 = {{1, 3, 22}, {4, 4, 2}};
    Matrix test1 = new Matrix(testM1);
    System.out.println(test1);
    double[][] testM2 = {{3, 6}, {2, 3}, {5, 1}};
    Matrix test2 = new Matrix(testM2);
    System.out.println(test2);

    System.out.println(test1.mult(test2));
    System.out.println((test1.mult(test2)).getDeterminant());
    System.out.println();

    double[][] testM3 = {{1, 3, 22, 5}, {4, 4, 2, 3}, {2, 7, 5, 6}};
    Matrix test3 = new Matrix(testM3);
    System.out.println(test3);
    double[][] testM4 = {{3, 6, 7}, {2, 3, 13}, {5, 1, 4}, {3, 3, 9}};
    Matrix test4 = new Matrix(testM4);
    System.out.println(test4);

    System.out.println(test3.mult(test4));
    System.out.println((test3.mult(test4)).getDeterminant());
    System.out.println();

    double[][] testM5 = {{2, 3}, {5, 7}};
    Matrix test5 = new Matrix(testM5);
    System.out.println(test5);
    System.out.println(test5.getInverse());
    System.out.println(test5.getInverse().matrixMod(26));
  }
}
