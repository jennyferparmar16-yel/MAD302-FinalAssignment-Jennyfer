/**
 * Course: MAD302
 * Name: Jennyfer Parmar
 * Student ID: A00201240
 * Date: 2026-04-22
 *
 * Description:
 * Displays product list using RecyclerView and handles navigation.
 */

package com.example.finalassignmentsem_2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalassignmentsem_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // List to store products
    private val productList = mutableListOf<Product>()

    private lateinit var adapter: ProductAdapter

    // Request codes
    private val ADD_REQUEST = 1
    private val EDIT_REQUEST = 2
    private val DELETE_REQUEST = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Keep your edge-to-edge design
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Keep your window inset handling
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize adapter with click actions
        adapter = ProductAdapter(productList,
            onClick = { product ->
                // Open detail screen
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("product", product)
                startActivityForResult(intent, DELETE_REQUEST)
            },
            onEdit = { product ->
                // Open edit screen
                val intent = Intent(this, AddEditActivity::class.java)
                intent.putExtra("product", product)
                startActivityForResult(intent, EDIT_REQUEST)
            },
            onDelete = { product ->
                // Delete directly
                productList.remove(product)
                adapter.notifyDataSetChanged()
            }
        )

        // Setup RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Add product button
        binding.addButton.setOnClickListener {
            startActivityForResult(
                Intent(this, AddEditActivity::class.java),
                ADD_REQUEST
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && data != null) {

            val product = data.getSerializableExtra("product") as Product

            when (requestCode) {

                // Add product
                ADD_REQUEST -> productList.add(product)

                // Edit product
                EDIT_REQUEST -> {
                    val index = productList.indexOfFirst { it.id == product.id }
                    if (index != -1) productList[index] = product
                }

                // Delete from detail screen
                DELETE_REQUEST -> {
                    productList.removeIf { it.id == product.id }
                }
            }

            // Refresh list
            adapter.notifyDataSetChanged()
        }
    }
}