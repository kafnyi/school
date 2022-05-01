package hu.wurfel.refference.school.model;

import java.util.Date;

public class StudentSearchDto {
	private long ID;
	private String Name;
	private Date Date;
	private int Diary;

	public StudentSearchDto(long ID, String name, Date date, int diary) {
		this.ID = ID;
		Name = name;
		Date = date;
		Diary = diary;
	}

	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		Date = date;
	}

	public int getDiary() {
		return Diary;
	}

	public void setDiary(int diary) {
		Diary = diary;
	}
}
