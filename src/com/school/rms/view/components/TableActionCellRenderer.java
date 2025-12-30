package com.school.rms.view.components;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import com.school.rms.util.Theme;

public class TableActionCellRenderer extends JPanel implements TableCellRenderer {
    private JButton cmdMenu;

    public TableActionCellRenderer() {
        setOpaque(false); // Transparent background to show table selection
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        // 3-dot menu button
        cmdMenu = new TableActionButton("⋮"); // Vertical ellipsis
        cmdMenu.setForeground(Theme.TEXT_SECONDARY);

        add(cmdMenu);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        if (isSelected) {
            // Adjust colors if selected if needed
        }
        return this;
    }

    // Inner class for simple styling
    private class TableActionButton extends JButton {
        public TableActionButton(String text) {
            super(text);
            setFont(Theme.FONT_TITLE); // Larger font for icon
            setBorderPainted(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setOpaque(false);
            setPreferredSize(new Dimension(30, 30));
        }
    }
}
