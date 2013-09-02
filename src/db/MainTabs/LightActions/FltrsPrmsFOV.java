package db.MainTabs.LightActions;

import additionalFunc.TableModify;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Dh
 * Date: 31.03.13
 * Time: 18:46
 * To change this template use File | Settings | File Templates.
 */
public class FltrsPrmsFOV {
    private static  String[]    columnNames= {"Параметры фильтров ФОВ"};
    Object[][] data = {null};

    private JTable fltrPrmsFOVTable;
    private JPanel fltrPrmsFOVPane;

    public JPanel getPane() {
        return fltrPrmsFOVPane;
    }

    public JTable getTable() {
        return fltrPrmsFOVTable;
    }

    private void createUIComponents() {
        fltrPrmsFOVTable = TableModify.initTable(data, columnNames);
        // TODO: place custom component creation code here
    }

}
