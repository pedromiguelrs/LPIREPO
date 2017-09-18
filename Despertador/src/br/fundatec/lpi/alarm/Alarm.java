package br.fundatec.lpi.alarm;

public interface Alarm {

	public void addNewAlarm(String reason, String time);
	public void removeAlarm(String reason);
	public void updateTimeAlarm(long id, String time);
	public void getAlarm();
}
