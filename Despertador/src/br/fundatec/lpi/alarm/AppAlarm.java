package br.fundatec.lpi.alarm;

import java.sql.Connection;
import java.sql.SQLException;

public class AppAlarm {

	public static void main(String[] args) throws SQLException {
		Connection con = new ConectionFactory().fabricate();
		Smartphone phone = new Smartphone(con);
		
		//Turning ON/OFF
		System.out.println("Status: " + phone.getDs_status());
		phone.turnOff();
		System.out.println("Status: " + phone.getDs_status());
		phone.turnOn();

		phone.addNewAlarm("Test", "20:00");
		phone.getAlarm();
		
		phone.addNewAlarm("Test Udate", "16:32");
		phone.getAlarm();
		phone.updateTimeAlarm(2, "20:30");
		phone.getAlarm();
		
		phone.addNewAlarm("Test delete", "00:00");
		phone.getAlarm();
		phone.removeAlarm("Test delete");
		phone.getAlarm();
		
		phone.turnOff();
		System.out.println("Status: " + phone.getDs_status());
		
		con.close();
	}

}
