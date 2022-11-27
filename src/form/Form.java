package form;

import json.object.CPU;
import service.Service;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

/**
 * Робата с формой, создание компонентов
 */
public class Form {

    public static void form() {
        JFrame frame = new JFrame("Пример работы с ЦПУ");
        frame.setSize(650, 650);
        addComponent(frame);
        frame.show();
    }

    private static void addComponent(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        // По умолчанию натуральная высота, максимальная ширина
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridy = 0;

        JLabel label = new JLabel("Наименование");
        constraints.gridx = 0;
        panel.add(label, constraints);

        label = new JLabel("Модель");
        constraints.gridx = 1;
        panel.add(label, constraints);

        label = new JLabel("Частота");
        constraints.gridx = 2;
        panel.add(label, constraints);

        label = new JLabel("Описание");
        constraints.gridx = 3;
        panel.add(label, constraints);

        constraints.gridy = 1;

        JTextField fieldName = new JTextField();
        constraints.gridx = 0;
        panel.add(fieldName, constraints);

        JTextField fieldModel = new JTextField();
        constraints.gridx = 1;
        panel.add(fieldModel, constraints);

        JTextField fieldGgz = new JTextField();
        constraints.gridx = 2;
        panel.add(fieldGgz, constraints);

        JTextField fieldDescription = new JTextField();
        constraints.gridx = 3;
        panel.add(fieldDescription, constraints);

        JButton button = new JButton("Добавить в базу");
        constraints.gridx = 0;
        constraints.gridy = 2;

        panel.add(button, constraints);

        Table tablePanel = new Table();
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridheight = 4;
        constraints.gridwidth = 5;
        panel.add(tablePanel, constraints);

        JButton buttonAdd = new JButton("Обновить список");
        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tablePanel.clear();
                    Service.getCpu().forEach(cpu -> tablePanel.setCpu(cpu));
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(frame,
                            "Ошибка подключения",
                            "Внимание ошибка!",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        panel.add(buttonAdd, constraints);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Service.sendAdd(new CPU(fieldModel.getText(), fieldName.getText(), fieldGgz.getText(), fieldDescription.getText()));
                    JOptionPane.showMessageDialog(frame,
                            "Объект добавлен");
                    tablePanel.clear();
                    Service.getCpu().forEach(cpu -> tablePanel.setCpu(cpu));
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(frame,
                            "Объект не добавлен",
                            "Внимание ошибка!",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        JButton buttonDelete = new JButton("Удалить");
        constraints.gridx = 1;

        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int row = tablePanel.getTable().getSelectedRow();
                    if (row < 0) {
                        JOptionPane.showMessageDialog(frame,
                                "Объект не выбран");
                    }
                    Service.deleteCpu(tablePanel.getTable().getModel().getValueAt(row, 0).toString());
                    tablePanel.clear();
                    Service.getCpu().forEach(cpu -> tablePanel.setCpu(cpu));
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(frame,
                            "Ошибка удаления",
                            "Внимание ошибка!",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        panel.add(buttonDelete, constraints);


        frame.add(panel);
    }
}
