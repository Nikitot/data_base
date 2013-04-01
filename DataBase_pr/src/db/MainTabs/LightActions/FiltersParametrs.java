package db.MainTabs.LightActions;

import additionalFunc.TableModify;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Dh
 * Date: 30.03.13
 * Time: 0:29
 * To change this template use File | Settings | File Templates.
 */
public class FiltersParametrs {

    private static  String[]    columnNames= {"???", "???", "Оптимальные скорости для", "осадительных фильтров", "Оптимальные скорости для", "сорбц. и каталит. фильтров"};
    Object[][] data =   {{null, null, null, null, null, null}};

    private JPanel filtrPrmPane;
    private JTable filtrPrmTable;

    public JPanel getPane() {
        return  filtrPrmPane;
    }

    public JTable getTable() {
        return filtrPrmTable;
    }

    private void createUIComponents() {
        filtrPrmTable = TableModify.initTable(data, columnNames);
        // TODO: place custom component creation code here
    }
}
