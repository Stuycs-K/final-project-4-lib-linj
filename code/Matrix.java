public class Matrix{
  public int[][] m;
  public int r, c;

  Matrix(){
    m = new int[2][2];
    r = 2; c = 2;
    ident();
  }

  Matrix(int row, int col){
    m = new int[row][col];
    r = row; c = col;
  }

  Matrix(int[][] a){
    r = a.length; c = a[0].length;
    m = new int[r][c];
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
    m = new int[r][c];
  }

  public Matrix mult(Matrix a){
    if (c != a.r){
      System.out.println("invalid dimensions for mult");
      System.exit(0);
    }
    int[][] result = new int[r][a.c];
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

  // public int getDeterminant(){
  //   if (r != c){
  //     System.out.println("invalid dimensions for determinant");
  //     System.exit(0);
  //   }
  //
  // }

  // testing
  // public static void main(String[] args) {
  //   Matrix test = new Matrix(4, 3);
  //   System.out.println(test);
  //   test.ident();
  //   System.out.println(test);
  //
  //   int[][] testM1 = {{1, 3, 22}, {4, 4, 2}};
  //   Matrix test1 = new Matrix(testM1);
  //   System.out.println(test1);
  //   int[][] testM2 = {{3, 6}, {2, 3}, {5, 1}};
  //   Matrix test2 = new Matrix(testM2);
  //   System.out.println(test2);
  //
  //   System.out.println(test1.mult(test2));
  // }
}
