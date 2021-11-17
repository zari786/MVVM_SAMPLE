package com.example.mvvm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvvm.databinding.FragmentPrivacyBinding


class PrivacyFragment : Fragment() {

    private lateinit var privacyBinding: FragmentPrivacyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        privacyBinding = FragmentPrivacyBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return privacyBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}