package com.pinpos.ui;

import java.awt.Frame;
import java.awt.LayoutManager;

import com.pinpos.model.util.IllegalModelStateException;
import com.pinpos.ui.dialog.BeanEditorDialog;

public abstract class BeanEditor<E> extends com.pinpos.swing.TransparentPanel {
	protected E bean;
	protected BeanEditorDialog editorDialog;
	protected boolean editMode;

	public BeanEditor(LayoutManager layout) {
		super(layout);
	}

	public BeanEditor() {
		super();
	}

	public void createNew() {

	}

	public void clearFields() {

	}
	
	public boolean delete() {
		return false;
	}
	
	public void setFieldsEnable(boolean enable) {
		
	}

	public abstract boolean save();

	protected abstract void updateView();

	protected abstract boolean updateModel() throws IllegalModelStateException;

	public abstract String getDisplayText();

	public E getBean() {
		return bean;
	}

	public void setBean(E bean) {
		setBean(bean, true);
	}

	public void setBean(E bean, boolean updateView) {
		this.bean = bean;

		if (bean == null) {
			clearFields();
		}
		else if (updateView) {
			updateView();
		}
	}

	public Frame getParentFrame() {
		return (Frame) editorDialog.getOwner();
	}

	public BeanEditorDialog getEditorDialog() {
		return editorDialog;
	}

	public void setEditorDialog(BeanEditorDialog editorDialog) {
		this.editorDialog = editorDialog;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
}
