package db.MainTabs.FilteringSystems;

import additionalFunc.TableModify;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 29.03.13
 * Time: 1:30
 * To change this template use File | Settings | File Templates.
 */
public class AutoWashMotorTH {
    private static  String[]    columnNames= {"Марка", "Производительность, м3/ч", "Тип присоединения",
                                            "Диаметр патрубков,мм", "Высота (А),мм", "Диаметр (В),мм", "Вес, кг",
                                            "Прайс, евро", "Цена руб."};
    Object[][] data =   {{null, null, null, null, null, null, null, null, null}};

    private JPanel awmthPane;
    private JTable awmthTable;

    private void createUIComponents() {
        awmthTable = TableModify.initTable(data, columnNames);
        // TODO: place custom component creation code here
    }

    public JPanel getPane() {
        return awmthPane;
    }

    public JTable getTable() {
        return awmthTable;
    }
}
