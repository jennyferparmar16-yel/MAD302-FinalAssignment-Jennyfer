/**
 * Course: MAD302
 * Name: Your Name
 * Student ID: XXXXXXX
 * Date: 2026-04-22
 *
 * Description:
 * Data class representing a product in the app.
 */

package com.example.finalassignmentsem_2

import java.io.Serializable

data class Product(
    val id: Int,
    var name: String,
    var price: Double,
    var description: String
) : Serializable