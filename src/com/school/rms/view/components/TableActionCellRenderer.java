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
        setOpaque(false); 
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        
        cmdMenu = new TableActionButton("⋮"); 
        cmdMenu.setForeground(Theme.TEXT_SECONDARY);

        add(cmdMenu);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        if (isSelected) {
            
        }
        return this;
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
        }
    }
}
