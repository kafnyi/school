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

	public String getTName() {
		return TName;
	}

	public void setTName(String TName) {
		this.TName = TName;
	}

	public Date getTBirth() {
		return TBirth;
	}

	public void setTBirth(Date TBirth) {
		this.TBirth = TBirth;
	}

	public int getTDiaryID() {
		return TDiaryID;
	}

	public void setTDiaryID(int TDiaryID) {
		this.TDiaryID = TDiaryID;
	}

	@Override
	public String toString() {
		return "Teacher{" +
				"ID=" + ID +
				", Name='" + TName + '\'' +
				", Birthdate=" + TBirth +
				", DiaryID=" + TDiaryID +
				'}';
	}
}
