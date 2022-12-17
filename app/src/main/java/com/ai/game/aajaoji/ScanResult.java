package com.ai.game.aajaoji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ai.game.aajaoji.StudentRoom.Student;
import com.ai.game.aajaoji.StudentRoom.StudentViewModel;

public class ScanResult extends AppCompatActivity {

    StudentViewModel studentViewModel;

    TextView tvName,tvEmail,tvRoll, tvAlreadyPresent;
    Button bCancel, bConfirm;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        tvEmail = findViewById(R.id.TVEmail);
        tvName = findViewById(R.id.TVName);
        tvRoll = findViewById(R.id.TVRoll);
        tvAlreadyPresent = findViewById(R.id.TVAlreadyPresent);
        bCancel = findViewById(R.id.BCancel);
        bConfirm = findViewById(R.id.BConfirm);

        String result = getIntent().getStringExtra("Result");   // Result --> Hash Value
        student = studentViewModel.getStudent(result);

        if(student != null)
            Log.d("TAG", "onCreate: true/false = "+student.isValid);

        if(student != null && student.isValid){
            tvEmail.setText(student.email);
            tvRoll.setText(student.roll);
            tvName.setText(student.name);

            tvAlreadyPresent.setVisibility(View.INVISIBLE);
            tvRoll.setVisibility(View.VISIBLE);
            bConfirm.setEnabled(true);
        }
        else if(student != null)
        {
            tvEmail.setText(student.email);
            tvRoll.setText(student.roll);
            tvName.setText(student.name);

            tvAlreadyPresent.setVisibility(View.VISIBLE);
            tvRoll.setVisibility(View.VISIBLE);
            bConfirm.setEnabled(false);
        }
        else
        {
            tvName.setText("Student not found!!");

            tvAlreadyPresent.setVisibility(View.INVISIBLE);
            tvRoll.setVisibility(View.INVISIBLE);
            tvEmail.setVisibility(View.INVISIBLE);
            bConfirm.setEnabled(false);
        }

        bConfirm.setOnClickListener(v->{
            student.isValid = false;
            studentViewModel.Update(student);

            onBackPressed();
        });

        bCancel.setOnClickListener(v->{
            onBackPressed();
        });
    }
}
