package db;

import db.MainTabs.ChangeSchematicBlocks.SchemConstructor;
import db.MainTabs.FilteringSystemsFrame;
import db.MainTabs.InputTable;
import db.MainTabs.PlantsTable;
import db.MainTabs.LaTbFrame;

import javax.swing.*;


/**
 * Created with IntelliJ IDEA.
 * User: Dh
 * Date: 26.03.13
 * Time: 23:35
 * To change this template use File | Settings | File Templates.
 */


public class mainFrm{

    private JPanel mainPane;
    private JTabbedPane tabbedPane;
    private PlantsTable plantsTable;
    private InputTable inputTable;
    private FilteringSystemsFrame fsFrame;
    private LaTbFrame laFrame;
    private SchemConstructor scFrame;

    public static void main(String args[]){
        JFrame frame = new JFrame("PlantsTable");
        frame.setContentPane(new mainFrm().mainPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }


    private void createUIComponents() {
        plantsTable = new PlantsTable();
        inputTable = new InputTable();
        fsFrame = new FilteringSystemsFrame();
        laFrame = new LaTbFrame();
        scFrame = new SchemConstructor();

        inputTable.setInputTableInPlantsTable(plantsTable.getInDataTable());
        tabbedPane = new JTabbedPane();

        tabbedPane.add("ИСХДАН", inputTable.getPanel());
        tabbedPane.add("БВПУ МВПУ", plantsTable.getPanel());
        tabbedPane.add("КОН-Р СХЕМ",scFrame.getPanel());
        tabbedPane.add("СФУФМ", fsFrame.getPanel());
        tabbedPane.add("ОСВЕТЛ",laFrame.getPanel());
    }
}
