package com.ai.game.aajaoji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ai.game.aajaoji.StudentRoom.Student;
import com.ai.game.aajaoji.StudentRoom.StudentViewModel;

import java.util.List;

public class DeskActivity extends AppCompatActivity {

	EditText etdesk;
	Button bdesk;

	StudentViewModel studentViewModel;

	SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_desk);

		studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

		etdesk=findViewById(R.id.ETDesk);
		bdesk=findViewById(R.id.Bdesk);

		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

		bdesk.setOnClickListener(v -> {
			if(!etdesk.getText().toString().equals(""))
			{
				int givenDeskNo=Integer.parseInt(etdesk.getText().toString());

				List<Student> students=studentViewModel.getAllStudents();
				for(int i=-1;++i<students.size();)
				{
					if(students.get(i).deskNo!=givenDeskNo)
					{
						studentViewModel.Delete(students.get(i));
						Toast.makeText(this, "removed "+students.get(i).sr_no, Toast.LENGTH_LONG).show();
					}
				}

				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putInt("desk_no", givenDeskNo);
				editor.apply();

				Intent intent =new Intent(this, ScanActivity.class);
				intent.putExtra("desk_no", givenDeskNo);
				startActivity(intent);
				finish();
			}
			else
			{
				//gaand mrao
			}

		});
	}
}