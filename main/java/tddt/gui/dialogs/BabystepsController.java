package main.java.tddt.gui.dialogs;

import javafx.event.ActionEvent;
import javafx.stage.Stage;


public class BabystepsController {

    private Stage stage;

    public void apply(ActionEvent actionEvent) {
        stage.close();
    }

    public void cancel(ActionEvent actionEvent) {
        stage.close();
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
}
