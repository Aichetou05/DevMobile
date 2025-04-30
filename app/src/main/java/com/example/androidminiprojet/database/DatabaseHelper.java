package com.example.androidminiprojet.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

import com.example.androidminiprojet.models.User;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "app_database.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<User, Integer> userDao;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            // Create the database table for User
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Error creating database", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            // Drop the old table and create a new one (Note: Data will be lost!)
            TableUtils.dropTable(connectionSource, User.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Error upgrading database", e);
        }
    }

    // Get DAO for User
    public Dao<User, Integer> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = getDao(User.class);
        }
        return userDao;
    }

    // Insert a new user into the database
    public void insertUser(User user) throws SQLException {
        getUserDao().create(user);
    }

    // Update an existing user in the database
    public void updateUser(User user) throws SQLException {
        getUserDao().update(user);
    }

    // Delete a user from the database
    public void deleteUser(User user) throws SQLException {
        getUserDao().delete(user);
    }

    // Get all users from the database
    public List<User> getAllUsers() throws SQLException {
        return getUserDao().queryForAll();
    }

    // Close the helper when no longer needed
    public void closeHelper() {
        super.close();
    }
}