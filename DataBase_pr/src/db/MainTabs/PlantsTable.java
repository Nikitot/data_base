package db.MainTabs;

import additionalFunc.TableModify;

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
    private static  String[]    columnNames= {"Марка",  "Зав. №", "Тип", "Произв-ть м3/чзс", "Запах  баллы", "Мутность мг/л", "Цветность град",
            "Окисл перм   О2 мг/л", "рН", "Жесткость, мг-экв/л", "Минер-ция, мг/л", "Fe, мг/л", "Mn, мг/л", "Хлориды мг/л",
            "Сульф мг/л", "Аммиак  мг/л", "Источник (С, Р,О)", "КОЭ", "Шел-ть мг/л", "B        мг/л", "Br           мг/л",
            "Li        мг/л", "Ba      мг/л", "Si       мг/л"};
    Object[][] data =   {{null, null, null, null, null, null, null, null, null, null, null, null,
                          null, null, null, null, null, null, null, null, null, null, null}};

    private         JTable      plantsTable;
    private         JPanel      plantsTablePane;

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
