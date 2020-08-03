package com.patrikduch.school.neuro.perceptron.helpers.threads;

import com.patrikduch.ai.backpropagation.BackproConst;
import com.patrikduch.ai.backpropagation.BackpropNeuralNetwork;
import javafx.scene.control.TextArea;

public class Runnable implements java.lang.Runnable {

    private TextArea _resultArea;
    private float[][]_trainingResults;
    private float[][] _input;

    public Runnable(TextArea resultArea, float[][] trainingResults, float[][] input) {

        this._resultArea = resultArea;
        this._trainingResults = trainingResults;
        this._input = input;

    }

    @Override
    public void run() {

        BackpropNeuralNetwork backpropNeuralNetwork = new BackpropNeuralNetwork(2,3,1);


        for(int iteration = 0; iteration< BackproConst.ITERATIONS; iteration++) {

            for(int i = 0; i<_trainingResults.length; i++) {

                backpropNeuralNetwork.train(_input[i], _trainingResults[i], BackproConst.LEARNING_RATE,
                        BackproConst.MOMENTUM);
            }


            for(int i = 0; i< _trainingResults.length;i++) {

                float [] tmp = _input[i];

                this._resultArea.setText("a");
                System.out.println("Číslo iterace "+ iteration+1);

                System.out.printf("%.1f, %.1f --> %.3f\n", tmp[0], tmp[1], backpropNeuralNetwork.run(tmp)[0]);
            }
        }

    }
}
