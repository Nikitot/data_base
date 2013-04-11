package db.MainTabs;

import additionalFunc.DataBaseInteraction;
import additionalFunc.PlantRecord;
import additionalFunc.TableModify;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 26.03.13
 * Time: 18:35
 * To change this template use File | Settings | File Templates.
 */

public class PlantsTable {
    private static  String[]    columnNames= {"Марка",  "Зав. №", "Тип", "Произв-ть м3/чзс", "Запах  баллы", "Мутность мг/л", "Цветность град",
            "Окисл перм   О2 мг/л", "рН", "Жесткость, мг-экв/л", "Минер-ция, мг/л", "Fe, мг/л", "Mn, мг/л", "Хлориды мг/л",
            "Сульф мг/л", "Аммиак  мг/л", "Источник (С, Р,О)", "КОЭ", "Шел-ть мг/л", "B        мг/л", "Br           мг/л",
            "Li        мг/л", "Ba      мг/л", "Si       мг/л"};
    Object[][] data =   {{null}};

    private         JTable      plantsTable;
    private         JPanel      plantsTablePane;
    private         JButton     findButton;
    private JButton addBlankRowButton;
    private JButton uploadToDBButton;
    private JButton updateFromDBButton;

    private double deltaTurbidity = 1; //delta мутности
    private double deltaChroma = 10; //delta цветности
    private double deltaOxidation = 2; //delta окисления
    private double deltaHardness = 3; //delta жесткости
    private double deltaFe = 2; //delta Fe
    private double deltaMn = 1; //delta Mn

    private double Turbidity = 0;
    private double Chroma = 0;
    private double Oxidation = 0;
    private double Hardness = 0;
    private double Fe = 0;
    private double Mn = 0;

    public PlantsTable() {
        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionFind("Мутность мг/л", Turbidity, deltaTurbidity);
                actionFind("Цветность град", Chroma, deltaChroma);
                actionFind("Окисл перм О2 мг/л", Oxidation, deltaOxidation);
                actionFind("Жесткость, мг-экв/л", Hardness, deltaHardness);
                actionFind("Fe, мг/л", Fe, deltaFe);
                actionFind("Mn, мг/л", Mn, deltaMn);
            }
        });
        addBlankRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableModify.addBlankRow(plantsTable);
            }
        });
        uploadToDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i=0; i<plantsTable.getRowCount(); i++){
                    uploadRowToDb(i);
                }
                TableModify.clearTable(plantsTable);
                fillTableFromDb();
            }
        });
        updateFromDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableModify.clearTable(plantsTable);
                fillTableFromDb();
            }
        });
    }

    private void uploadRowToDb(int i) {
        String[] values = new String[plantsTable.getColumnCount()];
        for (int j=0; j<plantsTable.getColumnCount(); j++){
            values[j] = (String) plantsTable.getValueAt(i, j);
        }
        if (values[1] == null || values[1] == "") {
            return;
        }
        PlantRecord plantRecord = new PlantRecord();
        plantRecord.setValuesFromStrings(values);
        int result = plantRecord.putRecordInDb(false);
        if (result == -1){
            int chose = JOptionPane.showConfirmDialog(plantsTablePane, "В БД запись о приборе " + values[1]
                        + " уже существует. Обновить?", "Запись уже существует", JOptionPane.YES_NO_OPTION);
            if (chose == JOptionPane.YES_OPTION){
                plantRecord.putRecordInDb(true);
            }
        }
    }

    public void actionFind(String strName, double value, double deltaValue){
        for(int i = 0 ; i < plantsTable.getColumnCount(); i ++)  {
            String columnName = plantsTable.getColumnName(i);
            if(columnName.equals(strName)) {
                try{

                    String cellValue = plantsTable.getValueAt(0,i).toString();
                    String cellDeltaValue = plantsTable.getValueAt(1,i).toString();
                    value = getDouble(value, cellValue);
                    deltaValue = getDouble(deltaValue,cellDeltaValue);

                }catch (Exception ex){
                    //JOptionPane.showMessageDialog(null,"Неверно введены параметры исходной воды","Error",2);
                }

                for(int j = 2; j < plantsTable.getRowCount(); j++){
                    try{
                        Scanner sc = new Scanner(plantsTable.getValueAt(j,i).toString());
                        //System.out.println((value + deltaValue) + " " + (value - deltaValue));
                        if(sc.nextDouble() > value + deltaValue || sc.nextDouble() < value - deltaValue){
                            TableModify.removeRow(plantsTable,j);
                        }
                    }catch (Exception ex){
                        //JOptionPane.showMessageDialog(null,"Неверно введены параметры установки" + j,"Error",2);
                    }
                }
            }
        }
    }

    private double getDouble(double value, String strValue) {
        Scanner sc = new Scanner(strValue);

        if(sc.hasNext()){
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
        plantsTable = TableModify.initTable(columnNames);
        fillTableFromDb();
    }

    private void fillTableFromDb() {
        PlantRecord plantRecord = new PlantRecord();
        ResultSet resultSet = DataBaseInteraction.getAllTable("PLANT");
        try {
            while (resultSet.next()){
                plantRecord.setValuesFromResultSet(resultSet);
                TableModify.addRow(plantsTable, plantRecord.getValues());
            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
