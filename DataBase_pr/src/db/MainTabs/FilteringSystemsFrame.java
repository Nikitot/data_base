package db.MainTabs;

import db.MainTabs.FilteringSystems.*;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Vasya
 * Date: 28.03.13
 * Time: 23:48
 * To change this template use File | Settings | File Templates.
 */
public class FilteringSystemsFrame {
    private JTabbedPane      fsTabbedPane;
    private JPanel           fsPane;
    private GridFiltersFrame gfFrame;
    private CWFiltersF76     cwFiltersF76;
    private AutoWashMotorTH  awmthFrame;
    private WMFiltersTH      wmFiltersTH;
    private BBPackageTH      bbPackageTH;
    private BBAdditionalParts bbAdditionalParts;
    private CFFiltersTH      cfFiltersTH;
    private HIFPackageTH     hifPackageTH;
    private XFLOWMembrane xflowMembrane;
    private NORITMembrane    noritMembrane;
    private ERUMembrane      eruMembrane;

    private void createUIComponents() {
        fsTabbedPane = new JTabbedPane();

        gfFrame = new GridFiltersFrame();
        cwFiltersF76 = new CWFiltersF76();
        awmthFrame = new AutoWashMotorTH();
        wmFiltersTH = new WMFiltersTH();
        bbPackageTH = new BBPackageTH();
        bbAdditionalParts = new BBAdditionalParts();
        cfFiltersTH = new CFFiltersTH();
        hifPackageTH = new HIFPackageTH();
        xflowMembrane = new XFLOWMembrane();
        noritMembrane = new NORITMembrane();
        eruMembrane = new ERUMembrane();

        fsTabbedPane.add(gfFrame.getPane(), "Сис-мы фильтров");
        fsTabbedPane.setToolTipTextAt(0, "Системы для удаления из воды механических взвесей и примесей " +
                "(сетчатые и картриджные фильтры)");
        fsTabbedPane.add(cwFiltersF76.getPane(), "Фильтры F76");
        fsTabbedPane.setToolTipTextAt(1, "Фильтры для холодной воды серии F76");
        fsTabbedPane.add(awmthFrame.getPane(), "ТХ приводов промывки");
        fsTabbedPane.setToolTipTextAt(2, "Технические характеристики привода для автоматической " +
                "промывки сетчатых фильтров");
        fsTabbedPane.add(wmFiltersTH.getPane(), "ТХ фильтров ВМ");
        fsTabbedPane.setToolTipTextAt(3, "Технические характеристики сетчатых фильтров серии WM");
        fsTabbedPane.add(bbPackageTH.getPane(), "ТХ корпусов BB");
        fsTabbedPane.setToolTipTextAt(4, "Технические характеристики корпусов фильтров Big Blue");
        fsTabbedPane.add(bbAdditionalParts.getPane(), "BB Принадлежности");
        fsTabbedPane.setToolTipTextAt(5, "Принадлежности для фильтров Big Blue");
        fsTabbedPane.add(cfFiltersTH.getPane(), "ТХ фильтров CF");
        fsTabbedPane.setToolTipTextAt(6, "Технические характеристики мультипатронных фильтров серии CF");
        fsTabbedPane.add(hifPackageTH.getPane(), "ТХ корпусов HIF");
        fsTabbedPane.setToolTipTextAt(7, "Технические характеристики корпусов фильтра HIF");
        fsTabbedPane.add(xflowMembrane.getPane(), "ТХ Meмбран X-Flow");
        fsTabbedPane.setToolTipTextAt(8, "Рабочие характеристики ультрафильтрационных мембран типа X-FLOW XIGATM и X-FLOW AquaflexTM");
        fsTabbedPane.add(noritMembrane.getPane(), "ТХ мембран NORIT");
        fsTabbedPane.setToolTipTextAt(9, "Характеристика ультрафильтрационных мембран фирмы NORIT");
        fsTabbedPane.add(eruMembrane.getPane(), "ТХ мембран ЭРУ");
        fsTabbedPane.setToolTipTextAt(10, "Типы и основные рабочие характеристики ультрафильтрационных  мембранных элементов типа ЭРУ");

        // TODO: place custom component creation code here
    }

    public JPanel getPane() {
        return fsPane;
    }
}
