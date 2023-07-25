package org.openjfx;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TableView<ErrorLogDb> tableLogs;

    @FXML
    private TableColumn<ErrorLogDb, String> tableLogsLog;

    @FXML
    private TableColumn<ErrorLogDb, Integer> tableLogsNumber;

    @FXML
    private TableView<TestDb> tableTests;

    @FXML
    private TableColumn<TestDb, String> tableTestsDescription;

    @FXML
    private TableColumn<TestDb, String> tableTestsName;

    @FXML
    private TableColumn<TestDb, Integer> tableTestsNumber;

    @FXML
    private TableColumn<TestDb, String> tableTestsVersion;

    @FXML
    private TableView<ResultDb> tableResults;

    @FXML
    private TableColumn<ResultDb, String> tableResultsDate;

    @FXML
    private TableColumn<ResultDb, Integer> tableResultsErrorNum;

    @FXML
    private TableColumn<ResultDb, String> tableResultsName;

    @FXML
    private TableColumn<ResultDb, Integer> tableResultsNumber;

    @FXML
    private TableColumn<ResultDb, String> tableResultsResult;

    @FXML
    private TableColumn<ResultDb, String> tableResultsTime;

    @FXML
    private TableColumn<ResultDb, String> tableResultsUser;

    @FXML
    private TableColumn<ResultDb, String> tableResultsVersion;

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

    private ObservableList<TestDb> testList = FXCollections.observableArrayList();

    private ObservableList<ResultDb> resultList = FXCollections.observableArrayList();

    private ObservableList<ErrorLogDb> logtList = FXCollections.observableArrayList();

    @FXML
    void initialize() {

        StatementsDb statementsDb = new StatementsDb();
        ConnectorDb connectorDb = new ConnectorDb(); //new object dbConnector
        try {
            connectorDb.setCon();
        } catch (Exception e) {
            e.printStackTrace();
        }

        MonitorSize size = new MonitorSize();
        windowData.setPrefSize((double) size.width / 5 * 4, size.height);
        tableResultsName.setPrefWidth((double) size.width / 5 * 4 - 1052);
        tableLogsLog.setPrefWidth((double) size.width / 5 * 4 - 182);
        tableStatMostOften.setPrefWidth((double) size.width / 5 * 4 - 1262);
        tableErrorsDescription.setPrefWidth((double) size.width / 5 * 4 - 782);
        tableTestsDescription.setPrefWidth((double) size.width / 5 * 4 - 782);

        try {
            ResultSet rs = connectorDb.statement.executeQuery(statementsDb.result);
            while (rs.next()) {
                resultList.add(new ResultDb(rs.getInt("id"), rs.getString("name"), rs.getString("result"),
                        rs.getString("time"), rs.getString("date"), rs.getString("user"), rs.getString("version"), rs.getInt("number")));
            }
            tableResultsNumber.setCellValueFactory(cell -> cell.getValue().idProperty().asObject());
            tableResultsName.setCellValueFactory(cell-> cell.getValue().nameProperty());
            tableResultsResult.setCellValueFactory(cell-> cell.getValue().resultProperty());
            tableResultsTime.setCellValueFactory(cell-> cell.getValue().timeProperty());
            tableResultsDate.setCellValueFactory(cell-> cell.getValue().dateProperty());
            tableResultsUser.setCellValueFactory(cell-> cell.getValue().userProperty());
            tableResultsVersion.setCellValueFactory(cell -> cell.getValue().versionProperty());
            tableResultsErrorNum.setCellValueFactory(cell -> cell.getValue().numberProperty().asObject());
            tableResults.setItems(resultList);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

                ResultSet rs = connectorDb.statement.executeQuery(statementsDb.log);
                while (rs.next()) {
                    logtList.add(new ErrorLogDb(rs.getInt("test_result_id"), rs.getString("log")));
                }
                tableLogsNumber.setCellValueFactory(cell -> cell.getValue().numberProperty().asObject());
                tableLogsLog.setCellValueFactory(cell-> cell.getValue().logProperty());
                tableLogs.setItems(logtList);
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
                ResultSet rs = connectorDb.statement.executeQuery(statementsDb.test);
                while (rs.next()) {
                    testList.add(new TestDb(rs.getInt("id"), rs.getString("name"), rs.getString("version"), rs.getString("description")));
                }
                tableTestsNumber.setCellValueFactory(cell -> cell.getValue().idProperty().asObject());
                tableTestsName.setCellValueFactory(cell-> cell.getValue().nameProperty());
                tableTestsVersion.setCellValueFactory(cell -> cell.getValue().versionProperty());
                tableTestsDescription.setCellValueFactory(cell -> cell.getValue().descriptionProperty());
                tableTests.setItems(testList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
