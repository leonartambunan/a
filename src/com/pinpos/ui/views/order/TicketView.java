
package com.pinpos.ui.views.order;

import com.pinpos.Messages;
import com.pinpos.POSConstants;
import com.pinpos.PosException;
import com.pinpos.main.Application;
import com.pinpos.model.*;
import com.pinpos.model.MenuItem;
import com.pinpos.model.dao.CookingInstructionDAO;
import com.pinpos.model.dao.MenuItemDAO;
import com.pinpos.print.PosPrintService;
import com.pinpos.swing.PosButton;
import com.pinpos.ui.dialog.BeanEditorDialog;
import com.pinpos.ui.dialog.POSMessageDialog;
import com.pinpos.ui.views.CookingInstructionSelectionView;
import com.pinpos.ui.views.SwitchboardView;
import com.pinpos.ui.views.order.actions.OrderListener;
import com.pinpos.util.NumberUtil;
import com.pinpos.util.PosGuiUtil;
import net.miginfocom.swing.MigLayout;
import org.hibernate.StaleObjectStateException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TicketView extends JPanel {
	private java.util.Vector<OrderListener> orderListeners = new java.util.Vector<OrderListener>();
	private Ticket ticket;

	public final static String VIEW_NAME = "TICKET_VIEW";

	public TicketView() {
		initComponents();

		chkTaxExempt.setEnabled(false);
		btnAddCookingInstruction.setEnabled(false);
		btnIncreaseAmount.setEnabled(false);
		btnDecreaseAmount.setEnabled(false);

		ticketViewerTable.setRowHeight(35);
		ticketViewerTable.getRenderer().setInTicketScreen(true);
		ticketViewerTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					updateSelectionView();
				}
			}

		});

		ticketViewerTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

				Object selected = ticketViewerTable.getSelected();
				if (!(selected instanceof ITicketItem)) {
					return;
				}

				ITicketItem item = (ITicketItem) selected;

				Boolean printedToKitchen = item.isPrintedToKitchen();

				btnAddCookingInstruction.setEnabled(item.canAddCookingInstruction());
				btnIncreaseAmount.setEnabled(!printedToKitchen);
				btnDecreaseAmount.setEnabled(!printedToKitchen);
			}

		});
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
	private void initComponents() {
		jPanel1 = new com.pinpos.swing.TransparentPanel();
		ticketAmountPanel = new com.pinpos.swing.TransparentPanel();
		controlPanel = new com.pinpos.swing.TransparentPanel();
		controlPanel.setOpaque(true);
		btnPay = new com.pinpos.swing.PosButton();
		btnSetAmount = new com.pinpos.swing.PosButton();
		btnCancel = new com.pinpos.swing.PosButton();
		btnFinish = new com.pinpos.swing.PosButton();
		scrollerPanel = new com.pinpos.swing.TransparentPanel();
		btnIncreaseAmount = new com.pinpos.swing.PosButton();
		btnDecreaseAmount = new com.pinpos.swing.PosButton();
		btnScrollUp = new com.pinpos.swing.PosButton();
		btnScrollDown = new com.pinpos.swing.PosButton();
		jPanel2 = new com.pinpos.swing.TransparentPanel();
		ticketViewerTable = new com.pinpos.ui.ticket.TicketViewerTable();
		jScrollPane1 = new javax.swing.JScrollPane(ticketViewerTable);

		setBorder(javax.swing.BorderFactory.createTitledBorder(null, com.pinpos.POSConstants.TICKET, javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.DEFAULT_POSITION));
		setPreferredSize(new java.awt.Dimension(420, 463));
		setLayout(new java.awt.BorderLayout(5, 5));
		jPanel1.setLayout(new BorderLayout(5, 5));
		ticketAmountPanel.setLayout(new MigLayout("alignx trailing,fill", "[grow][]", "[][][][][][][][]"));
		jLabel5 = new javax.swing.JLabel();

		jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12));
		jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel5.setText(POSConstants.SUBTOTAL + ":");
		ticketAmountPanel.add(jLabel5, "cell 0 1,growx,aligny center");
		tfSubtotal = new javax.swing.JTextField();
		tfSubtotal.setHorizontalAlignment(SwingConstants.TRAILING);

		tfSubtotal.setEditable(false);
		tfSubtotal.setFont(new java.awt.Font("Tahoma", 1, 12));
		ticketAmountPanel.add(tfSubtotal, "cell 1 1,growx,aligny center");
		jLabel1 = new javax.swing.JLabel();

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel1.setText(Messages.getString("Discount") + ":");
		ticketAmountPanel.add(jLabel1, "cell 0 2,growx,aligny center");
		tfDiscount = new javax.swing.JTextField();
		tfDiscount.setHorizontalAlignment(SwingConstants.TRAILING);

		tfDiscount.setEditable(false);
		tfDiscount.setFont(new java.awt.Font("Tahoma", 1, 12));
		ticketAmountPanel.add(tfDiscount, "cell 1 2,growx,aligny center");
		TaxLabel = new javax.swing.JLabel();

		TaxLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
		TaxLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		TaxLabel.setText(POSConstants.TAX + ":");
		ticketAmountPanel.add(TaxLabel, "cell 0 3,growx,aligny center");
		tfTax = new javax.swing.JTextField();
		tfTax.setHorizontalAlignment(SwingConstants.TRAILING);

		tfTax.setEditable(false);
		tfTax.setFont(new java.awt.Font("Tahoma", 1, 12));
		ticketAmountPanel.add(tfTax, "cell 1 3,growx,aligny center");

		lblServiceCharge = new JLabel();
		lblServiceCharge.setText(Messages.getString("RECEIPT_REPORT_SERVICE_CHARGE_LABEL")+":");
		lblServiceCharge.setHorizontalAlignment(SwingConstants.RIGHT);
		lblServiceCharge.setFont(new Font("Dialog", Font.BOLD, 12));
		ticketAmountPanel.add(lblServiceCharge, "cell 0 4,alignx trailing");

		tfServiceCharge = new JTextField();
		tfServiceCharge.setHorizontalAlignment(SwingConstants.TRAILING);
		tfServiceCharge.setEditable(false);
        tfServiceCharge.setFont(new java.awt.Font("Tahoma", 1, 12));
		ticketAmountPanel.add(tfServiceCharge, "cell 1 4,growx,aligny center");
		tfServiceCharge.setColumns(10);
		jLabel6 = new javax.swing.JLabel();

		jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12));
		jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel6.setText("Total :");
		ticketAmountPanel.add(jLabel6, "cell 0 5,growx,aligny center");
		tfTotal = new javax.swing.JTextField();
		tfTotal.setHorizontalAlignment(SwingConstants.TRAILING);

		tfTotal.setEditable(false);
		tfTotal.setFont(new java.awt.Font("Tahoma", 1, 12));
		ticketAmountPanel.add(tfTotal, "cell 1 5,growx,aligny center");
		chkTaxExempt = new javax.swing.JCheckBox();

		chkTaxExempt.setFont(new java.awt.Font("Tahoma", 1, 12));
		chkTaxExempt.setText(com.pinpos.POSConstants.TAX_EXEMPT);
		chkTaxExempt.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		chkTaxExempt.setFocusable(false);
		chkTaxExempt.setMargin(new java.awt.Insets(0, 0, 0, 0));
		ticketAmountPanel.add(chkTaxExempt, "cell 1 6,growx,aligny center");

		btnPay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pay_32.png")));
		btnPay.setText(com.pinpos.POSConstants.PAY_NOW);
		btnPay.setPreferredSize(new java.awt.Dimension(76, 45));
		btnPay.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				doPayNow(evt);
			}
		});

        btnSetAmount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit_ticket_32.png")));
        btnSetAmount.setText("JLH ITEM");
        btnSetAmount.setPreferredSize(new java.awt.Dimension(76, 45));
        btnSetAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int amountNumber = PosGuiUtil.captureAmountNumber();
                if (amountNumber > 0) {
                    setAmount(evt,amountNumber);
                }
            }
        });

        controlPanel.setLayout(new MigLayout("insets 0", "[202px,grow][202px,grow]", "[45px][45px]"));
//		controlPanel.add(btnPay, "cell 0 0 2 1,grow");
		controlPanel.add(btnPay, "cell 0 0,grow");
		controlPanel.add(btnSetAmount, "cell 1 0,grow");

		btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_32.png")));
		btnCancel.setText(com.pinpos.POSConstants.CANCEL);
		btnCancel.setPreferredSize(new java.awt.Dimension(76, 45));
		btnCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				doCancelOrder(evt);
			}
		});
		controlPanel.add(btnCancel, "cell 0 1,grow");

		btnFinish.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/finish_32.png")));
		btnFinish.setText(com.pinpos.POSConstants.FINISH);
		btnFinish.setPreferredSize(new java.awt.Dimension(76, 45));
		btnFinish.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				doFinishOrder(evt);
			}
		});
		controlPanel.add(btnFinish, "cell 1 1,grow");

		btnIncreaseAmount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_user_32.png")));
		btnIncreaseAmount.setPreferredSize(new java.awt.Dimension(76, 45));
		btnIncreaseAmount.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				doIncreaseAmount(evt);
			}
		});
		scrollerPanel.setLayout(new MigLayout("insets 0", "[133px,grow][133px,grow][133px,grow]", "[45px][45px]"));
		scrollerPanel.add(btnIncreaseAmount, "cell 0 0,grow");

		btnDecreaseAmount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minus_32.png")));
		btnDecreaseAmount.setPreferredSize(new java.awt.Dimension(76, 45));
		btnDecreaseAmount.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				doDecreaseAmount(evt);
			}
		});
		scrollerPanel.add(btnDecreaseAmount, "cell 1 0,grow");

		btnScrollUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/up_32.png")));
		btnScrollUp.setPreferredSize(new java.awt.Dimension(76, 45));
		btnScrollUp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				doScrollUp(evt);
			}
		});
		scrollerPanel.add(btnScrollUp, "cell 2 0,grow");

		btnScrollDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/down_32.png")));
		btnScrollDown.setPreferredSize(new java.awt.Dimension(76, 45));
		btnScrollDown.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				doScrollDown(evt);
			}
		});
		btnDelete = new com.pinpos.swing.PosButton();

		btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete_32.png")));
		btnDelete.setText(com.pinpos.POSConstants.DELETE);
		btnDelete.setPreferredSize(new java.awt.Dimension(80, 17));
		btnDelete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				doDeleteSelection(evt);
			}
		});

		btnAddCookingInstruction = new PosButton();
		btnAddCookingInstruction.setEnabled(false);
		btnAddCookingInstruction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doAddCookingInstruction();
			}
		});
		btnAddCookingInstruction.setText("<html><center>INSTRUKSI<br/>MASAK</center></html>");

        scrollerPanel.add(btnAddCookingInstruction, "cell 0 1,grow");
		scrollerPanel.add(btnDelete, "cell 1 1,grow");
		scrollerPanel.add(btnScrollDown, "cell 2 1,grow");

		JPanel amountPanelContainer= new JPanel(new BorderLayout(5, 5));
		amountPanelContainer.add(ticketAmountPanel);
		amountPanelContainer.add(scrollerPanel, BorderLayout.SOUTH);

		jPanel1.add(amountPanelContainer);
		jPanel1.add(controlPanel, BorderLayout.SOUTH);

		add(jPanel1, java.awt.BorderLayout.SOUTH);

		jPanel2.setLayout(new java.awt.BorderLayout());

		//		jScrollPane1.setBorder(null);
		jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane1.setPreferredSize(new java.awt.Dimension(180, 200));
		//jScrollPane1.setViewportView(ticketViewerTable);

		jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

		add(jPanel2, java.awt.BorderLayout.CENTER);
		
	}// </editor-fold>//GEN-END:initComponents

	protected void doAddCookingInstruction() {

		try {
			Object object = ticketViewerTable.getSelected();
			if (!(object instanceof TicketItem)) {
				POSMessageDialog.showError("Silahkan memilih sebuah item");
				return;
			}

			TicketItem ticketItem = (TicketItem) object;

			if (ticketItem.isPrintedToKitchen()) {
				POSMessageDialog.showError("Instruksi masak tidak dapat dibuat pada item yang sudah diprint ke dapur");
				return;
			}

			List<CookingInstruction> list = CookingInstructionDAO.getInstance().findAll();
			CookingInstructionSelectionView cookingInstructionSelectionView = new CookingInstructionSelectionView();
			BeanEditorDialog dialog = new BeanEditorDialog(cookingInstructionSelectionView, Application.getPosWindow(), true);
			dialog.setBean(list);
			dialog.setSize(450, 300);
			dialog.setLocationRelativeTo(Application.getPosWindow());
			dialog.setVisible(true);

			if (dialog.isCanceled()) {
				return;
			}

			List<TicketItemCookingInstruction> instructions = cookingInstructionSelectionView.getTicketItemCookingInstructions();
			ticketItem.addCookingInstructions(instructions);

			ticketViewerTable.updateView();
		} catch (Exception e) {
			e.printStackTrace();
			POSMessageDialog.showError(e.getMessage());
		}
	}

	private synchronized void doFinishOrder(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doFinishOrder
		try {

			updateModel();

			ticket.clearDeletedItems();
			OrderController.saveOrder(ticket);

			RootView.getInstance().showView(SwitchboardView.VIEW_NAME);

            if (ticket.needsKitchenPrint()) {
                PosPrintService.printToKitchen(ticket);
            }

		} catch (StaleObjectStateException e) {
			POSMessageDialog.showError("Terjadi kegagalan pada saat menyimpan. Tiket sudah mengalami perubahan. Kemungkinan diubah oleh orang lain atau oleh terminal lain.");
			return;
		} catch (PosException x) {
			POSMessageDialog.showError(x.getMessage());
		} catch (Exception e) {
			POSMessageDialog.showError(Application.getPosWindow(), POSConstants.ERROR_MESSAGE, e);
		}
	}//GEN-LAST:event_doFinishOrder

	private void doCancelOrder(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doCancelOrder
		RootView.getInstance().showView(SwitchboardView.VIEW_NAME);
	}//GEN-LAST:event_doCancelOrder

	private synchronized void updateModel() {
		if (ticket.getTicketItems() == null || ticket.getTicketItems().size() == 0) {
			throw new PosException(com.pinpos.POSConstants.TICKET_IS_EMPTY_);
		}

		ticket.calculatePrice();
	}

	private void doPayNow(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doPayNow
		try {
			updateModel();

			OrderController.saveOrder(ticket);
			
			firePayOrderSelected();
		} catch (PosException e) {
			POSMessageDialog.showError(e.getMessage());
		}
	}//GEN-LAST:event_doPayNow

	private void doDeleteSelection(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doDeleteSelection
		Object object = ticketViewerTable.deleteSelectedItem();
		if (object != null) {
			updateView();

			if (object instanceof TicketItemModifier) {
				ModifierView modifierView = OrderView.getInstance().getModifierView();
				if (modifierView.isVisible()) {
					modifierView.updateVisualRepresentation();
				}
			}
		}

	}//GEN-LAST:event_doDeleteSelection

	private void doIncreaseAmount(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doIncreaseAmount
		if (ticketViewerTable.increaseItemAmount()) {
			ModifierView modifierView = OrderView.getInstance().getModifierView();
			if (modifierView.isVisible()) {
				modifierView.updateVisualRepresentation();
			}
			updateView();
		}

	}//GEN-LAST:event_doIncreaseAmount

	private void doDecreaseAmount(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doDecreaseAmount
		if (ticketViewerTable.decreaseItemAmount()) {
			ModifierView modifierView = OrderView.getInstance().getModifierView();
			if (modifierView.isVisible()) {
				modifierView.updateVisualRepresentation();
			}
			updateView();
		}
	}//GEN-LAST:event_doDecreaseAmount

	private void setAmount(java.awt.event.ActionEvent evt,int amount) {//GEN-FIRST:event_doDecreaseAmount
		if (ticketViewerTable.setItemAmount(amount)) {
			ModifierView modifierView = OrderView.getInstance().getModifierView();
			if (modifierView.isVisible()) {
				modifierView.updateVisualRepresentation();
			}
			updateView();
		}
	}//GEN-LAST:event_doDecreaseAmount

	private void doScrollDown(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doScrollDown
		ticketViewerTable.scrollDown();
	}//GEN-LAST:event_doScrollDown

	private void doScrollUp(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doScrollUp
		ticketViewerTable.scrollUp();
	}//GEN-LAST:event_doScrollUp

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private com.pinpos.swing.TransparentPanel controlPanel;
	private com.pinpos.swing.PosButton btnCancel;
	private com.pinpos.swing.PosButton btnDecreaseAmount;
	private com.pinpos.swing.PosButton btnDelete;
	private com.pinpos.swing.PosButton btnFinish;
	private com.pinpos.swing.PosButton btnIncreaseAmount;
	private com.pinpos.swing.PosButton btnPay;
	private com.pinpos.swing.PosButton btnSetAmount;
	private com.pinpos.swing.PosButton btnScrollDown;
	private com.pinpos.swing.PosButton btnScrollUp;
	private javax.swing.JCheckBox chkTaxExempt;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel TaxLabel;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private com.pinpos.swing.TransparentPanel jPanel1;
	private com.pinpos.swing.TransparentPanel jPanel2;
	private com.pinpos.swing.TransparentPanel ticketAmountPanel;
	private com.pinpos.swing.TransparentPanel scrollerPanel;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextField tfDiscount;
	private javax.swing.JTextField tfSubtotal;
	private javax.swing.JTextField tfTax;
	private javax.swing.JTextField tfTotal;
	private com.pinpos.ui.ticket.TicketViewerTable ticketViewerTable;
	private JTextField tfServiceCharge;
	private JLabel lblServiceCharge;
	private PosButton btnAddCookingInstruction;

	// End of variables declaration//GEN-END:variables

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket _ticket) {
		this.ticket = _ticket;

		ticketViewerTable.setTicket(_ticket);

		updateView();
	}

	public void addTicketItem(TicketItem ticketItem) {
		ticketViewerTable.addTicketItem(ticketItem);
		updateView();
	}

	public void removeModifier(TicketItem parent, TicketItemModifier modifier) {
		ticketViewerTable.removeModifier(parent, modifier);
	}

	public void updateAllView() {
		ticketViewerTable.updateView();
		updateView();
	}

	public void selectRow(int rowIndex) {
		ticketViewerTable.selectRow(rowIndex);
	}

	public void updateView() {
		if (ticket == null || ticket.getTicketItems() == null || ticket.getTicketItems().size() <= 0) {
			tfSubtotal.setText("");
			tfDiscount.setText("");
			tfTax.setText("");
			tfServiceCharge.setText("");
			tfTotal.setText("");

			return;
		}
		

		ticket.calculatePrice();

		tfSubtotal.setText(NumberUtil.formatToCurrency(ticket.getSubtotalAmount()));
		tfDiscount.setText(NumberUtil.formatToCurrency(ticket.getDiscountAmount()));

		if(Application.getInstance().isPriceIncludesTax()) {
			tfTax.setText(Messages.getString("INCLUDED"));
		}
		else {
			tfTax.setText(NumberUtil.formatToCurrency(ticket.getTaxAmount()));
		}

		if (ticket.isTaxExempt()) {
			chkTaxExempt.setSelected(true);
		}
		else {
			chkTaxExempt.setSelected(false);
		}

		tfServiceCharge.setText(NumberUtil.formatToCurrency(ticket.getServiceCharge()));
		tfTotal.setText(NumberUtil.formatToCurrency(ticket.getTotalAmount()));
	}

	public void addOrderListener(OrderListener listenre) {
		orderListeners.add(listenre);
	}

	public void removeOrderListener(OrderListener listenre) {
		orderListeners.remove(listenre);
	}

	public void firePayOrderSelected() {
		for (OrderListener listener : orderListeners) {
			listener.payOrderSelected(getTicket());
		}
	}

	public void setControlsVisible(boolean visible) {
		if (visible) {
			controlPanel.setVisible(true);
			btnIncreaseAmount.setEnabled(true);
			btnDecreaseAmount.setEnabled(true);
			btnDelete.setEnabled(true);
		}
		else {
			controlPanel.setVisible(false);
			btnIncreaseAmount.setEnabled(false);
			btnDecreaseAmount.setEnabled(false);
			btnDelete.setEnabled(false);
		}
	}

	private void updateSelectionView() {
		Object selectedObject = ticketViewerTable.getSelected();

		OrderView orderView = OrderView.getInstance();

		TicketItem selectedItem = null;
		if (selectedObject instanceof TicketItem) {
			selectedItem = (TicketItem) selectedObject;
			MenuItemDAO dao = new MenuItemDAO();
			MenuItem menuItem = dao.get(selectedItem.getItemId());
			MenuGroup menuGroup = menuItem.getParent();
			MenuItemView itemView = OrderView.getInstance().getItemView();
			if (!menuGroup.equals(itemView.getMenuGroup())) {
				itemView.setMenuGroup(menuGroup);
			}
			orderView.showView(MenuItemView.VIEW_NAME);

			MenuCategory menuCategory = menuGroup.getParent();
			orderView.getCategoryView().setSelectedCategory(menuCategory);
			return;
		}
		else if (selectedObject instanceof TicketItemModifier) {
			selectedItem = ((TicketItemModifier) selectedObject).getParent().getParent();
		}
		if (selectedItem == null)
			return;

		ModifierView modifierView = orderView.getModifierView();

		if (selectedItem.isHasModifiers()) {
			MenuItemDAO dao = new MenuItemDAO();
			MenuItem menuItem = dao.get(selectedItem.getItemId());
			if (!menuItem.equals(modifierView.getMenuItem())) {
				menuItem = dao.initialize(menuItem);
				modifierView.setMenuItem(menuItem, selectedItem);
			}

			MenuCategory menuCategory = menuItem.getParent().getParent();
			orderView.getCategoryView().setSelectedCategory(menuCategory);

			orderView.showView(ModifierView.VIEW_NAME);
		}
	}
}
