package com.nekhoroshkina.testapplication.registration.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nekhoroshkina.testapplication.R
import com.nekhoroshkina.testapplication.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private val viewModel: RegistrationViewModel by viewModels()

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeListeners()
        initializeObservers()
    }

    private fun initializeListeners() {
        binding.buttonSave.setOnClickListener {
            val name = binding.editName.text.toString()
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()
            val confirmPassword = binding.editConfirmPassword.text.toString()

            viewModel.registerUser(name, email, password, confirmPassword)
        }
    }

    private fun initializeObservers() {
        viewModel.registrationScreenState.observe(viewLifecycleOwner) { screenState ->
            when (screenState) {
                is RegistrationScreenState.Success -> {
                    Toast.makeText(
                        requireActivity(),
                        getString(R.string.registration_success_message),
                        Toast.LENGTH_SHORT
                    ).show()

                    clearInputFields()
                    clearFocus()
                }

                is RegistrationScreenState.Error -> {
                    Toast.makeText(requireActivity(), screenState.error, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun clearInputFields() {
        binding.editName.text.clear()
        binding.editEmail.text.clear()
        binding.editPassword.text.clear()
        binding.editConfirmPassword.text.clear()
    }

    private fun clearFocus() {
        binding.editName.clearFocus()
        binding.editEmail.clearFocus()
        binding.editPassword.clearFocus()
        binding.editConfirmPassword.clearFocus()

        val inputMethodManager =
            ContextCompat.getSystemService(requireContext(), InputMethodManager::class.java)
        inputMethodManager?.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
