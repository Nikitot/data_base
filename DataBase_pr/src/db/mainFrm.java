package db;

import db.FilteringSystems.FilteringSystemsFrame;
import db.LightActions.LaTbFrame;
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
    private LaTbFrame laFrame;


    public mainFrm() {

    }

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

        tabbedPane = new JTabbedPane();

        tabbedPane.add("ИСХДАН", inputTable.getInputTablePane());
        tabbedPane.add("БВПУ МВПУ", plantsTable.getPlantsTablePane());
        tabbedPane.add("СФУФМ", fsFrame.getFsPane());
        tabbedPane.add("ОСВЕТЛ",laFrame.getLaPane());
        // TODO: place custom component creation code here
    }
}
