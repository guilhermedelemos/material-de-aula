package com.estadodaarte.exemplos;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VantagemCombustivelView {

    private Stage janela;
    private VBox layout;
    private Label labelGasolina;
    private Label labelEtanol;
    private TextField textfieldGasolina;
    private TextField textfieldEtanol;
    private Button buttonCalcular;
    private Button buttonLimpar;

    public VantagemCombustivelView() {
        super();
        janela = new Stage();
        janela.initModality(Modality.APPLICATION_MODAL);
        janela.setTitle("Vantagem de Combustível");
        janela.setHeight(250);
        janela.setWidth(200);

        labelEtanol = new Label("Preço do Etanol");
        textfieldEtanol = new TextField();

        labelGasolina = new Label("Preço da Gasolina");
        textfieldGasolina = new TextField();

        buttonCalcular = new Button("Calcular");
        buttonCalcular.setOnAction(e -> {
            double resultado = Double.parseDouble(textfieldEtanol.getText().replace(",", ".")) /
                Double.parseDouble(textfieldGasolina.getText().replace(",", "."));
            if(resultado <= 70) {
                AlertBox.exibir("Vantagem", "Abasteça com GASOLINA");
            } else if(resultado > 70) {
                AlertBox.exibir("Vantagem", "Abasteça com ETANOL");
            } else {
                AlertBox.exibir("Vantagem", "Valor fora da faixa aceitável");
            }

        });

        buttonLimpar = new Button("Limpar");
        buttonLimpar.setOnAction(e -> {
            textfieldGasolina.clear();
            textfieldEtanol.clear();
        });

        layout = new VBox(10);
        layout.getChildren().addAll(labelEtanol, textfieldEtanol, labelGasolina, textfieldGasolina, buttonCalcular, buttonLimpar);
        layout.setAlignment(Pos.CENTER);

        janela.setScene(new Scene(layout));
    }

    public void exibir() {
        janela.showAndWait();
    }

}
