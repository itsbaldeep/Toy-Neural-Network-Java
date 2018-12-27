/*
  * This is the Matrix class
  * Contains algorithms for mathematical functions of a matrix
*/

public class Matrix {
    
    // Variables holding number of rows and columns
    private int rows;
    private int columns;
    
    // Variable holding all the data
    private double[][] data;
    
    // Constructor from number of rows and columns 
    Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new double[this.rows][this.columns];
    }
    
    // Constructor from 2D Array
    Matrix(double[][] data) {
        this.rows = data.length;
        this.columns = data[0].length;
        this.data = data;
    }
    
    // Map method
    public Matrix map(Function function) {
        for (int i = 0; i < this.rows; i++) for (int j = 0; j < this.columns; j++)
                this.data[i][j] = function.map(this.data[i][j], i, j);
        return this;
    }
    
    // Static: Map method
    public static Matrix map(Matrix matrix, Function function) {
        return new Matrix(matrix.rows, matrix.columns).map((value, i, j) -> function.map(matrix.data[i][j], i, j));
    }
    
    // Copy method
    public Matrix copy() {
        return Matrix.map(this, (value, i, j) -> this.data[i][j]);
    }
    
    // Matrix From Array method
    public static Matrix fromArray(double[] array) {
        return new Matrix(array.length, 1).map((value, i, j) -> array[i]);
    }
    
    // Matrix To Array Method
    public double[] toArray() {
        double[] array = new double[this.rows * this.columns];
        for (int i = 0, k = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                array[k++] = this.data[i][j];
            }
        }
        return array;
    }
    
    // Randomize every element
    public Matrix randomize(int range) {
        this.map((value, i, j) -> (Math.random() * range * 2) - range);
        return this;
    }
    
    // Default range for randomize method is 1
    public void randomize() {
        this.randomize(1);
    }
    
    // Static: Get a random matrix
    public static Matrix random(int rows, int columns, int range) {
        return new Matrix(rows, columns).randomize(range);
    }
    
    // Static: Default range for random method is 1
    public static Matrix random(int rows, int columns) {
        return Matrix.random(rows, columns, 1);
    }
    
    // Adding a number to a matrix
    public void add(double number) {
        this.map((value, i, j) -> value + number);
    }
    
    // Adding a matrix to another matrix
    public void add(Matrix matrix) throws Exception {
        if (matrix.rows != this.rows || matrix.columns != this.columns) 
            throw new Exception(
                "Can not add " + matrix.rows + " x " + matrix.columns + 
                " matrix with " + this.rows + " x " + this.columns + " matrix"
            );
        this.map((value, i, j) ->  value + matrix.data[i][j]);
    }
    
    // Static: Adding a number to a matrix
    public static Matrix add(Matrix matrix, double number) {
        return new Matrix(matrix.rows, matrix.columns).map((value, i, j) -> matrix.data[i][j] + number);
    }
    
    // Static: Adding a matrix to another matrix
    public static Matrix add(Matrix matrix1, Matrix matrix2) throws Exception {
        if (matrix1.rows != matrix2.rows || matrix1.columns != matrix2.columns) 
            throw new Exception(
                "Can not add " + matrix1.rows + " x " + matrix1.columns + 
                " matrix with " + matrix2.rows + " x " + matrix2.columns + " matrix"
            );
        return new Matrix(matrix1.rows, matrix1.columns).map((value, i, j) -> matrix1.data[i][j] + matrix2.data[i][j]);
    }
    
    // Subtracting a number from matrix
    public void subtract(double number) {
        this.map((value, i, j) -> value - number);
    }
    
    // Subtracting a matrix from another matrix
    public void subtract(Matrix matrix) throws Exception {
        if (matrix.rows != this.rows || matrix.columns != this.columns) 
            throw new Exception(
                "Can not subtract " + matrix.rows + " x " + matrix.columns + 
                " matrix from " + this.rows + " x " + this.columns + " matrix"
            );
        this.map((value, i, j) ->  value - matrix.data[i][j]);
    }
    
    // Static: Subtracting a number from a matrix
    public static Matrix subtract(Matrix matrix, double number) {
        return new Matrix(matrix.rows, matrix.columns).map((value, i, j) -> matrix.data[i][j] - number);
    }
    
    // Static: Subtracting a matrix from another matrix
    public static Matrix subtract(Matrix matrix1, Matrix matrix2) throws Exception {
        if (matrix1.rows != matrix2.rows || matrix1.columns != matrix2.columns) 
            throw new Exception(
                "Can not subtract " + matrix1.rows + " x " + matrix1.columns + 
                " matrix from " + matrix2.rows + " x " + matrix2.columns + " matrix"
            );
        
        return new Matrix(matrix1.rows, matrix1.columns).map((value, i, j) -> matrix1.data[i][j] - matrix2.data[i][j]);
    }
    
    // Transposing a matrix
    public void transpose() {
        Matrix transpose = Matrix.transpose(this);
        this.rows = transpose.rows;
        this.columns = transpose.columns;
        this.data = transpose.data;
    }
    
    // Static: Transposing a matrix
    public static Matrix transpose(Matrix matrix) {
        return new Matrix(matrix.columns, matrix.rows).map((value, i, j) -> matrix.data[j][i]);
    }
    
    // Scale a matrix
    public void scale(double number) {
        this.map((value, i, j) -> value * number);
    }
    
    // Static: Scale a matrix
    public static Matrix scale(Matrix matrix, double number) {
        return new Matrix(matrix.rows, matrix.columns).map((value, i, j) -> matrix.data[i][j] * number);
    }
    
    // Hadamard multiplication
    public void multiply(Matrix matrix) throws Exception {
        if (this.rows != matrix.rows || this.columns != matrix.columns)
            throw new Exception(
                    "Can not take hadamard product of " + this.rows + " x " + this.columns + " matrix with " +
                    matrix.rows + " x " + matrix.columns + " matrix"
            );
        
        this.map((value, i, j) -> value * matrix.data[i][j]);
    }
    
    // Static: Matrix Multiplication
    public static Matrix multiply(Matrix matrix1, Matrix matrix2) throws Exception {
        if (matrix1.columns != matrix2.rows)
            throw new Exception(
                    "Can not perform matrix multiplication of " + matrix1.rows + " x " + matrix2.columns +
                    " matrix with " + matrix2.rows + " x " + matrix2.columns + " matrix"
            );
        
        return new Matrix(matrix1.rows, matrix2.columns).map((value, i, j) -> {
            double sum = 0;
            for (int k = 0; k < matrix1.columns; k++) 
                sum += matrix1.data[i][k] * matrix2.data[k][j];
            return sum;
          });
    }
    
    // Activation of matrix
    public void activate(String function) {
        switch(function) {
            case "Sigmoid":
                this.map((value, i, j) -> 1 / ( 1 + ( 1 / Math.exp(value))));
                break;
            case "Tanh":
                this.map((value, i, j) -> Math.tanh(value));
                break;
        }
    }
    
    // Static: Activation of matrix
    public static Matrix activate(Matrix matrix, String function) {
        switch(function) {
            case "Sigmoid":
                return Matrix.map(matrix, (value, i, j) -> 1 / ( 1 + ( 1 / Math.exp(value))));
            case "Tanh":
                return Matrix.map(matrix, (value, i, j) -> Math.tanh(value));
            default:
                return matrix;
        }
    }
    
    // Deactivation of matrix
    public void deactivate(String function) {
        switch(function) {
            case "Sigmoid":
                this.map((value, i, j) -> value * (1 - value));
                break;
            case "Tanh":
                this.map((value, i, j) -> 1 / Math.pow(Math.cosh(value), 2));
                break;
        }
    }
    
    // Static: Deactivation of matrix
    public static Matrix deactivate(Matrix matrix, String function) {
        switch(function) {
            case "Sigmoid":
                return Matrix.map(matrix, (value, i, j) -> value * (1 - value));
            case "Tanh":
                return Matrix.map(matrix, (value, i, j) -> 1 / Math.pow(Math.cosh(value), 2));
            default:
                return matrix;
        }
    }
    
    // Printing the matrix
    public void print() {
        System.out.format("Matrix: %d Rows x %d Columns", this.rows, this.columns).println();
        for (int i = 0; i < this.rows; i++) {
            System.out.print("[   ");
            for (int j = 0; j < this.columns; j++) {
                System.out.print(this.data[i][j] + "   ");
            }
            System.out.print("]");
            System.out.println();
        }
        System.out.println();
    }
}
