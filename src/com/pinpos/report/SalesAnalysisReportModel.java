package com.pinpos.report;

import com.pinpos.bo.ui.explorer.ListTableModel;
import com.pinpos.util.NumberUtil;

import java.util.List;

public class SalesAnalysisReportModel extends ListTableModel {

	public SalesAnalysisReportModel(List<SalesAnalysisData> dataList) {
		super(new String[] { "shiftName", "categoryName", "count", "gross", "discount", "netSales", "avgGross", "avgDiscount", "avgNet", "percentage" }, dataList);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		SalesAnalysisData data = (SalesAnalysisData) rows.get(rowIndex);

		switch (columnIndex) {
			case 0:
				return data.shiftName;

			case 1:
				return data.categoryName;

			case 2:
				return (data.count);

			case 3:
				return (data.gross);

			case 4:
				return (data.discount);

			case 5:
				return (data.netSales);

			case 6:
				return (data.avgGross);

			case 7:
				return (data.avgDiscount);

			case 8:
				return (data.avgNet);

			case 9:
				return (data.percentage);
		}

		return null;
	}

	public static class SalesAnalysisData {
		private String shiftName;
		private String categoryName;
		private int count;
		private double gross;
		private double discount;
		private double netSales;
		private double avgGross;
		private double avgDiscount;
		private double avgNet;
		private double percentage;
		
		public void calculate() {
			netSales = gross - discount;
		}

		public double getAvgDiscount() {
			return avgDiscount;
		}

		public void setAvgDiscount(double avgDiscount) {
			this.avgDiscount = avgDiscount;
		}

		public double getAvgGross() {
			return avgGross;
		}

		public void setAvgGross(double avgGross) {
			this.avgGross = avgGross;
		}

		public double getAvgNet() {
			return avgNet;
		}

		public void setAvgNet(double avgNet) {
			this.avgNet = avgNet;
		}

		public String getCategoryName() {
			return categoryName;
		}

		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public double getDiscount() {
			return discount;
		}

		public void setDiscount(double discount) {
			this.discount = discount;
		}

		public double getGross() {
			return gross;
		}

		public void setGross(double gross) {
			this.gross = gross;
		}

		public double getNetSales() {
			return netSales;
		}

		public void setNetSales(double netSales) {
			this.netSales = netSales;
		}

		public double getPercentage() {
			return percentage;
		}

		public void setPercentage(double percentage) {
			this.percentage = percentage;
		}

		public String getShiftName() {
			return shiftName;
		}

		public void setShiftName(String shiftName) {
			this.shiftName = shiftName;
		}

	}

}
