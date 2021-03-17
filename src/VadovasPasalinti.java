

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VadovasPasalinti {


    @FXML
    private TextField pav;

    @FXML
    private TextField kod;

    @FXML
    private Button saugoti;

    @FXML
    private Button atgal;

    @FXML
    private MenuBar save;

    ImonesBudzietas imone = LD3.getImone();


    @FXML
    void initialize(){
        saugoti.setOnAction(event -> {
            int a = 0;
            if(pav.getText().trim().length() == 0 || kod.getText().trim().length() == 0)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Nurodykite varda ir koda");
                alert.showAndWait();
            }
            else{
                String vardas = pav.getText().trim();
                int kodas = Integer.parseInt(kod.getText().trim());
                AsmuoAtsakingasUzSistema kateg = new AsmuoAtsakingasUzSistema();
                ResultSet result = null;
                AsmuoAtsakingasUzSistema b = new AsmuoAtsakingasUzSistema(vardas, kodas);

                DatebaseHandler dbHandler = new DatebaseHandler();


                try {
                    result = dbHandler.ArYrVartotojas(b);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                int temp = 0;
                while(true)
                {
                    try {
                        if (!result.next()) break;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    temp++;
                }
                if (temp>0) {
                    try {
                        dbHandler.VartotojoSalinimas(new AsmuoAtsakingasUzSistema(vardas, kodas));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Vartotojas pasalintas");
                    alert.showAndWait();
                    a = 1;
                }
                if(a == 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Nera tokio vartotojo");
                    alert.showAndWait();
                }
                saugoti.getScene().getWindow().hide();
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
            }
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
        atgal.setOnAction(event -> {
            atgal.getScene().getWindow().hide();
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
    }
}
