package trabalhofinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TrabalhoFinal extends Application {
    
    private static BorderPane root = new BorderPane();
    
    public static BorderPane getRoot(){
        return root;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        MenuBar menuBar = FXMLLoader.load(getClass().getResource("/view/FXMLMenuBarPrincipal.fxml"));
        root.setTop(menuBar);
        
        Scene scene = new Scene(root,640,480);
        stage.setScene(scene);
        stage.setTitle("Sistema Gerenciador");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
