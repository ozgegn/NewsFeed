package com.comenginar.newsfeed.ui.newsfeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private val newsFeedListAdapter by lazy { NewsFeedListAdapter(emptyList())  }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_feed, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        layoutManager = LinearLayoutManager(context)
        binding.listNews.layoutManager = layoutManager

        newsFeedViewModel.data.observe(this, Observer {
            checkListThenBind(newsFeedViewModel.data.value)
        })

        newsFeedViewModel.loadingState.observe(this, Observer {
            //Observe the loading state
        })
    }

    private fun checkListThenBind(articles: MutableList<Article>?) {
        // Live Data not stable variable , to do this, either use SingleLiveEvent or use the memory correctly by defining the variables lazy.
        if (!articles.isNullOrEmpty()){
            newsFeedListAdapter.notifyDataList(articles)
            binding.listNews.adapter = newsFeedListAdapter
        }
    }
}
