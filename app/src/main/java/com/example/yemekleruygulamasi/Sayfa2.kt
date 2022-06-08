package com.example.yemekleruygulamasi

import android.app.Activity
import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yemekleruygulamasi.entity.Yemekler

@Composable
fun Sayfa2(yemek: Yemekler) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Order")},
                contentColor =MaterialTheme.colors.secondary ,
                backgroundColor = Color.Black
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 30.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start,
            ) {
                val activity = (LocalContext.current as Activity)
                Image(bitmap = ImageBitmap.imageResource(id = activity.resources.getIdentifier(
                    yemek.yemek_resim_adi,"drawable",activity.packageName
                ) ), contentDescription = "",modifier = Modifier
                    .size(250.dp)
                    .clip(
                        RoundedCornerShape(corner = CornerSize(16.dp))
                    ))
                Row(modifier = Modifier.padding(all = 5.dp)) {
                    Text(text = "${yemek.yemek_adi}", fontSize =35.sp,color= colorResource(id = R.color.anaRenk))
                }
                Row(modifier = Modifier.padding(all = 5.dp)) {
                    Icon(painter = painterResource(id = R.drawable.ic_baseline_star_24), contentDescription = "star",
                        )
                    Spacer(modifier = Modifier.padding(start = 5.dp, end = 5.dp))
                    Text(text = "4,5 (50+)")
                }
                Row(modifier = Modifier.padding(5.dp)) {
                   
                    Text(text = "$",color =colorResource(id = R.color.anaRenk), fontSize = 15.sp)
                    Text(text = "${yemek.yemek_fiyat} ",color =colorResource(id = R.color.anaRenk),fontSize = 25.sp)
                }
              
                Button(onClick = {
                },
                    modifier = Modifier.size(250.dp,50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.anaRenk),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Add to cart")
                }
            }
        }
    )
}

