package com.example.tmdb_partone.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.tmdb_partone.R
import com.example.tmdb_partone.databinding.FragmentRegisterBinding
import com.example.tmdb_partone.ui.auth.state.AuthStateEvent
import com.example.tmdb_partone.ui.auth.state.RegistrationFields

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : BaseAuthFragment() {

    private var _binding: FragmentRegisterBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG,"RegisterFragment: ${viewModel.hashCode()}")

        _binding = FragmentRegisterBinding.bind(view)
        binding.registerButton.setOnClickListener{
            register()
        }
        subscribeObservers()
    }

    fun subscribeObservers(){
        viewModel.viewState.observe(viewLifecycleOwner, Observer{viewState ->
            viewState.registrationFields?.let {
                it.registration_email?.let{binding.inputEmail.setText(it)}
                it.registration_username?.let{binding.inputUsername.setText(it)}
                it.registration_password?.let{binding.inputPassword.setText(it)}
                it.registration_confirm_password?.let{binding.inputPasswordConfirm.setText(it)}
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setRegistrationFields(
            RegistrationFields(
                binding.inputEmail.text.toString(),
                binding.inputUsername.text.toString(),
                binding.inputPassword.text.toString(),
                binding.inputPasswordConfirm.text.toString()
            )
        )
    }

    fun register(){
        viewModel.setStateEvent(
                AuthStateEvent.RegisterAttemptEvent(
                        binding.inputEmail.text.toString(),
                        binding.inputUsername.text.toString(),
                        binding.inputPassword.text.toString(),
                        binding.inputPasswordConfirm.text.toString()
                )
        )
    }
}