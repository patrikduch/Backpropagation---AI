package com.patrikduch.school.neuro.perceptron.helpers.perceptron;

public class Perceptron {

    // Vahy
    private float [] weights;
    // Vstupy
    private float [][] input;
    // Vystupy
    private float [] output;
    // Počet vah
    private int numOfWeights;

    private StringBuilder statuStringBuilder;
    String newLine = System.getProperty("line.separator");

    public Perceptron() {
        // Inicializace uložiště pro ukládání chyb
        this.statuStringBuilder = new StringBuilder();
    }

    public Perceptron(float[][] input, float[] output) {

        this.input = input;
        this.output = output;
        this.numOfWeights = input[0].length; // První řádek testovacích dat
        this.weights = new float[numOfWeights];

        // Inicializace uložiště pro ukládání chyb
        this.statuStringBuilder = new StringBuilder();
    }


    // Inicializace hran
    public void initialiWeight() {

        for(int i = 0; i< numOfWeights; ++i) {
            weights[i] = 0;
        }
    }

    // epochs - počet iteraci
    public void train(int epochs, float learningRate) {

        // Predikce, že na začátku 100% chyba
        float totalError = 1;

        int counter = 0;

        while(totalError > 0) {

            if(counter> epochs) {

                this.statuStringBuilder.append("Jednovrstvá sít nedokáže vyřešit tento problém.");
                return;
            }

            totalError = 0;

            for(int i =0; i< output.length; i++) {

                float calculatedOutput = calculateOutput(input[i]);
                float error = Math.abs(output[i] - calculatedOutput); // Rozdil mezi aktualnim  a trenovacimi datz

                totalError += error;

                for(int j =0; j<numOfWeights; j++) {
                    this.statuStringBuilder.append("Aktualizace hrany: "+weights[j]);
                    weights[j] = weights[j] + learningRate * input[i][j] * error;
                    this.statuStringBuilder.append("-->: "+weights[j]);
                    this.statuStringBuilder.append(newLine);
                }
            }

            if(totalError!= 0) {

            }
            this.statuStringBuilder.append("Síť se musí stale učit. Stav chyby je: " + totalError);
            this.statuStringBuilder.append(newLine);


            counter++;

        }

        this.statuStringBuilder.append("Síť je naučena.");
        this.statuStringBuilder.append(newLine);
    }

    public float calculateOutput(float[] input) {

        float sum = 0f;

        for(int i = 0; i< input.length; ++i) {

            sum = sum + weights[i] * input[i];
        }

        return ActivationFunction.stepFunction(sum);
    }

    // Gettery, settery

    public float[][] getInput() {
        return input;
    }

    public void setInput(float[][] input) {
        this.input = input;
    }

    public float[] getOutput() {
        return output;
    }

    public void setOutput(float[] output) {
        this.output = output;
    }


    public StringBuilder getStatuStringBuilder() {
        return statuStringBuilder;
    }

    public void setStatuStringBuilder(StringBuilder statuStringBuilder) {
        this.statuStringBuilder = statuStringBuilder;
    }


}
