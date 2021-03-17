

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

public class VadovasPridet {

    @FXML
    private TextField pav;

    @FXML
    private TextField pass;

    @FXML
    private TextField kod;

    @FXML
    private MenuBar save;

    @FXML
    private Button saugoti;

    @FXML
    private Button atgal;

    ImonesBudzietas imone = LD3.getImone();

    @FXML
    void initialize()
    {
        saugoti.setOnAction(event -> {
            String vardas = null;
            int kodas = 0;
            String passs;
            if(pav.getText().trim().length() == 0 || pass.getText().trim().length() == 0 || kod.getText().trim().length() == 0)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Nurodykite visus duomenys");
                alert.showAndWait();
            }
            else {
                boolean VartotojasArYra = false;
                ResultSet result = null;
                AsmuoAtsakingasUzSistema a = new AsmuoAtsakingasUzSistema(vardas, kodas);

                DatebaseHandler dbHandler = new DatebaseHandler();

                try {
                    result = dbHandler.ArYrVartotojas(a);
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
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("Tokia vartotojo su vardu:" + vardas + " ir kodu:" + kodas + " jau yra.");
                        alert.showAndWait();
                        VartotojasArYra = true;


                }
                if (!VartotojasArYra) { {
                        kodas = Integer.parseInt(kod.getText().trim());
                        vardas = pav.getText().trim();
                        passs = pass.getText().trim();
                        AsmuoAtsakingasUzSistema s = new AsmuoAtsakingasUzSistema(vardas, kodas, passs);
                        //imone.addVartotojas(s);
                    try {
                        dbHandler.VartotojaPrideti(s);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("Vartotojas pridetas");
                        alert.showAndWait();
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
                }
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

