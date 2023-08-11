package com.example.myapplication.presentaion.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.presentaion.adapter.UsersAdapter
import com.example.myapplication.presentaion.viewmodel.UserViewModel
import com.example.notes.R
import com.example.notes.Utils.Resource
import com.example.notes.databinding.FragmentUsersListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UsersListFragment : Fragment() {
    private lateinit var binding: FragmentUsersListBinding
    private val usersViewModel by activityViewModels<UserViewModel>()

    @Inject
    lateinit var usersAdapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usersViewModel.getUsers()
        initViews()
        initObservers()
    }

    private fun initViews() {
        with(binding) {
            ivBack.setOnClickListener { findNavController().popBackStack() }
            rvUsers.adapter = usersAdapter
        }
    }

    private fun initObservers() {
        usersViewModel.usersLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.somethingWentWrong),
                        Toast.LENGTH_LONG
                    ).show()
                }

                is Resource.Success -> {
                    usersAdapter.submitList(it.data ?: mutableListOf())
                }
            }
        }
    }
}