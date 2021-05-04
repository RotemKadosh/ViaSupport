package com.example.support.member

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.support.R
import com.example.support.Networking.TeamMember


class MemberViewModel( memberProperty: TeamMember, app: Application) : AndroidViewModel(app) {
    // The internal MutableLiveData for the selected property
    private val _selectedProperty = MutableLiveData<TeamMember>()

    // The external LiveData for the SelectedProperty
    val selectedProperty: LiveData<TeamMember>
        get() = _selectedProperty

    init {
        _selectedProperty.value = memberProperty
    }
    val displayAvailability =Transformations.map(selectedProperty){
        when(it.available){
            true -> R.drawable.ic_baseline_available
            false-> R.drawable.ic_baseline_block
        }
    }
}

