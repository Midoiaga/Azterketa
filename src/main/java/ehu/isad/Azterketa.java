package ehu.isad;

import ehu.isad.controllers.ui.AzterketaKud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class Azterketa extends Application {

    private Parent azterketaUI;

    private Stage stage;

    private AzterketaKud azterketaKud;
    private Scene sceneAz;


    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        pantailakKargatu();

        stage.setTitle("Azterketa");
        stage.setScene(sceneAz);
        stage.show();
    }

    private void pantailakKargatu() throws IOException {


        FXMLLoader loaderAzterketak = new FXMLLoader(getClass().getResource("/Azterketa.fxml"));
        azterketaUI = (Parent) loaderAzterketak.load();
        sceneAz = new Scene(azterketaUI);
        azterketaKud = loaderAzterketak.getController();

    }
}