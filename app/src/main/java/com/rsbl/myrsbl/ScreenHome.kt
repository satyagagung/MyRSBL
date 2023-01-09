package com.rsbl.myrsbl

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
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
import com.rsbl.myrsbl.data.Datasource
import com.rsbl.myrsbl.model.News
import kotlinx.coroutines.delay

@Composable
fun ScreenMain(){

    MainScreen(newsList = Datasource().loadNews())
    BackPressEvent()
}

@Composable
fun MainScreen(newsList: List<News>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
    ){
        item {
            Row(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Article",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.weight(1f))
                TextButton(
                    onClick = { /*TODO*/ },
                ) {
                    Text(
                        text = "See All",
                        style = MaterialTheme.typography.body1
                    )
                }
            }
            LazyRow {
                items(newsList) { news ->
                    NewsCard(news)
                }
            }
        }
        item{

        }
    }

}

@Composable
fun NewsCard(news: News, modifier: Modifier = Modifier) {
    Card(modifier = Modifier
        .padding(8.dp),
        elevation = 4.dp
    ) {
        Column {
            Image(
                painter = painterResource(news.imageResourceId),
                contentDescription = stringResource(news.stringResourceId),
                modifier = Modifier
                    .width(LocalConfiguration.current.screenWidthDp.dp - 32.dp)
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = LocalContext.current.getString(news.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.h6
            )
        }
    }
}


sealed class BackPress {
    object Idle : BackPress()
    object InitialTouch : BackPress()
}

@Composable
private fun BackPressEvent() {
    var showToast by remember { mutableStateOf(false) }
    var backPressState by remember { mutableStateOf<BackPress>(BackPress.Idle) }
    val context = LocalContext.current

    if(showToast){
        Toast.makeText(context, "Press again to exit", Toast.LENGTH_SHORT).show()
        showToast= false

    }

    LaunchedEffect(key1 = backPressState) {
        if (backPressState == BackPress.InitialTouch) {
            delay(1500)
            backPressState = BackPress.Idle
        }
    }

    BackHandler(backPressState == BackPress.Idle) {
        backPressState = BackPress.InitialTouch
        showToast = true
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    ScreenMain()
}