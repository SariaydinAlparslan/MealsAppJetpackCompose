package com.example.yemekleruygulamasi.Room

import androidx.room.Dao
import androidx.room.Query
import com.example.yemekleruygulamasi.entity.Yemekler

@Dao
interface YemeklerDao {

    @Query("SELECT * FROM yemekler")
    suspend fun tumyemekler():List<Yemekler>
}