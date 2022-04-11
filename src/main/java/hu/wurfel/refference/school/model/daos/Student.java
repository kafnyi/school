package hu.wurfel.refference.school.model.daos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
	@Id
	@Column(name = "SCID", nullable = false, length = 11, table = "student")
	private long ID;
	@Column(name = "SName", nullable = false, table = "student")
	private String SName;
	@Column(name = "SBirth", nullable = false, table = "student")
	private String SBirth;
	@Column(name = "SDiaryID", nullable = false, table = "student")
	private int SDiaryID;

	public Student() {
	}

	public Student(long Id, String Nm, String BD, int Dr) {
		this.ID = Id;
		this.SName = Nm;
		this.SBirth = BD;
		this.SDiaryID = Dr;
	}

	public long getID() {
		return ID;
	}

	public void setID(long SCNumber) {
		this.ID = SCNumber;
	}

	public String getName() {
		return SName;
	}

	public void setName(String name) {
		this.SName = name;
	}

	public String getSBirth() {
		return SBirth;
	}

	public void setSBirth(String SBirthdate) {
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
