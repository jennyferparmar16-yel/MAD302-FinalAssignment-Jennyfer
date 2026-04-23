/**
 * Course: MAD302
 * Name: Jennyfer Parmar
 * Student ID: A00201240
 * Date: 2026-04-22
 *
 * Description:
 * Handles adding and editing products with validation.
 */

package com.example.finalassignmentsem_2

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalassignmentsem_2.databinding.ActivityAddEditBinding
import kotlin.random.Random

class AddEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditBinding

    // Holds product if editing
    private var existingProduct: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get product from intent (if editing)
        existingProduct = intent.getSerializableExtra("product") as? Product

        // Pre-fill fields if editing
        existingProduct?.let {
            binding.nameEditText.setText(it.name)
            binding.priceEditText.setText(it.price.toString())
            binding.descriptionEditText.setText(it.description)
        }

        // Save button click
        binding.saveButton.setOnClickListener {
            saveProduct()
        }
    }

    /**
     * Validate input fields
     */
    private fun validateInput(name: String, price: String, desc: String): Boolean {

        // Clear previous errors
        binding.nameEditText.error = null
        binding.priceEditText.error = null
        binding.descriptionEditText.error = null

        var isValid = true

        // Name validation
        if (name.isEmpty()) {
            binding.nameEditText.error = "Name is required"
            isValid = false
        }

        // Convert price safely
        val priceValue = price.toDoubleOrNull()

        // Price validation
        if (price.isEmpty()) {
            binding.priceEditText.error = "Price required"
            isValid = false
        } else if (priceValue == null || priceValue <= 0) {
            binding.priceEditText.error = "Invalid price"
            isValid = false
        }

        // Description validation
        if (desc.isEmpty()) {
            binding.descriptionEditText.error = "Description required"
            isValid = false
        }

        return isValid
    }

    /**
     * Save product
     */
    private fun saveProduct() {

        // Get input values
        val name = binding.nameEditText.text.toString().trim()
        val price = binding.priceEditText.text.toString().trim()
        val desc = binding.descriptionEditText.text.toString().trim()

        // Stop if validation fails
        if (!validateInput(name, price, desc)) {
            Toast.makeText(this, "Fix errors", Toast.LENGTH_SHORT).show()
            return
        }

        // Update existing OR create new
        val product = existingProduct?.apply {
            this.name = name
            this.price = price.toDouble()
            this.description = desc
        } ?: Product(
            id = Random.nextInt(), // generate ID
            name = name,
            price = price.toDouble(),
            description = desc
        )

        // Return result
        val intent = intent
        intent.putExtra("product", product)

        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}