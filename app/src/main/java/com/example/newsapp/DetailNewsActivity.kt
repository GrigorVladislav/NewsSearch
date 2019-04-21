package com.example.newsapp

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.newsapp.DTO.NewsEntity
import kotlinx.android.synthetic.main.activity_detail_news.*


class DetailNewsActivity : AppCompatActivity() {

    val adapter = MainAdapter
    lateinit var news:NewsEntity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news)

        if (getId() < 0){
            Toast.makeText(this, "Somethink going wrong!!", Toast.LENGTH_SHORT).show()
        }else{
            news = adapter.newsList.get(getId())
        }
    }

    override fun onResume() {
        super.onResume()
        more_details_button.setOnClickListener{
            followTheLink()
        }
        setContent()
    }

    private fun followTheLink() {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(news.url)
        startActivity(openURL)
    }

    private fun setContent() {
        title_details.text = news.title
        content_details.text = news.content
    }

    private fun getId():Int  {
        if (intent.extras!=null){
            return intent.getIntExtra("id",-1)
        }
        return -1
    }

}
