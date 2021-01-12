package ehu.isad.controllers.ui;

import ehu.isad.Utils;
import ehu.isad.controllers.db.AzterketaDBKud;
import ehu.isad.models.URLModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.ResourceBundle;

public class AzterketaKud implements Initializable {

    @FXML
    private TableView<URLModel> tbDena;

    @FXML
    private TableColumn<URLModel, String> clURL;

    @FXML
    private TableColumn<URLModel, String> clMd5;

    @FXML
    private TableColumn<URLModel, String> clVersion;

    @FXML
    private Button btnCheck;

    @FXML
    private TextField textId;

    @FXML
    private Label lblDago;

    private ObservableList<URLModel> urlModels = FXCollections.observableArrayList();

    @FXML
    void onCheck(ActionEvent event) {
        String pUrl= textId.getText()+"/README";
        String md5=Utils.checkHashURL(pUrl);
        URLModel model=AzterketaDBKud.getInstantzia().dago(md5);
        if(model.getMd5()==""){
            model.setUrl(pUrl);
            model.setMd5(md5);
            urlModels.add(model);
            tbDena.setItems(urlModels);
            lblDago.setText("Ez da datubasean aurkitu");
        }
        else{
            model.setUrl(textId.getText());
            urlModels.add(model);
            tbDena.setItems(urlModels);
            lblDago.setText("Datubasean zegoen");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tbDena.setEditable(true);

        clURL.setCellValueFactory(new PropertyValueFactory<>("Url"));
        clMd5.setCellValueFactory(new PropertyValueFactory<>("Md5"));
        clVersion.setCellValueFactory(new PropertyValueFactory<>("version"));
        clVersion.setCellFactory(TextFieldTableCell.<URLModel> forTableColumn());

        clVersion.setOnEditCommit(
                t -> {
                    URLModel i = t.getTableView().getItems().get(t.getTablePosition().getRow());
                    i.setVersion(t.getNewValue());
                    AzterketaDBKud.getInstantzia().md5Sartu(i.getMd5(),t.getNewValue());
                    lblDago.setText("md5 eta bertsio berria datubasean sartu dira");

                });

    }
}