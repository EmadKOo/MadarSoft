package com.example.myapplication.presentaion.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.presentaion.viewmodel.UserViewModel
import com.example.notes.R
import com.example.notes.data.enums.UserGender
import com.example.notes.databinding.FragmentRegisterationBinding
import com.example.notes.domain.extensions.disable
import com.example.notes.domain.extensions.enable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterationFragment : Fragment() {
    lateinit var binding: FragmentRegisterationBinding
    private val userViewModel by activityViewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterationBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {
        with(binding) {
            etUserName.requestFocus()
            etUserName.doOnTextChanged { text, _, _, _ ->
                userViewModel.setUserName(text.toString())
            }
            etUserJob.doOnTextChanged { text, _, _, _ ->
                userViewModel.setUserJob(text.toString())
            }

            etUserAge.doOnTextChanged { text, _, _, _ ->
                userViewModel.setUserAge(Integer.parseInt(text.toString()))
            }


            genderGroup.setOnCheckedChangeListener { _, checkedID ->
                when (checkedID) {
                    R.id.rdMale -> userViewModel.setUserGender(UserGender.MALE)
                    R.id.rdFemale -> userViewModel.setUserGender(UserGender.FEMALE)
                }
            }

            btnSave.setOnClickListener {
                userViewModel.addUser()
                findNavController().navigate(RegisterationFragmentDirections.actionRegisterationFragmentToUsersListFragment())
            }
        }
    }

    private fun initObservers() {
        userViewModel.newUserEnabled.observe(viewLifecycleOwner) {
            if (it) binding.btnSave.enable() else binding.btnSave.disable()
        }
    }
}