package misc;

import java.lang.Math;

public class TransformationBuilder {
    private Matrix transformationMatrix;

    public TransformationBuilder() {
        this.transformationMatrix = Matrix.createIdentityMatrix();
    }

    public TransformationBuilder reset() {
        this.transformationMatrix = Matrix.createIdentityMatrix();
        return this;
    }

    public Matrix create() {
        return this.transformationMatrix;
    }

    public TransformationBuilder translation(double x, double y, double z) {
        // initialize transformation matrix B.
        double[][] matrixInitValues = {
                {1, 0, 0, x},
                {0, 1, 0, y},
                {0, 0, 1, z},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }

    public TransformationBuilder scaling(double x, double y, double z) {
        // initialize transformation matrix B.
        double[][] matrixInitValues = {
                {x, 0, 0, 0},
                {0, y, 0, 0},
                {0, 0, z, 0},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }

    /**
     * This method will shear the X coordinate by adding fY.
     */
    public TransformationBuilder shearingXY(double f) {
        // initialize transformation matrix B.
        double[][] matrixInitValues = {
                {1, f, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }

    /**
     * This method will shear the X coordinate by adding fZ.
     */
    public TransformationBuilder shearingXZ(double f) {
        // initialize transformation matrix B.
        double[][] matrixInitValues = {
                {1, 0, f, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }

    /**
     * This method will shear the Y coordinate by adding fX.
     */
    public TransformationBuilder shearingYX(double f) {
        // initialize transformation matrix B.
        double[][] matrixInitValues = {
                {1, 0, 0, 0},
                {f, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }

    /**
     * This method will shear the Y coordinate by adding fZ.
     */
    public TransformationBuilder shearingYZ(double f) {
        // initialize transformation matrix B.
        double[][] matrixInitValues = {
                {1, 0, 0, 0},
                {0, 1, f, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }

    /**
     * This method will shear the Z coordinate by adding fX.
     */
    public TransformationBuilder shearingZX(double f) {
        // initialize transformation matrix B.
        double[][] matrixInitValues = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {f, 0, 1, 0},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }

    /**
     * This method will shear the Z coordinate by adding fY.
     */
    public TransformationBuilder shearingZY(double f) {
        // initialize transformation matrix B.
        double[][] matrixInitValues = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, f, 1, 0},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }

    /**
     * This method will rotate around the X-axis.
     * param angle is the corner in °.
     */
    public TransformationBuilder rotateX(double angle) {
        // initialize transformation matrix B.
        double angleInRadians = Math.toRadians(angle);
        double[][] matrixInitValues = {
                {1, 0, 0, 0},
                {0, Math.cos(angleInRadians), -Math.sin(angleInRadians), 0},
                {0, Math.sin(angleInRadians), Math.cos(angleInRadians), 0},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }

    /**
     * This method will rotate around the Y-axis.
     * param angle is the corner in °.
     */
    public TransformationBuilder rotateY(double angle) {
        // initialize transformation matrix B.
        double angleInRadians = Math.toRadians(angle);
        double[][] matrixInitValues = {
                {Math.cos(angleInRadians), 0, Math.sin(angleInRadians), 0},
                {0, 1, 0, 0},
                {-Math.sin(angleInRadians), 0, Math.cos(angleInRadians), 0},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }

    /**
     * This method will rotate around the Z-axis.
     * param angle is the corner in °.
     */
    public TransformationBuilder rotateZ(double angle) {
        // initialize transformation matrix B.
        double angleInRadians = Math.toRadians(angle);
        double[][] matrixInitValues = {
                {Math.cos(angleInRadians), -Math.sin(angleInRadians), 0, 0},
                {Math.sin(angleInRadians), Math.cos(angleInRadians), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }

    /**
     * This method will rotate around an arbitrary axis.
     * param angle is the corner in °.
     */
    public TransformationBuilder rotateArbitraryAxis(double angle, Vector u) {
        // initialize transformation matrix B.
        double angleInRadians = Math.toRadians(angle);
        double c = Math.cos(angleInRadians);
        double s = Math.sin(angleInRadians);
        double[][] matrixInitValues = {
                {c + (1-c)*Math.pow(u.getX(),2), (1-c) * u.getY() * u.getX() - s * u.getZ(), (1-c) * u.getZ() * u.getX() + s*u.getY(), 0},
                {(1-c) * u.getX() * u.getY() + s * u.getZ(), c + (1-c)*Math.pow(u.getY(),2), (1-c) * u.getZ() * u.getY() - s * u.getX(), 0},
                {(1-c) * u.getX() * u.getZ() - s * u.getY(), (1-c) * u.getY() * u.getZ() + s * u.getX(), c + (1-c)*Math.pow(u.getZ(),2), 0},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }

    public TransformationBuilder inverseTranslation(double x, double y, double z) {
        // initialize transformation matrix B.
        double[][] matrixInitValues = {
                {1, 0, 0, -x},
                {0, 1, 0, -y},
                {0, 0, 1, -z},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }

    public TransformationBuilder inverseScaling(double x, double y, double z) {
        // initialize transformation matrix B.
        double[][] matrixInitValues = {
                {1/x, 0, 0, 0},
                {0, 1/y, 0, 0},
                {0, 0, 1/z, 0},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }

    /**
     * This method will shear the X coordinate by adding fY.
     */
    public TransformationBuilder inverseShearingXY(double f) {
        // initialize transformation matrix B.
        double[][] matrixInitValues = {
                {1, -f, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }

    /**
     * This method will shear the X coordinate by adding fZ.
     */
    public TransformationBuilder inverseShearingXZ(double f) {
        // initialize transformation matrix B.
        double[][] matrixInitValues = {
                {1, 0, -f, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }

    /**
     * This method will shear the Y coordinate by adding fX.
     */
    public TransformationBuilder inverseShearingYX(double f) {
        // initialize transformation matrix B.
        double[][] matrixInitValues = {
                {1, 0, 0, 0},
                {-f, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }

    /**
     * This method will shear the Y coordinate by adding fZ.
     */
    public TransformationBuilder inverseShearingYZ(double f) {
        // initialize transformation matrix B.
        double[][] matrixInitValues = {
                {1, 0, 0, 0},
                {0, 1, -f, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }

    /**
     * This method will shear the Z coordinate by adding fX.
     */
    public TransformationBuilder inverseShearingZX(double f) {
        // initialize transformation matrix B.
        double[][] matrixInitValues = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {-f, 0, 1, 0},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }

    /**
     * This method will shear the Z coordinate by adding fY.
     */
    public TransformationBuilder inverseShearingZY(double f) {
        // initialize transformation matrix B.
        double[][] matrixInitValues = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, -f, 1, 0},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }

    /**
     * This method will rotate around the X-axis.
     * param angle is the corner in °.
     */
    public TransformationBuilder inverseRotateX(double angle) {
        // initialize transformation matrix B.
        double angleInRadians = Math.toRadians(angle);
        double[][] matrixInitValues = {
                {1, 0, 0, 0},
                {0, Math.cos(angleInRadians), Math.sin(angleInRadians), 0},
                {0, -Math.sin(angleInRadians), Math.cos(angleInRadians), 0},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }

    /**
     * This method will rotate around the Y-axis.
     * param angle is the corner in °.
     */
    public TransformationBuilder inverseRotateY(double angle) {
        // initialize transformation matrix B.
        double angleInRadians = Math.toRadians(angle);
        double[][] matrixInitValues = {
                {Math.cos(angleInRadians), 0, -Math.sin(angleInRadians), 0},
                {0, 1, 0, 0},
                {Math.sin(angleInRadians), 0, Math.cos(angleInRadians), 0},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }

    /**
     * This method will rotate around the Z-axis.
     * param angle is the corner in °.
     */
    public TransformationBuilder inverseRotateZ(double angle) {
        // initialize transformation matrix B.
        double angleInRadians = Math.toRadians(angle);
        double[][] matrixInitValues = {
                {Math.cos(angleInRadians), Math.sin(angleInRadians), 0, 0},
                {-Math.sin(angleInRadians), Math.cos(angleInRadians), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        Matrix matrix = new Matrix(matrixInitValues);
        // Multiply B with A (BxA) so that it will transform as requested. A is the previous transformationMatrix
        this.transformationMatrix = Operations.matrixProduct(matrix, this.transformationMatrix);
        return this;
    }
}
