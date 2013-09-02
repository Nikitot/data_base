package db.MainTabs.LightActions;

import additionalFunc.TableModify;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Dh
 * Date: 30.03.13
 * Time: 0:16
 * To change this template use File | Settings | File Templates.
 */
public class LFWFrameForTS {
    private static  String[]    columnNames= {"Наименование показателя", "???", "Данные из ТЗ заказчика (исх. вода)"};

    Object[][] data =   {{null, null, null}};
    private JPanel lfwForTsPane;
    private JTable lfwForTsTable;

    public JPanel getPane() {
        return lfwForTsPane;
    }

    public JTable getTable() {
        return lfwForTsTable;
    }

    private void createUIComponents() {
        lfwForTsTable = TableModify.initTable(data, columnNames);
        // TODO: place custom component creation code here
    }
}
