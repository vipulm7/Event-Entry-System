package com.ai.game.aajaoji;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ai.game.aajaoji.StudentRoom.Student;
import com.ai.game.aajaoji.StudentRoom.StudentViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	StudentViewModel studentViewModel;

	SharedPreferences sharedPreferences;
	boolean first_time;

	String TAG = "TestTAG";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

//		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		sharedPreferences = this.getSharedPreferences("com.manan.eventLogin", Context.MODE_PRIVATE);
		first_time = sharedPreferences.getBoolean("first_time", true);

		Log.d(TAG, "onCreate: "+first_time);

		if(first_time)
		{
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putBoolean("first_time", false);
			editor.apply();

			try {
				JSONObject object = new JSONObject(loadJSONFromAsset());
				JSONArray array = object.getJSONArray("users");
				int i=-1;
				while (++i<array.length()) {
					JSONObject studentData = array.getJSONObject(i);
					Student student = new Student(studentData.getString("Roll"), studentData.getString("Name"), studentData.getString("Email"), studentData.getString("Hash"), i+1);

//					Log.d(TAG, "onCreate: "+student.name);
					studentViewModel.Insert(student);
				}
				Log.d(TAG, "onCreate: i = "+i);

			} catch (JSONException e) {
				Log.d(TAG, "onCreate: catch came");
				e.printStackTrace();
			}
			AsyncTask.execute(new Runnable() {
				@Override
				public void run() {
					try{
						JSONObject object = new JSONObject(loadJSONFromAsset());
						JSONArray array = object.getJSONArray("users");

						if(studentViewModel.getAllStudents().size() == array.length()){
							Intent intent=new Intent(getApplicationContext(), ScanActivity.class);
							startActivity(intent);
							finish();
						}
					}
					catch (JSONException e){
						e.printStackTrace();
					}

				}
			});
		}
		else{
			Intent intent=new Intent(getApplicationContext(), ScanActivity.class);
			startActivity(intent);
			finish();
		}
	}

	public String loadJSONFromAsset()
	{
		String json = null;
		try {
			InputStream inputStream = getAssets().open("first_year.json");
			getAssets();
			int size = inputStream.available();
			byte[] buffer = new byte[size];
			inputStream.read(buffer);
			inputStream.close();
			json = new String(buffer, StandardCharsets.UTF_8);

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return json;
	}
}
