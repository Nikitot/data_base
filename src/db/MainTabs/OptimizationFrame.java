package db.MainTabs;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Фомин
 * Date: 19.10.13
 * Time: 19:56
 * To change this template use File | Settings | File Templates.
 */
public class OptimizationFrame {
    private JTabbedPane tabbedPane;
    private JPanel panel;

    private void createUIComponents() {
        tabbedPane = new JTabbedPane();

        tabbedPane.add("Вход", new db.MainTabs.BwpuOptimization.InputTable().getPanel());
    }

    public JPanel getPanel() {
        return panel;
    }
}
