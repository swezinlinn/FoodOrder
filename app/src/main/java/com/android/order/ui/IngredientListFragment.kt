package com.android.order.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.order.R
import com.android.order.databinding.FragmentIngredientListBinding
import com.android.order.domain.model.Ingredient
import com.android.order.presentation.adapter.IngredientListAdapter
import com.android.order.util.toJson
import com.android.order.util.toObject
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class IngredientListFragment : Fragment(R.layout.fragment_ingredient_list) {
    companion object {
        fun newInstance(category: Ingredient) = IngredientListFragment().apply {
            arguments = Bundle().apply {
                putString("category", category.toJson())
            }
        }
    }
    private lateinit var ingredientData : Ingredient
    private lateinit var _binding: FragmentIngredientListBinding
    private val binding get() = _binding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentIngredientListBinding.bind(view)
        ingredientData = this.arguments?.getString("category","")?.toObject() ?: Ingredient()
        setUpIngredientList()
    }

    private fun setUpIngredientList() {
            binding.rvData.apply {
                var mLayoutManager: LinearLayoutManager? = null
                mLayoutManager = GridLayoutManager(activity,2)
                layoutManager = mLayoutManager
                setHasFixedSize(true)
            }
            binding.rvData.adapter = IngredientListAdapter(requireContext(),
                ingredientData.list
            )
    }
}