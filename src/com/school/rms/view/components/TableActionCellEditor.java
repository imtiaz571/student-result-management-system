package com.school.rms.view.components;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import com.school.rms.util.Theme;

public class TableActionCellEditor extends DefaultCellEditor {
    private JPanel panel;
    private JButton cmdMenu;
    private JPopupMenu popupMenu;
    private ActionListener listener;

    public interface ActionListener {
        void onEdit(int row);

        void onDelete(int row);
    }

    public TableActionCellEditor() {
        super(new JCheckBox());

        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        panel.setOpaque(false);

        cmdMenu = new TableActionButton("⋮"); 

        
        popupMenu = new JPopupMenu();
        JMenuItem editItem = new JMenuItem("Edit");
        JMenuItem deleteItem = new JMenuItem("Delete");

        popupMenu.add(editItem);
        popupMenu.add(deleteItem);

        cmdMenu.addActionListener(e -> {
            popupMenu.show(cmdMenu, cmdMenu.getWidth() / 2, cmdMenu.getHeight() / 2);
        });

        editItem.addActionListener(e -> {
            if (listener != null) {
                JTable table = (JTable) cmdMenu.getParent().getParent();
                int row = table.getEditingRow();
                listener.onEdit(row);
            }
            fireEditingStopped();
        });

        deleteItem.addActionListener(e -> {
            if (listener != null) {
                JTable table = (JTable) cmdMenu.getParent().getParent();
                int row = table.getEditingRow();
                listener.onDelete(row);
            }
            fireEditingStopped();
        });

        panel.add(cmdMenu);
    }

    public void setActionListener(ActionListener listener) {
        this.listener = listener;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return panel;
    }

    private class TableActionButton extends JButton {
        public TableActionButton(String text) {
            super(text);
            setFont(Theme.FONT_TITLE);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setOpaque(false);
            setPreferredSize(new Dimension(30, 30));
            setForeground(Theme.TEXT_SECONDARY);
        }
    }
}
