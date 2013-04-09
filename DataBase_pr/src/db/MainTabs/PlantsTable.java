package db.MainTabs;

import additionalFunc.TableModify;

import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 26.03.13
 * Time: 18:35
 * To change this template use File | Settings | File Templates.
 */

public class PlantsTable {
    private static String[] columnNames = {"Марка", "Зав. №", "Тип", "Произв-ть м3/чзс", "Запах  баллы", "Мутность мг/л", "Цветность град",
            "Окисл перм   О2 мг/л", "рН", "Жесткость, мг-экв/л", "Минер-ция, мг/л", "Fe, мг/л", "Mn, мг/л", "Хлориды мг/л",
            "Сульф мг/л", "Аммиак  мг/л", "Источник (С, Р,О)", "КОЭ", "Шел-ть мг/л", "B        мг/л", "Br           мг/л",
            "Li        мг/л", "Ba      мг/л", "Si       мг/л"};
    Object[][] data = {{null}};

    private JTable plantsTable;
    private JPanel plantsTablePane;
    private JButton Find;

    private double deltaTurbidity = 1; //delta мутности
    private double deltaChroma = 10; //delta цветности
    private double deltaOxidation = 2; //delta окисления
    private double deltaHardness = 3; //delta жесткости
    private double deltaFe = 2; //delta Fe
    private double deltaMn = 1; //delta Mn


    public PlantsTable() {

        Find.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                for(int i = 0; i < plantsTable.getColumnCount(); i++){
                    if(i > 2 && i != 16)
                    actionFind(columnNames[i].toString(),0,0);
                }
            }
        });
    }

    public void actionFind(String strName, double value, double deltaValue) {
        for (int i = 0; i < plantsTable.getColumnCount(); i++) {
            String columnName = plantsTable.getColumnName(i);
            if (columnName.equals(strName)) {
                try {

                    String cellValue = plantsTable.getValueAt(0, i).toString();
                    String cellDeltaValue = plantsTable.getValueAt(1, i).toString();
                    value = getDouble(value, cellValue);
                    deltaValue = getDouble(deltaValue, cellDeltaValue);
                } catch (Exception ex) {
                    plantsTable.setValueAt(0, 0, i);
                    if(i == 5){
                        plantsTable.setValueAt(deltaTurbidity, 1, i);
                    }else if(i == 6){
                        plantsTable.setValueAt(deltaChroma, 1, i);
                    }else if(i == 7){
                        plantsTable.setValueAt(deltaOxidation, 1, i);
                    }else if(i == 9){
                        plantsTable.setValueAt(deltaHardness, 1, i);
                    }else if(i == 11){
                        plantsTable.setValueAt(deltaFe, 1, i);
                    }else if(i == 12){
                        plantsTable.setValueAt(deltaMn, 1, i);
                    }else{
                        plantsTable.setValueAt(0, 1, i);
                    }
                }

                for (int j = 2; j < plantsTable.getRowCount(); j++) {
                    try {
                        Scanner sc = new Scanner(plantsTable.getValueAt(j, i).toString());
                        if (sc.nextDouble() > value + deltaValue || sc.nextDouble() < value - deltaValue) {
                            TableModify.removeRow(plantsTable, j);
                        }
                    } catch (Exception ex) {
                        TableModify.removeRow(plantsTable,j);
                    }
                }
            }
        }
    }

    private double getDouble(double value, String strValue) {
        Scanner sc = new Scanner(strValue);

        if (sc.hasNext()) {
            value = sc.nextDouble();
        }
        return value;
    }

    public JTable getPlantsTable() {
        return plantsTable;
    }

    public JPanel getPlantsTablePane() {
        return plantsTablePane;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    private void createUIComponents() {
        plantsTable = TableModify.initTable(data, columnNames);
    }
}
