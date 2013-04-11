package db.MainTabs;

import additionalFunc.TableModify;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created with IntelliJ IDEA.
 * User: Dh
 * Date: 26.03.13
 * Time: 22:24
 * To change this template use File | Settings | File Templates.
 */
public class InputTable {
    private static String[] columnNames = {"Наименование показателя", "Единицы измерения", "Исходная вода", "СанПиН 2.1.4.1074-01"};

    private static String[] dataNames = {""};

    private static String[] measurNames = {""};
    Object[][] data = {{null}};

    private JTable inputTable;
    private JPanel inputTablePane;


    public JTable getinputTable() {
        return inputTable;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public JPanel getInputTablePane() {
        return inputTablePane;
    }

    private void createUIComponents() {
        inputTable = TableModify.initTable(data, columnNames);
        while (inputTable.getRowCount() < dataNames.length) {
            TableModify.addRow(inputTable, data);
        }
        for (int i = 0; i < inputTable.getRowCount(); i++) {
            inputTable.setValueAt(dataNames[i], i, 0);
            inputTable.setValueAt(measurNames[i], i, 1);
        }


    }
}
