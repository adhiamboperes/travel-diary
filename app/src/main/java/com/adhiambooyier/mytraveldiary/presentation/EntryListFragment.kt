package com.adhiambooyier.mytraveldiary.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adhiambooyier.mytraveldiary.R
import com.adhiambooyier.mytraveldiary.databinding.EntryListFragmentBinding

const val FRAGMENT_TAG = "EntryListFragment"

class EntryListFragment : Fragment() {
    private lateinit var binding: EntryListFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EntryListFragmentBinding.inflate(inflater, container, /* attachToRoot= */ false)
        binding.entryListNewEntryButton.setOnClickListener {
            startActivity(EntryDetailsActivity.getIntent(requireContext()))
        }

        return binding.root
    }

    companion object {
        fun newInstance(): EntryListFragment = EntryListFragment()
    }
}
