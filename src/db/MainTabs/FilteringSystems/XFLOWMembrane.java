package db.MainTabs.FilteringSystems;

import additionalFunc.TableModify;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 29.03.13
 * Time: 2:52
 * To change this template use File | Settings | File Templates.
 */
public class XFLOWMembrane {
    private static  String[]    columnNames= {"Параметры", "X-FLOW XIGATM", "X-FLOW AquaflexTM"};
    Object[][] data =   {{null, null, null}};

    private JPanel xFlowMembranePane;
    private JTable xFlowMembraneTable;

    private void createUIComponents() {
        xFlowMembraneTable = TableModify.initTable(data, columnNames);
        // TODO: place custom component creation code here
    }

    public JPanel getPane() {
        return xFlowMembranePane;
    }

    public JTable getTable() {
        return xFlowMembraneTable;
    }
}
