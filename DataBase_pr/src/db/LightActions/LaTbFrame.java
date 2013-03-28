package db.LightActions;

import db.FilteringSystems.GridFilters.GridFiltersFrame;
import db.LightActions.LightAction.*;
import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Dh
 * Date: 29.03.13
 * Time: 1:40
 * To change this template use File | Settings | File Templates.
 */
public class LaTbFrame {
    private JTabbedPane laTabbedPane;
    private JPanel laPane;
    //private GridFiltersFrame gfFrame;
    private  LightActionFrame laFrame;

    private void createUIComponents() {
        laTabbedPane = new JTabbedPane();

        laFrame = new LightActionFrame();

        laTabbedPane.add(laFrame.getLightActionPane(), "ТЗиР");
        // TODO: place custom component creation code here
    }

    public JPanel getLaPane() {
        return laPane;
    }
}
