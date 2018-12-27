# Toy-Neural-Network-Java
This is a basic machine learning library written in Java based on Daniel Shiffman's [Toy-Neural-Network-JS](https://github.com/CodingTrain/Toy-Neural-Network-JS).

# Usage
```java

NeuralNetwork brain = new NeuralNetwork(2,2,1);
double[] inputs = {1,2};
double[] outputs = {3}

for (int i = 0; i < 10000; i++) brain.train(inputs, outputs);

double[] guess = brain.predict(inputs);
System.out.println(guess[0]);

```

# Possible configurations
```java
brain.setLearningRate(0.2);
brain.setActivationFunction("Tanh"); // Only Sigmoid or Tanh
```

# Inspiration
I watched the Neural Networks playlist on The Coding Train YouTube Channel. I loved that and followed along while coding in JavaScript, after some getting some interest and knowledge in Neural Networks, I decided to remake that in Java.

Link to the playlist: [Session 4 - Neural Networks - Intelligence and Learning](https://www.youtube.com/playlist?list=PLRqwX-V7Uu6Y7MdSCaIfsxc561QI0U0Tb)

# Why Java?
I think as Matrix Implementation can take advantage of Polymorphism in Java, which is not yet available in JavaScript. Also static typing allows reliable code. In addition, the better performance over JavaScript made me want to use Java.

# Any different?
Not really, I wanted to do different things, like optimizing it more, adding more activation funtions, making dynamic sequential layer system, and implementing diffferent optimizers and loss functions. But that can be done another day in a seperate and advanced library.

# Possibilities?
Depends, since JavaScript runs on browser, we can easily implement neural networks in user interfaces (for example, games). On the contrary, Java does not have any **easy** implementations of user interface. But Java can run better on server side technologies, so it can be good for running training on big data/databases much better than JavaScript.
