package com.example.yemekleruygulamasi.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemekleruygulamasi.entity.Yemekler
import com.example.yemekleruygulamasi.repo.YemeklerDaoRepository

class AnasayfaViewModel(application: Application): AndroidViewModel(application) {
    var yemeklerListesi= MutableLiveData<List<Yemekler>>()
    var yrepo =YemeklerDaoRepository(application)
    init {
        yemekleriYukle()
        yemeklerListesi=yrepo.yemeklerigetir()
    }
    fun yemekleriYukle(){
        yrepo.tumyemekleriAl()
    }
}