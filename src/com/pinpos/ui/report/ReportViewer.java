package com.pinpos.ui.report;

import com.pinpos.bo.ui.BackOfficeWindow;
import com.pinpos.swing.MessageDialog;
import com.pinpos.ui.dialog.POSMessageDialog;
import com.pinpos.ui.util.UiUtil;

import javax.swing.*;
import java.util.Date;

import static org.jdesktop.layout.GroupLayout.*;
import static org.jdesktop.layout.LayoutStyle.RELATED;

public class ReportViewer extends javax.swing.JPanel {
    private Report report;

    /** Creates new form ReportViewer */
    public ReportViewer() {
        initComponents();
    }

    public ReportViewer(Report report) {
        initComponents();

        setReport(report);
    }

    private void initComponents() {
        reportConstraintPanel = new com.pinpos.swing.TransparentPanel();
        //jLabel1 = new javax.swing.JLabel();
        //cbReportType = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        dpStartDate = UiUtil.getCurrentDate();
//        dpStartDate = UiUtil.getCurrentMonthStart();
        dpEndDate = UiUtil.getCurrentDate();
//        dpEndDate = UiUtil.getCurrentMonthEnd();
        jLabel3 = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        reportPanel = new com.pinpos.swing.TransparentPanel();

        setLayout(new java.awt.BorderLayout(5, 5));

        //jLabel1.setText("Report Type" + ":");

        //cbReportType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { com.pinpos.POSConstants.PREVIOUS_SALE_AFTER_DRAWER_RESET_, com.pinpos.POSConstants.SALE_BEFORE_DRAWER_RESET }));

        jLabel2.setText(com.pinpos.POSConstants.START_DATE + ":");

        jLabel3.setText(com.pinpos.POSConstants.END_DATE + ":");

        btnRefresh.setText(com.pinpos.POSConstants.GO);
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doRefreshReport(evt);
            }
        });

        org.jdesktop.layout.GroupLayout reportConstraintPanelLayout = new org.jdesktop.layout.GroupLayout(reportConstraintPanel);
        reportConstraintPanel.setLayout(reportConstraintPanelLayout);
        reportConstraintPanelLayout.setHorizontalGroup(
                reportConstraintPanelLayout.createParallelGroup(LEADING)
                        .add(reportConstraintPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .add(reportConstraintPanelLayout.createParallelGroup(LEADING)
                                        .add(reportConstraintPanelLayout.createSequentialGroup()
                                                        .add(reportConstraintPanelLayout.createParallelGroup(LEADING)
                                                                        .add(jLabel2)
//                            .add(jLabel1)
                                                        )
                                                        .add(15, 15, 15)
                                                        .add(reportConstraintPanelLayout.createParallelGroup(LEADING, false)
//                            .add(cbReportType, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .add(dpStartDate, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addPreferredGap(RELATED)
                                                        .add(jLabel3)
                                                        .addPreferredGap(RELATED)
                                                        .add(dpEndDate, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                                        .add(spacerLabel)
                                                        .addPreferredGap(RELATED)
                                                        .add(btnRefresh)
                                        )

                                        .add(TRAILING, btnRefresh)
                                )
                                .addContainerGap())
        );
        reportConstraintPanelLayout.setVerticalGroup(
                reportConstraintPanelLayout.createParallelGroup(LEADING)
                        .add(reportConstraintPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .add(reportConstraintPanelLayout.createParallelGroup(BASELINE)
//                    .add(jLabel1)
//                    .add(cbReportType, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                )
                                .addPreferredGap(RELATED)
                                .add(reportConstraintPanelLayout.createParallelGroup(LEADING)
                                        .add(spacerLabel, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .add(btnRefresh, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .add(dpEndDate, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .add(jLabel3)
                                        .add(reportConstraintPanelLayout.createSequentialGroup()
                                                        .add(reportConstraintPanelLayout.createParallelGroup(LEADING)
                                                                .add(dpStartDate, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                                                .add(jLabel2))
                                                        .addPreferredGap(RELATED)
//                                                .add(btnRefresh)
                                        ))
                                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE))
        );

        reportConstraintPanelLayout.linkSize(new java.awt.Component[] {dpEndDate, dpStartDate, jLabel2, jLabel3}, org.jdesktop.layout.GroupLayout.VERTICAL);

        add(reportConstraintPanel, java.awt.BorderLayout.NORTH);

        reportPanel.setLayout(new java.awt.BorderLayout());

        add(reportPanel, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents

    private void doRefreshReport(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doRefreshReport
        Date fromDate = dpStartDate.getDate();
        Date toDate = dpEndDate.getDate();

        if(fromDate.after(toDate)) {
            POSMessageDialog.showError(BackOfficeWindow.getInstance(), com.pinpos.POSConstants.FROM_DATE_CANNOT_BE_GREATER_THAN_TO_DATE_);
            return;
        }

        try {
            reportPanel.removeAll();
            reportPanel.revalidate();

            if (report != null) {
//				int reportType = cbReportType.getSelectedIndex();
                int reportType = 1;

                report.setReportType(reportType);
                report.setStartDate(fromDate);
                report.setEndDate(toDate);

                report.refresh();

                if (report != null && report.getViewer() != null) {
                    reportPanel.add(report.getViewer());
                    reportPanel.revalidate();
                }
            }

        } catch (Exception e) {
            MessageDialog.showError(com.pinpos.POSConstants.ERROR_MESSAGE, e);
        }
    }//GEN-LAST:event_doRefreshReport


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    //    private javax.swing.JComboBox cbReportType;
    private org.jdesktop.swingx.JXDatePicker dpEndDate;
    private org.jdesktop.swingx.JXDatePicker dpStartDate;
    //    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private com.pinpos.swing.TransparentPanel reportConstraintPanel;
    private com.pinpos.swing.TransparentPanel reportPanel;

    private JLabel spacerLabel = new JLabel(" ");

    // End of variables declaration//GEN-END:variables

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;

//		if(report != null) {
//			cbReportType.setEnabled(report.isTypeSupported());
//			this.dpStartDate.setEnabled(report.isDateRangeSupported());
//			this.dpEndDate.setEnabled(report.isDateRangeSupported());
//		}
    }

}
