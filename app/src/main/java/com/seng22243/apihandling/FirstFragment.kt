package com.seng22243.apihandling

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.seng22243.apihandling.api.UserAPIService
import com.seng22243.apihandling.databinding.FragmentFirstBinding
import com.seng22243.apihandling.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val userAPIService = UserAPIService.create()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val id = binding.editTextNumberDecimal.editableText

        val user = userAPIService.getUser("1");
        user.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val body = response.body()
                body?.let {
                    Log.i("FirstFragment", it.name)
                    binding.textviewName.text = it.name
                    binding.textViewEmail.text = it.email
                    binding.textViewUsername.text = it.username
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.i("FirstFragment", "error")
            }

        })

        binding.buttonFirst.setOnClickListener {
            //
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}