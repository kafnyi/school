package hu.wurfel.refference.school.model;


public class StudentSearchRequestDto {
	private String id;
	private String name;
	private String birth;
	private String diary;

	public StudentSearchRequestDto(String ID, String name, String birth, String diary) {
		this.id = ID;
		this.name = name;
		this.birth = birth;
		this.diary = diary;
	}

	public StudentSearchRequestDto() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getDiary() {
		return diary;
	}

	public void setDiary(String diary) {
		this.diary = diary;
	}
}
