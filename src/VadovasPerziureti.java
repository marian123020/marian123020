
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VadovasPerziureti {

    private ObservableList<AsmuoAtsakingasUzSistema> usersData = FXCollections.observableArrayList();

    @FXML
    private MenuBar save;

    @FXML
    private TableColumn<AsmuoAtsakingasUzSistema, String> Vardas;

    @FXML
    private TableColumn<AsmuoAtsakingasUzSistema, Integer> kod;

    @FXML
    private TableColumn<AsmuoAtsakingasUzSistema, String> pass;

    @FXML
    private Button atgal;

    @FXML
    private TableView<AsmuoAtsakingasUzSistema> table = new TableView<>();

    ImonesBudzietas imone = LD3.getImone();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        DatebaseHandler dbHandler = new DatebaseHandler();
        ResultSet result = dbHandler.VartotojoPerziuretiSarasa();
        while(result.next()){
            Vardas.setCellValueFactory(new PropertyValueFactory<AsmuoAtsakingasUzSistema, String>("Name"));
            kod.setCellValueFactory(new PropertyValueFactory<AsmuoAtsakingasUzSistema, Integer>("Kodas"));
            pass.setCellValueFactory(new PropertyValueFactory<AsmuoAtsakingasUzSistema, String>("Password"));
            usersData.add(new AsmuoAtsakingasUzSistema(result.getString(1), result.getInt(2), result.getString(3)));
        }
        table.setItems(usersData);
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
