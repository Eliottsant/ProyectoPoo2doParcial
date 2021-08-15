package com.mycompany.sistemaciudadela;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        try{
            FXMLLoader fxmlLoader
                    =new FXMLLoader(App.class.getResource("VistaPrincipal.fxml"));
            Parent root=fxmlLoader.load();
            scene = new Scene(root, 700, 400);
            stage.setScene(scene);
            stage.show();
                    
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    static void setRoot(Parent root) {
        scene.setRoot(root);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}