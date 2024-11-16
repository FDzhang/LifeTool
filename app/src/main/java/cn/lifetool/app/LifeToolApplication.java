package cn.lifetool.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LifeToolApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LifeToolApplication.class.getResource("life_tool_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Context.STAGE_MAP.put(fxmlLoader.getController(), stage);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}