package db.MainTabs.BwpuOptimization;

import additionalFunc.TableModify;

import javax.swing.*;
import javax.swing.table.TableColumn;

/**
 * Created with IntelliJ IDEA.
 * User: Фомин
 * Date: 19.10.13
 * Time: 19:47
 * To change this template use File | Settings | File Templates.
 */
public class InputTable {
    private static final int        EXCHANGE_RATE = 43;
    private static final String[]   columns = {"MODEL", "PRICE_NODE", "D1_D5", "D2_D3", "D4", "D6"
            , "ZE1", "ET", "BG", "N1_N2", "M1", "KSH1"
            , "KSH2_KSH3", "KSH4_KSH5", "KSH6", "KSH7", "KSH8", "KO1_KO2"};
    private static final String[]   columnLabels = {
            "Модель компл",
            "Узел цены",
            "Д1, Д5",
            "Д2, Д3",
            "Д5",
            "Д6",
            "ЗЭ1",
            "ЕТ",
            "БГ",
            "Н1, Н2",
            "М1",
            "КШ1",
            "КШ2, КШ3",
            "КШ4, КШ5",
            "КШ6",
            "КШ7",
            "КШ8",
            "КО1, КО2"
    };
    private JPanel panel;
    private JTable table;

    public JPanel getPanel() {
        return panel;
    }

    private void createUIComponents() {
        table = TableModify.initTable(columnLabels);

        table.getTableHeader().setReorderingAllowed(false);
        table.setColumnSelectionAllowed(true);
        table.setRowSelectionAllowed(true);

        TableModify.fillTableFromDb(table, "BWPU_INPUT", columns);
        addPriceColumns();

    }

    private void addPriceColumns() {
        table.addColumn(new TableColumn() {{
            setHeaderValue("Цена узла");
        }});
        table.addColumn(new TableColumn() {{
            setHeaderValue("Сумма");
        }});
        for (int i = 0; i < table.getRowCount(); i++) {
            if (((String)table.getValueAt(i, 1)).contains("Узел")) {
                table.setValueAt("---", i, table.getColumnCount() - 2);
                table.setValueAt("---", i, table.getColumnCount() - 1);
            } else {
                float price = calculatePrice(i);
                
                table.setValueAt(price, i, table.getColumnCount() - 2);
                table.setValueAt(price * EXCHANGE_RATE, i, table.getColumnCount() - 1);
            }
        }

    }

    private float calculatePrice(int rowIndex) {
        float price = 0;
        for (int j = 2; j <= 16; j++) {
            price += getFloatValue(rowIndex, j);
        }
        price += getFloatValue(rowIndex, 2);
        price += getFloatValue(rowIndex, 3);
        price += getFloatValue(rowIndex, 9);
        price += getFloatValue(rowIndex, 12);
        price += getFloatValue(rowIndex, 13);
        price += Math.pow(getFloatValue(rowIndex, 17), 2);
        return price;
    }

    private float getFloatValue(int i, int j) {
        return Float.parseFloat(table.getValueAt(i, j).toString().replace(',', '.'));
    }
}
