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
import com.idn.muslimmediaapp.databinding.FragmentWarningBinding

class WarningFragment : Fragment() {
    private var _binding: FragmentWarningBinding? = null
    private  val binding get() = _binding as FragmentWarningBinding

    private var _warningNewsViewModel: NewsViewModel? = null
    private val warningViewModel get() = _warningNewsViewModel as NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWarningBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadingView.root.visibility = View.VISIBLE

        _warningNewsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        warningViewModel.warningForMuslimNews()

        warningViewModel.warningForMuslim.observe(viewLifecycleOwner) {
            val mAdapter = NewsAdapter()
            mAdapter.setData(it.articles)
            Log.i(
                "WarningFragment",
                "onViewCreated ${it.articles}"
            )
            binding.rvWarning.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(view.context)
            }
            binding.loadingView.root.visibility
        }
    }
}