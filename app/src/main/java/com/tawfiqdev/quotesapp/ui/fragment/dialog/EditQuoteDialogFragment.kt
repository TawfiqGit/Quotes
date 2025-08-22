package com.tawfiqdev.quotesapp.ui.fragment.dialog

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.tawfiqdev.quotesapp.R
import com.tawfiqdev.quotesapp.databinding.DialogFragmentEditQuoteBinding

class EditQuoteDialogFragment : DialogFragment() {

    companion object {
        const val RESULT_KEY = "add_quote_result"
        const val ARG_ID = "id"
        const val ARG_ICON = "icon"
        const val ARG_CONTENT = "content"
        const val ARG_AUTHOR = "author"
        const val ARG_YEAR = "year"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogFragmentEditQuoteBinding.inflate(layoutInflater)

        binding.buttonAddQuote.setOnClickListener {
            val content = binding.editTextQuote.text.toString().trim()
            val author = binding.editTextAuthor.text.toString().trim()
            val yearStr = binding.editTextYear.text.toString().trim()

            if (content.isNotEmpty() && author.isNotEmpty() && yearStr.isNotEmpty()) {
                val year = yearStr.toIntOrNull()

                if (year == null) {
                    Toast.makeText(requireContext(), "Année invalide", Toast.LENGTH_SHORT).show()
                } else {
                    val id = (1..Int.MAX_VALUE).random()
                    parentFragmentManager.setFragmentResult(
                        RESULT_KEY,
                        bundleOf(
                            ARG_ID to id,
                            ARG_ICON to id,
                            ARG_CONTENT to content,
                            ARG_AUTHOR to author,
                            ARG_YEAR to year
                        )
                    )
                    Log.i("EditQuoteDialog", "Result sent -> id=$id, content=$content, author=$author, year=$year")
                    dismiss()
                }

            } else Toast.makeText(requireContext(), "Veuillez renseigner tous les champs", Toast.LENGTH_SHORT).show()
        }

        return AlertDialog.Builder(requireContext(), R.style.QuoteDialog)
            .setView(binding.root)
            .create()
            .also {
                it.setCancelable(true)
                isCancelable = true
            }
    }
}