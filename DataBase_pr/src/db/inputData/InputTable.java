package db.inputData;

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
public class InputTable{
    private static  String[]    columnNames= {"Наименование показателя", "Единицы измерения", "Исходная вода", "СанПиН 2.1.4.1074-01"};

    Object[][] data =   {{null, null, null, null}};

    private         JTable      inputTable;
    private         JPanel      inputTablePane;


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
    }



}
