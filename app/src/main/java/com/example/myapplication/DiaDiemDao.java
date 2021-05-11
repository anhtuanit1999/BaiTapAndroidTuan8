package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DiaDiemDao {
    @Query("SELECT * FROM diadiem")
    List<DiaDiem> getAll();

    @Query("SELECT * FROM diadiem WHERE diadiem.id = (:diaDiemId)")
    DiaDiem getDiaDiemById(int diaDiemId);


    @Insert
    void add(DiaDiem... diaDiems);

    @Delete
    void delete(DiaDiem diaDiem);
}
