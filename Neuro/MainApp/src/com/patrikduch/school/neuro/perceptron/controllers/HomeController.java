package com.patrikduch.school.neuro.perceptron.controllers;
import com.patrikduch.ai.backpropagation.BackproConst;
import com.patrikduch.ai.backpropagation.BackpropNeuralNetwork;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.Arrays;

// Kontroller pro uvodni view
public class HomeController {

    @FXML
    TextArea resultArea;

    @FXML
    Text resultText, infoResultText;

    @FXML
    BorderPane mainLayout;

    @FXML
    Button outputBtnFour, outputBtnTwo, outputBtnThree, outputBtnOne;
    String newLine = System.getProperty("line.separator");

    @FXML
    Text gateInfo;

    private String actualGate;



    BackpropNeuralNetwork backpropNeuralNetwork;

    // Nastaveni pravdivostní hodnoty jak pro vstup tak výstup
    public void onButtonClicked(ActionEvent event){

        Button button = (Button) event.getSource();

        if(button.getText().equals("1")) {
            button.setText("0");
        } else {
            button.setText("1");
        }
    }


    private String getGate(float[] input) {

        if(Arrays.equals(input, new float[]{0,1,1,1})) {

            return "OR";
        }

        if(Arrays.equals(input, new float[]{0,0,0,1})) {

            return "AND";
        }

        if(Arrays.equals(input, new float[]{0,1,1,0})) {

            return "XOR";
        }

        if(Arrays.equals(input, new float[]{1,0,0,1})) {

            return "XNOR";
        }


        if(Arrays.equals(input, new float[]{1,0,0,0})) {

            return "NOR";
        }


        if(Arrays.equals(input, new float[]{1,1,1,0})) {

            return "NAND";
        }



        return "N/A";
    }


    public void onRemovalButtonClicked() {

        outputBtnOne.setText("0");
        outputBtnTwo.setText("0");
        outputBtnThree.setText("0");
        outputBtnFour.setText("0");

        resultArea.setText("");
        gateInfo.setText("");

    }

    StringBuilder sb = new StringBuilder();


    public void onSubmitClicked(ActionEvent event) {

        gateInfo.setText("");

        BackpropNeuralNetwork backpropNeuralNetwork = new BackpropNeuralNetwork(2,3,1);

        // Ziskani vystupu od uživatele
        float[] output = {
                Integer.parseInt(outputBtnOne.getText()),
                Integer.parseInt(outputBtnTwo.getText()),
                Integer.parseInt(outputBtnThree.getText()),
                Integer.parseInt(outputBtnFour.getText()),
        };


        float [][] trainingResults = new float[][] {
                new float[] {1},
                new float[] {0},
                new float[] {0},
                new float[] {1},
        };


        if(getGate(output).equals("XNOR")) {

            trainingResults = new float[][] {
                    new float[] {1},
                    new float[] {0},
                    new float[] {0},
                    new float[] {1},
            };

            actualGate = "XNOR";
        }


        else if(getGate(output).equals("XOR")) {

            trainingResults = new float[][] {
                    new float[] {0},
                    new float[] {1},
                    new float[] {1},
                    new float[] {0},
            };

            actualGate = "XOR";

        }


        else if(getGate(output).equals("AND")) {

            trainingResults = new float[][] {
                    new float[] {0},
                    new float[] {0},
                    new float[] {0},
                    new float[] {1},
            };

            actualGate = "AND";

        }


        else if(getGate(output).equals("OR")) {

            trainingResults = new float[][] {
                    new float[] {0},
                    new float[] {1},
                    new float[] {1},
                    new float[] {1},
            };

            actualGate = "OR";

        } else if(getGate(output).equals("N/A")) {

            resultArea.setText("Neexistující hradlo.");

            actualGate = "N/A";

            gateInfo.setText("Hradlo: "+actualGate);

            return;
        }

        else if(getGate(output).equals("NOR")) {

            trainingResults = new float[][] {
                    new float[] {1},
                    new float[] {0},
                    new float[] {0},
                    new float[] {0},
            };

            actualGate = "NOR";

        }


        else if(getGate(output).equals("NAND")) {

            trainingResults = new float[][] {
                    new float[] {1},
                    new float[] {1},
                    new float[] {1},
                    new float[] {0},
            };

            actualGate = "NAND";

        }


        // Trénovací množina
        float[][] trainingData = new float[][] {
                new float[] {0,0},
                new float[] {0,1},
                new float[] {1,0},
                new float[] {1,1},
        };

        resultArea.setFont(Font.font ("Verdana", 15));
        resultArea.setText("Probiha zpracování");
        resultArea.setStyle("-fx-text-fill: green;");



        float[][] finalTrainingResults = trainingResults;
        Task task = new Task<Void>() {
            @Override
            public Void call() {

                try {

                    for(int iteration = 0; iteration< BackproConst.ITERATIONS; iteration++) {

                        for(int i = 0; i< finalTrainingResults.length; i++) {

                            backpropNeuralNetwork.train(trainingData[i], finalTrainingResults[i],
                                    BackproConst.LEARNING_RATE, BackproConst.MOMENTUM);
                        }


                        for(int i = 0; i< finalTrainingResults.length; i++) {

                            float [] tmp = trainingData[i];

                            System.out.println("Číslo iterace "+ iteration+1);

                            sb.append("Číslo iterace "+ (iteration+1));
                            sb.append("     Učení: "+ tmp[0] + " --> " + tmp[1]);
                            sb.append("     Stav: "+ backpropNeuralNetwork.run(tmp)[0]);
                            sb.append(newLine);

                            System.out.printf("%.1f, %.1f --> %.3f\n", tmp[0], tmp[1], backpropNeuralNetwork.run(tmp)[0]);
                        }
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        task.setOnSucceeded(taskFinishEvent -> {

            resultArea.setStyle("-fx-text-fill:  black;");
            resultArea.setText(sb.toString());

            gateInfo.setText("Hradlo: "+ actualGate);
        });


        new Thread(task).start();



    }





}
