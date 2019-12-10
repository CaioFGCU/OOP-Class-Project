package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


import java.io.IOException;

public class ErrorMessage {

    @FXML
    private TextArea Error_message_txtArea;
    private static Stage thisStage;

    @FXML
    public void setError(String e) {
        Error_message_txtArea.setText(e);
    }

    @FXML
    void error_popup_okbtn(ActionEvent event) {
        thisStage.close();


    }

    /**
     * creates pop-up whenever text field/area in program is left blank
     * displaying an error message to user
     *
     * @param error used to set error in loader
     */
    public static void popUpCreater(String error) {
        if (thisStage != null) {
            thisStage.close();
        }
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("errorGUI.fxml"));
            Parent root = loader.load();
            ((ErrorMessage) loader.getController()).setError(error);
            stage.setScene(new Scene(root));
            thisStage = stage;
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

