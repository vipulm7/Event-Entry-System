package com.ai.game.aajaoji.StudentRoom;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Student.class, version = 1)
public abstract class StudentDatabase extends RoomDatabase {
	private static StudentDatabase instance;

	public abstract StudentDAO studentDAO();

	public static synchronized StudentDatabase getInstance(Context context)
	{
		if(instance==null)
			instance = Room.databaseBuilder(context.getApplicationContext(),
					StudentDatabase.class,
					"student_database")
					.fallbackToDestructiveMigration()
					.allowMainThreadQueries()
					.setJournalMode(JournalMode.TRUNCATE)
					.addCallback(roomCallback).build();

		return instance;
	}

	private static Callback roomCallback = new Callback() {
		@Override
		public void onCreate(@NonNull SupportSQLiteDatabase db) {
			super.onCreate(db);
			new PopulateDBAsyncTask(instance).execute();
		}
	};

	private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void>
	{
		StudentDAO studentDAO;

		private PopulateDBAsyncTask(StudentDatabase database)
		{
			studentDAO=database.studentDAO();
		}


		@Override
		protected Void doInBackground(Void... voids)
		{
			studentDAO.Insert(new Student(20001003137L, "Vipul Mittal", "20001003137@jcboseust.ac.in", "abcd", 1));
			studentDAO.Insert(new Student(20001003109L, "Sameer", "20001003109@jcboseust.ac.in", "abcde", 2));

			return null;
		}
	}

}
