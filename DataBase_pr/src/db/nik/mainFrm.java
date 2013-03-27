package db.nik;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Dh
 * Date: 26.03.13
 * Time: 23:35
 * To change this template use File | Settings | File Templates.
 */


public class mainFrm extends JFrame{

    //Таблица исходных данных
    private JTabbedPane tabbedInputData;
    protected JScrollPane scPnInputData;

    public mainFrm() throws HeadlessException {
        initData();

    }



    public static void main(String args[]){

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new mainFrm().setVisible(true);
            }
        });

    }

    @SuppressWarnings("unchecked")
    private void initData(){

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tabbedInputData, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tabbedInputData, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }




    //Функция добавления строки в конец
    private void addRow(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{"", "", ""});
        table.setModel(model);
    }
    //Функция удаления i строки
    private void removeRow(JTable table, int i) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(i);
        table.setModel(model);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
