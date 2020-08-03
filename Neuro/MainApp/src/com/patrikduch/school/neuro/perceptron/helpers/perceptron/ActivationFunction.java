package com.patrikduch.school.neuro.perceptron.helpers.perceptron;

// Aktivační funkce využivající krokovou funkci
public class ActivationFunction {

    private static int bias = 1;

    public static int stepFunction(float activation) {

        if(activation >= bias) { // 1 značí práh

            return 1; // Aktivace neuronu
        }

        return 0; // Neuron neni aktivovan
    }
}
