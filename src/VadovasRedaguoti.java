
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VadovasRedaguoti {

    private ObservableList<AsmuoAtsakingasUzSistema> usersData = FXCollections.observableArrayList();

    @FXML
    private TableView<AsmuoAtsakingasUzSistema> table;

    @FXML
    private TableColumn<AsmuoAtsakingasUzSistema, String> pav;

    @FXML
    private TableColumn<AsmuoAtsakingasUzSistema, Integer> kod;

    @FXML
    private TableColumn<AsmuoAtsakingasUzSistema, String> pass;

    @FXML
    private Button atgal;

    @FXML
    private TextField kodasEsantis;

    @FXML
    private TextField Vardas;

    @FXML
    private TextField Kodas;

    @FXML
    private TextField Slaptazodis;

    @FXML
    private Button Keisti_pavadinima;

    @FXML
    private Button KeistiK;

    @FXML
    private MenuBar save;

    @FXML
    private Button Keisti_pass;

    ImonesBudzietas imone = LD3.getImone();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        DatebaseHandler dbHandler = new DatebaseHandler();
        ResultSet result = dbHandler.VartotojoPerziuretiSarasa();
        while(result.next()){
            pav.setCellValueFactory(new PropertyValueFactory<AsmuoAtsakingasUzSistema, String>("Name"));
            kod.setCellValueFactory(new PropertyValueFactory<AsmuoAtsakingasUzSistema, Integer>("Kodas"));
            pass.setCellValueFactory(new PropertyValueFactory<AsmuoAtsakingasUzSistema, String>("Password"));
            usersData.add(new AsmuoAtsakingasUzSistema(result.getString(1), result.getInt(2), result.getString(3)));
        }
        table.setItems(usersData);
        Keisti_pavadinima.setOnAction(event -> {
            if(kodasEsantis.getText().trim().length() == 0 || Vardas.getText().trim().length() == 0)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Nurodykite vartotojo koda ir varda kuri norite pakeisti");
                alert.showAndWait();
            }
            else {
                int kodass = Integer.parseInt(kodasEsantis.getText().trim());
                String pavadinimas = Vardas.getText().trim();
                try {
                    dbHandler.UpdateNameVartotojas(pavadinimas, kodass);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Keisti_pavadinima.getScene().getWindow().hide();
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
            }
        });
        KeistiK.setOnAction(event -> {
            if(kodasEsantis.getText().trim().length() == 0 || Kodas.getText().trim().length() == 0)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Nurodykite vartotojo koda ir koda kuri norite pakeisti");
                alert.showAndWait();
            }
            else {
                int kodass = Integer.parseInt(kodasEsantis.getText().trim());
                int kodasss = Integer.parseInt(Kodas.getText().trim());
                try {
                    dbHandler.UpdateKodasVartotojas(kodasss, kodass);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                KeistiK.getScene().getWindow().hide();
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
            }
        });
        Keisti_pass.setOnAction(event -> {
            if(kodasEsantis.getText().trim().length() == 0 || pass.getText().trim().length() == 0)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Nurodykite vartotojo koda ir slaptazodi kuri norite pakeisti");
                alert.showAndWait();
            }
            else {
                int kodass = Integer.parseInt(kodasEsantis.getText().trim());
                String passs = Slaptazodis.getText().trim();
                try {
                    dbHandler.UpdatePassVartotojas(passs, kodass);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Keisti_pass.getScene().getWindow().hide();
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
