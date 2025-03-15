package com.nekhoroshkina.testapplication.registration.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                updateButtonState()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        binding.editName.addTextChangedListener(textWatcher)
        binding.editEmail.addTextChangedListener(textWatcher)
        binding.editPassword.addTextChangedListener(textWatcher)
        binding.editConfirmPassword.addTextChangedListener(textWatcher)


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
                }

                is RegistrationScreenState.Error -> {
                    Toast.makeText(requireActivity(), screenState.error, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    fun updateButtonState() {
        val isFormValid = binding.editName.text.isNotBlank() &&
                binding.editEmail.text.isNotBlank() &&
                binding.editPassword.text.isNotBlank() &&
                binding.editConfirmPassword.text.isNotBlank()

        binding.buttonSave.isEnabled = isFormValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
