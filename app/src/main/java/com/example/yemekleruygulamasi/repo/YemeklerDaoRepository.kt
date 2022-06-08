package com.example.yemekleruygulamasi.repo

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.yemekleruygulamasi.Room.VeriTabanı
import com.example.yemekleruygulamasi.entity.Yemekler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class YemeklerDaoRepository(var application: Application) {
    var yemeklerListesi= MutableLiveData<List<Yemekler>>()
    var vt : VeriTabanı
    init {
        vt=VeriTabanı.veritabaniErisim(application)!!
        yemeklerListesi= MutableLiveData()

    }
    fun yemeklerigetir():MutableLiveData<List<Yemekler>>{
        return yemeklerListesi
    }
    fun tumyemekleriAl(){
        val job:Job= CoroutineScope(Dispatchers.Main).launch {
            yemeklerListesi.value=vt.yemeklerDao().tumyemekler()
        }

    }
}