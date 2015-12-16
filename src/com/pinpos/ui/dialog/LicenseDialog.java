package com.pinpos.ui.dialog;

import com.pinpos.POSConstants;
import com.pinpos.config.AppConfig;
import com.pinpos.main.Application;
import com.pinpos.swing.MessageDialog;
import com.pinpos.util.Guid;
import org.apache.commons.codec.binary.Base64;

import java.awt.event.ActionEvent;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LicenseDialog extends javax.swing.JFrame {

    Application application;
    public LicenseDialog(Application application) {
        initComponents();
        this.application = application;
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        this.setTitle("License Key "+ POSConstants.MDS_POS);

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        idTF = new javax.swing.JTextField();
        keyTF = new javax.swing.JTextField();
        saveBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        try {

            idTF.setText(Guid.constructGUID());

        } catch (Exception e) {
            e.printStackTrace();
        }

        jLabel1.setText("ID Mesin");

        jLabel2.setText("License Key");

        saveBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        saveBtn.setText("S A V E");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        cancelBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cancelBtn.setText("C A N C E L");


        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabel2))
                                                .addGap(31, 31, 31)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(idTF)
                                                        .addComponent(keyTF)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(90, 90, 90)
                                                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(32, 32, 32)
                                                .addComponent(cancelBtn)
                                                .addGap(0, 49, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(idTF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(keyTF, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45))
        );

        pack();

    }// </editor-fold>

    private void cancelBtnActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        System.exit(0);
    }

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {

        try {

            AppConfig.setMachineId(idTF.getText());

            MessageDigest md = MessageDigest.getInstance("SHA");

            md.update(idTF.getText().getBytes("UTF-8"));

            String key = new String(md.digest(),"UTF-8");

            Base64 base64 = new Base64(true);

            key = base64.encodeAsString(key.getBytes("UTF-8")).trim().toUpperCase();

            key = key.replaceAll("_","").replaceAll("-","");

            if (key.equalsIgnoreCase(keyTF.getText())) {
                AppConfig.setLicenceKey(key);
                this.setVisible(false);
                application.start();

            } else {
                MessageDialog.showError("License Key tidak valid");
            }


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
    private javax.swing.JButton cancelBtn;
    private javax.swing.JTextField idTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField keyTF;
    private javax.swing.JButton saveBtn;
}
