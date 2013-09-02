package db.MainTabs.FilteringSystems;

import additionalFunc.TableModify;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 29.03.13
 * Time: 2:56
 * To change this template use File | Settings | File Templates.
 */
public class ERUMembrane {
    private static  String[]    columnNames= {"Рабочие характеристики", "П-100-508-20000", "П-100-1016-20000",
                                                "П-100-508-50000", "П-100-1016-50000"};
    Object[][] data =   {{null, null, null, null, null}};

    private JPanel eruMembranePane;
    private JTable eryMembraneTable;

    private void createUIComponents() {
        eryMembraneTable = TableModify.initTable(data, columnNames);
        // TODO: place custom component creation code here
    }

    public JPanel getPane() {
        return eruMembranePane;
    }

    public JTable getTable() {
        return eryMembraneTable;
    }
}
