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

    @Override
    public void start(Stage primaryStage) throws Exception{
        VantagemCombustivelView view = new VantagemCombustivelView();
        view.exibir();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
