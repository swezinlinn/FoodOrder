package com.android.order.ui

import android.app.Activity
import android.content.Context
import android.database.Cursor
import android.database.MatrixCursor
import android.os.Build
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.order.R
import com.android.order.databinding.FragmentCommonIngredientBinding
import com.android.order.domain.model.Ingredient
import com.android.order.presentation.adapter.SearchCustomAdapter
import com.android.order.presentation.adapter.ViewPagerAdapter
import com.android.order.presentation.viewModel.IngredientListViewModel
import com.android.order.util.ZoomOutPageTransformer
import com.android.order.util.toJson
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommonIngredientFragment : Fragment(R.layout.fragment_common_ingredient), SearchView.OnQueryTextListener,TabLayout.OnTabSelectedListener {
    private lateinit var searchItemListView: ListView
    private lateinit var _binding: FragmentCommonIngredientBinding
    private val binding get() = _binding
    private val viewModel: IngredientListViewModel by viewModels()
    private var searchMenuItem: MenuItem? = null
    private lateinit var searchView: SearchView
    lateinit var adapter: ViewPagerAdapter
    private lateinit var searchAdapter: SearchCustomAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCommonIngredientBinding.bind(view)

        setStatusBar()
        setHasOptionsMenu(true)
        setUpToolbar(binding.toolbar)
        getIngredientList()
    }

    private fun getIngredientList() {
        viewModel.ingredient.observe(viewLifecycleOwner) {
            setUpPage(it)
            Log.d("TAG","Items---> ${it.toJson()}")
            initSearchItemList()
        }
        viewModel.getIngredient(true)
    }

    private fun setUpToolbar(toolbar: Toolbar) {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
    }

    private fun setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            requireActivity().window.statusBarColor = resources.getColor(R.color.gray1)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            requireActivity().window.statusBarColor = resources.getColor(R.color.colorPrimary)
        }
    }

    private fun initSearchItemList() {
        searchItemListView = binding.listView as ListView

        searchAdapter = SearchCustomAdapter(requireContext(), null, 0)

        searchItemListView.adapter = searchAdapter
        searchItemListView.divider = null
        searchItemListView.isTextFilterEnabled = true;

        // set up click listener
        searchItemListView.setOnItemClickListener { _, _, position, _ ->
            val categoryItem = searchAdapter.getItem(position) as Cursor
            val name = categoryItem.getString(categoryItem.getColumnIndex("name"))

            Toast.makeText(context,"Item clicked $name",Toast.LENGTH_LONG).show()
        }
    }

    private fun setUpPage(it: List<Ingredient>) {
        adapter = ViewPagerAdapter(childFragmentManager)
        try {
            for (items in it) {
                adapter.addFragment(IngredientListFragment.newInstance(items), items.category)
            }
            binding.viewPager.adapter = adapter
            binding.viewPager.setPageTransformer(true, ZoomOutPageTransformer())
            binding.tabs.setupWithViewPager(binding.viewPager)
            binding.tabs.addOnTabSelectedListener(this)
            val length: Int = binding.tabs.tabCount
            for (i in 0 until length) {
                binding.tabs.getTabAt(i)?.customView = getTabView(i)
            }
        } catch (e: NumberFormatException) {
            e.message
        }

    }

    private fun getTabView(position: Int): View {
        val view: View = LayoutInflater.from(requireContext()).inflate(R.layout.tab_layout, null)
        val title = view.findViewById(R.id.title) as TextView
        title.text = adapter.getPageTitle(position)
        return view
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val inflater = requireActivity().menuInflater
        inflater.inflate(R.menu.menu_search, menu)
        searchMenuItem = menu.findItem(R.id.search)
        searchView = searchMenuItem?.actionView as SearchView

        val searchIcon: ImageView = searchView.findViewById(R.id.search_button)
        searchIcon.setImageDrawable(resources.getDrawable(R.drawable.ic_search))
        val searchCloseIcon: ImageView = searchView.findViewById(R.id.search_close_btn)
        searchCloseIcon.setImageDrawable(resources.getDrawable(R.drawable.ic_cross))
        val searchText: EditText = searchView.findViewById(R.id.search_src_text)
        searchText.setTextColor(resources.getColor(R.color.colorTextPrimary))
        searchView.setOnQueryTextListener(this)
        searchView.queryHint = "Search..."
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->

                if (::searchAdapter.isInitialized)
                    searchAdapter.changeCursor(null)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        pupulateSuggestionItem(newText?: "")
        return true
    }

    override fun onStop() {
        super.onStop()
        hideKeyboardFrom(requireContext(),requireView())
    }

    private fun pupulateSuggestionItem(query: String) {
        val c = MatrixCursor(arrayOf(BaseColumns._ID, "name"))
        viewModel.getIngredientListByName(query)
        viewModel.ingredientData.observe(viewLifecycleOwner,{
            for(i in it.indices){
                c.addRow(arrayOf<Any>(i, it[i].name))
            }
        })

        if (::searchAdapter.isInitialized) {
            searchAdapter.changeCursor(null)
            searchAdapter.changeCursor(c)
        }
    }

    override fun onTabSelected(tab: TabLayout.Tab) {
        val title = tab.customView?.findViewById(R.id.title) as? TextView ?: return
        adapter.onSelectView(title, requireContext(), false)
    }

    override fun onTabUnselected(tab: TabLayout.Tab) {
        val title = tab.customView?.findViewById(R.id.title) as? TextView ?: return
        adapter.onSelectView(title, requireContext(), true)
    }

    override fun onTabReselected(tab: TabLayout.Tab) {
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}