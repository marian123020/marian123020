

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class VadovoLangas {
    @FXML
    private Button Prideti;

    @FXML
    private Button Pasalinti;

    @FXML
    private Button atgal;

    @FXML
    private MenuBar save;

    @FXML
    private Button perziureti_sarasa;

    @FXML
    private Button redaguoti;

    @FXML
    private Button paslaugos;

    @FXML
    void initialize()
    {
        Prideti.setOnAction(event -> {
            Prideti.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(VadovasPridet.class.getResource("VadovasPridet.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                System.out.println("");
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
        Pasalinti.setOnAction(event -> {
            Pasalinti.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(VadovasPasalinti.class.getResource("VadovasPasalinti.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                System.out.println("");
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
        perziureti_sarasa.setOnAction(event -> {
            perziureti_sarasa.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(VadovasPerziureti.class.getResource("VadovasPerziureti.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                System.out.println("");
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
        redaguoti.setOnAction(event -> {
            redaguoti.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(VadovasRedaguoti.class.getResource("VadovasRedaguoti.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                System.out.println("");
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
        paslaugos.setOnAction(event -> {
                    paslaugos.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(VadovasSarasas.class.getResource("VadovasSarasas.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        System.out.println("");
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                });
        atgal.setOnAction(event -> {
            atgal.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PagrindinisLangas.class.getResource("Langas.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                System.out.println("");
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
        save.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    LD3.irasytiBiudzeta();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
