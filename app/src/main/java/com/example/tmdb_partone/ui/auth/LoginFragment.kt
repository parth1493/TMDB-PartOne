package com.example.tmdb_partone.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.tmdb_partone.R
import com.example.tmdb_partone.databinding.FragmentLoginBinding
import com.example.tmdb_partone.models.AuthToken
import com.example.tmdb_partone.ui.auth.state.AuthStateEvent
import com.example.tmdb_partone.ui.auth.state.LoginFields


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : BaseAuthFragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG,"LoginFragment: ${viewModel.hashCode()}")
        _binding = FragmentLoginBinding.bind(view)

        binding.loginButton.setOnClickListener {
            login()
        }
        subscribeObservers()
    }

    fun subscribeObservers(){
        viewModel.viewState.observe(viewLifecycleOwner, Observer{
            it.loginFields?.let{
                it.login_email?.let{binding.inputEmail.setText(it)}
                it.login_password?.let{binding.inputPassword.setText(it)}
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setLoginFields(
            LoginFields(
                binding.inputEmail.text.toString(),
                binding.inputPassword.text.toString()
            )
        )
    }

    fun login(){
        Log.d(TAG,"Auth Enter : ${binding.inputEmail.text}")
        viewModel.setStateEvent(
                AuthStateEvent.LoginAttemptEvent(
                    binding.inputEmail.text.toString(),
                    binding.inputPassword.text.toString()
                )
        )
    }

}