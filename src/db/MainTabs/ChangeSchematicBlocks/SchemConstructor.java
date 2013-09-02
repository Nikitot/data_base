package db.MainTabs.ChangeSchematicBlocks;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Dh
 * Date: 10.07.13
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
public class SchemConstructor {

    private JDialog chooseDialog = new JDialog();

    private JPanel drawSchemPanel;
    private JPanel mainPanel;
    private JPanel choosePanel = new JPanel();

    private JComboBox<String> chooseComboBox = new JComboBox<String>();
    private JButton chooseOKButon = new JButton();

    private JLabel typeLabel = new JLabel();
    private JLabel doneWork1;
    private JLabel doneWork2;
    private JLabel doneWork3;
    private JLabel doneWork4;
    private JLabel doneWork5;
    private JLabel doneWork6;
    private JLabel doneWork7;
    private JLabel doneWork8;
    private JLabel doneWork9;
    private JLabel doneWorks[] = {doneWork1, doneWork2, doneWork3, doneWork4, doneWork5, doneWork6, doneWork7, doneWork8, doneWork9};

    BufferedImage imageScheme = new BufferedImage(3000, 1500, BufferedImage.TYPE_INT_RGB);
    String blockTypeName[] = {"Вход", "ФСС", "НД", "Фильтры", "УОВ", "Контейнеры", "Осадок", "РЧВ", "Себестоимость"};
    int blockCoords[][] = {
            {0, 408, 588, 147, 20, 0, 1270, 737, 0},
            {0, 185, 200, 490, 337, 0, 40, 484, 0}};
    int blockTypeCount = 0;

    int blockSubtypeCount[] = new int[9];


    /**
     * image - изображение размера текущего блока(берем последний блок), т.к. все блоки одного типа имеют один размер
     * clearImage - пустое изображение размера текущего блока для очистки (когда вибираем -)
     */
    public SchemConstructor() {
        initChooseDialog();
        clearImage(0, imageScheme.getWidth(), 0, imageScheme.getHeight(), imageScheme);
        initBlocks();


        for (int i = 0; i < blockTypeName.length; i++) {
            if (i != blockTypeName.length - 1)
                doneWorks[i].setText(blockTypeName[i] + " >  ");
            else doneWorks[i].setText(blockTypeName[i]);
        }
        chooseOKButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String blockSubtypeName = chooseComboBox.getSelectedItem().toString();

                    int x = blockCoords[0][blockTypeCount];
                    int y = blockCoords[1][blockTypeCount];
                    if (blockTypeCount == 2) {
                        drawInpToND(!blockSubtypeName.equals("-"));
                    }
                    if (blockTypeCount == 3) {
                        drawNDToFilters(!blockSubtypeName.equals("-"));
                    }

                    BufferedImage image = ImageIO.read(new File("schemes\\" + blockTypeCount + "-" + blockSubtypeName + ".png"));


                    addBlockToScheme(image, x, y);

                    blockSubtypeCount[blockTypeCount] = chooseComboBox.getSelectedIndex();

                    if (blockSubtypeName.equals("-")) {
                        doneWorks[blockTypeCount].setForeground(Color.black);
                    } else {
                        doneWorks[blockTypeCount].setForeground(new Color(255, 190, 0));
                    }

                } catch (IOException el) {
                    JOptionPane.showMessageDialog(null, "Отсутствует файл с изображением" + " schemes\\" + blockTypeCount + "--.png", "Error", 0);
                }
            }
        });

        drawSchemPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                for (int i = 0; i < blockSubtypeCount.length; i++) {
                    if (i != 5 && i != 6 && i != 8) {
                        try {
                            BufferedImage image = ImageIO.read(new File("schemes\\" + i + "--.png"));
                            int x = e.getX();
                            int y = e.getY();
                            int x1 = blockCoords[0][i];
                            int y1 = blockCoords[1][i];
                            int x2 = x1 + image.getWidth();
                            int y2 = y1 + image.getHeight();

                            if (x >= x1 && x <= x2) {
                                if (y >= y1 && y <= y2) {
                                    blockTypeCount = i;

                                    typeLabel.setText(blockTypeName[i]);
                                    setSubtypes(i);
                                    chooseComboBox.setSelectedIndex(blockSubtypeCount[i]);
                                    chooseDialog.setVisible(true);
                                }
                            }
                        } catch (IOException e1) {
                            JOptionPane.showMessageDialog(null, "Отсутствует файл с изображением" + "schemes\\" + i + "--.png", "Error", 0);
                        }
                    }
                }
            }
        });
    }

    private void initBlocks() {
        for (int i = 0; i < blockSubtypeCount.length; i++) {
            try {
                if (i != 5 && i != 6 && i != 8) {
                    BufferedImage image = ImageIO.read(new File("schemes\\" + i + "--.png"));
                    int x = blockCoords[0][i];
                    int y = blockCoords[1][i];
                    imageScheme.getGraphics().drawImage(image, x, y, null, null);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Отсутствует файл с изображением" + "schemes\\" + i + "--.png", "Error", 0);
            }

        }

    }

    private void initChooseDialog() {
        int w = 200;
        int h = 100;
        chooseDialog.add(choosePanel);
        choosePanel.setSize(w, h);
        chooseDialog.setSize(w, h);
        choosePanel.add(typeLabel);
        typeLabel.setText("");
        choosePanel.add(chooseComboBox);
        choosePanel.add(chooseOKButon);
        chooseOKButon.setText("OK");

        chooseDialog.move(100, 100);

    }

    private void drawNDToFilters(boolean bool) {
        int lineCoords[][] = {{1201, 275}, {1202, 465}};
        addBlockToScheme(drawStraightLine(lineCoords, bool), lineCoords[0][0], lineCoords[0][1]);

        lineCoords = new int[][]{{146, 464}, {1202, 465}};
        addBlockToScheme(drawStraightLine(lineCoords, bool), lineCoords[0][0], lineCoords[0][1]);

        lineCoords = new int[][]{{146, 464}, {147, 545}};
        addBlockToScheme(drawStraightLine(lineCoords, bool), lineCoords[0][0], lineCoords[0][1]);
    }

    private void drawInpToND(boolean bool) {
        int lineCoords[][] = {{408, 23}, {921, 24}};
        addBlockToScheme(drawStraightLine(lineCoords, bool), lineCoords[0][0], lineCoords[0][1]);

        lineCoords = new int[][]{{921, 23}, {922, 257}};
        addBlockToScheme(drawStraightLine(lineCoords, bool), lineCoords[0][0], lineCoords[0][1]);
    }

    private void setSubtypes(int i) {
        chooseComboBox.removeAllItems();
        if (i == 0) {
            setStringChooseCB(new String[]{"-", "вход"});
        }
        if (i == 1) {
            setStringChooseCB(new String[]{"-", "БВПУ-1--5-К", "БВПУ-10-25-К"});
        }
        if (i == 2) {
            setStringChooseCB(new String[]{"-", "БВПУ-1--5-К", "БВПУ-10-К", "БВПУ-25-К", "БВПУ-15-К"});
        }
        if (i == 3) {
            setStringChooseCB(new String[]{"-", "БВПУ-1-К", "БВПУ-2,5-К", "БВПУ-5-К", "БВПУ-10-К", "БВПУ-15-К", "БВПУ-25-К"});
        }
        if (i == 4) {
            setStringChooseCB(new String[]{"-", "УОВ"});
        }
        if (i == 6) {
            setStringChooseCB(new String[]{"-", "осадок"});
        }
        if (i == 7) {
            setStringChooseCB(new String[]{"-", "БВПУ-1-2,5-5-К", "БВПУ-10-К", "БВПУ-15-К", "БВПУ-25-К"});
        }

    }

    private void clearImage(int x1, int y1, int x2, int y2, BufferedImage image) {
        for (int i = x1; i < y1; i++) {
            for (int j = x2; j < y2; j++) {
                image.setRGB(i, j, Color.white.getRGB());
            }
        }
    }

    private BufferedImage drawStraightLine(int lineCoords[][], boolean bool) {
        if (bool) {
            return new BufferedImage(lineCoords[1][0] - lineCoords[0][0], lineCoords[1][1] - lineCoords[0][1], BufferedImage.TYPE_INT_RGB);
        } else {
            BufferedImage image = new BufferedImage(lineCoords[1][0] - lineCoords[0][0], lineCoords[1][1] - lineCoords[0][1], BufferedImage.TYPE_INT_RGB);
            clearImage(0, image.getWidth(), 0, image.getHeight(), image);
            return image;
        }

    }

    private void addBlockToScheme(BufferedImage image, int x, int y) {
        imageScheme.getGraphics().drawImage(image, x, y, null, null);
        drawSchemPanel.getGraphics().drawImage(image, x, y, null, null);
        chooseDialog.setVisible(false);
    }

    private void setStringChooseCB(String[] items) {
        for (String item : items) {
            chooseComboBox.addItem(item);
        }
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    private void createUIComponents() {
        drawSchemPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imageScheme, 0, 0, null, null);
            }
        };
        mainPanel = new JPanel();

    }
}
