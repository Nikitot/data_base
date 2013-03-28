package db.nik.inputData;

import db.nik.mainFrm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created with IntelliJ IDEA.
 * User: Dh
 * Date: 26.03.13
 * Time: 22:24
 * To change this template use File | Settings | File Templates.
 */
public class InputTable{
    private static  String[]    columnNames= {"Наименование показателя", "Единицы измерения", "Исходная вода", "СанПиН 2.1.4.1074-01"};

    Object[][] data =   {{null, null, null, null}};

    private         JTable      inputTable;
    private         JPanel      inputTablePane;


    public JTable initTable() {
        inputTable = new javax.swing.JTable() {};
        inputTable.setModel(new javax.swing.table.DefaultTableModel(data,columnNames));
        inputTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        inputTable.setFocusable(false);
        for (int i = 0; i < 20; i++)
            addRow(inputTable);

        return inputTable;
    }

    //Функция добавления строки в конец
    private void addRow(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{"", "", ""});
        table.setModel(model);
    }

    public JTable getinputTable() {
        return inputTable;
    }

    public JPanel getinputTablePane() {
        return inputTablePane;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    private void createUIComponents() {
        inputTable = initTable();
    }


}
