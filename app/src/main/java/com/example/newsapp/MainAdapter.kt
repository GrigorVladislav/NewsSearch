package com.example.newsapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.DTO.NewsEntity
import com.example.newsapp.DTO.Response
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_view.view.*
import java.lang.Exception


object MainAdapter:RecyclerView.Adapter<CustomViewHolder>(){
    var newsList = listOf<NewsEntity>()

    fun setList(list:List<NewsEntity>){

        newsList = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val card = layoutInflater.inflate(R.layout.card_view,parent,false)
        return CustomViewHolder(card)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        try {
            var news = newsList.get(position+1)
            holder.view.title.text = news.title
            holder.view.description.text = news.description
            Picasso.with(holder.view.context).load(news.urlToImage)
                .resize(100,100).into(holder.view.news_image)
            holder.ClickItem(position+1)
        }catch (e:Exception){

        }

    }
}

class CustomViewHolder(val view:View):RecyclerView.ViewHolder(view) {

    fun ClickItem ( id:Int){
        view.setOnClickListener{
            val inten = Intent(view.context,DetailNewsActivity::class.java)
            inten.putExtra("id", id)
            view.context.startActivity(inten)
        }
    }
}
