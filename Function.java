/*
 * This is a functional interface
 * This is made to work with map method of Matrix class
*/

@FunctionalInterface

public interface Function {
    /* 
     * Map method to apply some function to every value of a matrix
     * i is current row index
     * j is current column index
     * value is current element at index i, j
    */
    double map(double value, int i, int j);
    
}
