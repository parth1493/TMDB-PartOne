package com.example.tmdb_partone.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.tmdb_partone.R
import com.example.tmdb_partone.util.GenericApiResponse

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

        viewModel.testRegister().observe(viewLifecycleOwner, Observer { response ->

            when(response){
                is GenericApiResponse.ApiSuccessResponse ->{
                    Log.d(TAG, "REGISTER RESPONSE: ${response.body}")
                }
                is GenericApiResponse.ApiErrorResponse ->{
                    Log.d(TAG, "REGISTER RESPONSE: ${response.errorMessage}")
                }
                is GenericApiResponse.ApiEmptyResponse ->{
                    Log.d(TAG, "REGISTER RESPONSE: Empty Response")
                }
            }
        })
    }
}