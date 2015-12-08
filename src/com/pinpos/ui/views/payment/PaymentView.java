package com.pinpos.ui.views.payment;

import com.pinpos.POSConstants;
import com.pinpos.model.Ticket;
import com.pinpos.model.dao.TicketDAO;
import com.pinpos.swing.PosButton;
import com.pinpos.ui.dialog.POSMessageDialog;
import com.pinpos.util.NumberUtil;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

public class PaymentView extends JPanel {

	private static final String ADD = "0";

	private static final String REMOVE = "1";

	protected SettleTicketView settleTicketView;

	private com.pinpos.swing.PosButton btnCancel;
	private com.pinpos.swing.PosButton btnFinish;
	private com.pinpos.swing.TransparentPanel calcButtonPanel;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel6;
	private com.pinpos.swing.TransparentPanel jPanel4;
	private com.pinpos.swing.PosButton posButton1;
	private com.pinpos.swing.PosButton posButton10;
	private com.pinpos.swing.PosButton posButton11;
	private com.pinpos.swing.PosButton posButton12;
	private com.pinpos.swing.PosButton posButton2;
	private com.pinpos.swing.PosButton posButton3;
	private com.pinpos.swing.PosButton posButton4;
	private com.pinpos.swing.PosButton posButton5;
	private com.pinpos.swing.PosButton posButton6;
	private com.pinpos.swing.PosButton posButton7;
	private com.pinpos.swing.PosButton posButton8;
	private com.pinpos.swing.PosButton posButton9;
	private com.pinpos.swing.FocusedTextField tfAmountTendered;
	private com.pinpos.swing.FocusedTextField tfDueAmount;
	private com.pinpos.swing.TransparentPanel transparentPanel1;
//	private POSToggleButton btnTaxExempt;
	private PosButton btnCoupon;
	private PosButton btnViewCoupons;
	private JLabel label;

	public PaymentView(SettleTicketView settleTicketView) {
		this.settleTicketView = settleTicketView;

		initComponents();

	}

	private void initComponents() {
		calcButtonPanel = new com.pinpos.swing.TransparentPanel();
		posButton1 = new com.pinpos.swing.PosButton();
		posButton2 = new com.pinpos.swing.PosButton();
		posButton3 = new com.pinpos.swing.PosButton();
		posButton4 = new com.pinpos.swing.PosButton();
		posButton5 = new com.pinpos.swing.PosButton();
		posButton6 = new com.pinpos.swing.PosButton();
		posButton9 = new com.pinpos.swing.PosButton();
		posButton8 = new com.pinpos.swing.PosButton();
		posButton7 = new com.pinpos.swing.PosButton();
		posButton11 = new com.pinpos.swing.PosButton();
		posButton10 = new com.pinpos.swing.PosButton();
		posButton12 = new com.pinpos.swing.PosButton();
		jPanel4 = new com.pinpos.swing.TransparentPanel();
		transparentPanel1 = new com.pinpos.swing.TransparentPanel();
		jLabel4 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		tfDueAmount = new com.pinpos.swing.FocusedTextField();
		tfDueAmount.setFocusable(false);
		tfAmountTendered = new com.pinpos.swing.FocusedTextField();

		setLayout(new MigLayout("", "[]", "[73px][251px][grow,fill][][shrink 0]"));

		calcButtonPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
		calcButtonPanel.setLayout(new java.awt.GridLayout(0, 3, 5, 5));

		posButton1.setAction(calAction);
		posButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/7_32.png"))); // NOI18N
		posButton1.setActionCommand("7");
		posButton1.setFocusable(false);
		calcButtonPanel.add(posButton1);

		posButton2.setAction(calAction);
		posButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/8_32.png"))); // NOI18N
		posButton2.setActionCommand("8");
		posButton2.setFocusable(false);
		calcButtonPanel.add(posButton2);

		posButton3.setAction(calAction);
		posButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/9_32.png"))); // NOI18N
		posButton3.setActionCommand("9");
		posButton3.setFocusable(false);
		calcButtonPanel.add(posButton3);

		posButton4.setAction(calAction);
		posButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/4_32.png"))); // NOI18N
		posButton4.setActionCommand("4");
		posButton4.setFocusable(false);
		calcButtonPanel.add(posButton4);

		posButton5.setAction(calAction);
		posButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/5_32.png"))); // NOI18N
		posButton5.setActionCommand("5");
		posButton5.setFocusable(false);
		calcButtonPanel.add(posButton5);

		posButton6.setAction(calAction);
		posButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/6_32.png"))); // NOI18N
		posButton6.setActionCommand("6");
		posButton6.setFocusable(false);
		calcButtonPanel.add(posButton6);

		posButton9.setAction(calAction);
		posButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1_32.png"))); // NOI18N
		posButton9.setActionCommand(REMOVE);
		posButton9.setFocusable(false);
		calcButtonPanel.add(posButton9);

		posButton8.setAction(calAction);
		posButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/2_32.png"))); // NOI18N
		posButton8.setActionCommand("2");
		posButton8.setFocusable(false);
		calcButtonPanel.add(posButton8);

		posButton7.setAction(calAction);
		posButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3_32.png"))); // NOI18N
		posButton7.setActionCommand("3");
		posButton7.setFocusable(false);
		calcButtonPanel.add(posButton7);

		posButton11.setAction(calAction);
		posButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/0_32.png"))); // NOI18N
		posButton11.setActionCommand(ADD);
		posButton11.setFocusable(false);
		calcButtonPanel.add(posButton11);

		posButton10.setAction(calAction);
		posButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/000_32.png"))); // NOI18N
		posButton10.setActionCommand("000");
		posButton10.setFocusable(false);
		calcButtonPanel.add(posButton10);

		posButton12.setAction(calAction);
		posButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clear_32.png"))); // NOI18N
		posButton12.setText("CLEAR");
		posButton12.setFocusable(false);
		calcButtonPanel.add(posButton12);

		add(calcButtonPanel, "cell 0 1,grow");
		jPanel4.setLayout(new MigLayout("", "[][]", "[][][][]"));

//		btnTaxExempt = new POSToggleButton(com.pinpos.POSConstants.TAX_EXEMPT);
//		btnTaxExempt.setText("PENGURANG PAJAK");
//		btnTaxExempt.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				settleTicketView.doTaxExempt(btnTaxExempt.isSelected());
//			}
//		});

//		btnGratuity = new PosButton();
//		btnGratuity.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				doSetGratuity();
//			}
//		});

//		btnMyKalaDiscount = new PosButton();
//		btnMyKalaDiscount.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				settleTicketView.makeMyKalaDiscount();
//				Ticket ticket = settleTicketView.getTicketsToSettle().get(0);
//				settleTicketView.submitMyKalaDiscount(ticket);
//			}
//		});

//		btnUseKalaId = new PosButton();
//		btnUseKalaId.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String actionCommand = e.getActionCommand();
//				if (ADD.equals(actionCommand)) {
//					addMyKalaId();
//				}
//				else {
//					removeKalaId();
//				}
//			}
//		});
//		btnMyKalaDiscount.setText("LOYALTY DISCOUNT");
//		jPanel4.add(btnMyKalaDiscount, "cell 1 0,growx");
//		btnGratuity.setText("GRATUITY");
//		jPanel4.add(btnGratuity, "cell 0 1,growx");
//		jPanel4.add(btnTaxExempt, "cell 1 1,grow");

		btnCoupon = new PosButton(com.pinpos.POSConstants.COUPON_DISCOUNT);
		btnCoupon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settleTicketView.doApplyCoupon();
			}
		});
		jPanel4.add(btnCoupon, "cell 0 2,grow");

		btnViewCoupons = new PosButton(com.pinpos.POSConstants.VIEW_DISCOUNTS);
		btnViewCoupons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settleTicketView.doViewDiscounts();
			}
		});
		jPanel4.add(btnViewCoupons, "cell 1 2,grow");

		btnFinish = new com.pinpos.swing.PosButton();
		jPanel4.add(btnFinish, "cell 1 3,grow");
		btnFinish.setText(POSConstants.PAY);
		btnCancel = new com.pinpos.swing.PosButton();
		jPanel4.add(btnCancel, "cell 0 3,grow");
		btnCancel.setText(POSConstants.CANCEL);
		btnCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelActionPerformed(evt);
			}
		});
		btnFinish.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				doFinish(evt);
			}
		});

		label = new JLabel("");
		add(label, "cell 0 2,growy");

		add(jPanel4, "cell 0 4,grow");

		transparentPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 15, 15));

		jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		jLabel4.setText("JUMLAH HRS DIBAYAR:");

		jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		jLabel6.setText("JUMLAH UANG DITERIMA:");

		tfDueAmount.setEditable(false);
		tfDueAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfDueAmount.setBackground(Color.GREEN);
        tfDueAmount.setFont(new Font("Tahoma",Font.BOLD,18));

		tfAmountTendered.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        tfAmountTendered.setBackground(Color.GREEN);
        tfAmountTendered.setFont(new Font("Tahoma",Font.BOLD,18));


        add(transparentPanel1, "cell 0 0,growx,aligny top");

		transparentPanel1.setLayout(new MigLayout("", "[][grow,fill]", "[19px][19px]"));
		transparentPanel1.add(jLabel4, "cell 0 0,alignx right,aligny center");
		transparentPanel1.add(jLabel6, "cell 0 1,alignx left,aligny center");
		transparentPanel1.add(tfDueAmount, "cell 1 0,growx,aligny top");
		transparentPanel1.add(tfAmountTendered, "cell 1 1,growx,aligny top");
	}// </editor-fold>//GEN-END:initComponents

	protected void removeKalaId() {
		Ticket ticket = settleTicketView.getTicketsToSettle().get(0);
		ticket.getProperties().remove(SettleTicketView.LOYALTY_ID);
		TicketDAO.getInstance().saveOrUpdate(ticket);

		POSMessageDialog.showMessage("Loyalty Id removed");
	}

	public void addMyKalaId() {
		String loyaltyid = JOptionPane.showInputDialog("Enter loyalty id:");

		if (StringUtils.isEmpty(loyaltyid)) {
			return;
		}

		Ticket ticket = settleTicketView.getTicketsToSettle().get(0);
		ticket.addProperty(SettleTicketView.LOYALTY_ID, loyaltyid);
		TicketDAO.getInstance().saveOrUpdate(ticket);

		POSMessageDialog.showMessage("Loyalty id set.");
	}

	protected void doSetGratuity() {
		settleTicketView.doSetGratuity();
	}

	protected void doTaxExempt() {
	}

	private void doFinish(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doFinish
		settleTicketView.doSettle();
	}//GEN-LAST:event_doFinish

	private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
		settleTicketView.setCanceled(true);
		settleTicketView.dispose();
	}//GEN-LAST:event_btnCancelActionPerformed

	// End of variables declaration//GEN-END:variables

	Action calAction = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			JTextField textField = tfAmountTendered;

			PosButton button = (PosButton) e.getSource();
			String s = button.getActionCommand();
			if (s.equals("CLEAR")) {
				textField.setText(ADD);
			}
			else if (s.equals(".")) {
				if (textField.getText().indexOf('.') < 0) {
					textField.setText(textField.getText() + ".");
				}
			}
			else {
				String string = textField.getText();
				int index = string.indexOf('.');
				if (index < 0) {
					double value = 0;
					try {
						value = Double.parseDouble(string);
					} catch (NumberFormatException x) {
						Toolkit.getDefaultToolkit().beep();
					}
					if (value == 0) {
						textField.setText(s);
					}
					else {
						textField.setText(string + s);
					}
				}
				else {
					textField.setText(string + s);
				}
			}
		}
	};
//	private PosButton btnMyKalaDiscount;
	//private PosButton btnUseKalaId;

	public void updateView() {
		List<Ticket> tickets = settleTicketView.getTicketsToSettle();
		if (tickets.size() == 1) {
//			btnTaxExempt.setEnabled(true);
//			btnTaxExempt.setSelected(tickets.get(0).isTaxExempt());
		}
		else {
//			btnTaxExempt.setEnabled(false);
		}

		double dueAmount = getDueAmount();

		tfDueAmount.setText(NumberUtil.formatToCurrency(dueAmount));
		tfAmountTendered.setText(NumberUtil.formatToCurrency(dueAmount));
	}

	public double getTenderedAmount() throws ParseException {
		NumberFormat numberFormat = NumberFormat.getInstance();
        return numberFormat.parse(tfAmountTendered.getText()).doubleValue();
	}

	public SettleTicketView getSettleTicketView() {
		return settleTicketView;
	}

	public void setSettleTicketView(SettleTicketView settleTicketView) {
		this.settleTicketView = settleTicketView;
	}

	protected double getPaidAmount() {
		List<Ticket> ticketsToSettle = settleTicketView.getTicketsToSettle();
		if (ticketsToSettle == null) {
			return 0;
		}

		double total = 0;
		for (Ticket ticket : ticketsToSettle) {
			total += ticket.getPaidAmount();
		}
		return total;
	}

	protected double getDueAmount() {
		List<Ticket> ticketsToSettle = settleTicketView.getTicketsToSettle();
		if (ticketsToSettle == null) {
			return 0;
		}

		double total = 0;
		for (Ticket ticket : ticketsToSettle) {
			total += ticket.getDueAmount();
		}
		return total;
	}

	protected double getTotalGratuity() {
		List<Ticket> ticketsToSettle = settleTicketView.getTicketsToSettle();
		if (ticketsToSettle == null) {
			return 0;
		}

		double total = 0;
		for (Ticket ticket : ticketsToSettle) {
			if (ticket.getGratuity() != null) {
				total += ticket.getGratuity().getAmount();
			}
		}
		return total;
	}

	public void setDefaultFocus() {
		tfAmountTendered.requestFocus();
	}

}
