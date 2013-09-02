package db.MainTabs.FilteringSystems;

import additionalFunc.TableModify;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 29.03.13
 * Time: 1:07
 * To change this template use File | Settings | File Templates.
 */
public class CWFiltersF76 {
    private static  String[]    columnNames= {"Тип", "Присоединение, R", "Pmax, МПа", "Tmax, 0С", "Q, м3/ч",
                                                "Размеры,L/IH/h/D, мм", "Масса, кг", "Прайс, евро", "Цена руб."};
    Object[][] data =   {{null, null, null, null, null, null, null, null, null}};

    private JPanel cwFiltersPane;
    private JTable cwFiltersTable;
    private JTextPane textTip;

    public JPanel getPane() {
        return cwFiltersPane;
    }

    public JTable getTable() {
        return cwFiltersTable;
    }

    private void createUIComponents() {
        cwFiltersTable = TableModify.initTable(data, columnNames);
        // TODO: place custom component creation code here
    }
}
