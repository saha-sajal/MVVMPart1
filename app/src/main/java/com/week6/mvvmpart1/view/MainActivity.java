package com.week6.mvvmpart1.view;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.week6.mvvmpart1.model.Note;
import com.week6.mvvmpart1.model.NoteAdapter;
import com.week6.mvvmpart1.R;
import com.week6.mvvmpart1.viewmodel.NoteViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private NoteViewModel noteViewModel;
    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;

    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        noteAdapter = new NoteAdapter();
        recyclerView.setAdapter(noteAdapter);


        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                noteAdapter.setNotes(notes);
            }
        });

        floatingActionButton = findViewById(R.id.button_add_note);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                addNoteLauncher.launch(intent);
            }
        });


    }

    private ActivityResultLauncher<Intent> addNoteLauncher = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(), result->{
                if(result.getResultCode() == RESULT_OK)
                {
                     Intent data = result.getData();

                     String title = data.getStringExtra("Title");
                     String description = data.getStringExtra("Description");
                     int priority = data.getIntExtra("Priority",1);

                     Note note = new Note(title, description, priority);
                     noteViewModel.insert(note);
                     Toast.makeText(this, "Note Saved!", Toast.LENGTH_LONG).show();

                }

    });
}