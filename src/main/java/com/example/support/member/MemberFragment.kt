
package com.example.support.member

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.support.databinding.MemberFragmentBinding

class MemberFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        val binding = MemberFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val teamMember = MemberFragmentArgs.fromBundle(arguments!!).selectedProperty
        val viewModelFactory = MemberViewModelFactory(teamMember, application)
        binding.viewModel = ViewModelProvider(
                this, viewModelFactory).get(MemberViewModel::class.java)

        return binding.root
    }
}