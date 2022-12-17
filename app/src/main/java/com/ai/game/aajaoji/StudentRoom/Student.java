package com.ai.game.aajaoji.StudentRoom;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_table")
public class Student
{
	@PrimaryKey
	public long sr_no;

	public String roll;
	public String name;
	public String email;
	public String hashed;
	public boolean isValid;

	public Student(String roll, String name, String email, String hashed, long sr_no)
	{
		this.roll = roll;
		this.name = name;
		this.email = email;
		this.hashed = hashed;
		isValid = true;
		this.sr_no=sr_no;
	}
}
