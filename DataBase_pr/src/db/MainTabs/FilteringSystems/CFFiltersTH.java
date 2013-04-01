package db.MainTabs.FilteringSystems;

import additionalFunc.TableModify;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 29.03.13
 * Time: 2:34
 * To change this template use File | Settings | File Templates.
 */
public class CFFiltersTH {
    private static  String[]    columnNames= {"Модель", "Производительность, м3/час", "Размеры(диам/высота), мм",
                                    "Размеры портов (вход/выход), дюймы", "Размер дренажного трубопровода, дюймы",
                                    "Размер и количество картриджей", "Прайс, евро", "Цена руб."};
    Object[][] data =   {{null, null, null, null, null, null, null, null}};

    private JPanel cfFiltersThPane;
    private JTable cfFiltersThTable;

    private void createUIComponents() {
        cfFiltersThTable = TableModify.initTable(data, columnNames);
        // TODO: place custom component creation code here
    }

    public JPanel getPane() {
        return cfFiltersThPane;
    }

    public JTable getTable() {
        return cfFiltersThTable;
    }
}
