package com.android.order.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.order.R
import com.android.order.databinding.FragmentOrderListBinding
import com.android.order.domain.model.OrderList
import com.android.order.presentation.adapter.OrderListAdapter
import com.android.order.presentation.viewModel.OrderListViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class OrderListFragment: Fragment(R.layout.fragment_order_list) {
    private lateinit var _binding: FragmentOrderListBinding
    private val binding get() = _binding
    private val viewModel: OrderListViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOrderListBinding.bind(view)

        setUpLoadingState()

        getOrderList()
        listener()
    }

    private fun listener() {
        binding.imgIngredient.setOnClickListener {
            findNavController().navigate(OrderListFragmentDirections.actionOrderListToIngredients())
        }
    }

    private fun setUpLoadingState() {
        viewModel.loadingState.observe(viewLifecycleOwner,{
            if(it){
                binding.loading.visibility = View.VISIBLE
            }else{
                binding.loading.visibility = View.GONE
            }
        })
    }

    private fun getOrderList() {
        viewModel.orderList.observe(viewLifecycleOwner, Observer {
            var mLayoutManager: LinearLayoutManager? = null
            mLayoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            binding.rvIngredient.apply {
                layoutManager = mLayoutManager
                setHasFixedSize(true)
            }
            binding.rvIngredient.adapter = OrderListAdapter(requireContext(),
                it as ArrayList<OrderList>
            )
        })
        viewModel.getOrderList()
    }
}