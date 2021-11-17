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
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mvvm.adapters.FavoritesAdapter
import com.example.mvvm.api.RetrofitService1
import com.example.mvvm.databinding.FragmentTwoBinding
import com.example.mvvm.repository.FavoritesRepository
import com.example.mvvm.viewmodels.FavoritesViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class FavoritesFragment : Fragment() {
    private lateinit var favoritesFragmentBinding: FragmentTwoBinding
    @Inject lateinit var favoritesRepository: FavoritesRepository
    @Inject lateinit var favoritesViewModel: FavoritesViewModel
    @Inject lateinit var adapter : FavoritesAdapter
    @Inject lateinit var retrofitService1 :RetrofitService1

    //private lateinit var favoritesViewModelFactory: FavoritesViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        favoritesFragmentBinding = FragmentTwoBinding.inflate(inflater, container, false)

        return favoritesFragmentBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //favoritesRepository = FavoritesRepository(retrofitService1)
        //favoritesViewModelFactory = FavoritesViewModelFactory(favoritesRepository)
        //favoritesViewModel = ViewModelProvider(this, favoritesViewModelFactory).get(FavoritesViewModel::class.java)


        favoritesFragmentBinding.recyclerview.adapter = adapter
        favoritesFragmentBinding.recyclerview.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        favoritesViewModel.isInternet.observe(viewLifecycleOwner,{
            if(it){
                favoritesViewModel.getAllPosts()
            }else{
                favoritesFragmentBinding.noInternet.visibility = View.VISIBLE
                favoritesFragmentBinding.recyclerview.visibility = View.GONE
            }
        })

        favoritesViewModel.favoritesList.observe(viewLifecycleOwner, {
            Log.d("Response", "$it")
            adapter.setPostList(it)
        })

        favoritesViewModel.error.observe(viewLifecycleOwner, {
            Log.d("Exception", "$it")
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
        })

        favoritesViewModel.loading.observe(viewLifecycleOwner, {
            if (it) {
                favoritesFragmentBinding.progress.visibility = View.VISIBLE
                favoritesFragmentBinding.recyclerview.visibility = View.GONE
            } else {
                favoritesFragmentBinding.recyclerview.visibility = View.VISIBLE
                favoritesFragmentBinding.progress.visibility = View.GONE
            }
        })


        favoritesViewModel.checkInternet(requireContext())

    }


}