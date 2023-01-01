package com.rsbl.myrsbl.data

import com.rsbl.myrsbl.R
import com.rsbl.myrsbl.model.News

class Datasource() {
    fun loadNews(): List<News> {
        return listOf<News>(
            News(R.string.news_header, R.drawable.logo_rsbl_1),
            News(R.string.news_header, R.drawable.logo_rsbl_1),
            News(R.string.news_header, R.drawable.logo_rsbl_1),
            News(R.string.news_header, R.drawable.logo_rsbl_1),
            News(R.string.news_header, R.drawable.logo_rsbl_1))
    }
}