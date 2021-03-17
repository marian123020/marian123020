

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


public class DarbuotojoLangoKontroleris1 {
    @FXML
    private Button pridet;

    @FXML
    private Button pasalinti;

    @FXML
    private Button exit2;

    @FXML
    private Button perziureti_sarasa;

    @FXML
    private MenuBar save;

    @FXML
    private Button perziureti;

    @FXML
    private Button redaguoti;

    @FXML
    void initialize() {
        pridet.setOnAction(event -> {
            pridet.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DarbuotojoLangasPridet.class.getResource("DarbuotoLangasPrideti.fxml"));
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
        pasalinti.setOnAction(event -> {
            pasalinti.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DarbuotojoLangasPasalinti1.class.getResource("DarbuotojoLangasPasalinti.fxml"));
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
            loader.setLocation(DarbuotojoLangasVisasSarasas.class.getResource("DarbuotojoLangasVisasSarasas.fxml"));
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
            loader.setLocation(DarbuotojoLangasRedaguotiPajamos.class.getResource("DarbuotojoLangasRedaguotiPajamos.fxml"));
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
        exit2.setOnAction(event -> {
            exit2.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DarbuotojoLangasRedaguotiPajamos.class.getResource("Langas.fxml"));
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
