package com.ai.game.aajaoji.StudentRoom;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ai.game.aajaoji.R;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

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
//			studentDAO.Insert(new Student(20001003137L, "Vipul Mittal", "20001003137@jcboseust.ac.in", "8b48f467e195988ed7ccbd41b539332edea9a50127a3ef1bf5b279d9b2c8bf5", 1));
//			studentDAO.Insert(new Student(20001003109L, "Sameer", "20001003109@jcboseust.ac.in", "b34c91756c79700ecf8bcc1582c56966bf4df79ae8fbe32787bd988917087bd", 2));

			return null;
		}
	}
}
