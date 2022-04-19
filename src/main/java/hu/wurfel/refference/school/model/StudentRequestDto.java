package hu.wurfel.refference.school.model;


public class StudentRequestDto {
	private String ID;
	private String Name;
	private String Birth;
	private String Diary;

	public StudentRequestDto(String ID, String name, String birth, String diary) {
		this.ID = ID;
		this.Name = name;
		this.Birth = birth;
		this.Diary = diary;
	}

	public StudentRequestDto() {
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getBirth() {
		return Birth;
	}

	public void setBirth(String birth) {
		this.Birth = birth;
	}

	public String getDiary() {
		return Diary;
	}

	public void setDiary(String diary) {
		this.Diary = diary;
	}
}
