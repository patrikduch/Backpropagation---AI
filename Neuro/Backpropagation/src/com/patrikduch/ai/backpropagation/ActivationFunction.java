package com.patrikduch.ai.backpropagation;

public class ActivationFunction {


    public static float sigmoid(float x) {
        return (float) (1 / (1+Math.exp(-x)));
    }

    // Derivace sigmoidské funkce
    public static float dSigmoid(float x) {
        return x*(1-x);
    }

}
