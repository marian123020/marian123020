
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DarbuotojoLangasPridet {

    @FXML
    private CheckBox islaidos;

    @FXML
    private MenuBar save;

    @FXML
    private CheckBox pajamos;

    @FXML
    private TextField pav;

    @FXML
    private TextField kod;

    @FXML
    private TextField kainaa;

    @FXML
    private TextField subkat;

    @FXML
    private Button saugoti;

    @FXML
    private Button atgal;

    ImonesBudzietas imone = LD3.getImone();

    @FXML
    void initialize() {
        DatebaseHandler dbHandler = new DatebaseHandler();
        saugoti.setOnAction(event -> {
            if ((pav.getText().trim().length() == 0 || kainaa.getText().trim().length() == 0 || kod.getText().trim().length() == 0) && (islaidos.isSelected() || pajamos.isSelected())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Nurodykite visus duomenys");
                alert.showAndWait();
            } else {
                String pavadinimas = pav.getText().trim();
                int kodas = Integer.parseInt(kod.getText().trim());
                boolean KategorijaArYra = false;
                Kategorija kategorija = new Kategorija();
                if(islaidos.isSelected()) {
                    kategorija.setKategorija("islaidos");
                }
                else
                {
                    kategorija.setKategorija("pajamos");
                }
                kategorija.setPavadinimas(pavadinimas);
                kategorija.setKodas(kodas);
                ResultSet result = null;
                try {
                    result = dbHandler.ArYrKategorija(kategorija);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                int temp = 0;
                while(true) {
                    try {
                        if (!result.next()) break;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    temp++;
                }
                        if (temp>0) {
                            String a = "";
                            if(islaidos.isSelected()) {
                                a = "islaidos";
                            }
                            else
                            {
                                a = "pajamos";
                            }
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information");
                            alert.setHeaderText(null);
                            alert.setContentText("Tokia " + a + " su pavadinimu:" + pavadinimas + " ir kodu:" + kodas + " jau yra.");
                            alert.showAndWait();
                            KategorijaArYra = true;
                }
                if (KategorijaArYra == false) {
                    int kaina = Integer.parseInt(kainaa.getText().trim());
                    String sa = subkat.getText().trim();
                    String[] subklass = sa.split(";");
                    ArrayList<String> subklas = new ArrayList<String>();
                    for (String a : subklass) {
                        subklas.add(a);
                    }
                    String kategorijaa = null;
                    if (islaidos.isSelected()) {
                        kategorijaa = "islaidos";
                        imone.biudzetas(kaina);
                    } else if (pajamos.isSelected()) {
                        kategorijaa = "pajamos";
                        imone.biudzetas(kaina * -1);
                    }
                    Kategorija s = new Kategorija(kategorijaa, pavadinimas, kodas, kaina, subklas);
                    imone.setIslaidos_ir_pajamos(s);
                    try {
                        dbHandler.KategorijaPrideti(s);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Paslauga prideta");
                    alert.showAndWait();
                }
                saugoti.getScene().getWindow().hide();
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
        atgal.setOnAction(event->{
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
    }
}
