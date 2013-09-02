package additionalFunc;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dh
 * Date: 18.05.13
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
public class EditDocx {
    public void editDocx(String newStr, int num) {
        try {
            String path = new File("").getAbsolutePath() + "\\aqms.docx";
            FileInputStream fis = new FileInputStream(path);
            XWPFDocument doc = new XWPFDocument(fis);
            XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
            XWPFStyles styles = doc.getStyles();


            String textDocx = extractor.getText();
            int startInd = textDocx.indexOf("/" + num + "^");
            int stopInd = textDocx.indexOf("^" + num + "/") + 3;

            if (startInd != -1 && stopInd != -1) {
                String editStr = textDocx.substring(startInd, stopInd);
                textDocx = textDocx.replace(editStr, newStr);

            }


            fis.close();
            //XWPFDocument outDoc  = new XWPFDocument();


            FileOutputStream outStream = new FileOutputStream(new File("").getAbsolutePath() + "\\aqms2.docx");

            doc.write(outStream);
            outStream.close();

        } catch (FileNotFoundException e) {
        } catch (IOException ee) {
        }
    }

    public void readAndSaveDocx() {
        try {
            String path = new File("").getAbsolutePath() + "\\";
            XWPFDocument doc = new XWPFDocument(new FileInputStream(path + "aqms.docx"));
            List<XWPFParagraph> list = doc.getParagraphs();
            XWPFDocument tmp = new XWPFDocument();
            tmp.createStyles();
            String fileName = "test1";
            boolean isNew = true;

            for (XWPFParagraph p : list) {
                XWPFStyles style = tmp.getStyles();
                if (p.getStyleID() != null && !style.styleExist(p.getStyleID())) {
                    style.addStyle(doc.getStyles().getStyle(p.getStyle()));
                }

                XWPFParagraph tmpParagraph = tmp.createParagraph();
                tmpParagraph.setStyle(p.getStyle());
                tmpParagraph.setAlignment(p.getAlignment());
                isNew = false;

                for (XWPFRun r : p.getRuns()) {

                    XWPFRun tmpRun = tmpParagraph.createRun();

                    tmpRun.setTextPosition(r.getTextPosition());
                    // важно использовать именно метод toString() поскольку
                    // этот метод сохраняет возможные символы "\n", которые getText обрезает
                    tmpRun.setText(r.toString());

                    tmpRun.setBold(r.isBold());
                    tmpRun.setItalic(r.isItalic());
                    tmpRun.setStrike(r.isStrike());

                    tmpRun.setFontFamily(r.getFontFamily());

                    tmpRun.setFontSize(r.getFontSize());
                    tmpRun.setSubscript(r.getSubscript());
                    tmpRun.setUnderline(r.getUnderline());
                    // метод isPageBreak всегда возвращает false,
                    // независимо от того, содержится ли разрыв страницы в параграфе или нет
                    // так что используем грязный хак
                    //????????????????//p.setPageBreak(r.getCTR().toString().contains("<w:br w:type=\"page\"/>"));
                }

                if (p.isPageBreak()) {
                    try {
                        isNew = true;
                        tmp.write(new FileOutputStream(path + fileName + ".doc"));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    tmp = new XWPFDocument();
                    // требуется версия POI  >= 3.8 чтобы сделать это
                    tmp.createStyles();
                }
            }


            try {
                // сохраним последний кусок в файл
                tmp.write(new FileOutputStream(path + fileName + ".doc"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
        } catch (IOException ee) {
        }
    }
}
