package db.MainTabs;

import additionalFunc.TableModify;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
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
    private         JButton     Find;

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
        Find.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionFind("Мутность мг/л");
                actionFind("Цветность град");
                actionFind("Окисл перм   О2 мг/л");
                actionFind("Жесткость, мг-экв/л");
                actionFind("Fe, мг/л");
                actionFind("Mn, мг/л");
            }
        });
    }

    public void actionFind(String strName){
        for(int i = 0 ; i < plantsTable.getColumnCount(); i ++)  {
            String columnName = plantsTable.getColumnName(i);
            if(columnName.equals(strName)) {
                try{

                    String cellValue = plantsTable.getValueAt(0,i).toString();
                    Scanner sc = new Scanner(cellValue);

                    if(sc.hasNext()){
                        Turbidity = sc.nextDouble();
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Неверно введены параметры исходной воды","Error",2);
                }

                for(int j = 1; j < plantsTable.getRowCount(); j++){
                try{
                    Scanner sc = new Scanner(plantsTable.getValueAt(j,i).toString());
                    if(sc.nextDouble() > Turbidity + deltaTurbidity || sc.nextDouble() < Turbidity - deltaTurbidity){
                        TableModify.removeRow(plantsTable,j);
                    }
                }catch (Exception ex){
                    //JOptionPane.showMessageDialog(null,"Неверно введены параметры установки" + j,"Error",2);
                }
                }


            }
        }
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
