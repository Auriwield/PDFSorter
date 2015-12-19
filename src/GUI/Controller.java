package GUI;

import SortMachine.Sorter;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import java.io.File;

public class Controller extends Sorter{

    @FXML
    private Button button;
    @FXML
    private Button btn;
    @FXML
    private TextField txf;
    @FXML private AnchorPane AnchorPane;

    @FXML
    public void initialize(){
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    sort(txf.getText());
                }catch (Exception e){
                    txf.setText("Для сортировки нужно выбрать папку");
                }

            }
        });
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                DirectoryChooser dirOpen = new DirectoryChooser();
                dirOpen.setTitle("Choose directory");
                File defaultDirectory = new File("C:\\");
                dirOpen.setInitialDirectory(defaultDirectory);
                File selectedDirectory = dirOpen.showDialog(AnchorPane.getScene().getWindow());
                String a = selectedDirectory.getPath();
                txf.setText(a);
            }
        });
    }

}