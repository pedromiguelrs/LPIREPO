package br.fundatec.lpi.alarm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class AlarmDao {

	private Connection con;

	public AlarmDao(Connection con) {
		super();
		this.con = con;
	}

	public void create() {
		String sql = "create table alarm(" + "	id SERIAL PRIMARY KEY," + "    reason VARCHAR(255),"
				+ "    time VARCHAR(255)" + ");";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void insert(AlarmJb alarm) {
		String sql = "INSERT INTO alarm (reason, time) VALUES (?, ?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, alarm.getReason());
			stmt.setString(2, alarm.getTime());
			stmt.execute();
			stmt.close();
			System.out.println("Inserted");
		} catch (SQLException e) {
			System.out.println("Insert Error");
			e.printStackTrace();
		}
	}

	public List<AlarmJb> getList() {
		List<AlarmJb> alarms = new ArrayList<AlarmJb>();
		String sql = "SELECT * FROM alarm";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				AlarmJb alarm = new AlarmJb();
				alarm.setId(rs.getLong("id"));
				alarm.setReason(rs.getString("reason"));
				alarm.setTime(rs.getString("time"));
				alarms.add(alarm);
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Select error");
			e.printStackTrace();
		}
		return alarms;
	}

	public void deleteAlarms() {
		String sql = "DELETE FROM alarm";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.execute();
			stmt.close();
			System.out.println("Delete all from alarm");
		} catch (SQLException e) {
			System.out.println("Delete all error");
			e.printStackTrace();
		}

	}

	public void deleteAlarmByReason(String reason) {
		String sql = "DELETE FROM alarm WHERE reason = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, reason);
			stmt.execute();
			stmt.close();
			System.out.println("Alarm " + '"' +  reason + '"' + " Deleted");
		} catch (SQLException e) {
			System.out.println("delete error");
			e.printStackTrace();
		}

	}

	public void updateAlarm(AlarmJb updateAlarm) {
		String sql = "UPDATE alarm SET time = ? WHERE id = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, updateAlarm.getTime());
			stmt.setLong(2, updateAlarm.getId());
			stmt.execute();
			stmt.close();
			System.out.println("Alarm " + '"' + updateAlarm.getReason() + '"' + " upated");
		} catch (SQLException e) {
			System.out.println("Update error");
			e.printStackTrace();
		}

	}
}
