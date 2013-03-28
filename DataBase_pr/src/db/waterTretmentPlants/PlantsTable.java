package db.waterTretmentPlants;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 26.03.13
 * Time: 18:35
 * To change this template use File | Settings | File Templates.
 */

public class PlantsTable {
    private static  String[]    columnNames= {"Марка",  "Зав. №", "Тип", "", "Запах  баллы", "Мутность мг/л", "Цветность град",
            "Окисл перм   О2 мг/л", "рН", "Жесткость, мг-экв/л", "Минер-ция, мг/л", "Fe, мг/л", "Mn, мг/л", "Хлориды мг/л",
            "Сульф мг/л", "Аммиак  мг/л", "Источник (С, Р,О)", "КОЭ", "Шел-ть мг/л", "B        мг/л", "Br           мг/л",
            "Li        мг/л", "Ba      мг/л", "Si       мг/л"};
    private         JTable      plantsTable;
    private         JPanel      plantsTablePane;

    private static JTable initJTable (){
        Object[][] data = null;
        ArrayList<String> row = new ArrayList<String>();
        ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();

        try {
            FileInputStream fin = new FileInputStream("waterTretmentPlantsInitValues.txt");
            String tmp = "";
            char ch;
            boolean continueIs = true;
            while(continueIs) {
                ch = (char)fin.read();
                switch (ch) {
                    case (char)-1:
                        continueIs = false;
                        if (row.size()!=0){
                            rows.add(row);
                        }
                    case '\t':
                        row.add(tmp);
                        tmp = "";
                        break;
                    case '\n':
                        rows.add(row);
                        row = new ArrayList<String>();
                        break;
                    default:
                        tmp += ch;
                        break;
                }
            }

            data = new Object[rows.size()][rows.get(0).size()];
            for (int i = 0; i<rows.get(0).size(); i++)
                for (int j=0; j<rows.size(); j++){
                    data [j][i] = rows.get(j).get(i);
                }



        } catch (FileNotFoundException e) {
            System.err.println("Can't open initial file");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.err.println("Can't read from file");
            e.printStackTrace();
            return null;
        }
       return new JTable(data, columnNames);

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
        plantsTable = initJTable();
    }
}
