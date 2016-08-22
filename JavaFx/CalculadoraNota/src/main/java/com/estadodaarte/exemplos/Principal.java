package com.estadodaarte.exemplos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Principal extends Application {

    private static final double PESO_AO = 0.3;
    private static final double PESO_AP = 0.3;
    private static final double PESO_T  = 0.3;
    private static final double PESO_A  = 0.2;

    /* Janela da aplicação (incluindo barra de título, botões de ação e seu conteúdo */
    private Stage janela;
    /* Conteúdo da janela */
    private Scene conteudo;
    /* Componentes de tela */
    private Label labelResultado;
    private Label labelAO;
    private Label labelAP;
    private Label labelT;
    private Label labelA;
    private TextField textfieldNotaAO;
    private TextField textfieldNotaAP;
    private TextField textfieldNotaT;
    private TextField textfieldNotaA;
    private Button buttonCalcular;

    @Override
    public void start(Stage primaryStage) throws Exception{
        double notaAO = 0;
        double notaAP = 0;
        double notaT  = 0;
        double notaA  = 0;

        this.janela = primaryStage;

        primaryStage.setTitle("Calculadora Notas");
        VBox layout = new VBox();

        labelAO = new Label("Nota da Avaliação Oficial (AO) [peso 0,3]:");
        layout.getChildren().add(labelAO);

        textfieldNotaAO = new TextField();
        layout.getChildren().add(textfieldNotaAO);

        labelAP = new Label("Nota da Avaliação Parcial (AP) [peso 0,3]:");
        layout.getChildren().add(labelAP);

        textfieldNotaAP = new TextField();
        layout.getChildren().add(textfieldNotaAP);

        labelT = new Label("Nota do Trabalho (T) [peso 0,3]:");
        layout.getChildren().add(labelT);

        textfieldNotaT = new TextField();
        layout.getChildren().add(textfieldNotaT);

        labelA = new Label("Nota de Assiduidade (A) [peso 0,1]:");
        layout.getChildren().add(labelA);

        textfieldNotaA = new TextField();
        layout.getChildren().add(textfieldNotaA);

        labelResultado = new Label();
        layout.getChildren().add(labelResultado);

        buttonCalcular = new Button("Calcular");
        buttonCalcular.setOnAction(e -> {
            double resultado = calcularNotaFinal(
                    Double.parseDouble(textfieldNotaAO.getText()),
                    Double.parseDouble(textfieldNotaAP.getText()),
                    Double.parseDouble(textfieldNotaT.getText()),
                    Double.parseDouble(textfieldNotaA.getText())
            );
            String situacao = "";
            if(resultado < 40) {
                situacao = "Reprovado";
            } else if(resultado >= 40 && resultado <70) {
                situacao = "Exame";
            } else if(resultado >=70) {
                situacao = "Aprovado";
            } else {
                situacao = "Hummm, ehhhh, acho que, ..., não sei!";
            }
            labelResultado.setText("Sua nota é: " + resultado + "\nSituação: " + situacao);
        });
        layout.getChildren().add(buttonCalcular);

        this.conteudo = new Scene(layout, 800, 600);
        primaryStage.setScene(this.conteudo);
        primaryStage.show();
    }

    private double calcularNotaFinal(double notaAO, double notaAP, double notaT, double notaA) {
        return notaAO * PESO_AO + notaAP * PESO_AP + notaT * PESO_T + notaA * PESO_A;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
