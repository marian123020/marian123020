
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

public class DarbuotojoLangasPasalinti1 {

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
    private Button saugoti;

    @FXML
    private Button atgal;

    ImonesBudzietas imone = LD3.getImone();

    @FXML
    void initialize(){
        saugoti.setOnAction(event -> {
            int a = 0;
            if ((pav.getText().trim().length() == 0 || kod.getText().trim().length() == 0) && (islaidos.isSelected() || pajamos.isSelected())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Nurodykite visus duomenys");
                alert.showAndWait();
                saugoti.getScene().getWindow().hide();
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
            } else {
                String pavadinimas = pav.getText().trim();
                int kodas = Integer.parseInt(kod.getText().trim());
                int kain = 0;
                String kategorijaa = null;
                if (islaidos.isSelected()) {
                    kategorijaa = "islaidos";
                    //imone.biudzetas(kain * -1);
                } else if (pajamos.isSelected()) {
                    kategorijaa = "pajamos";
                    //imone.biudzetas(kain);
                }
                Kategorija kateg = new Kategorija();
                DatebaseHandler dbHandler = new DatebaseHandler();
                kateg.setKategorija(kategorijaa);
                kateg.setPavadinimas(pavadinimas);
                kateg.setKodas(kodas);
                ResultSet result = null;
                try {
                    result = dbHandler.ArYrKategorija(kateg);
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
                    try {
                        kain = result.getInt(4);
                        temp++;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                    if (temp>0) {
                        if (islaidos.isSelected()) {
                            imone.biudzetas(kain * -1);
                        } else if (pajamos.isSelected()) {
                            imone.biudzetas(kain);
                        }
                        try {
                            dbHandler.KategorijaSalinimas(kateg);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        //imone.getIslaidos_ir_pajamos().remove(kateg);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("Paslauga pasalinta");
                        alert.showAndWait();
                        a=1;
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
                }
                if(a==0)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Nera tokios paslaugos");
                    alert.showAndWait();
                    saugoti.getScene().getWindow().hide();
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
