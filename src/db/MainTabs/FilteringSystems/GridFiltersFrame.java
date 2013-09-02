package db.MainTabs.FilteringSystems;

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
    private static  String[]    columnNames= {"Технологическая задача", "Удаляемые вещества", "Методы решения",
            "Используемое технологическое оборудование", "Используемые расходные материалы", "Используемое оборудование"};
    Object[][] data =   {{null, null, null, null, null, null}};

    private JPanel gridFiltersPane;
    private JTable gridFiltersTable;


    public JPanel getPane() {
        return gridFiltersPane;
    }

    public JTable getTable() {
        return gridFiltersTable;
    }

    private void createUIComponents() {
        gridFiltersTable = TableModify.initTable(data, columnNames);
        // TODO: place custom component creation code here
    }

}
