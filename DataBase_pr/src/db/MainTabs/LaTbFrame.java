package db.MainTabs;

import db.MainTabs.LightActions.*;

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
    private TechTaskDecisionFrame ttdFrame;
    private LableFirstWaterFrame lfwFrame;
    private LFWFrameForTS lfwftsFrame;
    private FiltersParametrs fpFrame;
    private FltrsPrmsFOV fpfovFrame;
    //private DUTubingNomogramm dutnFrame;


    private void createUIComponents() {
        laTabbedPane = new JTabbedPane();

        ttdFrame = new TechTaskDecisionFrame();
        lfwFrame = new LableFirstWaterFrame();
        lfwftsFrame = new LFWFrameForTS();
        fpFrame = new FiltersParametrs();
        fpfovFrame = new FltrsPrmsFOV();
        //dutnFrame = new DUTubingNomogramm();

        laTabbedPane.add(ttdFrame.getTchTskDciPane(), "ТЗиР");
        laTabbedPane.add(lfwFrame.getLfwPane(), "ПИВ");
        laTabbedPane.add(lfwftsFrame.getPane(), "ПИВ по ТЗ Заказчика");
        laTabbedPane.add(fpFrame.getPane(),"Параметры фильтров");
        laTabbedPane.add(fpfovFrame.getPane(),"Параметры фильтров ФОВ");
        //laTabbedPane.add(dutnFrame.getPane(),"Номограмма ДУ труб");



        // TODO: place custom component creation code here
    }

    public JPanel getLaPane() {
        return laPane;
    }
}
