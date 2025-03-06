package org.openjfx;

import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ControllerData {

    @FXML
    private TableView<ResultFromDb> tableResults;

    @FXML
    private TableColumn<ResultFromDb, Integer> tableResultsNumber;

    @FXML
    private TableColumn<ResultFromDb, String> tableResultsDate;

    @FXML
    private TableColumn<ResultFromDb, String> tableResultsName;

    @FXML
    private TableColumn<ResultFromDb, String> tableResultsResult;

    @FXML
    private TableColumn<ResultFromDb, String> tableResultsLog;

    @FXML
    private AnchorPane windowData;

    private final ObservableList<ResultFromDb> resultList = FXCollections.observableArrayList();

    @FXML
    void initialize() {

        ConnectorDb connectorDb = new ConnectorDb();
        connectorDb.setCon();

        MonitorSize size = new MonitorSize();
        windowData.setPrefSize((double) size.width / 5 * 4, size.height);
        tableResultsLog.setPrefWidth((double) size.width / 5 * 4 - 182);

        try {
            ResultSet rs = connectorDb.statement.executeQuery("SELECT * FROM test_result");
            while (rs.next()) {
                resultList.add(new ResultFromDb(rs.getInt("id"), rs.getString("date"), rs.getString("name"), rs.getString("result"), rs.getString("log")));
            }
            tableResultsNumber.setCellValueFactory(cell -> cell.getValue().idProperty().asObject());
            tableResultsDate.setCellValueFactory(cell-> cell.getValue().dateProperty());
            tableResultsName.setCellValueFactory(cell-> cell.getValue().nameProperty());
            tableResultsResult.setCellValueFactory(cell-> cell.getValue().resultProperty());
            tableResultsLog.setCellValueFactory(cell-> cell.getValue().logProperty());
            tableResults.setItems(resultList);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
