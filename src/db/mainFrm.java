package db;

import db.MainTabs.*;
import db.MainTabs.ChangeSchematicBlocks.SchemConstructor;

import javax.swing.*;

public class mainFrm {
    private JPanel mainPane;
    private JTabbedPane tabbedPane;

    public static void main(String args[]){
        JFrame frame = new JFrame("PlantsTable");
        frame.setContentPane(new mainFrm().mainPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        PlantsTable plantsTable = new PlantsTable();
        InputTable inputTable = new InputTable();
        inputTable.setInputTableInPlantsTable(plantsTable.getInDataTable());

        tabbedPane = new JTabbedPane();
        tabbedPane.add("ИСХДАН"             , inputTable.getPanel());
        tabbedPane.add("БВПУ МВПУ"          , plantsTable.getPanel());
        tabbedPane.add("КОН-Р СХЕМ"         , new SchemConstructor().getPanel());
        tabbedPane.add("СФУФМ"              , new FilteringSystemsFrame().getPanel());
        tabbedPane.add("ОСВЕТЛ"             , new LaTbFrame().getPanel());
        tabbedPane.add("Оптимизация БВПУ"   , new OptimizationFrame().getPanel());
    }
}
