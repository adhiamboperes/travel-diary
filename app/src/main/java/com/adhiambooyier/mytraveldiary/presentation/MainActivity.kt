package com.adhiambooyier.mytraveldiary.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.adhiambooyier.mytraveldiary.R
import com.adhiambooyier.mytraveldiary.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        binding.lifecycleOwner = this

        val listFragment = EntryListFragment.newInstance()

        if (getEntryListFragment() == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_placeholder,
                listFragment
            ).commitNow()
        }
    }

    private fun getEntryListFragment(): EntryListFragment? {
        return supportFragmentManager.findFragmentByTag(FRAGMENT_TAG) as? EntryListFragment
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
        }
    }
}
