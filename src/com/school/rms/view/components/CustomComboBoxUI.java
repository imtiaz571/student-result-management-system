package com.school.rms.view.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import com.school.rms.util.Theme;

public class CustomComboBoxUI extends BasicComboBoxUI {

    @Override
    protected JButton createArrowButton() {
        
        JButton btn = new BasicArrowButton(BasicArrowButton.SOUTH, Color.WHITE, Color.WHITE, Theme.TEXT_SECONDARY,
                Color.WHITE) {
            @Override
            public void paint(Graphics g) {
                
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth();
                int h = getHeight();
                g2.setColor(Theme.TEXT_SECONDARY);
                
                g2.drawLine(w / 3, h / 2 - 2, w / 2, h / 2 + 2);
                g2.drawLine(w / 2, h / 2 + 2, 2 * w / 3, h / 2 - 2);
            }
        };
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        return btn;
    }

    @Override
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
        
    }

    
    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), Theme.CORNER_RADIUS, Theme.CORNER_RADIUS);

        
        g2.setColor(Theme.BORDER_GRAY);
        g2.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, Theme.CORNER_RADIUS, Theme.CORNER_RADIUS);

        super.paint(g, c);
    }
}
