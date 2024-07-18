package org.openjfx;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ControllerMain {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonDataOff;

    @FXML
    private Button buttonDataOn;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonTestCreateAll;

    @FXML
    private Button buttonTestCreateCom;

    @FXML
    private Button buttonTestCreateRoute;

    @FXML
    private Button buttonTestDBook;

    @FXML
    private Button buttonTestDBuy;

    @FXML
    private Button buttonTestDeleteCom;

    @FXML
    private Button buttonTestExperimental;

    @FXML
    private Button buttonTestMBook;

    @FXML
    private Button buttonTestMBuy;

    @FXML
    private Button buttonTestSelect;

    @FXML
    private Button buttonTestSettingsApply;

    @FXML
    private Button buttonTestSettingsOff;

    @FXML
    private Button buttonTestSettingsOn;

    @FXML
    private Button buttonTestsOff;

    @FXML
    private Button buttonTestsOn;

    @FXML
    private Circle checkTestCreateAll;

    @FXML
    private Circle checkTestCreateCom;

    @FXML
    private Circle checkTestCreateRoute;

    @FXML
    private Circle checkTestDBook;

    @FXML
    private Circle checkTestDBuy;

    @FXML
    private Circle checkTestDeleteCom;

    @FXML
    private Circle checkTestExperimental;

    @FXML
    private Circle checkTestMBook;

    @FXML
    private Circle checkTestMBuy;

    @FXML
    private Circle checkTestSelect;

    @FXML
    private TextField fieldTestSettingsFrom;

    @FXML
    private TextField fieldTestSettingsTo;

    @FXML
    private ImageView imageLogo;

    @FXML
    private AnchorPane windowMain;

    @FXML
    private Pane windowTestSettings;

    String from = "Тверь";
    String to = "Новосокольники";

    @FXML
    public void initialize() {
        Stage dataStage = new Stage();
        Test test = new Test();
        ExperimentalTest exTest = new ExperimentalTest();

        MonitorSize size = new MonitorSize();
        windowMain.setPrefSize((double) size.width / 5, size.height);
        imageLogo.setLayoutX((double) size.width / 10 -100);
        imageLogo.setLayoutY((double) size.height / 2 - 200);

        ResultToDb insertResult = new ResultToDb();
        String date = new CurrentDate().today;

        buttonTestsOn.setOnAction(event -> {
            buttonTestsOff.setVisible(true);
            buttonTestsOn.setVisible(false);
            buttonTestMBook.setVisible(true);
            buttonTestMBuy.setVisible(true);
            buttonTestDBook.setVisible(true);
            buttonTestDBuy.setVisible(true);
            buttonTestCreateCom.setVisible(true);
            buttonTestCreateRoute.setVisible(true);
            buttonTestSelect.setVisible(true);
            buttonTestDeleteCom.setVisible(true);
            buttonTestCreateAll.setVisible(true);
            buttonTestExperimental.setVisible(true);
            checkTestMBook.setVisible(true);
            checkTestMBuy.setVisible(true);
            checkTestDBook.setVisible(true);
            checkTestDBuy.setVisible(true);
            checkTestCreateCom.setVisible(true);
            checkTestCreateRoute.setVisible(true);
            checkTestSelect.setVisible(true);
            checkTestDeleteCom.setVisible(true);
            checkTestCreateAll.setVisible(true);
            checkTestExperimental.setVisible(true);
            buttonTestSettingsOn.setVisible(true);
            imageLogo.setVisible(false);
        });

        buttonDataOn.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("controllerdata.fxml")));
                dataStage.setTitle("Data");
                dataStage.setX((double) size.width / 5);
                dataStage.setY(0);
                dataStage.setScene(new Scene(root));
                dataStage.show();
                buttonDataOff.setVisible(true);
                buttonDataOn.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        });

        buttonDataOff.setOnAction(event -> {
            try {
                dataStage.close();
                buttonDataOn.setVisible(true);
                buttonDataOff.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        });

        buttonTestSettingsOn.setOnAction(event -> {
            buttonTestSettingsOff.setVisible(true);
            buttonTestSettingsOn.setVisible(false);
            buttonTestSettingsApply.setVisible(true);
            windowTestSettings.setVisible(true);
            fieldTestSettingsFrom.setVisible(true);
            fieldTestSettingsTo.setVisible(true);
        });

        buttonTestSettingsOff.setOnAction(event -> {
            buttonTestSettingsOn.setVisible(true);
            buttonTestSettingsOff.setVisible(false);
            buttonTestSettingsApply.setVisible(false);
            windowTestSettings.setVisible(false);
            fieldTestSettingsFrom.setVisible(false);
            fieldTestSettingsTo.setVisible(false);
        });

        buttonTestSettingsApply.setOnAction(event -> {
            from=fieldTestSettingsFrom.getText();
            to=fieldTestSettingsTo.getText();
            buttonTestSettingsOn.setVisible(true);
            buttonTestSettingsOff.setVisible(false);
            buttonTestSettingsApply.setVisible(false);
            windowTestSettings.setVisible(false);
            fieldTestSettingsFrom.setVisible(false);
            fieldTestSettingsTo.setVisible(false);
        });

        buttonTestMBook.setOnAction(event -> {
            String name = "Mobile booking";
            try {
                test.bookingMobile(from, to);
                checkTestMBook.setFill(Paint.valueOf("#00ff00cc"));
                String result = "successful";
                String log = "";
                insertResult.insertToDb(date, name, result, log);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                checkTestMBook.setFill(Paint.valueOf("#ff0000cc"));
                String result = "unsuccessful";
                String log = e.toString().length() > 1000 ? e.toString().substring(0, 1000) : e.toString();
                insertResult.insertToDb(date, name, result, log);
            }
        });

        buttonTestMBuy.setOnAction(event -> {
            String name = "Mobile buying";
            try {
                test.buyingMobile(from, to);
                checkTestMBuy.setFill(Paint.valueOf("#00ff00cc"));
                String result = "successful";
                String log = "";
                insertResult.insertToDb(date, name, result, log);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                checkTestMBuy.setFill(Paint.valueOf("#ff0000cc"));
                String result = "unsuccessful";
                String log = e.toString().length() > 1000 ? e.toString().substring(0, 1000) : e.toString();
                insertResult.insertToDb(date, name, result, log);
            }
        });

        buttonTestDBook.setOnAction(event -> {
            String name = "Desktop booking";
            try {
                test.bookingDesktop(from, to);
                checkTestDBook.setFill(Paint.valueOf("#00ff00cc"));
                String result = "successful";
                String log = "";
                insertResult.insertToDb(date, name, result, log);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                checkTestDBook.setFill(Paint.valueOf("#ff0000cc"));
                String result = "unsuccessful";
                String log = e.toString().length() > 1000 ? e.toString().substring(0, 1000) : e.toString();
                insertResult.insertToDb(date, name, result, log);
            }
        });

        buttonTestDBuy.setOnAction(event -> {
            String name = "Desktop buying";
            try {
                test.buyingDesktop(from, to);
                checkTestDBuy.setFill(Paint.valueOf("#00ff00cc"));
                String result = "successful";
                String log = "";
                insertResult.insertToDb(date, name, result, log);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                checkTestDBuy.setFill(Paint.valueOf("#ff0000cc"));
                String result = "unsuccessful";
                String log = e.toString().length() > 1000 ? e.toString().substring(0, 1000) : e.toString();
                insertResult.insertToDb(date, name, result, log);
            }
        });

        buttonTestCreateCom.setOnAction(event -> {
            String name = "Create company";
            try {
                test.createCompany();
                checkTestCreateCom.setFill(Paint.valueOf("#00ff00cc"));
                String result = "successful";
                String log = "";
                insertResult.insertToDb(date, name, result, log);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                checkTestCreateCom.setFill(Paint.valueOf("#ff0000cc"));
                String result = "unsuccessful";
                String log = e.toString().length() > 1000 ? e.toString().substring(0, 1000) : e.toString();
                insertResult.insertToDb(date, name, result, log);
            }
        });

        buttonTestCreateRoute.setOnAction(event -> {
            String name = "Create route";
            try {
                test.createRoute();
                checkTestCreateRoute.setFill(Paint.valueOf("#00ff00cc"));
                String result = "successful";
                String log = "";
                insertResult.insertToDb(date, name, result, log);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                checkTestCreateRoute.setFill(Paint.valueOf("#ff0000cc"));
                String result = "unsuccessful";
                String log = e.toString().length() > 1000 ? e.toString().substring(0, 1000) : e.toString();
                insertResult.insertToDb(date, name, result, log);
            }
        });

        buttonTestSelect.setOnAction(event -> {
            String name = "Sales activation";
            try {
                test.salesActivation();
                checkTestSelect.setFill(Paint.valueOf("#00ff00cc"));
                String result = "successful";
                String log = "";
                insertResult.insertToDb(date, name, result, log);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                checkTestSelect.setFill(Paint.valueOf("#ff0000cc"));
                String result = "unsuccessful";
                String log = e.toString().length() > 1000 ? e.toString().substring(0, 1000) : e.toString();
                insertResult.insertToDb(date, name, result, log);
            }
        });

        buttonTestDeleteCom.setOnAction(event -> {
            String name = "Delete company";
            try {
                test.deleteCompany();
                checkTestDeleteCom.setFill(Paint.valueOf("#00ff00cc"));
                String result = "successful";
                String log = "";
                insertResult.insertToDb(date, name, result, log);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                checkTestDeleteCom.setFill(Paint.valueOf("#ff0000cc"));
                String result = "unsuccessful";
                String log = e.toString().length() > 1000 ? e.toString().substring(0, 1000) : e.toString();
                insertResult.insertToDb(date, name, result, log);
            }
        });

        buttonTestCreateAll.setOnAction(event -> {
            String name = "Creation by admin";
            try {
                test.createAll();
                checkTestCreateAll.setFill(Paint.valueOf("#00ff00cc"));
                String result = "successful";
                String log = "";
                insertResult.insertToDb(date, name, result, log);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                checkTestCreateAll.setFill(Paint.valueOf("#ff0000cc"));
                String result = "unsuccessful";
                String log = e.toString().length() > 1000 ? e.toString().substring(0, 1000) : e.toString();
                insertResult.insertToDb(date, name, result, log);
            }
        });

        buttonTestExperimental.setOnAction(event -> {
            try {
                exTest.experimentTest();
                checkTestExperimental.setFill(Paint.valueOf("#00ff00cc"));
            } catch (Exception e) {
                e.printStackTrace(System.out);
                checkTestExperimental.setFill(Paint.valueOf("#ff0000cc"));
            }
        });

        buttonExit.setOnAction(event -> System.exit(0));
    }
}