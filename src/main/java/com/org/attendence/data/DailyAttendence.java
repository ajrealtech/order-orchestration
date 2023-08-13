package com.org.attendence.data;

import java.time.LocalDate;
import java.util.Objects;

public class DailyAttendence {

	private Long id;
	
	private LocalDate todayDate;
	
	private String attendence;
	

	@Override
	public int hashCode() {
		return Objects.hash(id, todayDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DailyAttendence other = (DailyAttendence) obj;
		return Objects.equals(id, other.id) && Objects.equals(todayDate, other.todayDate);
	}

	public Long getId() {
		return id;
	}

	public String getAttendence() {
		return attendence;
	}

	public void setAttendence(String attendence) {
		this.attendence = attendence;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(LocalDate todayDate) {
		this.todayDate = todayDate;
	}

}
