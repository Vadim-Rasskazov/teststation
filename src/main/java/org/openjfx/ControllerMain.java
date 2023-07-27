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
    private Circle checkTestCreateAllG;

    @FXML
    private Circle checkTestCreateAllR;

    @FXML
    private Circle checkTestCreateCom;

    @FXML
    private Circle checkTestCreateComG;

    @FXML
    private Circle checkTestCreateComR;

    @FXML
    private Circle checkTestCreateRoute;

    @FXML
    private Circle checkTestCreateRouteG;

    @FXML
    private Circle checkTestCreateRouteR;

    @FXML
    private Circle checkTestDBook;

    @FXML
    private Circle checkTestDBookG;

    @FXML
    private Circle checkTestDBookR;

    @FXML
    private Circle checkTestDBuy;

    @FXML
    private Circle checkTestDBuyG;

    @FXML
    private Circle checkTestDBuyR;

    @FXML
    private Circle checkTestDeleteCom;

    @FXML
    private Circle checkTestDeleteComG;

    @FXML
    private Circle checkTestDeleteComR;

    @FXML
    private Circle checkTestExperimental;

    @FXML
    private Circle checkTestExperimentalG;

    @FXML
    private Circle checkTestExperimentalR;

    @FXML
    private Circle checkTestMBook;

    @FXML
    private Circle checkTestMBookG;

    @FXML
    private Circle checkTestMBookR;

    @FXML
    private Circle checkTestMBuy;

    @FXML
    private Circle checkTestMBuyG;

    @FXML
    private Circle checkTestMBuyR;

    @FXML
    private Circle checkTestSelect;

    @FXML
    private Circle checkTestSelectG;

    @FXML
    private Circle checkTestSelectR;

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
            try {
                test.bookingMobile(from, to);
                checkTestMBookG.setVisible(true);
                checkTestMBook.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                checkTestMBookR.setVisible(true);
                checkTestMBook.setVisible(false);
            }
        });

        buttonTestMBuy.setOnAction(event -> {
            try {
                test.buyingMobile(from, to);
                checkTestMBuyG.setVisible(true);
                checkTestMBuy.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                checkTestMBuyR.setVisible(true);
                checkTestMBuy.setVisible(false);
            }
        });

        buttonTestDBook.setOnAction(event -> {
            try {
                test.bookingDesktop(from, to);
                checkTestDBookG.setVisible(true);
                checkTestDBook.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                checkTestDBookR.setVisible(true);
                checkTestDBook.setVisible(false);
            }
        });

        buttonTestDBuy.setOnAction(event -> {
            try {
                test.buyingDesktop(from, to);
                checkTestDBuyG.setVisible(true);
                checkTestDBuy.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                checkTestDBuyR.setVisible(true);
                checkTestDBuy.setVisible(false);
            }
        });

        buttonTestCreateCom.setOnAction(event -> {
            try {
                test.createCompany();
                checkTestCreateComG.setVisible(true);
                checkTestCreateCom.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                checkTestCreateComR.setVisible(true);
                checkTestCreateCom.setVisible(false);
            }
        });

        buttonTestCreateRoute.setOnAction(event -> {
            try {
                test.createRoute();
                checkTestCreateRouteG.setVisible(true);
                checkTestCreateRoute.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                checkTestCreateRouteR.setVisible(true);
                checkTestCreateRoute.setVisible(false);
            }
        });

        buttonTestSelect.setOnAction(event -> {
            try {
                test.salesActivation();
                checkTestSelectG.setVisible(true);
                checkTestSelect.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                checkTestSelectR.setVisible(true);
                checkTestSelect.setVisible(false);
            }
        });

        buttonTestDeleteCom.setOnAction(event -> {
            try {
                test.deleteCompany();
                checkTestDeleteComG.setVisible(true);
                checkTestDeleteCom.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                checkTestDeleteComR.setVisible(true);
                checkTestDeleteCom.setVisible(false);
            }
        });

        buttonTestCreateAll.setOnAction(event -> {
            try {
                test.createAll();
                checkTestCreateAllG.setVisible(true);
                checkTestCreateAll.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                checkTestCreateAllR.setVisible(true);
                checkTestCreateAll.setVisible(false);
            }
        });

        buttonTestExperimental.setOnAction(event -> {
            try {
                exTest.experimentBot();
                checkTestExperimentalG.setVisible(true);
                checkTestExperimental.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                checkTestExperimentalR.setVisible(true);
                checkTestExperimental.setVisible(false);
            }
        });

        buttonExit.setOnAction(event -> System.exit(0));
    }
}