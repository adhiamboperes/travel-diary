package com.adhiambooyier.mytraveldiary.presentation

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.adhiambooyier.mytraveldiary.databinding.EntryDetailsFragmentBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class EntryDetailsFragment : Fragment() {
    private lateinit var binding: EntryDetailsFragmentBinding

    private val REQUEST_PERMISSION = 1
    private val REQUEST_IMAGE_PICK = 2

    private val selectedImages = ArrayList<Uri>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            EntryDetailsFragmentBinding.inflate(inflater, container, /* attachToRoot= */ false)

        binding.entryDetailsDateCreated.text = getFormattedDate()

        binding.entryDetailsAddImages.setOnClickListener { checkPermissionAndPickImages() }

        return binding.root
    }

    private fun getFormattedDate(): String {
        val dateTime = Date()

        val pattern = "MMM d, yyyy h:mma"
        val locale = Locale.ENGLISH

        val sdf = SimpleDateFormat(pattern, locale)
        return sdf.format(dateTime)
    }

    private fun checkPermissionAndPickImages() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_PERMISSION
            )
        } else {
            pickImages()
        }
    }

    private fun pickImages() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        requireActivity().startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK) {
            if (data != null) {
                if (data.clipData != null) {
                    val count = data.clipData!!.itemCount
                    for (i in 0 until count) {
                        val imageUri = data.clipData!!.getItemAt(i).uri
                        selectedImages.add(imageUri)
                    }
                } else if (data.data != null) {
                    val imageUri = data.data
                    selectedImages.add(imageUri!!)
                }
                Log.d("SelectedImages", selectedImages.toString())
            }
        }
    }

    companion object {
        fun newInstance(): EntryDetailsFragment = EntryDetailsFragment()
    }
}
