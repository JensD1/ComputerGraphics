package misc;

public class Operations {

    public Operations() {
    }

    public static Vector vectorSum(Vector vector1, Vector vector2) {
        Vector vector = new Vector();
        for (int i = 0; i < 4; i++) {
            vector.setElement(i, vector1.getElement(i) + vector2.getElement(i));
        }
        return vector;
    }

    public static Vector scalarVectorProduct(double scalar, Vector vector){
        Vector returnVector = new Vector();
        for(int i = 0; i<4; i++){
            returnVector.setElement(i, scalar * vector.getElement(i));
        }
        return returnVector;
    }

    /**
     * This method will return a scalar that is the result of a dot product.
     * @param point1
     * @param point2
     * @return
     */
    public static double dotProduct(Point point1, Point point2) {
        double dotProduct = 0;
        for (int i = 0; i < 3; i++) {
            dotProduct = dotProduct + point1.getElement(i) * point2.getElement(i);
        }
        return dotProduct;
    }

    /**
     * This method will return a scalar that is the result of a dot product.
     * @param vector1
     * @param point1
     * @return
     */
    public static double dotProduct(Vector vector1, Point point1) {
        double dotProduct = 0;
        for (int i = 0; i < 3; i++) {
            dotProduct = dotProduct + vector1.getElement(i) * point1.getElement(i);
        }
        return dotProduct;
    }

    /**
     * This method will return a scalar that is the result of a dot product.
     * @param point1
     * @param vector2
     * @return
     */
    public static double dotProduct(Point point1, Vector vector2) {
        double dotProduct = 0;
        for (int i = 0; i < 3; i++) {
            dotProduct = dotProduct + point1.getElement(i) * vector2.getElement(i);
        }
        return dotProduct;
    }

    /**
     * This method will return a scalar that is the result of a dot product.
     * @param vector1
     * @param vector2
     * @return
     */
    public static double dotProduct(Vector vector1, Vector vector2) {
        double dotProduct = 0;
        for (int i = 0; i < 3; i++) {
            dotProduct = dotProduct + vector1.getElement(i) * vector2.getElement(i);
        }
        return dotProduct;
    }

    public static Point pointSum(Point point1, Point point2) {
        Point point = new Point();
        for (int i = 0; i < 4; i++) {
            point.setElement(i, point1.getElement(i) + point2.getElement(i));
        }
        return point;
    }

    public static Point pointProduct(Point point1, Point point2) {
        Point point = new Point();
        for (int i = 0; i < 4; i++) {
            point.setElement(i, point1.getElement(i) * point2.getElement(i));
        }
        return point;
    }

    public static Matrix matrixAddition(Matrix matrix1, Matrix matrix2) {
        Matrix matrix = new Matrix();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix.setElement(i, j, matrix1.getElement(i, j) + matrix2.getElement(i, j));
            }
        }
        return matrix;
    }

    public static Matrix matrixProduct(Matrix matrix1, Matrix matrix2) {
        Matrix matrix = new Matrix();
        double tempValue;
        for (int rowFirstMatrix = 0; rowFirstMatrix < 4; rowFirstMatrix++) {
            for (int columnLastMatrix = 0; columnLastMatrix < 4; columnLastMatrix++) {
                tempValue = 0;
                for (int i = 0; i < 4; i++) {
                    tempValue += matrix1.getElement(rowFirstMatrix, i) * matrix2.getElement(i, columnLastMatrix);
                }
                matrix.setElement(rowFirstMatrix, columnLastMatrix, tempValue);
            }
        }
        return matrix;
    }

    public static Vector vectorTransformation(Matrix matrix, Vector vector){
        Vector returnVector = new Vector();
        double tempValue;
        for (int rowFirstMatrix = 0; rowFirstMatrix < 4; rowFirstMatrix++) {
            tempValue = 0;
            for (int i = 0; i < 4; i++) {
                tempValue = tempValue + matrix.getElement(rowFirstMatrix, i) * vector.getElement(i);
            }
            returnVector.setElement(rowFirstMatrix, tempValue);
        }
        return returnVector;
    }

    public static Point pointVectorAddition(Point point, Vector vector){
        Point returnPoint = new Point();
        for(int i = 0; i<4; i++){
            returnPoint.setElement(i, point.getElement(i) + vector.getElement(i));
        }
        return returnPoint;
    }

    public static Point pointTransformation(Matrix matrix, Point point){
        Point returnPoint = new Point();
        double tempValue;
        for (int rowFirstMatrix = 0; rowFirstMatrix < 4; rowFirstMatrix++) {
            tempValue = 0;
            for (int i = 0; i < 4; i++) {
                tempValue = tempValue + matrix.getElement(rowFirstMatrix, i) * point.getElement(i);
            }
            returnPoint.setElement(rowFirstMatrix, tempValue);
        }
        return returnPoint;
    }

    public static Vector pointSubstraction(Point point1, Point point2){
        Vector vector = new Vector();
        for (int i = 0; i < 4; i++) {
            vector.setElement(i, point1.getElement(i) - point2.getElement(i));
        }
        return vector;
    }
}
