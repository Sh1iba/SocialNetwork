package com.example.social_network.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.social_network.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)


        val fullName = arguments?.getString("fullName")
        val bio = arguments?.getString("bio")

        val fullNameTextView: TextView = rootView.findViewById(R.id.textViewFullName)
        val bioTextView: TextView = rootView.findViewById(R.id.textViewBio)

        fullNameTextView.text = fullName ?: "No full name available"
        bioTextView.text = bio ?: "No bio available"
        Log.d("FRAAAAACCCCCC","fullname: $fullName |  bio: $bio")

        return rootView
    }


}