package com.example.yemekleruygulamasi.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.yemekleruygulamasi.entity.Yemekler

//Veritabanına erişme ve İnterface i kullanma
@Database(entities = [Yemekler::class], version = 1)
abstract class VeriTabanı : RoomDatabase(){
    abstract fun yemeklerDao():YemeklerDao

    companion object{
        var INSTANCE : VeriTabanı?=null

        fun veritabaniErisim(context: Context):VeriTabanı?{
            if (INSTANCE == null){
                synchronized(VeriTabanı::class){
                    INSTANCE=
                        Room.databaseBuilder(context.applicationContext,VeriTabanı::class.java,"yemekler.sqlite")
                        .createFromAsset("yemekler.sqlite")
                        .build()
                }
            }
            return INSTANCE
        }
    }
}
