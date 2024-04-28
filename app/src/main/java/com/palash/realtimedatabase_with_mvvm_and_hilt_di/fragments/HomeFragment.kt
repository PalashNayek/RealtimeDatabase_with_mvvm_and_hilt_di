package com.palash.realtimedatabase_with_mvvm_and_hilt_di.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.firebase.database.getValue
import com.palash.realtimedatabase_with_mvvm_and_hilt_di.R
import com.palash.realtimedatabase_with_mvvm_and_hilt_di.databinding.FragmentHomeBinding
import com.palash.realtimedatabase_with_mvvm_and_hilt_di.view_model.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding?= null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set data
        viewModel.setData("Name","Palash")

        //observe data
        GlobalScope.launch(Dispatchers.Main) {
            viewModel.data.collect{
                /*if (it==null){
                    Log.d("MyRealtimeData", "No Data available")
                }*/
                binding.tv.text = it?.getValue().toString()
                Log.d("MyRealtimeData", it?.value.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}