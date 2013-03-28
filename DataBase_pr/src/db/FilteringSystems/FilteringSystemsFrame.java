package db.FilteringSystems;

import db.FilteringSystems.GridFilters.GridFiltersFrame;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 28.03.13
 * Time: 23:48
 * To change this template use File | Settings | File Templates.
 */
public class FilteringSystemsFrame {
    private JTabbedPane fsTabbedPane;
    private JPanel fsPane;
    private GridFiltersFrame gfFrame;

    private void createUIComponents() {
        fsTabbedPane = new JTabbedPane();

        gfFrame = new GridFiltersFrame();

        fsTabbedPane.add(gfFrame.getGridFiltersPane(), "Системы для удаления из воды механических взвесей и примесей " +
                                                        "(сетчатые и картриджные фильтры)");
        // TODO: place custom component creation code here
    }

    public JPanel getFsPane() {
        return fsPane;
    }
}
