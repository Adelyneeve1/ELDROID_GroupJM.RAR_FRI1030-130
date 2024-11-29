package com.artisans.beaute

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookingFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RMyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking, container, false)

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView) // Match with your XML ID
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Sample data for RecyclerView
        val names = listOf("Adelyne Eve", "John Doe", "Jane Smith", "Sophia Brown")

        // Set up adapter
        adapter = RMyAdapter(names)
        recyclerView.adapter = adapter

        return view
    }
}
