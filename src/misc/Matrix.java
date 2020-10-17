package misc;

public class Matrix {

    private double[][] matrix;

    public Matrix(){
        this.matrix = new double[4][4];
        for(int i = 0; i<4; i++){
            for(int j = 0; i<4; i++){
                this.matrix[i][j] = 0;
            }
        }
    }

    public static Matrix createUnitMatrix(){
        double[][] matrixInitValues = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        return new Matrix(matrixInitValues);
    }


    public Matrix(double[][] matrix){
        if(matrix.length != 4)
            throw new IllegalArgumentException("base_classes.Matrix has not the correct dimentions of 4 x 4.");
        for(int i = 0; i<4; i++){
            if(matrix[i].length != 4)
                throw new IllegalArgumentException("base_classes.Matrix has not the correct dimentions of 4 x 4.");
        }
        this.matrix = matrix;
    }

    public void setElement(int row, int column, double value){
        if(row<0 || row>3 || column<0 || column>3)
            throw new IllegalArgumentException("This row or column of this element is not in a valid range (0 to 3).");
        this.matrix[row][column] = value;
    }

    public double getElement(int row, int column){
        return this.matrix[row][column];
    }

    @Override
    public String toString() {
        String string = "";
        for(int i = 0; i<4; i++) {
            string = string.concat("[ ");
            for (int j = 0; j < 4; j++){
                string = string.concat(""+matrix[i][j]+" ");
            }
            string = string.concat("]\n");
        }
        return string;
    }
}
