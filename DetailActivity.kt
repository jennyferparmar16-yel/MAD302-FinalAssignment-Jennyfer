/**
 * Course: MAD302
 * Name: Jennyfer Parmar
 * Student ID: A00201240
 * Date: 2026-04-22
 *
 * Description:
 * Displays product details and handles delete confirmation.
 */

package com.example.finalassignmentsem_2

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.finalassignmentsem_2.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get product from intent
        product = intent.getSerializableExtra("product") as Product

        // Display product info
        binding.nameTextView.text = product.name
        binding.priceTextView.text = "$${product.price}"
        binding.descriptionTextView.text = product.description

        // Delete button click
        binding.deleteButton.setOnClickListener {
            showDeleteDialog()
        }
    }

    /**
     * Show delete confirmation dialog
     */
    private fun showDeleteDialog() {

        AlertDialog.Builder(this)
            .setTitle("Delete Product")
            .setMessage("Are you sure you want to delete this product?")
            .setPositiveButton("Yes") { _, _ ->

                // Send delete result
                val intent = intent
                intent.putExtra("product", product)

                setResult(Activity.RESULT_OK, intent)
                finish()
            }
            .setNegativeButton("No", null)
            .show()
    }
}