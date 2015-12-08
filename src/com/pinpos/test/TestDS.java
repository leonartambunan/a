package com.pinpos.test;

import com.pinpos.report.AbstractReportDataSource;

public class TestDS extends AbstractReportDataSource {
	
	

	public TestDS() {
		super(new String[] {"A","B"});
	}
	
	@Override
	public int getRowCount() {
		return 2;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return "A";
	}

}
