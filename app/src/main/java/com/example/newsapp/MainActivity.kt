package com.example.newsapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.newsapp.DTO.Response
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


class MainActivity : AppCompatActivity() {
    val url : String = "https://newsapi.org/v2/everything?q="
    val apiKey : String = "&apiKey=fb7c9034fc2b462d9971fee554d6103d"
    var  adapter : MainAdapter = MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    recycler_news_list.layoutManager = LinearLayoutManager(this)
        recycler_news_list.adapter = adapter

    button_search.setOnClickListener{
        val searchInfo: String = search_field.text.toString()
        search(prapareUrl(searchInfo.trim().replace(" ", "")))
    }
    }

    private fun prapareUrl(searchInfo: String): String {
        return url + searchInfo + apiKey
    }

    private fun search(url: String) {

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback{
            override fun onResponse(call: Call, response: okhttp3.Response) {
                val body = response?.body()?.string()
                val gson = GsonBuilder().create()
                val content = gson.fromJson(body,Response::class.java)
                runOnUiThread {
                    adapter.setList(content.articles)
                }
            }
            override fun onFailure(call: Call, e: IOException) {

            }
        })

    }


}
