package db.MainTabs.FilteringSystems;

import additionalFunc.TableModify;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 29.03.13
 * Time: 2:45
 * To change this template use File | Settings | File Templates.
 */
public class NORITMembrane {
    private static  String[]    columnNames= {"Тип ультрафильтраци-онных мембран",
                            "Количества взвешенных веществ в исходной воде, мг/л"};
    Object[][] data =   {{null, null}};

    private JPanel noritMembranePane;
    private JTable noritMembraneTable;

    private void createUIComponents() {
        noritMembraneTable = TableModify.initTable(data, columnNames);
        // TODO: place custom component creation code here
    }

    public JPanel getPane() {
        return noritMembranePane;
    }

    public JTable getTable() {
        return noritMembraneTable;
    }
}
