package com.comenginar.newsfeed.ui.newsfeed


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.comenginar.newsfeed.R
import com.comenginar.newsfeed.databinding.FragmentNewsFeedBinding
import com.comenginar.newsfeed.model.Article
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class NewsFeedFragment : Fragment() {

    private val newsFeedViewModel by viewModel<NewsFeedViewModel>()
    private lateinit var binding: FragmentNewsFeedBinding
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var newsFeedListAdapter: NewsFeedListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_feed, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsFeedViewModel.data.observe(this, Observer {
            //Populate the UI
           bindList(newsFeedViewModel.data.value)
        })

        newsFeedViewModel.loadingState.observe(this, Observer {
            //Observe the loading state
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        layoutManager = LinearLayoutManager(context)
        binding.listNews.layoutManager = layoutManager
    }

    private fun bindList(articles : List<Article>?){
        newsFeedListAdapter = NewsFeedListAdapter(articles!!)
        binding.listNews.adapter = newsFeedListAdapter
    }

}
