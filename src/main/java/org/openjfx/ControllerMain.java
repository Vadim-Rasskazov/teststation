package org.openjfx;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    private Button buttonTestsOn, buttonTestsOff;

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
    Test test = new Test();

    String from = "Тверь";
    String to = "Новосокольники";

    @FXML
    public void initialize() {
        Stage dataStage = new Stage();
        ExperimentalTest exTest = new ExperimentalTest();

        MonitorSize size = new MonitorSize();
        windowMain.setPrefSize((double) size.width / 5, size.height);
        imageLogo.setLayoutX((double) size.width / 10 -100);
        imageLogo.setLayoutY((double) size.height / 2 - 200);

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

        buttonTestsOn.setOnAction(event -> setVisibility(true, false));
        buttonTestsOff.setOnAction(event -> setVisibility(false, false));
        buttonTestSettingsOn.setOnAction(event -> setSettingsVisibility(true));
        buttonTestSettingsOff.setOnAction(event -> setSettingsVisibility(false));

        buttonTestSettingsApply.setOnAction(event -> {
            from = fieldTestSettingsFrom.getText();
            to = fieldTestSettingsTo.getText();
            setSettingsVisibility(false);
        });

        buttonTestMBook.setOnAction(event -> testStart("Mobile booking", checkTestMBook, test::bookingMobile));
        buttonTestMBuy.setOnAction(event -> testStart("Mobile buying", checkTestMBuy, test::buyingMobile));
        buttonTestDBook.setOnAction(event -> testStart("Desktop booking", checkTestDBook, test::bookingDesktop));
        buttonTestDBuy.setOnAction(event -> testStart("Desktop buying", checkTestDBuy, test::buyingDesktop));

        buttonTestCreateCom.setOnAction(event -> testStart("Create company", checkTestCreateCom, test::createCompany));
        buttonTestCreateRoute.setOnAction(event -> testStart("Create route", checkTestCreateRoute, test::createRoute));
        buttonTestCreateRoute.setOnAction(event -> testStart("Sales activation", checkTestSelect, test::salesActivation));
        buttonTestCreateRoute.setOnAction(event -> testStart("Delete company", checkTestDeleteCom, test::deleteCompany));
        buttonTestCreateRoute.setOnAction(event -> testStart("Creation by admin", checkTestCreateAll, test::createAll));

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

    private void setVisibility(boolean testsVisible, boolean settingsVisible) {
        buttonTestsOn.setVisible(!testsVisible);
        buttonTestsOff.setVisible(testsVisible);

        List<Node> testButtons = Arrays.asList(
                buttonTestMBook, buttonTestMBuy, buttonTestDBook, buttonTestDBuy,
                buttonTestCreateCom, buttonTestCreateRoute, buttonTestSelect,
                buttonTestDeleteCom, buttonTestCreateAll, buttonTestExperimental,
                checkTestMBook, checkTestMBuy, checkTestDBook, checkTestDBuy,
                checkTestCreateCom, checkTestCreateRoute, checkTestSelect,
                checkTestDeleteCom, checkTestCreateAll, checkTestExperimental,
                buttonTestSettingsOn
        );

        testButtons.forEach(button -> button.setVisible(testsVisible));
        imageLogo.setVisible(!testsVisible);

        setSettingsVisibility(settingsVisible);
    }

    private void setSettingsVisibility(boolean visible) {
        buttonTestSettingsOn.setVisible(!visible);
        buttonTestSettingsOff.setVisible(visible);
        buttonTestSettingsApply.setVisible(visible);
        windowTestSettings.setVisible(visible);
        fieldTestSettingsFrom.setVisible(visible);
        fieldTestSettingsTo.setVisible(visible);
    }

    interface CheckedBiConsumer<T, U> {
        void accept(T t, U u) throws Exception;
    }

    private void testStart(String testName, Circle checkButton, CheckedBiConsumer<String, String> test) {
        try {
            test.accept(from, to);
            checkButton.setFill(Paint.valueOf("#00ff00cc"));
            insertResult.insertToDb(date, testName, "successful", "");
        } catch (Exception resultException) {
            unsuccessfulResult(checkButton, testName, resultException);
        }
    }

    interface Runnable {
        void run() throws Exception;
    }

    private void testStart(String testName, Circle checkButton, Runnable test) {
        try {
            test.run(); // Вызываем метод без аргументов
            checkButton.setFill(Paint.valueOf("#00ff00cc"));
            insertResult.insertToDb(date, testName, "successful", "");
        } catch (Exception resultException) {
            unsuccessfulResult(checkButton, testName, resultException);
        }
    }

    private void unsuccessfulResult(Circle checkButton,String testName, Exception resultException) {
        String exception = resultException.toString();
        System.out.println(exception);
        checkButton.setFill(Paint.valueOf("#ff0000cc"));
        insertResult.insertToDb(date, testName, "unsuccessful", exception.length() > 1000 ? exception.substring(0, 1000) : exception);
    }
}