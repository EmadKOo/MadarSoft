package com.example.myapplication.presentaion.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notes.databinding.FragmentRegisterationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterationFragment : Fragment() {
    lateinit var binding: FragmentRegisterationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterationBinding.inflate(inflater)
        return binding.root
    }

}