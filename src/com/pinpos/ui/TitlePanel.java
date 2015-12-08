/*
 * TitlePanel.java
 *
 * Created on February 27, 2006, 10:58 PM
 */

package com.pinpos.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.UIManager;

/**
 *
 * @author  MShahriar
 */
public class TitlePanel extends com.pinpos.swing.TransparentPanel {
    //private String title;
    
    /** Creates new form TitlePanel */
    public TitlePanel() {
        initComponents();
//        jSeparator1.setVisible(false);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        lblTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        lblTitle.setFont(getTitleFont());
        lblTitle.setForeground(getTitleColor());
        lblTitle.setText(com.pinpos.POSConstants.TITLE);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(lblTitle, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
            .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(lblTitle)
                .add(15, 15, 15)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	Graphics2D g2 = (Graphics2D) g;
    	int x = 0, y = 0;
    	float width = getWidth();
    	float height = getHeight();
    	
    	Color color1 = Color.WHITE;
    	Color color2 = getBackground();
    	g2.setPaint(new GradientPaint(x,y,color1, width, height,color2));
    	g2.fillRect(x, y, (int) width, (int) height);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JSeparator jSeparator1;
    protected javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables
    
    private Font getTitleFont(){
        Font f = lblTitle.getFont();
        f = f.deriveFont(Font.BOLD, 14);
        return f;
    }
    
    private Color getTitleColor(){
        return UIManager.getColor("TitledBorder.titleColor");
    }

	public String getTitle() {
		return lblTitle.getText();
	}

	public void setTitle(String title) {
		lblTitle.setText(title);
	}
}
