package db;

import db.FilteringSystems.FilteringSystemsFrame;
import db.inputData.InputTable;
import db.waterTretmentPlants.PlantsTable;

import javax.swing.*;


/**
 * Created with IntelliJ IDEA.
 * User: Dh
 * Date: 26.03.13
 * Time: 23:35
 * To change this template use File | Settings | File Templates.
 */


public class mainFrm{

    //protected JScrollPane scPnInputData;
    private JPanel mainPane;
    private JTabbedPane tabbedPane;
    private PlantsTable plantsTable;
    private InputTable inputTable;
    private FilteringSystemsFrame fsFrame;

    public mainFrm() {

    }

    public static void main(String args[]){
        JFrame frame = new JFrame("PlantsTable");
        frame.setContentPane(new mainFrm().mainPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    /*
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
    */

    private void createUIComponents() {
        plantsTable = new PlantsTable();
        inputTable = new InputTable();
        fsFrame = new FilteringSystemsFrame();

        tabbedPane = new JTabbedPane();

        tabbedPane.add("ИСХДАН", inputTable.getInputTablePane());
        tabbedPane.add("БВПУ МВПУ", plantsTable.getPlantsTablePane());
        tabbedPane.add("СФУФМ", fsFrame.getFsPane());
        // TODO: place custom component creation code here
    }
}
