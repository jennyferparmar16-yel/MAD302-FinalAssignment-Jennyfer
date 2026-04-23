/**
 * Course: MAD302
 * Name: Jennyfer Parmar
 * Student ID: A00201240
 * Date: 2026-04-22
 *
 * Description:
 * RecyclerView Adapter for displaying products.
 */

package com.example.finalassignmentsem_2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalassignmentsem_2.databinding.ItemProductBinding

class ProductAdapter(
    private val list: MutableList<Product>,
    private val onClick: (Product) -> Unit,
    private val onEdit: (Product) -> Unit,
    private val onDelete: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    // ViewHolder holds UI references
    class ViewHolder(val binding: ItemProductBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val product = list[position]

        // Set product data
        holder.binding.nameTextView.text = product.name
        holder.binding.priceTextView.text = "$${product.price}"

        // Open detail screen
        holder.binding.root.setOnClickListener {
            onClick(product)
        }

        // Edit product
        holder.binding.editButton.setOnClickListener {
            onEdit(product)
        }

        // Delete product
        holder.binding.deleteButton.setOnClickListener {
            onDelete(product)
        }
    }
}