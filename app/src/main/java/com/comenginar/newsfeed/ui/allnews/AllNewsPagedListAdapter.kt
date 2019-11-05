package com.comenginar.newsfeed.ui.allnews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.comenginar.newsfeed.databinding.AdapterNewsfeedListBinding
import com.comenginar.newsfeed.model.Article

class AllNewsPagedListAdapter :
    PagedListAdapter<Article, AllNewsPagedListAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterNewsfeedListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = getItem(position)
        getItem(position)?.let {
            holder.apply {
                bindArticle(article!!)
                itemView.tag = article
            }
        }
    }

    class ViewHolder(private val binding: AdapterNewsfeedListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindArticle(article: Article) {
            binding.apply {
                textNewsContent.text = article.content
                textNewsTitle.text = article.title
                Glide.with(binding.root.context).load(article.urlToImage).into(imgNews)
                executePendingBindings()
            }
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
            oldItem == newItem
    }
}