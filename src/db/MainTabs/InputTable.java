package db.MainTabs;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Dh
 * Date: 26.03.13
 * Time: 22:24
 * To change this template use File | Settings | File Templates.
 */
public class InputTable {


    private static String[] dataNames = {
            "Тип источника",
            "Запах",
            "Мутность",
            "Цветность",
            "Водородный показатель",
            "Окисляемость перманганатная",
            "Железо",
            "Марганец",
            "Хлориды",
            "Сульфаты",
            "Жесткость общая",
            "Общее солесодержание",
            "Аммиак",
            "Щелочность",
            "Br",
            "Li",
            "Ba",
            "B",
            "Si",
            "КОЭ"};

    private static final String[] measureNames = {
            "АС- Р - О - М ",
            "баллы",
            "мг/л",
            "градусы",
            "ед. рН",
            "мгО2/л",
            "мг/л",
            "мг/л",
            "мг/л",
            "мг/л",
            "мг-экв/л",
            "мг/л",
            "мг/л",
            "мг/л",
            "мг/л",
            "мг/л",
            "мг/л",
            "мг/л",
            "мг/л",
            "-"};

    private JTable inputTable;
    private JPanel inputTablePane;
    private JButton saveButton;
    private JTable inputTableInPlantsTable;


    public InputTable() {
        loadValues("sanpin.txt", 3);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //сохраняем значения значения и санпин
                saveValues("sanpin.txt", 3);
            }
        });
//Тамер для прорисовки
//        Timer updateTimer = new Timer(1000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//        updateTimer.start();
    }

    private void saveValues(String fileName, int columnNubmer) {
        PrintWriter out;
        try {
            out = new PrintWriter(new File(fileName).getAbsoluteFile());

            for (int i = 0; i < inputTable.getRowCount(); i++) {
                out.println(inputTable.getValueAt(i, columnNubmer));
            }
            out.close();
        } catch (Exception ignored) {
        }

    }

    private void loadValues(String fileName, int columnNubmer) {
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
            for (int i = 0; i < inputTable.getRowCount(); i++) {
                try {
                    String str = in.readLine();
                    if (str.equals("null")) {
                        str = "";
                    }
                    inputTable.setValueAt(str, i, columnNubmer);
                } catch (Exception ex) {
                    in.close();
                }
            }
            in.close();
        } catch (Exception ignored) {
        }
    }


    public JTable getinputTable() {
        return inputTable;
    }

    public JPanel getPanel() {
        return inputTablePane;
    }

    private void createUIComponents() {
        final TableModel inputTableModel = new TableModel();

        inputTableModel.setRowCount(dataNames.length);
        inputTableModel.setColumnCount(4);
        for (int i = 0; i < inputTableModel.getRowCount(); i++) {
            inputTableModel.setValueAt(dataNames[i], i, 0);
            inputTableModel.setValueAt(measureNames[i], i, 1);
        }


        inputTableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    if (e.getColumn() == 2) {
                        int row = e.getFirstRow();
                        int targetCol[] = {13, 1, 2, 3, 5, 4, 8, 9, 10, 11, 6, 7, 12, 15, 17, 18, 19, 16, 20, 14};
                        if (row != 0) {
                            try {
                                String data = inputTableModel.getValueAt(row, 2).toString();
                                data = data.replaceAll(",", ".");

                                inputTableInPlantsTable.setValueAt(Double.parseDouble(data), 0, targetCol[row]);
                            } catch (NumberFormatException ignored) {
                            }
                        } else {
                            inputTableInPlantsTable.setValueAt(inputTableModel.getValueAt(row, 2), 0, targetCol[row]);
                        }

                    }
                }

            }
        });

        inputTable = new JTable(inputTableModel);


        inputTable.getTableHeader().setReorderingAllowed(false);
        inputTable.setColumnSelectionAllowed(true);
        inputTable.setRowSelectionAllowed(true);

    }

    public void setInputTableInPlantsTable(JTable inputTableInPlantsTable) {
        this.inputTableInPlantsTable = inputTableInPlantsTable;
    }

}

class TableModel extends javax.swing.table.DefaultTableModel {
    private static String[] columnNames = {"Наименование показателя", "Единицы измерения", "Исходная вода", "СанПиН 2.1.4.1074-01"};

    public boolean isCellEditable(int row, int col) {
        return !(col == 0 || col == 1);
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }
}
