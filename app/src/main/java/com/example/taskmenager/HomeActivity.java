package com.example.taskmenager;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.*;

public class HomeActivity extends AppCompatActivity {

    TextView todayDate;
    EditText newTaskInput, dontForgetInput, noteInput;
    Button addTaskButton;
    ListView taskListView;
    ArrayAdapter<String> taskAdapter;
    ArrayList<String> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialiser les vues
        todayDate = findViewById(R.id.todayDate);
        newTaskInput = findViewById(R.id.newTaskInput);
        addTaskButton = findViewById(R.id.addTaskButton);
        taskListView = findViewById(R.id.taskListView);
        dontForgetInput = findViewById(R.id.dontForgetInput);
        noteInput = findViewById(R.id.noteInput);

        // Afficher la date actuelle
        String date = new SimpleDateFormat("EEEE dd MMMM yyyy", Locale.getDefault()).format(new Date());
        todayDate.setText(date);

        // To-Do List setup
        taskList = new ArrayList<>();
        taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        taskListView.setAdapter(taskAdapter);

        // Ajouter une tâche
        addTaskButton.setOnClickListener(v -> {
            String task = newTaskInput.getText().toString().trim();
            if (!task.isEmpty()) {
                taskList.add(task);
                taskAdapter.notifyDataSetChanged();
                newTaskInput.setText("");
            } else {
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show();
            }
        });

        // Long click pour supprimer une tâche
        taskListView.setOnItemLongClickListener((parent, view, position, id) -> {
            taskList.remove(position);
            taskAdapter.notifyDataSetChanged();
            return true;
        });
    }
}
