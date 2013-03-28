package db.FilteringSystems.GridFilters;

import javax.swing.*;
import additionalFunc.TableModify;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 28.03.13
 * Time: 23:54
 * To change this template use File | Settings | File Templates.
 */
public class GridFiltersFrame {
    private static  String[]    columnNames= {"Наименование показателя", "Единицы измерения", "Исходная вода", "СанПиН 2.1.4.1074-01"};

    Object[][] data =   {{null, null, null, null}};
    private JPanel gridFiltersPane;
    private JTable gridFiltersTable;


    public JPanel getGridFiltersPane() {
        return gridFiltersPane;
    }

    public JTable getGridFiltersTable() {
        return gridFiltersTable;
    }

    private void createUIComponents() {
        gridFiltersTable = TableModify.initTable(data, columnNames);
        // TODO: place custom component creation code here
    }

}
