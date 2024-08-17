import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Bootstrap extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/dash_board_form.fxml"))));
        stage.setResizable(false);
        stage.setOnCloseRequest(windowEvent -> Platform.exit());
        stage.show();
    }
}
