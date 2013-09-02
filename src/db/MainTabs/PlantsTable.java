package db.MainTabs;

import additionalFunc.DataBaseInteraction;
import additionalFunc.PlantRecord;
import additionalFunc.TableModify;
import additionalFunc.EditDocx;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
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

    private JTable plantsTable;
    private JPanel plantsTablePane;

    private JCheckBox
            checkBox1, checkBox2, checkBox3, checkBox4, checkBox5,
            checkBox6, checkBox7, checkBox8, checkBox9, checkBox10,
            checkBox11, checkBox12, checkBox13, checkBox14, checkBox15,
            checkBox16, checkBox17, checkBox18, checkBox19, checkBox20;

    private JButton addBlankRowButton;
    private JButton uploadToDBButton;
    private JTable inDataTable;
    private JScrollPane jsc;
    private JButton testDocxButton;
    private JPanel filterPanel;

    private JCheckBox[] filterCbh;

    JDialog filter = new JDialog();

    public PlantsTable() {

        filterCbh = new JCheckBox[]{
                checkBox1, checkBox2, checkBox3, checkBox4, checkBox5,
                checkBox6, checkBox7, checkBox8, checkBox9, checkBox10,
                checkBox11, checkBox12, checkBox13, checkBox14, checkBox15,
                checkBox16, checkBox17, checkBox18, checkBox19, checkBox20
        };

        setTextsChBs();                                                                 //подписываем чекбоксы
        TableModify.addBlankRow(inDataTable);

        //loadValues("plants_table_values.txt", 0);
        loadValues("delta_values.txt", 1);

        addBlankRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableModify.addBlankRow(plantsTable);
            }
        });
        uploadToDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < plantsTable.getRowCount(); i++) {
                    uploadRowToDb(i);
                }
//                TableModify.clearTable(plantsTable);
//                TableModify.addBlankRow(inDataTable);

                loadValues("plants_table_values.txt", 0);
                loadValues("delta_values.txt", 1);
                setRowsFromDb();
            }
        });

        for (int i = 0; i < filterCbh.length; i++) {
            final int finalI = i;
            filterCbh[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sorterRegulation(false);
                    if (filterCbh[finalI].isSelected()) {

                        isSelectedChBs();                                                       //осуществляем поиск исходя из фильтров
                    } else {
                        TableModify.clearTable(plantsTable);
                        loadValues("plants_table_values.txt", 0);
                        loadValues("delta_values.txt", 1);
                        setRowsFromDb();

                        isSelectedChBs();

                    }
                    sorterRegulation(true);
                }
            });
        }
        testDocxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditDocx editDocx = new EditDocx();
                editDocx.readAndSaveDocx();
            }
        });


    }

    private void sorterRegulation(boolean bool) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(plantsTable.getModel());
        for (int k = 0; k < plantsTable.getColumnCount(); k++) {
            sorter.setSortable(k, bool);
            plantsTable.setRowSorter(sorter);
        }
    }

    //by Vasya
    private void uploadRowToDb(int i) {
        boolean rowIsNew = false;
        String[] values = new String[plantsTable.getColumnCount()];
        for (int j = 0; j < plantsTable.getColumnCount(); j++) {
            values[j] = (String) plantsTable.getValueAt(i, j);
        }
        if (values[1] == null || values[1].equals("")) {
            return;
        }
        PlantRecord plantRecord = new PlantRecord();
        plantRecord.setValuesFromStrings(values);

        int result = plantRecord.putRecordInDb(false);
        if (result == -1) {
            try {
                ResultSet resultSet = DataBaseInteraction.getFromDb(null, "PLANT", "FABRIC_ID = '" + values[1] + "'");
                if (resultSet.next()) {
                    for (int j = 0; j < values.length; j++) {
                        if (!values[j].equals(resultSet.getString(j + 2))) {
                            rowIsNew = true;
                            break;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                return;
            }
            if (rowIsNew) {
                int chose = JOptionPane.showConfirmDialog(plantsTablePane, "В БД запись о приборе " + values[1]
                        + " уже существует. Обновить?", "Запись уже существует", JOptionPane.YES_NO_OPTION);
                if (chose == JOptionPane.YES_OPTION) {
                    plantRecord.putRecordInDb(true);
                }
            }
        }
    }


    //by Dh
    public void actionFind(int i) {
        double value = 0, deltaValue = 0;
        String columnName = inDataTable.getColumnName(i - 3);
        if (columnName.equals(columnNames[i])) {
            try {

                String cellValue = inDataTable.getValueAt(0, i - 3).toString();
                String cellDeltaValue = inDataTable.getValueAt(1, i - 3).toString();

                Scanner sc = new Scanner(cellValue);
                value = sc.nextDouble();
                sc = new Scanner(cellDeltaValue);
                deltaValue = sc.nextDouble();

            } catch (Exception ex) {
                System.out.println("Error of parsing 0 and 1 rows");
            }

            for (int j = 0; j < plantsTable.getRowCount(); j++) {
                try {
                    String getv = plantsTable.getValueAt(j, i).toString();
                    if (getv.indexOf('.') != -1) {
                        getv = getv.substring(0, getv.indexOf('.')) + "," + getv.substring((getv.indexOf('.') + 1));
                    }
                    boolean dash = false;

                    for (int k = 0; k < getv.length(); k++) {
                        char symbol = getv.toCharArray()[k];

                        if (!(Character.isDigit(symbol) || symbol == ',')) {

                            getv = getv.substring(0, k) + " " + getv.substring(k + 1);
                            dash = true;
                        }
                    }
                    if (dash) {

                        Scanner scan = new Scanner(getv);

                        double doublePart1 = scan.nextDouble();
                        double doublePart2 = scan.nextDouble();


                        if ((!(doublePart1 >= value - deltaValue && doublePart2 <= value + deltaValue)) || doublePart2 <= doublePart1) {
                            TableModify.removeRow(plantsTable, j);
                            j--;
                        }
                    } else {
                        Scanner scan = new Scanner(getv);
                        Double presentValue = scan.nextDouble();
                        if (presentValue > value + deltaValue || presentValue < value - deltaValue || getv.equals("*")) {
                            TableModify.removeRow(plantsTable, j);

                            j--;
                        }

                    }
                } catch (Exception ex) {
                    TableModify.removeRow(plantsTable, j);
                    j--;
                }
            }
        }
    }


    public JPanel getPanel() {
        return plantsTablePane;
    }

    private void createUIComponents() {
        filterPanel = new JPanel();

        final InDataTableModel inDataTableModel = new InDataTableModel();

        inDataTableModel.setRowCount(1);
        inDataTableModel.setColumnCount(columnNames.length - 3);

        inDataTable = new JTable(inDataTableModel);
        restartChBs();

        plantsTable = TableModify.initTable(columnNames);
        setSorter();

        plantsTable.getTableHeader().setReorderingAllowed(false);
        plantsTable.setColumnSelectionAllowed(true);
        plantsTable.setRowSelectionAllowed(true);

        setRowsFromDb();
    }

    private void setSorter() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(plantsTable.getModel()) {
            @Override
            public Comparator<?> getComparator(int column) {
                if (plantsTable.getColumnName(column).equals(columnNames[1])
                        || plantsTable.getColumnName(column).equals(columnNames[3])
                        || plantsTable.getColumnName(column).equals(columnNames[4])
                        || plantsTable.getColumnName(column).equals(columnNames[5])
                        || plantsTable.getColumnName(column).equals(columnNames[6])
                        || plantsTable.getColumnName(column).equals(columnNames[7])
                        || plantsTable.getColumnName(column).equals(columnNames[8])
                        || plantsTable.getColumnName(column).equals(columnNames[9])
                        || plantsTable.getColumnName(column).equals(columnNames[10])
                        || plantsTable.getColumnName(column).equals(columnNames[11])
                        || plantsTable.getColumnName(column).equals(columnNames[12])
                        || plantsTable.getColumnName(column).equals(columnNames[13])
                        || plantsTable.getColumnName(column).equals(columnNames[14])
                        || plantsTable.getColumnName(column).equals(columnNames[15])
                        || plantsTable.getColumnName(column).equals(columnNames[17])
                        || plantsTable.getColumnName(column).equals(columnNames[18])
                        || plantsTable.getColumnName(column).equals(columnNames[19])
                        || plantsTable.getColumnName(column).equals(columnNames[20])
                        || plantsTable.getColumnName(column).equals(columnNames[21])
                        || plantsTable.getColumnName(column).equals(columnNames[22])
                        || plantsTable.getColumnName(column).equals(columnNames[23])) {
                    return new Comparator<String>() {
                        @Override
                        public int compare(String s1, String s2) {
                            double d1, d2;
                            try {
                                s1 = s1.replace(',', '.').replace('-', ' ');
                                Scanner sc = new Scanner(s1);
                                d1 = Double.parseDouble(sc.next()) * 100;
                            } catch (Exception e) {
                                return -1;
                            }
                            try {
                                s2 = s2.replace(',', '.').replace('-', ' ');
                                Scanner sc = new Scanner(s2);
                                d2 = Double.parseDouble(sc.next()) * 100;
                            } catch (Exception e) {
                                return 1;
                            }
                            return (int) (d1 - d2);
                        }
                    };

                } else {
                    return super.getComparator(column);    //To change body of overridden methods use File | Settings | File Templates.
                }
            }
        };
        //sorter.
        plantsTable.setRowSorter(sorter);
    }

    //by Dh
    private void saveValues(String fileName, int rowNubmer) {
        PrintWriter out;
        try {
            out = new PrintWriter(new File(fileName).getAbsoluteFile());

            for (int i = 0; i < inDataTable.getColumnCount(); i++) {
                out.println(inDataTable.getValueAt(rowNubmer, i));
            }
            out.close();
        } catch (Exception ignored) {
        }

    }

    //by Dh
    private void loadValues(String fileName, int rowNubmer) {
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
            for (int i = 0; i < inDataTable.getColumnCount(); i++) {
                try {
                    String str = in.readLine();
                    if (str.equals("null")) {
                        str = "";
                    }
                    inDataTable.setValueAt(str, rowNubmer, i);
                } catch (Exception ex) {
                    in.close();
                }
            }
            in.close();
        } catch (Exception ignored) {
        }
    }

    private void restartChBs() {
        checkBox1 = new JCheckBox();
    }

    private void isSelectedChBs() {
        saveValues("plants_table_values.txt", 0);                               //сохраняем данные первой строки
        saveValues("delta_values.txt", 1);                                      //сохраняе данные второй строки
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
    }

    private void setRowsFromDb() {
        TableModify.fillTableFromDb(plantsTable, "PLANT");
    }

    public JTable getInDataTable() {
        return inDataTable;
    }
}

class InDataTableModel extends javax.swing.table.DefaultTableModel {
    private static String[] columnNames = {
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

    public boolean isCellEditable(int row, int col) {
        return !(row == 0 && col > 0);
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }
}