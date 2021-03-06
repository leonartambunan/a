package com.pinpos.model;

import com.pinpos.model.base.BaseTerminal;

public class Terminal extends BaseTerminal {
	private static final long serialVersionUID = 1L;

	/*[CONSTRUCTOR MARKER BEGIN]*/
	public Terminal () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Terminal (java.lang.Integer id) {
		super(id);
	}

	/*[CONSTRUCTOR MARKER END]*/
	
	@Override
	public String toString() {
		return getName();
	}
}