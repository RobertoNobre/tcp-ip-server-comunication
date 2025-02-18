package oneWayMatrix;

import java.io.Serializable;
import java.util.Random;

public class Matrix implements Serializable {

	private static final long serialVersionUID = -4307447869025181951L;
	
	private int[][] matrix;

	public Matrix(int m[][]) {
		matrix = m;
	}
	
	public Matrix(int i, int j) {
		super();
		matrix = buildMatrix(i, j);
	}
	
	public int[][] buildMatrix(int i, int j){
		int[][] matrix = new int [i][j];
		
		for( int a = 0; a < i; a++) {
			for( int b = 0; b < j; b++ ) {
		    	 Random r = new Random();
		    	 matrix[a][b] = r.nextInt(10-1) + 1;
			}
		}
		
		return matrix;
	}
	
	public void writeMatrix(int m[][]) {
		int c = m[0].length, l = m.length, j = 0;

        for(int i = 0; i < l; i++) {
        	for(j = 0; j < c; j++) {
        		if(j == 0) {
        			System.out.print("|");
        		}
        		System.out.print("  " + m[i][j] + "  ");
        		if(j == (c-1)) {
        			System.out.print("|");
        		}
        	}
    		System.out.println("\n");
        }
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
}
