package db.LightActions.LightAction;

import additionalFunc.TableModify;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 28.03.13
 * Time: 23:54
 * To change this template use File | Settings | File Templates.
 */
public class LightActionFrame {
    private static  String[]    columnNames= {"Технологическая задача", "Удаляемые вещества", "Методы решения", "Применяемое  оборудование", "Используемые  реагенты и материалы", "Используемые фильтрующие среды"};

    Object[][] data =   {{null, null, null, null, null, null}};
    private JPanel lightActionPane;
    private JTable lightActionTable;


    public JPanel getLightActionPane() {
        return lightActionPane;
    }

    public JTable getLightActionTable() {
        return lightActionTable;
    }

    private void createUIComponents() {
        lightActionTable = TableModify.initTable(data, columnNames);
        // TODO: place custom component creation code here
    }

}
