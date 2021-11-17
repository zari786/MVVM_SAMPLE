package com.example.mvvm.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.mvvm.adapters.MainAdapter
import com.example.mvvm.api.RetrofitService
import com.example.mvvm.databinding.FragmentOneBinding
import com.example.mvvm.repository.MainRepository
import com.example.mvvm.viewmodels.MainViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class HomeFragment : Fragment() {
    //private lateinit var mainViewModelFactory: MainViewModelFactory
    @Inject lateinit var mainRepository: MainRepository
    @Inject lateinit var adapter : MainAdapter
    @Inject lateinit var retrofitService:RetrofitService
    @Inject lateinit var mainViewModel: MainViewModel
    private lateinit var fragmentHomeBinding: FragmentOneBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentHomeBinding = FragmentOneBinding.inflate(inflater, container, false)
        return fragmentHomeBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainRepository = MainRepository(retrofitService)

        //mainViewModelFactory = MainViewModelFactory(mainRepository)

        //mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

        fragmentHomeBinding.recyclerview.adapter = adapter

        mainViewModel.movieList.observe(viewLifecycleOwner, Observer {
            adapter.setMovieList(it)
        })

        mainViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
        })

        mainViewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                fragmentHomeBinding.progress.visibility = View.VISIBLE
                fragmentHomeBinding.recyclerview.visibility = View.GONE
            } else {
                fragmentHomeBinding.progress.visibility = View.GONE
                fragmentHomeBinding.recyclerview.visibility = View.VISIBLE
            }
        })

        mainViewModel.isIntentAvailable.observe(viewLifecycleOwner, Observer {
            Log.d("Internet", "$it")
            if (it) {
                mainViewModel.getAllMovies()
                fragmentHomeBinding.noInternet.visibility = View.GONE
            } else {
                fragmentHomeBinding.recyclerview.visibility = View.GONE
                fragmentHomeBinding.noInternet.visibility = View.VISIBLE
            }
        })

        fragmentHomeBinding.noInternet.setOnClickListener {
            mainViewModel.checkInternet(requireContext())
        }

        mainViewModel.checkInternet(requireContext())

    }


}