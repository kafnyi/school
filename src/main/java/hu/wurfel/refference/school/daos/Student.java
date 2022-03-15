package hu.wurfel.refference.school.daos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Student implements DAO {
	@Id
	@Column(name = "SCID", nullable = false, length = 11,table = "student")
	private int ID;
	@Column(name = "SName", nullable = false, table = "student")
	private String Name;
	@Column(name = "SBirth", nullable = false, table = "student")
	private Date Birthdate;
	@Column(name = "SDiaryID", nullable = false, table = "student")
	private int Diary;

	public Student() {}
	public Student (int Id, String Nm, Date BD, int Dr){
		this.ID=Id;
		this.Name= Nm;
		this.Birthdate=BD;
		this.Diary=Dr;
	}

	public int getID() {
		return ID;
	}
	public void setID(int SCNumber) {
		this.ID = SCNumber;
	}

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}

	public Date getBirthdate() {
		return Birthdate;
	}
	public void setBirthdate(Date SBirthdate) {
		this.Birthdate = SBirthdate;
	}

	public int getDiary() {
		return Diary;
	}
	public void setDiary(int SDiary) {
		this.Diary = SDiary;
	}

	@Override
	public String toString() {
		return "Student{" +
				"Card Number=" + ID +
				", Name='" + Name + '\'' +
				", Birthdate=" + Birthdate +
				", Diary=" + Diary +
				'}';
	}
}
