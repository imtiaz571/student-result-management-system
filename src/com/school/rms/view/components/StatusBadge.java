package com.school.rms.view.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import com.school.rms.util.Theme;

public class StatusBadge extends JLabel implements TableCellRenderer {

    private Color badgeColor = Theme.BACKGROUND_COLOR;

    public StatusBadge() {
        setOpaque(false); // We paint the background manually
        setForeground(Color.BLACK);
        setFont(Theme.FONT_REGULAR);
        setHorizontalAlignment(JLabel.CENTER);
        setBorder(new EmptyBorder(4, 12, 4, 12)); // Padding for pill shape
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        setText(value != null ? value.toString() : "");

        // Apply colors based on column (for student table)
        // Column 0: Student Name (white)
        // Column 1: Session (light green)
        // Column 2: Batch (yellow)
        // Column 3: Department (light cyan)
        // Column 4: Overall CGPA (light purple)

        switch (column) {
            case 0: // Student Name - white/light gray
                badgeColor = Color.WHITE;
                setForeground(Color.BLACK);
                break;
            case 1: // Session - light green
                badgeColor = new Color(232, 245, 233); // Light green
                setForeground(Color.BLACK);
                break;
            case 2: // Batch - yellow
                badgeColor = new Color(255, 251, 230); // Light yellow
                setForeground(Color.BLACK);
                break;
            case 3: // Department - light cyan
                badgeColor = new Color(224, 247, 250); // Light cyan
                setForeground(Color.BLACK);
                break;
            case 4: // Overall CGPA - light purple
                badgeColor = new Color(237, 231, 246); // Light purple
                setForeground(Color.BLACK);
                break;
            default:
                badgeColor = Theme.BACKGROUND_COLOR;
                setForeground(Theme.TEXT_PRIMARY);
                break;
        }

        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Paint badge background
        g2.setColor(badgeColor);
        // Pill shape: arc width is height of component
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, Theme.CORNER_RADIUS * 2, Theme.CORNER_RADIUS * 2);

        g2.dispose();
        super.paintComponent(g);
    }
}
