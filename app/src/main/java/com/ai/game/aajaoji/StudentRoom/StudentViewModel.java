package com.ai.game.aajaoji.StudentRoom;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {

	public StudentRepo repo;

	public StudentViewModel(@NonNull Application application) {
		super(application);
		repo = new StudentRepo(application);
	}

	public void Insert(Student student)
	{
		repo.Insert(student);
	}

	public void Delete(Student student)
	{
		repo.Delete(student);
	}

	public void Update(Student student)
	{
		repo.Update(student);
	}

	public Student getStudent(String studentHash)
	{
		return repo.getStudent(studentHash);
	}

	public List<Student> getAllStudents()
	{
		return repo.getAllStudents();
	}

}
