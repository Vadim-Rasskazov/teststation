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
    private TableView<ResultDb> tableResults;

    @FXML
    private TableColumn<ResultDb, Integer> tableResultsNumber;

    @FXML
    private TableColumn<ResultDb, String> tableResultsDate;

    @FXML
    private TableColumn<ResultDb, String> tableResultsResult;

    @FXML
    private TableColumn<ResultDb, String> tableResultsLog;

    @FXML
    private AnchorPane windowData;

    private final ObservableList<ResultDb> resultList = FXCollections.observableArrayList();

    @FXML
    void initialize() {

        StatementsDb statementsDb = new StatementsDb();
        ConnectorDb connectorDb = new ConnectorDb(); //new object dbConnector
        try {
            connectorDb.setCon();
        } catch (Exception e) {

            e.printStackTrace(System.out);
        }

        MonitorSize size = new MonitorSize();
        windowData.setPrefSize((double) size.width / 5 * 4, size.height);
        tableResultsLog.setPrefWidth((double) size.width / 5 * 4 - 182);

        try {
            ResultSet rs = connectorDb.statement.executeQuery(statementsDb.result);
            while (rs.next()) {
                resultList.add(new ResultDb(rs.getInt("number"), rs.getString("date"), rs.getString("result"),
                        rs.getString("log")));
            }
            tableResultsNumber.setCellValueFactory(cell -> cell.getValue().numberProperty().asObject());
            tableResultsDate.setCellValueFactory(cell-> cell.getValue().dateProperty());
            tableResultsResult.setCellValueFactory(cell-> cell.getValue().resultProperty());
            tableResultsLog.setCellValueFactory(cell-> cell.getValue().logProperty());
            tableResults.setItems(resultList);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
