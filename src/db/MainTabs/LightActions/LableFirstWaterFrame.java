package db.MainTabs.LightActions;

import additionalFunc.TableModify;
import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 28.03.13
 * Time: 23:54
 * To change this template use File | Settings | File Templates.
 */
public class LableFirstWaterFrame {
    private static  String[]    columnNames= {"Наименование показателя", "Единицы измерения", "Исходная вода", "СанПиН 2.1.4.1074-01"};

    Object[][] data =   {{null, null, null, null}};
    private JPanel lfwPane;
    private JTable lfwTable;


    public JPanel getLfwPane() {
        return lfwPane;
    }

    public JTable getLfwForTsTable() {
        return lfwTable;
    }

    private void createUIComponents() {
        lfwTable = TableModify.initTable(data, columnNames);
        // TODO: place custom component creation code here
    }

}
