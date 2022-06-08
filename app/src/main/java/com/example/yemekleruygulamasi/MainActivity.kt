package com.example.yemekleruygulamasi

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.yemekleruygulamasi.ViewModel.AnasayfaViewModel
import com.example.yemekleruygulamasi.ViewModel.AnasayfaViewModelFac
import com.example.yemekleruygulamasi.entity.Yemekler
import com.example.yemekleruygulamasi.ui.theme.YemeklerUygulamasiTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YemeklerUygulamasiTheme {

                Surface(color = MaterialTheme.colors.background) {
                    SayfaGecisleri()
                }
            }
        }
    }
}

@Composable
fun SayfaGecisleri() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "sayfa1" ){
        composable("sayfa1"){
            Sayfa1(navController = navController)
        }
        composable("sayfa2/{yemek}",arguments = listOf(
            navArgument("yemek"){type = NavType.StringType}
        )){
            val json = it.arguments?.getString("yemek")
            val yemek = Gson().fromJson(json, Yemekler::class.java)
            Sayfa2(yemek = yemek)
        }
    }
}

@Composable
fun Sayfa1(navController: NavController) {
    val content= LocalContext.current
    val viewModel : AnasayfaViewModel= viewModel(
        factory = AnasayfaViewModelFac(content.applicationContext as Application)
    )
    val yemekListesi = viewModel.yemeklerListesi.observeAsState(listOf())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Best picks for you")},
                backgroundColor = Color.Black,
                contentColor = Color.White,

            )
        },
        content = {
            LazyColumn(modifier = Modifier.background(color = Color.Black)){
                items(
                    count = yemekListesi.value!!.count(),
                    itemContent = {
                        val yemek = yemekListesi.value!![it]
                        Card(modifier = Modifier
                            .padding(all = 5.dp)
                            .fillMaxWidth(), shape = RoundedCornerShape(corner = CornerSize(16.dp))) {
                            Row(modifier = Modifier.clickable {
                                val yemekJson = Gson().toJson(yemek)
                                navController.navigate("sayfa2/$yemekJson")
                            }) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .padding(all = 10.dp)
                                        .fillMaxWidth()
                                ) {
                                    val activity = (LocalContext.current as Activity)
                                    Image(bitmap = ImageBitmap.imageResource(id = activity.resources.getIdentifier(
                                        yemek.yemek_resim_adi,"drawable",activity.packageName
                                    ) ), contentDescription = "",modifier = Modifier
                                        .size(100.dp)
                                        .clip(
                                            RoundedCornerShape(corner = CornerSize(16.dp))
                                        ))
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Column(
                                            verticalArrangement = Arrangement.SpaceEvenly,
                                            modifier = Modifier.fillMaxHeight()
                                        ) {
                                            Text(text = yemek.yemek_adi,fontSize = 20.sp, modifier = Modifier.padding(start = 10.dp), color = Color.Black)
                                            Spacer(modifier = Modifier.size(30.dp))
                                            Text(text = "${yemek.yemek_fiyat} $",color = colorResource(
                                                id = R.color.anaRenk), modifier = Modifier.padding(all = 10.dp))
                                        }
                                        Icon(painter = painterResource(id = R.drawable.arrow_resim),
                                            contentDescription = "")
                                    }
                                }
                            }
                        }
                    }
                )
            }
        }
    ) 
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    YemeklerUygulamasiTheme {

    }
}