package com.artisans.beaute

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.result.contract.ActivityResultContracts

class PostAPicActivity : AppCompatActivity() {

    private var selectedImageUri: Uri? = null // To hold the selected image URI

    // Register the activity result launcher to select an image
    private val selectImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            // The URI of the selected image
            selectedImageUri = uri
            // Show a toast and display the image in the ImageView
            Toast.makeText(this, "Image selected!", Toast.LENGTH_SHORT).show()

            // Display the selected image in imageView6
            val imageView: ImageView = findViewById(R.id.view)
            imageView.setImageURI(selectedImageUri)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_apic) // Set your XML layout

        // Set up click listener for "POST" TextView
        val postButton: TextView = findViewById(R.id.Post) // Make sure this ID matches the XML
        postButton.setOnClickListener {
            // Check if an image URI is selected
            if (selectedImageUri != null) {
                // Prepare the result intent with the image URI
                val resultIntent = Intent().apply {
                    putExtra("IMAGE_URI", selectedImageUri.toString()) // Passing the image URI back
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish() // Finish the activity and send the result back to the calling activity
            } else {
                // If no image is selected, show a toast message
                Toast.makeText(this, "No image selected!", Toast.LENGTH_SHORT).show()
            }
        }

        // Set up click listener for image selection (for example, to pick an image from the gallery)
        val selectImageButton: TextView = findViewById(R.id.selectImageButton) // Trigger selection when clicked
        selectImageButton.setOnClickListener {
            selectImageLauncher.launch("image/*") // Launch the image picker (accepts any image type)
        }
    }
}
