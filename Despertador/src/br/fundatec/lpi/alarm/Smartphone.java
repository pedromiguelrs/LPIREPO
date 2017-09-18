package br.fundatec.lpi.alarm;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Smartphone implements Alarm {

	private String ds_status;
	private AlarmJb alarm;
	private AlarmDao alDao;

	public Smartphone(Connection con) {
		super();
		this.alarm = new AlarmJb();
		this.alDao = new AlarmDao(con);
		this.alDao.create();
		turnOn();
	}

	public String getDs_status() {
		return ds_status;
	}

	public void turnOn() {
		this.ds_status = "ON";
	}

	public void turnOff() {
		this.ds_status = "OFF";
	}

	@Override
	public void addNewAlarm(String reason, String time) {
		this.alarm.setReason(reason);
		this.alarm.setTime(time);
		this.alDao.insert(this.alarm);
	}

	@Override
	public void removeAlarm(String reason) {
		this.alDao.deleteAlarmByReason(reason);
	}

	@Override
	public void updateTimeAlarm(long id, String time) {
		this.alarm.setId(id);
		this.alarm.setTime(time);
		this.alDao.updateAlarm(this.alarm);
	}

	@Override
	public void getAlarm() {
		List<AlarmJb> alarms = new ArrayList<AlarmJb>();
		alarms = this.alDao.getList();
		System.out.println(" ");
		System.out.println("Star Select");
		for (AlarmJb alarmJb : alarms) {
			System.out.println("---------");
			System.out.println("ID: " + alarmJb.getId());
			System.out.println("REASON: " + alarmJb.getReason());
			System.out.println("TIME: " + alarmJb.getTime());
			System.out.println("---------");
		}
		System.out.println("End Select");
		System.out.println(" ");
	}

}
