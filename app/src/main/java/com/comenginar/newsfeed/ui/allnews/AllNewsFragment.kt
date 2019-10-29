package com.comenginar.newsfeed.ui.allnews


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.comenginar.newsfeed.R
import com.comenginar.newsfeed.databinding.FragmentAllNewsBinding
import com.comenginar.newsfeed.model.Article
import com.comenginar.newsfeed.ui.newsfeed.NewsFeedListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllNewsFragment : Fragment() {

    private val viewModel by viewModel<AllNewsViewModel>()
    private lateinit var binding: FragmentAllNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_news, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.data.observe(this, Observer {
            bindList(it)
        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        binding.listAllNews.layoutManager = layoutManager
    }

    private fun bindList(news: List<Article>) {

        val adapter = NewsFeedListAdapter(news)
        binding.listAllNews.adapter = adapter

    }

}
