package com.example.mvvm.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mvvm.adapters.CommentsAdapter
import com.example.mvvm.api.CommentsApi
import com.example.mvvm.databinding.FragmentCommentsBinding
import com.example.mvvm.repository.CommentsRepository
import com.example.mvvm.viewmodels.CommentsViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class CommentsFragment : Fragment() {

    private lateinit var commentsBinding: FragmentCommentsBinding
    @Inject lateinit var commentsApi: CommentsApi
    @Inject lateinit var commentsRepository: CommentsRepository
    @Inject lateinit var commentsViewModel: CommentsViewModel
    @Inject lateinit var adapter: CommentsAdapter


    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        commentsBinding = FragmentCommentsBinding.inflate(inflater, container, false)
        return commentsBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //adapter = CommentsAdapter(requireContext())
        //commentsApi = CommentsApi.getInstance()
        //commentsRepository = CommentsRepository(commentsApi)
        //commentsViewModel = ViewModelProvider(this, CommentsViewModelFactory(commentsRepository)).get(CommentsViewModel::class.java)

        commentsBinding.recyclerview.adapter = adapter
        commentsBinding.recyclerview.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )


        commentsViewModel.loadingState.observe(viewLifecycleOwner, {
            if (it) {
                commentsBinding.progress.visibility = View.VISIBLE
                commentsBinding.recyclerview.visibility = View.GONE
            } else {
                commentsBinding.progress.visibility = View.GONE
                commentsBinding.recyclerview.visibility = View.VISIBLE
            }
        })


        commentsViewModel.commentsList.observe(viewLifecycleOwner, {
            adapter.setAllComments(it)
        })

        commentsViewModel.errorState.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        commentsViewModel.isInternetAvailable.observe(viewLifecycleOwner,{
            if(it){
                commentsViewModel.getAllComments()
            }else{
                commentsBinding.noInternet.visibility = View.VISIBLE
                commentsBinding.progress.visibility = View.GONE
            }
        })

        commentsViewModel.checkInternet(requireContext())

    }


}