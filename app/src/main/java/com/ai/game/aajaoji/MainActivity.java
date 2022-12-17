package com.ai.game.aajaoji;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
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

	EditText EThash;
	Button Bsearch;
	TextView name, email, roll;

	StudentViewModel studentViewModel;

	int desk_no;
	SharedPreferences sharedPreferences;
	ActionBar actionBar;

	ArrayList<String> students = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

		Bsearch=findViewById(R.id.BSearch);
		EThash=findViewById(R.id.ETHashString);
		name=findViewById(R.id.TVName);
		email=findViewById(R.id.TVEmail);
		roll=findViewById(R.id.TVRoll);
		actionBar=getSupportActionBar();

//		try {
//			JSONObject object = new JSONObject(loadJSONFromAsset());
//			JSONArray array = object.getJSONArray("users");
//			for(int i=-1;++i<array.length();)
//			{
//				JSONObject studentData = array.getJSONObject(i);
//				String student = studentData.getString("name");
//				students.add(student);
//			}
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}

//		for(int i=-1;++i<students.size();)
//		{
//			Log.d("TAG", "onCreate: "+students.get(i));
//		}

		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		desk_no = sharedPreferences.getInt("desk_no", -1);

		Log.d("TAGvipul", "onCreate: "+desk_no);

		if(desk_no == -1)
		{
			try {
				JSONObject object = new JSONObject(loadJSONFromAsset());
				JSONArray array = object.getJSONArray("users");
				for(int i=-1;++i<array.length();)
				{
					JSONObject studentData = array.getJSONObject(i);
					Student student = new Student(studentData.getString("Roll"), studentData.getString("Name"), studentData.getString("Email"), studentData.getString("Hash"), i+1);

					Log.d("TAG", "onCreate: "+student.name);
					studentViewModel.Insert(student);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			Intent intent = new Intent(this, DeskActivity.class);

			startActivity(intent);
			finish();
		}
		else
		{
			desk_no=sharedPreferences.getInt("desk_no", -1);
			actionBar.setTitle("Desk No "+desk_no);

			Intent intent=new Intent(this, ScanActivity.class);
			startActivity(intent);
			finish();
		}



//		Bsearch.setOnClickListener(v -> {
//			String hash=EThash.getText().toString();
//
//			Student student = studentViewModel.getStudent(hash);
//			EThash.setText("");
//
//			if(student != null)
//			{
//				if(student.isValid)
//				{
//					name.setText(student.name);
//					email.setText(student.email);
//					roll.setText(""+student.roll);
//
//					student.isValid=false;
//					studentViewModel.Update(student);
//				}
//				else
//				{
//					name.setText("Student ID already used");
//					email.setText("");
//					roll.setText("");
//				}
//			}
//			else
//			{
//				name.setText("Student Does Not Exist");
//				email.setText("");
//				roll.setText("");
//			}
//		});
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
