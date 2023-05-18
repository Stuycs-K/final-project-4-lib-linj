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
    int[][] temp = new int[1][1];
    if (m[0].length == a.m.length){
      temp = new int[this.r][a.m[0].length];
      for (int i = 0; i < temp.length; i++){
        for (int j = 0; j < temp[0].length; j++){
          temp[i][j] += m[j][i] * a.m[i][j];
        }
      }
    } else{
      System.out.println("invalid dimensions");
      System.exit(0);
    }
    return new Matrix(temp);
  }

  // public String toString(){
  //   for (int j = 0; j < m[0].length; j++){
  //     for (int i = 0; i < m.length; i++){
  //       String str
  //     }
  //   }
  // }
}
