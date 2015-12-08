package com.pinpos.util;

import java.io.ObjectOutputStream;
import java.util.List;

public class DataExporter {
//	public static void main(String[] args) throws Exception {
//		_RootDAO.initialize();
//		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("default-data.obj"));
//
//		write(MenuModifierGroupDAO.getInstance().findAll(), out);
//		write(MenuModifierDAO.getInstance().findAll(), out);
//		write(MenuCategoryDAO.getInstance().findAll(), out);
//		write(MenuGroupDAO.getInstance().findAll(), out);
//		write(MenuItemDAO.getInstance().findAll(), out);
//
//		out.close();
//	}
	
	private static void write(List list, ObjectOutputStream out) throws Exception {
		out.writeObject(list);
	}
}
