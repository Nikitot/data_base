package db.MainTabs;

import additionalFunc.TableModify;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
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
    private static String[] columnNames = {"Наименование показателя", "Единицы измерения", "Исходная вода", "СанПиН 2.1.4.1074-01"};

    private static String[] dataNames = {"Тип источника",
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
            "Si",
            "КОЭ"};

    private static String[] measurNames = {"АС- Р - О - М ",
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
            ""};
    Object[][] data = {{null}};

    private JTable inputTable;
    private JPanel inputTablePane;
    private JButton saveButton;
    private JButton clearButton;


    public InputTable() {
        //загружаем значение в таблицу из памяти
        loadValues("values.txt", 2);
        loadValues("sanpin.txt", 3);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //сохраняем значения значения и санпин
                saveValues("values.txt", 2);
                saveValues("sanpin.txt", 3);
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // очистка вырбранного столбца
                for (int i = 0; i < inputTable.getRowCount(); i++) {
                    inputTable.setValueAt("", i, 2);
                }
                saveValues("values.txt",2);
            }
        });
    }

    private void saveValues(String fileName, int columnNubmer) {
        PrintWriter out;
        try {
            out = new PrintWriter(new File(fileName).getAbsoluteFile());

            for (int i = 0; i < inputTable.getRowCount(); i++) {
                out.println(inputTable.getValueAt(i, columnNubmer));
            }
            out.close();
        } catch (Exception ex) {
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
        } catch (Exception ex) {
        }
    }


    public JTable getinputTable() {
        return inputTable;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public JPanel getInputTablePane() {
        return inputTablePane;
    }

    private void createUIComponents() {
        inputTable = TableModify.initTable(data, columnNames);
        while (inputTable.getRowCount() < dataNames.length) {
            TableModify.addBlankRow(inputTable);
        }
        for (int i = 0; i < inputTable.getRowCount(); i++) {
            inputTable.setValueAt(dataNames[i], i, 0);
            inputTable.setValueAt(measurNames[i], i, 1);
        }


    }
}
