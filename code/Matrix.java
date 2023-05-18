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
    m = new int[a.length][a[0].length];
    for (int i = 0; i < a.length; i++){
      for (int j = 0; j < a[0].length; j++){
        m[i][j] = a[i][j];
      }
    }
  }

  public void ident(){
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
    if (m[0].length == a.length){
      int[][] temp = new int[this.r][a[0].length];
      for (int i = 0; temp.length; i++){
        for (int j = 0; temp[0].length; j++){
          temp[i][j] += m[j][i] * a[i][j];
        }
      }
      return new Matrix(temp);
    } else{
      System.out.println("invalid dimensions");
      System.exit(0);
    }
  }

  // public String toString(){
  //   for (int j = 0; j < m[0].length; j++){
  //     for (int i = 0; i < m.length; i++){
  //       String str
  //     }
  //   }
  // }
}
