package additionalFunc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 29.03.13
 * Time: 0:17
 * To change this template use File | Settings | File Templates.
 */
public class TableModify {
    static public void addRow(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[model.getColumnCount()]);
        table.setModel(model);
    }

    //Функция удаления i строки
    static public void removeRow(JTable table, int i) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(i);
        table.setModel(model);
    }

    static public JTable initTable(Object[][] data, String[] columnNames) {
        JTable table = new javax.swing.JTable() {};
        table.setModel(new javax.swing.table.DefaultTableModel(data,columnNames));
        table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table.setFocusable(false);
        for (int i = 0; i < 20; i++)
            addRow(table);

        return table;
    }

    public static void clearTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        while (model.getRowCount()>0){
            model.removeRow(0);
        }
        table.setModel(model);
    }
}
