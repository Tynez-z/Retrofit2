package com.example.retrofit2.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit2.api.Article
import com.example.retrofit2.R
import kotlinx.android.synthetic.main.item_article.view.*

class NewsAdapter(
    private var titles: List<String>,
    private var details: List<String>,
    private var images: List<String>,
    private var links: List<String>
) : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTitle: TextView = itemView.tv_title
        val itemDescription: TextView = itemView.tv_description
        val itemImg: ImageView = itemView.iv_article_image

        init {
            itemView.setOnClickListener { v: View? ->
                val position: Int = adapterPosition
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(links[position])
                ContextCompat.startActivity(itemView.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false))

    override fun getItemCount() = titles.size


    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemDescription.text = details[position]
        Glide.with(holder.itemImg).load(images[position]).into(holder.itemImg)
    }
}