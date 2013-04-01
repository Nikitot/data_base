package db.MainTabs.FilteringSystems;

import additionalFunc.TableModify;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 29.03.13
 * Time: 2:26
 * To change this template use File | Settings | File Templates.
 */
public class BBPackageTH {
    private static  String[]    columnNames= {"Артикул", "Параметры", "Прайс, евро", "Цена руб."};
    Object[][] data =   {{null, null, null, null}};

    private JPanel bbPackageThPane;
    private JTable bbPackageThTable;

    private void createUIComponents() {
        bbPackageThTable = TableModify.initTable(data, columnNames);
        // TODO: place custom component creation code here
    }

    public JPanel getPane() {
        return bbPackageThPane;
    }

    public JTable getTable() {
        return bbPackageThTable;
    }
}
