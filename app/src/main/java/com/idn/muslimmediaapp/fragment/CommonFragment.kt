package com.idn.muslimmediaapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.idn.muslimmediaapp.NewsViewModel
import com.idn.muslimmediaapp.adapter.NewsAdapter
import com.idn.muslimmediaapp.databinding.FragmentCommonBinding

class CommonFragment : Fragment() {
    private var _binding: FragmentCommonBinding? = null
    private val binding get() = _binding as FragmentCommonBinding

    private var _commonNewsViewModel: NewsViewModel? = null
    private val commonViewModel get() = _commonNewsViewModel as NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommonBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadingView.root.visibility = View.VISIBLE

        _commonNewsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        commonViewModel.commonMuslimNews()

        commonViewModel.commonMuslimNews.observe(viewLifecycleOwner) {
            val mAdapter = NewsAdapter()
            mAdapter.setData(it.articles)
            Log.i(
                "AboutCommonFragment",
                "onViewCreated ${it.articles}"
            )
            binding.rvCommon.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(view.context)
            }
            binding.loadingView.root.visibility = View.GONE
        }

    }
}