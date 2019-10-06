package oneWayMatrix;

public class Matrix {

	private int[][] matrix;
	
	public Matrix(int height, int width) {
		super();
		matrix = gerarMatriz(height, width);
	}
	
	public int[][] gerarMatriz(int height, int width){
		int[][] a = new int [height][width];
		
		for( int i = 0; i < 5; i++) {
			for( int j = 0; j < 5; j++ ) {
				a[i][j] = 1;
			}
		}
		
		return a;
	}
	
	public void escreveMatriz(int m[][]) {
        for(int i = 0; i < 5; i++) {
        	for(int j = 0; j < 5; j++) {
        		System.out.print(m[i][j] + "  ");
        	}
    		System.out.println("  ");
        }
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
}
