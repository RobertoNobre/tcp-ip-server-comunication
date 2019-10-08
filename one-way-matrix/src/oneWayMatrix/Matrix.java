package oneWayMatrix;

import java.io.Serializable;
import java.util.Random;

public class Matrix implements Serializable {

	private static final long serialVersionUID = -4414114422396163664L;
	
	private int[][] matrix;

	public Matrix(int m[][]) {
		matrix = m;
	}
	
	public Matrix(int i, int j) {
		super();
		matrix = gerarMatriz(i, j);
	}
	
	public int[][] gerarMatriz(int i, int j){
		int[][] matrix = new int [i][j];
		
		for( int a = 0; a < i; a++) {
			for( int b = 0; b < j; b++ ) {
		    	 Random r = new Random();
		    	 matrix[a][b] = r.nextInt(10-1) + 1;
			}
		}
		
		return matrix;
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
