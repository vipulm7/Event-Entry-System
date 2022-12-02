package com.ai.game.aajaoji.StudentRoom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDAO
{
	@Insert
	void Insert(Student student);

	@Update
	void Update(Student student);

	@Delete
	void Delete(Student student);

	@Query("SELECT * FROM student_table WHERE hashed=:studentHash")
	Student getStudent(String studentHash);

	@Query("SELECT * FROM student_table")
	List<Student> getAllStudents();
}
