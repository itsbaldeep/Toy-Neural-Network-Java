/*
  * This is the Neural Network class
  * Contains algorithms for Feedforward and Backpropagation
*/

public class NeuralNetwork {
    
    // Variables for nodes
    private final int inputNodes;
    private final int hiddenNodes;
    private final int outputNodes;
    
    // Variables for weights
    private final Matrix weightsIH;
    private final Matrix weightsHO;
    
    // Variables for baises
    private final Matrix hiddenBias;
    private final Matrix outputBias;
    
    // Learning Rate
    private double learningRate;
    
    // Activation Function
    private String activationFunction;
    
    NeuralNetwork(int inputNodes, int hiddenNodes, int outputNodes) {
        
        // Instantiating Layers
        this.inputNodes = inputNodes;
        this.hiddenNodes = hiddenNodes;
        this.outputNodes = outputNodes;
        
        // Instantiating Weights
        this.weightsIH = Matrix.random(this.hiddenNodes, this.inputNodes);
        this.weightsHO = Matrix.random(this.outputNodes, this.hiddenNodes);
        
        // Instantiating Biases
        this.hiddenBias = Matrix.random(this.hiddenNodes, 1);
        this.outputBias = Matrix.random(this.outputNodes, 1);
        
        // Instantiating Learning Rate
        this.learningRate = 0.1;
        
        // Instantiating Activation Function
        this.activationFunction = "Sigmoid";
        
    }
    
    // Feedforward algorithm
    public double[] predict(double[] inputsArray) {
        
        // Variables for each layer
        Matrix inputs;
        Matrix hidden;
        Matrix outputs;
        
        // Generating each node
        try {
            
            // Making a matrix from input array
            inputs = Matrix.fromArray(inputsArray);
            
            // Multiplying weights by inputs to get hidden layer
            hidden = Matrix.multiply(this.weightsIH, inputs);
            
            // Adding bias to and activating hidden layer
            hidden.add(this.hiddenBias);
            hidden.activate(this.activationFunction);
            
            // Multiplying weights by outputs to get output layer
            outputs = Matrix.multiply(this.weightsHO, hidden);
            
            // Adding bias to and activating outputs layer
            outputs.add(this.outputBias);
            outputs.activate(this.activationFunction);
            
            return outputs.toArray();
            
        } 
        
        // Printing error, if any
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        // Returning an empty array if failed ro predict
        return new double[this.outputNodes];
        
    }
    
    // Backpropagation algorithm
    public void train(double[] inputsArray, double[] labelsArray) {
        
        // Variables for each layer
        Matrix inputs;
        Matrix hidden;
        Matrix outputs;
        
        // Variable for labels
        Matrix labels;
        
        // Generating each node
        // Then propagating backwards
        try {
            
            // Making a matrix from input array and label array
            inputs = Matrix.fromArray(inputsArray);
            labels = Matrix.fromArray(labelsArray);
            
            // Feedforward algorithm
            hidden = Matrix.multiply(this.weightsIH, inputs);
            hidden.add(this.hiddenBias);
            hidden.activate(this.activationFunction);
            
            outputs = Matrix.multiply(this.weightsHO, hidden);
            outputs.add(this.outputBias);
            outputs.activate(this.activationFunction);
            
            // Calculating error and gradients
            Matrix outputError = Matrix.subtract(labels, outputs);
            Matrix outputGradients = Matrix.deactivate(outputs, this.activationFunction);
            
            // Multiplying gradients with error and scaling with learning rate
            outputGradients.multiply(outputError);
            outputGradients.scale(this.learningRate);
            
            // Finding the change of weights
            Matrix hiddenTranspose = Matrix.transpose(hidden);
            Matrix deltaWeightsHO = Matrix.multiply(outputGradients, hiddenTranspose);
            
            // Fixing the weights and adding to bias
            this.weightsHO.add(deltaWeightsHO);
            this.outputBias.add(outputGradients);
            
            // Calculating error and gradients
            Matrix weightsHOTranspose = Matrix.transpose(this.weightsHO);
            Matrix hiddenError = Matrix.multiply(weightsHOTranspose, outputError);
            Matrix hiddenGradients = Matrix.deactivate(hidden, this.activationFunction);
            
            // Multiplying gradients with error and scaling with learning rate
            hiddenGradients.multiply(hiddenError);
            hiddenGradients.scale(this.learningRate);
            
            // Finding the change of weights
            Matrix inputsTranspose = Matrix.transpose(inputs);
            Matrix deltaWeightsIH = Matrix.multiply(hiddenGradients, inputsTranspose);
            
            // Fixing the weights and adding to bias
            this.weightsIH.add(deltaWeightsIH);
            this.hiddenBias.add(hiddenGradients);
            
        }
        
        // Printing error, if any
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    // Setter for learning rate
    public void setLearningRate(double newLearningRate) {
        this.learningRate = newLearningRate;
    }
    
    // Setter for activation function
    public void setActivationFunction(String newActivationFunction) {
        this.activationFunction = newActivationFunction;
    }
    
}
