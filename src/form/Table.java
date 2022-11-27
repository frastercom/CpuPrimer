package form;

import json.object.CPU;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Table extends JPanel {

    private final String HEAD[] = {"Идентификатор","Наименование", "Модель", "Частота", "Описание"};
    private JTable table;
    public Table()
    {
        DefaultTableModel tableModel = new DefaultTableModel(null, HEAD);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    public void clear()
    {
        DefaultTableModel tableModel = new DefaultTableModel(null, HEAD);
        table.setModel(tableModel);
    }

    public void setCpu(CPU cpu)
    {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        String str[] = {cpu.getId().toString(), cpu.getName(), cpu.getModel(), cpu.getGgz(), cpu.getDescription()};
        tableModel.addRow(str);
    }
    public JTable getTable()
    {
        return table;
    }

}