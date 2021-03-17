
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

public class PagrindinisLangas {

    @FXML
    private Button Darb;

    @FXML
    private Button Vad;

    @FXML
    private Button exit;

    @FXML
    private MenuBar save;

    public Stage stage;

    @FXML
    void initialize() {
        ImonesBudzietas imone = new ImonesBudzietas("IIIIMONE", 468975236, 852245865);
        Darb.setOnAction(event -> {
            //Darb.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DarbuotojoLangoKontroleris1.class.getResource("DarbuotojasLangas1.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            stage.setScene(new Scene(root));
            stage.show();
        });
        Vad.setOnAction(event ->{
                Darb.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(VadovoLangas.class.getResource("VadovoLangas.fxml"));
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
        exit.setOnAction(event ->{
            try {
                LD3.irasytiBiudzeta();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
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
