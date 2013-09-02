package db.MainTabs.LightActions;

import additionalFunc.TableModify;
import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 28.03.13
 * Time: 23:54
 * To change this template use File | Settings | File Templates.
 */
public class TechTaskDecisionFrame {
    private static  String[]    columnNames= {"Технологическая задача", "Удаляемые вещества", "Методы решения", "Применяемое  оборудование", "Используемые  реагенты и материалы", "Используемые фильтрующие среды"};

    Object[][] data =   {{null, null, null, null, null, null}};
    private JPanel tchTskDciPane;
    private JTable tchTskDciTable;


    public JPanel getTchTskDciPane() {
        return tchTskDciPane;
    }

    public JTable getTchTskDciTable() {
        return tchTskDciTable;
    }

    private void createUIComponents() {
        tchTskDciTable = TableModify.initTable(data, columnNames);
        // TODO: place custom component creation code here
    }

}
