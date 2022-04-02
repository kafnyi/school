package hu.wurfel.refference.school.model.daos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Teacher {
	@Id
	@Column(name = "TID", nullable = false, table = "teacher", length = 6)
	private Long ID;
	@Column(name = "TName", nullable = false, table = "teacher")
	private String TName;
	@Column(name = "TBirth", nullable = false, table = "teacher")
	private Date TBirth;
	@Column(name = "TDiaryID", nullable = false, table = "teacher")
	private int TDiaryID;

	public Teacher() {
	}

	public Teacher(Long ID, String name, Date birthdate, int diaryID) {
		this.ID = ID;
		TName = name;
		TBirth = birthdate;
		TDiaryID = diaryID;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public String getName() {
		return TName;
	}

	public void setName(String TName) {
		this.TName = TName;
	}

	public Date getBirth() {
		return TBirth;
	}

	public void setBirth(Date TBirth) {
		this.TBirth = TBirth;
	}

	public int getDiaryID() {
		return TDiaryID;
	}

	public void setDiaryID(int TDiaryID) {
		this.TDiaryID = TDiaryID;
	}

	@Override
	public String toString() {
		return "Teacher{" + "ID=" + ID + ", Name='" + TName + '\'' + ", Birthdate=" + TBirth + ", DiaryID=" + TDiaryID
				+ '}';
	}
}
