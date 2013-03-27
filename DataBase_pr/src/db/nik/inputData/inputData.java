package db.nik.inputData;

import db.nik.mainFrm;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Dh
 * Date: 26.03.13
 * Time: 22:24
 * To change this template use File | Settings | File Templates.
 */
public class inputData extends mainFrm {
    private JTable table1;

    public inputData() throws HeadlessException {
        initJTable();
        scPnInputData.setViewportView(table1);
    }

    private void initJTable(){
        table1 = new javax.swing.JTable();
        table1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Наименование показателя", "Единицы измерения", "Исходная вода", "СанПиН 2.1.4.1074-01"}));
        table1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table1.setFocusable(false);

        scPnInputData.setViewportView(table1);
    }

    //public JTable returnJTable (){
    //    return initJTable();
    //}
}
