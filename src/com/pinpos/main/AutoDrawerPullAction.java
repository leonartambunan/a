package com.pinpos.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.pinpos.model.AttendenceHistory;
import com.pinpos.model.DrawerPullReport;
import com.pinpos.model.Restaurant;
import com.pinpos.model.Shift;
import com.pinpos.model.Terminal;
import com.pinpos.model.User;
import com.pinpos.model.dao.AttendenceHistoryDAO;
import com.pinpos.model.dao.RestaurantDAO;
import com.pinpos.model.dao.TerminalDAO;
import com.pinpos.model.dao.UserDAO;
import com.pinpos.print.DrawerpullReportService;
import com.pinpos.swing.GlassPane;
import com.pinpos.ui.dialog.POSMessageDialog;
import com.pinpos.util.ShiftUtil;

public class AutoDrawerPullAction implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		PosWindow posWindow = Application.getPosWindow();
		try {
			RestaurantDAO restaurantDAO = RestaurantDAO.getInstance();
			Restaurant restaurant = restaurantDAO.get(1);
			if(!restaurant.isAutoDrawerPullEnable()) {
				return;
			}
			Calendar currentTime = Calendar.getInstance();
			
			int currentHour = currentTime.get(Calendar.HOUR_OF_DAY);
			int currentMin = currentTime.get(Calendar.MINUTE);
			
			if(currentHour >= restaurant.getDrawerPullHour() && currentMin >= restaurant.getDrawerPullMin() 
					&& currentMin < restaurant.getDrawerPullMin() + 1) {
				
			}
			else {
				return;
			}
			((GlassPane) posWindow.getGlassPane()).setMessage(com.pinpos.POSConstants.PERFORMING_AUTO_DRAWER_PULL);
			posWindow.setGlassPaneVisible(true);
			DrawerPullReport report = DrawerpullReportService.buildDrawerPullReport();

			TerminalDAO dao = new TerminalDAO();
			Terminal terminal = Application.getInstance().getTerminal();
			dao.resetCashDrawer(report, terminal, null);
			
			Shift currentShift = ShiftUtil.getCurrentShift();
			
			UserDAO userDAO = new UserDAO();
			List<User> loggedInUsers = userDAO.getClockedInUser(terminal);
			for (User user : loggedInUsers) {
				AttendenceHistoryDAO attendenceHistoryDAO = new AttendenceHistoryDAO();

				AttendenceHistory attendenceHistory = attendenceHistoryDAO.findHistoryByClockedInTime(user);
				if (attendenceHistory == null) {
					attendenceHistory = new AttendenceHistory();
					Date lastClockInTime = user.getLastClockInTime();
					Calendar c = Calendar.getInstance();
					c.setTime(lastClockInTime);
					attendenceHistory.setClockInTime(lastClockInTime);
					attendenceHistory.setClockInHour((short) c.get(Calendar.HOUR));
					attendenceHistory.setUser(user);
					attendenceHistory.setTerminal(terminal);
					attendenceHistory.setShift(user.getCurrentShift());
				}

				user.doClockOut(attendenceHistory, currentShift, currentTime);
				user.doClockIn(terminal, currentShift, currentTime);
			}
		} catch (Exception ex) {
			POSMessageDialog.showError(com.pinpos.POSConstants.ERROR_MESSAGE, ex);
		} finally {
			posWindow.setGlassPaneVisible(false);
		}
	}

}
