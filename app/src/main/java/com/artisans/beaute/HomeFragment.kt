package com.artisans.beaute

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    // Mutable list to hold image data (both static and dynamic)
    private val imageData = mutableListOf<Uri>(
        Uri.parse("android.resource://com.artisans.beaute/" + R.drawable.m1),
        Uri.parse("android.resource://com.artisans.beaute/" + R.drawable.m2),
        Uri.parse("android.resource://com.artisans.beaute/" + R.drawable.m3),
        Uri.parse("android.resource://com.artisans.beaute/" + R.drawable.m4),
        Uri.parse("android.resource://com.artisans.beaute/" + R.drawable.m5),
        Uri.parse("android.resource://com.artisans.beaute/" + R.drawable.m6)
    )

    // Keys for the arguments
    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String?, param2: String?) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1 ?: "")
                    putString(ARG_PARAM2, param2 ?: "")
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.gallery)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Set up the adapter with the initial list of images
        val adapter = GalleryAdapter(imageData)
        recyclerView.adapter = adapter

        // Set up click listener for "Post a Pic" TextView
        val postAPicText: TextView = view.findViewById(R.id.postapictext)
        postAPicText.setOnClickListener {
            // Navigate to PostAPicActivity
            val intent = Intent(requireContext(), PostAPicActivity::class.java)
            startActivityForResult(intent, 101)  // Use unique request code
        }

        return view
    }

    // This function handles the result from PostAPicActivity when a new image is selected
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == AppCompatActivity.RESULT_OK) {
            val imageUriString = data?.getStringExtra("IMAGE_URI")
            if (imageUriString != null) {
                val imageUri = Uri.parse(imageUriString)
                imageData.add(imageUri)  // Add new image to the list

                // Notify the adapter that the data has changed and a new image has been added
                (view?.findViewById<RecyclerView>(R.id.gallery)?.adapter as GalleryAdapter).notifyItemInserted(imageData.size - 1)
            }
        }
    }
}