package com.browser.fire.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.browser.fire.R
import com.browser.fire.activity.BookmarkActivity
import com.browser.fire.activity.MainActivity
import com.browser.fire.activity.changeTab
import com.browser.fire.activity.checkForInternet
import com.browser.fire.adapter.BookmarkAdapter
import com.browser.fire.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.bind(view)

        return view
    }

    override fun onResume() {
        super.onResume()

        val mainActivityRef = requireActivity() as MainActivity
        MainActivity.tabsBtn.text = MainActivity.tabsList.size.toString()
        MainActivity.tabsList[MainActivity.myPager.currentItem].name = "Home"
        mainActivityRef.binding.topSearchBar.setText("")
        mainActivityRef.binding.lnSearchBar.visibility = View.INVISIBLE
        mainActivityRef.binding.browserIcon.visibility = View.GONE
        binding.searchView.queryHint = getString(R.string.search_hint)
        //binding.searchView.setQuery("", false)
        mainActivityRef.binding.webIcon.setImageResource(R.drawable.ic_search)

        mainActivityRef.binding.refreshBtn.visibility = View.GONE

        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(result: String?): Boolean {
                if (checkForInternet(requireContext())) {
                    mainActivityRef.binding.lnSearchBar.visibility = View.VISIBLE
                    mainActivityRef.binding.browserIcon.visibility = View.VISIBLE
                    changeTab(result!!, BrowseFragment(result))
                } else
                    Snackbar.make(binding.root, "Internet Not Connected\uD83D\uDE03", 3000).show()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean = false
        })
        mainActivityRef.binding.goBtn.setOnClickListener {
            if (checkForInternet(requireContext())){
                mainActivityRef.binding.lnSearchBar.visibility = View.VISIBLE
                mainActivityRef.binding.browserIcon.visibility = View.VISIBLE
                changeTab(
                    mainActivityRef.binding.topSearchBar.text.toString(),
                    BrowseFragment(mainActivityRef.binding.topSearchBar.text.toString())
                )
            }
            else
                Snackbar.make(binding.root, "Internet Not Connected\uD83D\uDE03", 3000).show()
        }

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.setItemViewCacheSize(5)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 5)
        binding.recyclerView.adapter = BookmarkAdapter(requireContext())

        if (MainActivity.bookmarkList.size < 1)
            binding.viewAllBtn.visibility = View.GONE
        binding.viewAllBtn.setOnClickListener {
            startActivity(Intent(requireContext(), BookmarkActivity::class.java))
        }
    }
}