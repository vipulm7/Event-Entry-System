package com.ai.game.aajaoji.StudentRoom;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

public class StudentRepo
{
	private StudentDAO studentDAO;
	public StudentDatabase studentDatabase;

	public StudentRepo(Application application)
	{
		studentDatabase = StudentDatabase.getInstance(application);
		studentDAO = studentDatabase.studentDAO();
	}

	public void Insert(Student student)
	{
		new InsertNoteASyncTask(studentDAO).execute(student);
	}

	public void Delete(Student student)
	{
		new DeleteNoteASyncTask(studentDAO).execute(student);
	}

	public void Update(Student student)
	{
		new UpdateNoteASyncTask(studentDAO).execute(student);
	}

	public Student getStudent(String studentHash)
	{
		return studentDAO.getStudent(studentHash);
	}

	public List<Student> getAllStudents()
	{
		return studentDAO.getAllStudents();
	}



	private static class InsertNoteASyncTask extends AsyncTask<Student, Void, Void>
	{
		private StudentDAO studentDAO;
		private InsertNoteASyncTask(StudentDAO studentDAO)
		{
			this.studentDAO=studentDAO;
		}

		@Override
		protected Void doInBackground(Student... students) {
			this.studentDAO.Insert(students[0]);
			return null;
		}
	}

	private static class DeleteNoteASyncTask extends AsyncTask<Student, Void, Void>
	{
		private StudentDAO studentDAO;
		private DeleteNoteASyncTask(StudentDAO studentDAO)
		{
			this.studentDAO=studentDAO;
		}

		@Override
		protected Void doInBackground(Student... students) {
			this.studentDAO.Delete(students[0]);
			return null;
		}
	}

	private static class UpdateNoteASyncTask extends AsyncTask<Student, Void, Void>
	{
		private StudentDAO studentDAO;
		private UpdateNoteASyncTask(StudentDAO studentDAO)
		{
			this.studentDAO=studentDAO;
		}

		@Override
		protected Void doInBackground(Student... students) {
			this.studentDAO.Update(students[0]);
			return null;
		}
	}


}
