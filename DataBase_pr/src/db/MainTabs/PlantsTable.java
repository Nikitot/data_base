package db.MainTabs;

import additionalFunc.TableModify;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 26.03.13
 * Time: 18:35
 * To change this template use File | Settings | File Templates.
 */

public class PlantsTable {
    private static String[] columnNames = {
            "Марка",
            "Зав. №",
            "Тип",
            "Произв-ть, м3/чзс",
            "Запах, баллы",
            "Мутность, мг/л",
            "Цветность, град",
            "Окисл перм, О2 мг/л",
            "рН",
            "Жесткость, мг-экв/л",
            "Минер-ция, мг/л",
            "Fe, мг/л",
            "Mn, мг/л",
            "Хлориды, мг/л",
            "Сульф, мг/л",
            "Аммиак,  мг/л",
            "Источник (С, Р,О)",
            "КОЭ", "Шел-ть, мг/л",
            "B, мг/л",
            "Br, мг/л",
            "Li, мг/л",
            "Ba, мг/л",
            "Si, мг/л"};
    Object[][] data = {null};

    private JTable plantsTable;
    private JPanel plantsTablePane;
    private JButton Find;

    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JCheckBox checkBox4;
    private JCheckBox checkBox5;
    private JCheckBox checkBox6;
    private JCheckBox checkBox7;
    private JCheckBox checkBox8;
    private JCheckBox checkBox9;
    private JCheckBox checkBox10;
    private JCheckBox checkBox11;
    private JCheckBox checkBox12;
    private JCheckBox checkBox13;
    private JCheckBox checkBox14;
    private JCheckBox checkBox15;
    private JCheckBox checkBox16;
    private JCheckBox checkBox17;
    private JCheckBox checkBox18;
    private JCheckBox checkBox19;
    private JCheckBox checkBox20;

    private JButton loadButton;
//    private JButton clearButton;

    private JCheckBox[] filterCbh;

    public PlantsTable() {

        filterCbh = new JCheckBox[]{
                checkBox1, checkBox2, checkBox3, checkBox4, checkBox5,
                checkBox6, checkBox7, checkBox8, checkBox9, checkBox10,
                checkBox11, checkBox12, checkBox13, checkBox14, checkBox15,
                checkBox16, checkBox17, checkBox18, checkBox19, checkBox20
        };

        setTextsChBs();                                                                 //подписываем чекбоксы


        TableModify.addBlankRow(plantsTable);
        TableModify.addBlankRow(plantsTable);

        loadValues("plants_table_values.txt", 0);
        loadValues("delta_values.txt", 1);


        Find.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                saveValues("plants_table_values.txt", 0);                               //сохраняем данные первой строки
                saveValues("delta_values.txt", 1);                                      //сохраняе данные второй строки
                isSelectedChBs();                                                       //осуществляем поиск исходя из фильтров
            }
        });
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadOridginData();
            }
        });

    }


    public void actionFind(int i) {
        double value = 0, deltaValue = 0;
        String columnName = plantsTable.getColumnName(i);
        if (columnName.equals(columnNames[i])) {
            try {

                String cellValue = plantsTable.getValueAt(0, i).toString();
                String cellDeltaValue = plantsTable.getValueAt(1, i).toString();

                value = Double.parseDouble(cellValue);
                deltaValue = Double.parseDouble(cellDeltaValue);

            } catch (Exception ex) {
                if (i < 16) {
                    filterCbh[i - 3].setSelected(false);
                } else {
                    filterCbh[i - 4].setSelected(false);
                }
            }

            for (int j = 2; j < plantsTable.getRowCount(); j++) {
                try {
                    String getv = plantsTable.getValueAt(j, i).toString();

                    if (getv.indexOf("-") != -1) {
                        getv = getv.replaceAll("-", " ");

                        Scanner scan = new Scanner(getv);

                        String part1 = scan.next();
                        String part2 = scan.next();

                        double doublePart1 = Double.parseDouble(part1);
                        double doublePart2 = Double.parseDouble(part2);

                        if (!(doublePart1 >= value - deltaValue && doublePart2 <= value + deltaValue)) {
                            TableModify.removeRow(plantsTable, j);
                        }

                    } else {
                        Double presentValue = Double.parseDouble(getv);
                        if (presentValue > value + deltaValue || presentValue < value - deltaValue) {
                            TableModify.removeRow(plantsTable, j);
                        }

                    }
                } catch (Exception ex) {
                    //JOptionPane.showMessageDialog(null,"Неверно введены параметры установки" + j,"Error",2);
                    //TableModify.removeRow(plantsTable, j);
                }
            }
        }
    }

    public void loadOridginData() {
        int[] columnNumber = {16, 4, 5, 6, 8, 7, 11, 12, 13, 14, 9, 10, 15, 18, 20, 21, 22, 23, 17};

        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(new File("values.txt").getAbsoluteFile()));
            try {
                for (int j = 0; j < columnNumber.length; j++) {
                    String str = in.readLine();
                    if (str.equals("null")) {
                        str = "";
                    }
                    plantsTable.setValueAt(str, 0, columnNumber[j]);
                }
            } catch (Exception ex) {
                in.close();
            }
            in.close();
        } catch (Exception ex) {
        }
    }

    public JPanel getPlantsTablePane() {
        return plantsTablePane;
    }

    private void createUIComponents() {
        restartChBs();
        plantsTable = TableModify.initTable(data, columnNames);
    }

    private void saveValues(String fileName, int rowNubmer) {
        PrintWriter out;
        try {
            out = new PrintWriter(new File(fileName).getAbsoluteFile());

            for (int i = 0; i < plantsTable.getColumnCount(); i++) {
                out.println(plantsTable.getValueAt(rowNubmer, i));
            }
            out.close();
        } catch (Exception ex) {
        }

    }

    private void loadValues(String fileName, int rowNubmer) {
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
            for (int i = 0; i < plantsTable.getColumnCount(); i++) {
                try {
                    String str = in.readLine();
                    if (str.equals("null")) {
                        str = "";
                    }
                    plantsTable.setValueAt(str, rowNubmer, i);
                } catch (Exception ex) {
                    in.close();
                }
            }
            in.close();
        } catch (Exception ex) {
        }
    }

    private void restartChBs() {
        checkBox1 = new JCheckBox();
        checkBox1 = new JCheckBox();
        checkBox2 = new JCheckBox();
        checkBox3 = new JCheckBox();
        checkBox4 = new JCheckBox();
        checkBox5 = new JCheckBox();
        checkBox6 = new JCheckBox();
        checkBox7 = new JCheckBox();
        checkBox8 = new JCheckBox();
        checkBox9 = new JCheckBox();
        checkBox10 = new JCheckBox();
        checkBox11 = new JCheckBox();
        checkBox12 = new JCheckBox();
        checkBox13 = new JCheckBox();
        checkBox14 = new JCheckBox();
        checkBox15 = new JCheckBox();
        checkBox15 = new JCheckBox();
        checkBox16 = new JCheckBox();
        checkBox17 = new JCheckBox();
        checkBox18 = new JCheckBox();
        checkBox19 = new JCheckBox();
    }

    private void isSelectedChBs() {
        for (int i = 0; i < filterCbh.length; i++) {
            if (filterCbh[i].isSelected()) {
                if (i < 12) {
                    actionFind(i + 3);
                } else {
                    actionFind(i + 4);
                }
            }
        }


    }

    private void setTextsChBs() {
        for (int i = 0; i < filterCbh.length; i++) {
            if (i < 13)
                filterCbh[i].setText(columnNames[i + 3]);
            else
                filterCbh[i].setText(columnNames[i + 4]);
        }
        ;
    }
}
