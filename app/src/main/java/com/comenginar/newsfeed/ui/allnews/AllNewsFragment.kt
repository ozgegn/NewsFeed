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
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllNewsFragment : Fragment() {

    private val viewModel by viewModel<AllNewsViewModel>()
    private lateinit var binding: FragmentAllNewsBinding
    private val adapter: AllNewsPagedListAdapter by lazy { AllNewsPagedListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_news, container, false)

        val layoutManager = LinearLayoutManager(context)
        binding.listAllNews.layoutManager = layoutManager
        binding.listAllNews.adapter = adapter

        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: AllNewsPagedListAdapter) {
        viewModel.data.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }
}
