package com.ai.game.aajaoji;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ai.game.aajaoji.StudentRoom.Student;
import com.ai.game.aajaoji.StudentRoom.StudentViewModel;

public class MainActivity extends AppCompatActivity {

	EditText EThash;
	Button Bsearch;
	TextView name, email, roll;

	StudentViewModel studentViewModel;

	int desk_no;
	SharedPreferences sharedPreferences;
	ActionBar actionBar;

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

		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		desk_no = sharedPreferences.getInt("desk_no", -1);

		if(desk_no == -1)
		{
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
}
