
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
import java.util.ArrayList;

public class DarbuotojoLangasRedaguotiPajamos {

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

    @FXML
    private TextField kodasEsantis;

    @FXML
    private TextField Kodass;

    @FXML
    private TextField pavadinimass;

    @FXML
    private Button Keisti_pavadinima;

    @FXML
    private Button Keisti_kaina;

    @FXML
    private Button prideti;

    @FXML
    private Button pasalinti;

    @FXML
    private Button KeistiK;

    @FXML
    private TextField kainaaa;

    @FXML
    private TextField Sub;

    @FXML
    private CheckBox islaidos;

    @FXML
    private CheckBox pajamos;

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
        Keisti_pavadinima.setOnAction(event -> {
            if((kodasEsantis.getText().trim().length() == 0 || pavadinimass.getText().trim().length() == 0) && (islaidos.isSelected() || pajamos.isSelected()))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Nurodykite paslaugos koda, pavadinima ir kategorija kuri norite pakeisti");
                alert.showAndWait();
            }
            else {
                String kategorijaa = null;
                if (islaidos.isSelected()) {
                    kategorijaa = "islaidos";
                } else if (pajamos.isSelected()) {
                    kategorijaa = "pajamos";
                }
                int kodass = Integer.parseInt(kodasEsantis.getText().trim());
                String pavadinimas = pavadinimass.getText().trim();
                try {
                    dbHandler.UpdateNameKategorija(kategorijaa, pavadinimas, kodass);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Pavadinimas pakeistas");
                alert.showAndWait();
                Keisti_pavadinima.getScene().getWindow().hide();
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
            }
        });
        KeistiK.setOnAction(event -> {
            if((kodasEsantis.getText().trim().length() == 0 || Kodass.getText().trim().length() == 0) && (islaidos.isSelected() || pajamos.isSelected()))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Nurodykite paslaugos koda, koda ir kategorija kuri norite pakeisti");
                alert.showAndWait();
            }
            else {
                String kategorijaa = null;
                if (islaidos.isSelected()) {
                    kategorijaa = "islaidos";
                } else if (pajamos.isSelected()) {
                    kategorijaa = "pajamos";
                }
                int kodass = Integer.parseInt(kodasEsantis.getText().trim());
                int kodasss = Integer.parseInt(Kodass.getText().trim());
                try {
                    dbHandler.UpdateKodasKategorija(kategorijaa, kodass, kodasss);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Kodas pakeistas");
                alert.showAndWait();
                KeistiK.getScene().getWindow().hide();
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
            }
        });
        Keisti_kaina.setOnAction(event -> {
            if((kodasEsantis.getText().trim().length() == 0 || kainaaa.getText().trim().length() == 0) && (islaidos.isSelected() || pajamos.isSelected()))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Nurodykite paslaugos koda, kaina ir kategorija kuri norite pakeisti");
                alert.showAndWait();
            }
            else {
                int kain = 0;
                String kategorijaa = null;
                if (islaidos.isSelected()) {
                    kategorijaa = "islaidos";
                } else if (pajamos.isSelected()) {
                    kategorijaa = "pajamos";
                }
                int kodass = Integer.parseInt(kodasEsantis.getText().trim());
                int kaina = Integer.parseInt(kainaaa.getText().trim());
                ResultSet resultt = null;
                try { 
                    resultt = dbHandler.KategorijaKaina(kategorijaa, kodass);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                while(true)
                {
                    try {
                        if (!resultt.next()) break;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        kain = resultt.getInt(1);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                try {
                    dbHandler.UpdateKainaKategorija(kategorijaa,kaina,kodass);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (islaidos.isSelected()) {
                    imone.biudzetas(kain * -1);
                    imone.biudzetas(kaina);
                } else if (pajamos.isSelected()) {
                    imone.biudzetas(kain);
                    imone.biudzetas(kaina * -1);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Kaina pakeista");
                alert.showAndWait();
                Keisti_kaina.getScene().getWindow().hide();
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
            }
        });
        prideti.setOnAction(event -> {
            if((kodasEsantis.getText().trim().length() == 0 || Sub.getText().trim().length() == 0) && (islaidos.isSelected() || pajamos.isSelected()))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Nurodykite paslaugos koda, subkategorija ir kategorija kuri norite pakeisti");
                alert.showAndWait();
            }
            else {
                int kodass = Integer.parseInt(kodasEsantis.getText().trim());
                String Subklas = Sub.getText().trim();
                String kategorijaa = null;
                if (islaidos.isSelected()) {
                    kategorijaa = "islaidos";
                } else if (pajamos.isSelected()) {
                    kategorijaa = "pajamos";
                }
                ResultSet resultt = null;
                String subkategorija = null;
                try {
                    resultt = dbHandler.KategorijaSub(kategorijaa, kodass);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                while(true)
                {
                    try {
                        if (!resultt.next()) break;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        subkategorija = resultt.getString(1);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                subkategorija += Subklas;
                subkategorija += ";";
                try {
                    dbHandler.UpdateSubkategorija(kategorijaa, subkategorija, kodass);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Subkategorija prideta");
                alert.showAndWait();
                prideti.getScene().getWindow().hide();
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
            }
        });
        pasalinti.setOnAction(event -> {
            int kodass = Integer.parseInt(kodasEsantis.getText().trim());
            String Subklas = Sub.getText().trim();
            String kategorijaa = null;
            if (islaidos.isSelected()) {
                kategorijaa = "islaidos";
            } else if (pajamos.isSelected()) {
                kategorijaa = "pajamos";
            }
            ResultSet resultt = null;
            String[] subkategorija = null;
            try {
                resultt = dbHandler.KategorijaSub(kategorijaa, kodass);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            while(true)
            {
                try {
                    if (!resultt.next()) break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    subkategorija = resultt.getString(1).split(";");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            ArrayList<String> d = new ArrayList<>();
            for(String a : subkategorija)
            {
                d.add(a);
            }
            d.remove(Subklas);
            String s = "";
            for(String a : d)
            {
                s += a;
                s += ";";
            }
            try {
                dbHandler.UpdateSubkategorija(kategorijaa, s, kodass);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Subkategorija pasalinta");
            alert.showAndWait();
            prideti.getScene().getWindow().hide();
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
        atgal.setOnAction(event -> {
            atgal.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DarbuotojoLangoKontroleris1.class.getResource("DarbuotojasLangas1.fxml"));
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
