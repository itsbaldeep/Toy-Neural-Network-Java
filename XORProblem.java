/* 
  * This is an example of the Toy Neural Network
  * Solves the famous XOR Problem
*/

public class XORProblem {
    
    // Only contains a main method
    public static void main(String[] args) {
        
        // Variables
        int trainingRate = 50000;
        int hiddenNodes = 2;
        double learningRate = 0.1;
        
        // Activation Fuction can only be Tanh or Sigmoid
        String activationFunction = "Sigmoid";  
        
        // Preparation
        NeuralNetwork brain = new NeuralNetwork(2, hiddenNodes, 1);
        brain.setLearningRate(learningRate);
        brain.setActivationFunction(activationFunction);
        
        double[][] inputs = { {1,0}, {0,1}, {1,1}, {0,0} };
        double[][] outputs = { {1}, {1}, {0}, {0} };
        
        // Training
        for (int n = 0; n < trainingRate; n++) {
            int index = (int) (Math.random() * inputs.length);
            brain.train(inputs[index], outputs[index]);
        }
        
        // Predicting
        for (double[] input : inputs) {
            double[] guess = brain.predict(input);
            System.out.println(guess[0]);
        }
        // Outputs (aprroximately) 1, 1, 0, 0
        
    }
    
}
