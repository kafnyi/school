package hu.wurfel.refference.school.daos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Teacher {
	@Id
	@Column(name = "TID", nullable = false, table = "teacher", length = 6)
	private int ID;
	@Column(name = "TName", nullable = false, table = "teacher")
	private String Name;
	@Column(name = "TBirth", nullable = false, table = "teacher")
	private Date Birthdate;
	@Column(name = "TDiaryID", nullable = false, table = "teacher")
	private  int DiaryID;

	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}

	public Date getBirthdate() {
		return Birthdate;
	}
	public void setBirthdate(Date birthdate) {
		Birthdate = birthdate;
	}

	public int getDiaryID() {
		return DiaryID;
	}
	public void setDiaryID(int diaryID) {
		DiaryID = diaryID;
	}

	@Override
	public String toString() {
		return "Teacher{" +
				"ID=" + ID +
				", Name='" + Name + '\'' +
				", Birthdate=" + Birthdate +
				", DiaryID=" + DiaryID +
				'}';
	}
}
