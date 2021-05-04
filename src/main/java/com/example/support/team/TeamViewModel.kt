package com.example.support.team

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.support.Networking.MembersApi
import com.example.support.Networking.TeamMember
import com.example.support.Networking.TeamMemberApiFilter
import kotlinx.coroutines.launch

enum class TeamMemberApiStatus { LOADING, ERROR, DONE }

class TeamViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<TeamMemberApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<TeamMemberApiStatus>
        get() = _status

    private val _members = MutableLiveData<List<TeamMember>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val members: LiveData<List<TeamMember>>
        get() = _members

    // LiveData to handle navigation to the selected property
    private val _navigateToSelectedMember= MutableLiveData<TeamMember>()
    val navigateToSelectedMember: LiveData<TeamMember>
        get() = _navigateToSelectedMember



    init {
        getTeamMembers(TeamMemberApiFilter.SHOW_ALL)
    }
    private fun parseString(json : String?) : List<TeamMember>{
        return mutableListOf( TeamMember(
            "rotem",
            "kadosh",
            true,
            "054-8166800",
            "rotemkadosh27@gmail.com",
            "https://images.unsplash.com/photo-1528660493888-ab6f4761e036?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1800&q=80"))
    }

    private fun getTeamMembers(filter: TeamMemberApiFilter) {
        viewModelScope.launch {
            _status.value = TeamMemberApiStatus.LOADING
            try {

                _members.value =MembersApi.retrofitService.getProperties().list
                _status.value = TeamMemberApiStatus.DONE
            } catch (e: Exception) {
                _status.value = TeamMemberApiStatus.ERROR
                _members.value = ArrayList()
                Log.e("rotem", e.message.toString())
            }
        }
    }

    fun updateFilter(filter: TeamMemberApiFilter) {
        getTeamMembers(filter)
    }

    fun displayMemberDetails(member: TeamMember) {
        _navigateToSelectedMember.value = member
    }

    /**
     * After the navigation has taken place, make sure navigateToSelectedProperty is set to null
     */
    fun displayMemberDetailsComplete() {
        _navigateToSelectedMember.value = null
    }
}
