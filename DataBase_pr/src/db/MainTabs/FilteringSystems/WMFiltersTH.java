package db.MainTabs.FilteringSystems;

import additionalFunc.TableModify;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 29.03.13
 * Time: 2:14
 * To change this template use File | Settings | File Templates.
 */
public class WMFiltersTH {
    private static  String[]    columnNames= {"Марка", "Производительность, м3/ч", "Тип присоединения",
            "Диаметр патрубков,мм", "Высота (А),мм", "Диаметр (В),мм", "Вес, кг", "Прайс, евро", "Цена руб."};
    Object[][] data =   {{null, null, null, null, null, null, null, null, null}};

    private JTable wmFiltersThTable;
    private JPanel wmFiltersThPane;

    private void createUIComponents() {
        wmFiltersThTable = TableModify.initTable(data, columnNames);
        // TODO: place custom component creation code here
    }

    public JTable getTable() {
        return wmFiltersThTable;
    }

    public JPanel getPane() {
        return wmFiltersThPane;
    }
}
