package org.openjfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ControllerData {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonErrorsOff;

    @FXML
    private Button buttonErrorsOn;

    @FXML
    private Button buttonLogsOff;

    @FXML
    private Button buttonLogsOn;

    @FXML
    private Button buttonStatOff;

    @FXML
    private Button buttonStatOn;

    @FXML
    private Button buttonTestsOff;

    @FXML
    private Button buttonTestsOn;

    @FXML
    private TableView<?> tableErrors;

    @FXML
    private TableColumn<?, ?> tableErrorsDescription;

    @FXML
    private TableColumn<?, ?> tableErrorsLevel;

    @FXML
    private TableColumn<?, ?> tableErrorsName;

    @FXML
    private TableColumn<?, ?> tableErrorsNumber;

    @FXML
    private TableColumn<?, ?> tableErrorsVersion;

    @FXML
    private TableView<?> tableLogs;

    @FXML
    private TableColumn<?, ?> tableLogsLog;

    @FXML
    private TableColumn<?, ?> tableLogsNumber;

    @FXML
    private TableView<?> tableTests;

    @FXML
    private TableColumn<?, ?> tableTestsDescription;

    @FXML
    private TableColumn<?, ?> tableTestsName;

    @FXML
    private TableColumn<?, ?> tableTestsNumber;

    @FXML
    private TableColumn<?, ?> tableTestsVersion;

    @FXML
    private TableView<?> tableResults;

    @FXML
    private TableColumn<?, ?> tableResultsErrorNum;

    @FXML
    private TableColumn<?, ?> tableResultsName;

    @FXML
    private TableColumn<?, ?> tableResultsNumber;

    @FXML
    private TableColumn<?, ?> tableResultsResult;

    @FXML
    private TableColumn<?, ?> tableResultsTime;

    @FXML
    private TableColumn<?, ?> tableResultsUser;

    @FXML
    private TableColumn<?, ?> tableResultsVersion;

    @FXML
    private TableView<?> tableStat;

    @FXML
    private TableColumn<?, ?> tableStatCount;

    @FXML
    private TableColumn<?, ?> tableStatMostOften;

    @FXML
    private TableColumn<?, ?> tableStatPeriod;

    @FXML
    private TableColumn<?, ?> tableStatTotal;

    @FXML
    private AnchorPane windowData;


    @FXML
    void initialize() {

        ConnectorDb connectorDb = new ConnectorDb(); //new object dbConnector
        try {
            connectorDb.setCon();
        } catch (Exception e) {
            e.printStackTrace();
        }

        MonitorSize size = new MonitorSize();
        windowData.setPrefSize((double) size.width / 5 * 4, size.height);
        tableResultsName.setPrefWidth((double) size.width / 5 * 4 - 1020);
        tableLogsLog.setPrefWidth((double) size.width / 5 * 4 - 180);
        tableStatMostOften.setPrefWidth((double) size.width / 5 * 4 - 1260);
        tableErrorsDescription.setPrefWidth((double) size.width / 5 * 4 - 780);
        tableTestsDescription.setPrefWidth((double) size.width / 5 * 4 - 780);

        buttonLogsOn.setOnAction(event -> {
            try {
                buttonLogsOff.setVisible(true);
                buttonLogsOn.setVisible(false);
                buttonStatOn.setVisible(true);
                buttonErrorsOn.setVisible(true);
                buttonTestsOn.setVisible(true);
                buttonStatOff.setVisible(false);
                buttonErrorsOff.setVisible(false);
                buttonTestsOff.setVisible(false);
                tableLogs.setVisible(true);
                tableStat.setVisible(false);
                tableErrors.setVisible(false);
                tableTests.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonStatOn.setOnAction(event -> {
            try {
                buttonStatOff.setVisible(true);
                buttonLogsOn.setVisible(true);
                buttonStatOn.setVisible(false);
                buttonErrorsOn.setVisible(true);
                buttonTestsOn.setVisible(true);
                buttonLogsOff.setVisible(false);
                buttonErrorsOff.setVisible(false);
                buttonTestsOff.setVisible(false);
                tableStat.setVisible(true);
                tableLogs.setVisible(false);
                tableErrors.setVisible(false);
                tableTests.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonErrorsOn.setOnAction(event -> {
            try {
                buttonErrorsOff.setVisible(true);
                buttonLogsOn.setVisible(true);
                buttonStatOn.setVisible(true);
                buttonErrorsOn.setVisible(false);
                buttonTestsOn.setVisible(true);
                buttonLogsOff.setVisible(false);
                buttonStatOff.setVisible(false);
                buttonLogsOff.setVisible(false);
                buttonTestsOff.setVisible(false);
                tableErrors.setVisible(true);
                tableLogs.setVisible(false);
                tableStat.setVisible(false);
                tableTests.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonTestsOn.setOnAction(event -> {
            try {
                buttonTestsOff.setVisible(true);
                buttonLogsOn.setVisible(true);
                buttonStatOn.setVisible(true);
                buttonErrorsOn.setVisible(true);
                buttonTestsOn.setVisible(false);
                buttonLogsOff.setVisible(false);
                buttonStatOff.setVisible(false);
                buttonErrorsOff.setVisible(false);
                tableTests.setVisible(true);
                tableLogs.setVisible(false);
                tableStat.setVisible(false);
                tableErrors.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
