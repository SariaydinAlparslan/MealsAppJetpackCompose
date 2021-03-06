package com.example.yemekleruygulamasi.repo

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.yemekleruygulamasi.Room.VeriTaban─▒
import com.example.yemekleruygulamasi.entity.Yemekler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class YemeklerDaoRepository(var application: Application) {
    var yemeklerListesi= MutableLiveData<List<Yemekler>>()
    var vt : VeriTaban─▒
    init {
        vt=VeriTaban─▒.veritabaniErisim(application)!!
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