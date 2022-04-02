package hu.wurfel.refference.school.model.daos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Student {
	@Id
	@Column(name = "SCID", nullable = false, length = 11, table = "student")
	private Long ID;
	@Column(name = "SName", nullable = false, table = "student")
	private String SName;
	@Column(name = "SBirth", nullable = false, table = "student")
	private Date SBirth;
	@Column(name = "SDiaryID", nullable = false, table = "student")
	private int SDiaryID;

	public Student() {
	}

	public Student(Long Id, String Nm, Date BD, Integer Dr) {
		this.ID = Id;
		this.SName = Nm;
		this.SBirth = BD;
		this.SDiaryID = Dr;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long SCNumber) {
		this.ID = SCNumber;
	}

	public String getName() {
		return SName;
	}

	public void setName(String name) {
		this.SName = name;
	}

	public Date getSBirth() {
		return SBirth;
	}

	public void setSBirth(Date SBirthdate) {
		this.SBirth = SBirthdate;
	}

	public int getSDiaryID() {
		return SDiaryID;
	}

	public void setSDiaryID(int SDiary) {
		this.SDiaryID = SDiary;
	}

	@Override
	public String toString() {
		return "Student{" +
				"Card Number=" + ID +
				", Name='" + SName + '\'' +
				", Birthdate=" + SBirth +
				", Diary=" + SDiaryID +
				'}';
	}
}
