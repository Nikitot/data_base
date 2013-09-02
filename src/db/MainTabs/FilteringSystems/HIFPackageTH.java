package db.MainTabs.FilteringSystems;

import additionalFunc.TableModify;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 29.03.13
 * Time: 2:39
 * To change this template use File | Settings | File Templates.
 */
public class HIFPackageTH {
    private static  String[]    columnNames= {"Модель", "Макс. производ. с картриджами 801-20, м3/ч", "Кол-во стандартных сменных картриджей, шт.",
            "Размеры H x D,мм", "Вес без картриджей, кг", "Присоединит. размеры, вход/выход/дренаж,мм", "Прайс, евро", "Цена руб."};
    Object[][] data =   {{null, null, null, null, null, null, null, null}};

    private JPanel hifPackageThPane;
    private JTable hifPackageThTable;

    private void createUIComponents() {
        hifPackageThTable = TableModify.initTable(data, columnNames);
        // TODO: place custom component creation code here
    }

    public JPanel getPane() {
        return hifPackageThPane;
    }

    public JTable getTable() {
        return hifPackageThTable;
    }
}
