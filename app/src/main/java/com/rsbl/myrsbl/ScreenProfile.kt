package com.rsbl.myrsbl

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rsbl.myrsbl.model.News

@Composable
fun ScreenProfile(){
    ProfileScreen()
}

@Composable
fun SingleCard(){
    Card(modifier = Modifier
        .padding(8.dp),
        elevation = 4.dp
    ) {
        Column {
            Image(
                painter = painterResource(R.drawable.logo_rsbl_1),
                contentDescription = stringResource(R.string.news_header),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = LocalContext.current.getString(R.string.news_header),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview
@Composable
fun ProfileScreen(){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
    ){
        item{
            SingleCard()

        }
    }
}