package com.example.androidminiprojet.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import com.example.androidminiprojet.R;
import com.example.androidminiprojet.database.DatabaseHelper;
import com.example.androidminiprojet.models.User;
import com.example.androidminiprojet.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UserAdapter extends ArrayAdapter<User> {

    public UserAdapter(Context context, List<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }

        // Get the current user object
        User user = getItem(position);

        // Find the views by their IDs
        TextView nameView = convertView.findViewById(R.id.userName);
        TextView emailView = convertView.findViewById(R.id.userEmail);
        Button syncButton = convertView.findViewById(R.id.syncButton);

        // Set the user data to the views
        if (user != null) {
            nameView.setText(user.getName());
            emailView.setText(user.getEmail());
        }

        // Set the click listener for the sync button
        syncButton.setOnClickListener(v -> {
            new AlertDialog.Builder(getContext())
                    .setTitle("Synchronisation")
                    .setMessage("Synchroniser l'utilisateur: " + user.getName() + "?")
                    .setPositiveButton("Oui", (dialog, which) -> syncUser(user, syncButton))
                    .setNegativeButton("Non", null)
                    .show();
        });

        // If the user is already synced, hide the sync button
        if (user != null && user.isSynced()) {
            syncButton.setVisibility(View.GONE);
        } else {
            syncButton.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    private void syncUser(User user, Button syncButton) {
        // Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/api/auth/") // Replace with your backend URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create API service
        ApiService apiService = retrofit.create(ApiService.class);

        // Call the API to synchronize the user
        apiService.createUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Synchronisation réussie", Toast.LENGTH_SHORT).show();
                    if (user != null) {
                        user.setSynced(true); // Mark as synced
                        try {
                            DatabaseHelper dbHelper = new DatabaseHelper(getContext());
                            dbHelper.getUserDao().update(user); // Update user in the local database
                            syncButton.setVisibility(View.GONE); // Hide the sync button
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Toast.makeText(getContext(), "Échec de la synchronisation: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), "Échec de la synchronisation", Toast.LENGTH_LONG).show();
            }
        });
    }
}