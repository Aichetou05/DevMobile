package com.example.androidminiprojet.models;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "users")
public class User {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    @SerializedName("name")
    private String name;

    @DatabaseField
    @SerializedName("email")
    private String email;

    @DatabaseField
    @SerializedName("password")
    private String password;

    @DatabaseField
    @SerializedName("synced")
    private boolean synced = false;

    // Constructeur vide (requis par ORMLite)
    public User() {}

    // Constructeur personnalisé
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.synced = false;
    }

    // Getters et Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSynced() {
        return synced;
    }

    public void setSynced(boolean synced) {
        this.synced = synced;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
