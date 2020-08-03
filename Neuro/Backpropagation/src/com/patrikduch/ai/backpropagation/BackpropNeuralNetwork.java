package com.patrikduch.ai.backpropagation;

import java.awt.*;

public class BackpropNeuralNetwork {

    private Layer[] layers;

    public BackpropNeuralNetwork(int inputSize, int hiddenSize, int outputSize){

        layers = new Layer[2];
        layers[0] = new Layer(inputSize, hiddenSize);
        layers[1] = new Layer(hiddenSize, outputSize);
    }

    // Proces dopředného šíření
    public float [] run(float[] input) {

        float[] activations  = input;

        for(int i = 0; i< layers.length; i++) {
            activations = layers[i].run(activations); // Aktualizace vstupnich aktivaci skrze vstvy
        }

        return activations;

    }

    // learning rate - alpha, momentum - mi
    public void train(float[] input, float[]  targetOutput, float learnignRate, float momentum) {

        // Spočitáme výstup neuronové sítě
        float[] calculatedOutput = run(input);
        float[] error = new float[calculatedOutput.length];// Error pro jednotlivé položky dat

        for(int i =0; i<error.length; i++) {
            error[i] =  targetOutput[i] - calculatedOutput[i];
        }

        // Zaciname od vystupnich neuronu
        for(int  i = layers.length-1;  i>=0; i--) { // Backpropagace
            error = layers[i].train(error, learnignRate, momentum);
        }

    }


    public Layer getLayer(int index) {

        return layers[index];
    }


}
