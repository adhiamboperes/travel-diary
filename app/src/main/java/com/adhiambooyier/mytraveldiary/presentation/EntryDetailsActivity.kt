package com.adhiambooyier.mytraveldiary.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.adhiambooyier.mytraveldiary.R
import com.adhiambooyier.mytraveldiary.databinding.EntryDetailsActivityBinding

class EntryDetailsActivity : AppCompatActivity() {
    private lateinit var binding: EntryDetailsActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.entry_details_activity)
        binding.lifecycleOwner = this

        val listFragment = EntryDetailsFragment.newInstance()

        if (getEntryDetailsFragment() == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_placeholder,
                listFragment
            ).commitNow()
        }
    }

    private fun getEntryDetailsFragment(): EntryDetailsFragment? {
        return supportFragmentManager.findFragmentByTag(FRAGMENT_TAG) as? EntryDetailsFragment
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, EntryDetailsActivity::class.java)
        }
    }
}
