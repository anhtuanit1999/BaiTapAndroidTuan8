package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE user.id = (:userId)")
    User getUserById(int userId);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUser(User... users);

    @Delete
    void delete(User user);
}
