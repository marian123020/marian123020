

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
import java.util.ArrayList;

public class VadovasSarasas {

    private ObservableList<Kategorija> usersData = FXCollections.observableArrayList();

    @FXML
    private TableView<Kategorija> table;

    @FXML
    private MenuBar save;

    @FXML
    private TableColumn<Kategorija, String> kategor;

    @FXML
    private TableColumn<Kategorija, String> pav;

    @FXML
    private TableColumn<Kategorija, Integer> kod;

    @FXML
    private TableColumn<Kategorija, Integer> kai;

    @FXML
    private TableColumn<Kategorija, ArrayList<String>> subk;

    @FXML
    private Button atgal;

    ImonesBudzietas imone = LD3.getImone();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        DatebaseHandler dbHandler = new DatebaseHandler();
        ResultSet result = dbHandler.KategorijaVisasSarasas();
        while(result.next()){
            String kategori = result.getString(1);
            String name = result.getString(2);
            int kodas = result.getInt(3);
            int kaina = result.getInt(4);
            String[] subklas = result.getString(5).split(";");
            ArrayList<String> subkla = new ArrayList<>();
            for(String a : subklas)
            {
                subkla.add(a);
            }
            kategor.setCellValueFactory(new PropertyValueFactory<Kategorija, String>("Kategorija"));
            pav.setCellValueFactory(new PropertyValueFactory<Kategorija, String>("Pavadinimas"));
            kod.setCellValueFactory(new PropertyValueFactory<Kategorija, Integer>("Kodas"));
            kai.setCellValueFactory(new PropertyValueFactory<Kategorija, Integer>("Kaina"));
            subk.setCellValueFactory(new PropertyValueFactory<Kategorija, ArrayList<String>>("Subkategorija"));
            usersData.add(new Kategorija(kategori, name, kodas, kaina, subkla));
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
