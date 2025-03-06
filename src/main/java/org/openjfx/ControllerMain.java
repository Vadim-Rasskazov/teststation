package org.openjfx;

import java.util.Objects;
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
    private Button buttonDataOff, buttonDataOn, buttonExit;

    @FXML
    private Button buttonTestCreateAll, buttonTestCreateCom, buttonTestCreateRoute, buttonTestDeleteCom, buttonTestSelect;

    @FXML
    private Button buttonTestDBook, buttonTestDBuy, buttonTestMBook, buttonTestMBuy, buttonTestExperimental;

    @FXML
    private Button buttonTestSettingsApply, buttonTestSettingsOff, buttonTestSettingsOn;

    @FXML
    private Button buttonTestsOff, buttonTestsOn;

    @FXML
    private Circle checkTestCreateAll, checkTestCreateCom, checkTestCreateRoute, checkTestDeleteCom, checkTestSelect;

    @FXML
    private Circle checkTestDBook, checkTestDBuy, checkTestMBook, checkTestMBuy, checkTestExperimental;

    @FXML
    private TextField fieldTestSettingsFrom, fieldTestSettingsTo;

    @FXML
    private ImageView imageLogo;

    @FXML
    private AnchorPane windowMain;

    @FXML
    private Pane windowTestSettings;

    ResultToDb insertResult = new ResultToDb();
    String date = new CurrentDate().today;

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
            String name = "Mobile booking";
            Circle check = checkTestMBook;
            try {
                test.bookingMobile(from, to);
                successfulResult(check, name);
            } catch (Exception resultException) {
                unsuccessfulResult(check, name, resultException.toString());
            }
        });

        buttonTestMBuy.setOnAction(event -> {
            String name = "Mobile buying";
            Circle check = checkTestMBuy;
            try {
                test.buyingMobile(from, to);
                successfulResult(check, name);
            } catch (Exception resultException) {
                unsuccessfulResult(check, name, resultException.toString());
            }
        });

        buttonTestDBook.setOnAction(event -> {
            String name = "Desktop booking";
            Circle check = checkTestDBook;
            try {
                test.bookingDesktop(from, to);
                successfulResult(check, name);
            } catch (Exception resultException) {
                unsuccessfulResult(check, name, resultException.toString());
            }
        });

        buttonTestDBuy.setOnAction(event -> {
            String name = "Desktop buying";
            Circle check = checkTestDBuy;
            try {
                test.buyingDesktop(from, to);
                successfulResult(check, name);
            } catch (Exception resultException) {
                unsuccessfulResult(check, name, resultException.toString());
            }
        });

        buttonTestCreateCom.setOnAction(event -> {
            String name = "Create company";
            Circle check = checkTestCreateCom;
            try {
                test.createCompany();
                successfulResult(check, name);
            } catch (Exception resultException) {
                unsuccessfulResult(check, name, resultException.toString());
            }
        });

        buttonTestCreateRoute.setOnAction(event -> {
            String name = "Create route";
            Circle check = checkTestCreateRoute;
            try {
                test.createRoute();
                successfulResult(check, name);
            } catch (Exception resultException) {
                unsuccessfulResult(check, name, resultException.toString());
            }
        });

        buttonTestSelect.setOnAction(event -> {
            String name = "Sales activation";
            Circle check = checkTestSelect;
            try {
                test.salesActivation();
                successfulResult(check, name);
            } catch (Exception resultException) {
                unsuccessfulResult(check, name, resultException.toString());
            }
        });

        buttonTestDeleteCom.setOnAction(event -> {
            String name = "Delete company";
            Circle check = checkTestDeleteCom;
            try {
                test.deleteCompany();
                successfulResult(check, name);
            } catch (Exception resultException) {
                unsuccessfulResult(check, name, resultException.toString());
            }
        });

        buttonTestCreateAll.setOnAction(event -> {
            String name = "Creation by admin";
            Circle check = checkTestCreateAll;
            try {
                test.createAll();
                successfulResult(check, name);
            } catch (Exception resultException) {
                unsuccessfulResult(check, name, resultException.toString());
            }
        });

        buttonTestExperimental.setOnAction(event -> {
            try {
                exTest.experimentTest();
                checkTestExperimental.setFill(Paint.valueOf("#00ff00cc"));
            } catch (Exception resultException) {
                checkTestExperimental.setFill(Paint.valueOf("#ff0000cc"));
            }
        });

        buttonExit.setOnAction(event -> System.exit(0));
    }

    private void successfulResult(Circle check, String name) {
        check.setFill(Paint.valueOf("#00ff00cc"));
        String result = "successful";
        String log = "";
        insertResult.insertToDb(date, name, result, log);
    }

    private void unsuccessfulResult(Circle check,String name, String resultException) {
        System.out.println(resultException);
        check.setFill(Paint.valueOf("#ff0000cc"));
        String result = "unsuccessful";
        String log = resultException.length() > 1000 ? resultException.substring(0, 1000) : resultException;
        insertResult.insertToDb(date, name, result, log);
    }
}