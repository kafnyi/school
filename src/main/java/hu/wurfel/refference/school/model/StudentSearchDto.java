package hu.wurfel.refference.school.model;

public class StudentSearchDto {
	private long ID;
	private String Name;
	private String Date;
	private int Diary;

	public StudentSearchDto(long ID, String name, String date, int diary) {
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

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public int getDiary() {
		return Diary;
	}

	public void setDiary(int diary) {
		Diary = diary;
	}
}
