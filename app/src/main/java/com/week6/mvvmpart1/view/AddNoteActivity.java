package com.week6.mvvmpart1.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.week6.mvvmpart1.R;

public class AddNoteActivity extends AppCompatActivity {


    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;

    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);


        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPickerPriority = findViewById(R.id.number_picker_priority);

        saveButton = findViewById(R.id.buttonSave);


        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = editTextTitle.getText().toString();

                String description = editTextDescription.getText().toString();

                int priority = numberPickerPriority.getValue();

                Intent data = new Intent();

                data.putExtra("Title", title);
                data.putExtra("Description", description);
                data.putExtra("Priority", priority);

                setResult(RESULT_OK, data);

                finish();


            }
        });



    }
}