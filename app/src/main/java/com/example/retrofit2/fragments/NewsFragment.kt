package com.example.retrofit2.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit2.Constants.Companion.BASE_URL
import com.example.retrofit2.api.NewsResponse
import com.example.retrofit2.R
import com.example.retrofit2.adapter.NewsAdapter
import com.example.retrofit2.api.NewsAPI
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsFragment : Fragment(R.layout.fragment_news) {


    private var titleList = mutableListOf<String>()
    private var descriptionList = mutableListOf<String>()
    private var imagesList = mutableListOf<String>()
    private var linksList = mutableListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makeApiRequest()
    }

    private fun setUpRecyclerView() {
        rv_breaking_news.layoutManager = LinearLayoutManager(context)
        rv_breaking_news.adapter = NewsAdapter(titleList, descriptionList, imagesList, linksList)
    }

    private fun addToList(title: String, description: String, image: String, link: String) {
        titleList.add(title)
        descriptionList.add(description)
        imagesList.add(image)
        linksList.add(link)
    }

    private fun makeApiRequest() {
        pg_progress_bar.visibility = View.VISIBLE
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPI::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: NewsResponse = api.getNews()
                for (article in response.articles) {
                    Log.i("MainActivity", "Results = $article")
                    addToList(article.title, article.description, article.urlToImage, article.url)
                }
                withContext(Dispatchers.Main) {
                    setUpRecyclerView()
                    pg_progress_bar.visibility = View.GONE
                }
            } catch (e: Exception) {
                Log.e("MainActivity", e.toString())
                withContext(Dispatchers.Main) {
                }
            }
        }
    }
}