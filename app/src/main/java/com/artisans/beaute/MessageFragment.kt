package com.artisans.beaute

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MessageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_message, container, false)

        // Initialize RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Sample data
        val people = listOf(
            Contacts("Anthena Buenco"),
            Contacts("Paul Unating"),
            Contacts("Jelly Eugenio"),
            Contacts("Bretman Rock"),
            Contacts("Adelyne Gipgano"),
            Contacts("Reyjean Lumapac"),
            Contacts("Sarah Johnson"),
            Contacts("Michael Chang"),
            Contacts("Emily Patel")
        )

        // Set adapter
        val adapter = ContactsAdapter(people)
        recyclerView.adapter = adapter

        return view
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MessageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
