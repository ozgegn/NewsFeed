package com.comenginar.newsfeed.ui.newsfeed

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.comenginar.newsfeed.R
import com.comenginar.newsfeed.databinding.AdapterNewsfeedListBinding
import com.comenginar.newsfeed.model.Article
import kotlinx.android.synthetic.main.adapter_newsfeed_list.view.*

class NewsFeedListAdapter(private val articles: List<Article>) :
    RecyclerView.Adapter<NewsFeedListAdapter.NewsFeedListViewHolder>() {

    private lateinit var binding: AdapterNewsfeedListBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsFeedListAdapter.NewsFeedListViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.adapter_newsfeed_list,
            parent,
            false
        )
        return NewsFeedListViewHolder(binding.root)
    }

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(
        holder: NewsFeedListAdapter.NewsFeedListViewHolder,
        position: Int
    ) {
        val article = articles[position]
        holder.bindArticle(article)
    }

    class NewsFeedListViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        private var article: Article? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Log.d("ViewHolder", "Clicked")
        }

        fun bindArticle(article: Article) {
            this.article = article
            Glide.with(view.context).load(article.urlToImage).into(view.img_news)
            view.text_news_title.text = article.title
            view.text_news_content.text = article.content
        }

    }

}