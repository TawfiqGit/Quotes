package com.tawfiqdev.quotesapp.ui.fragment.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.tawfiqdev.quotesapp.R

class EditQuoteDialogFragment : DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater: LayoutInflater = this.layoutInflater
        val dialogView: View = inflater.inflate(R.layout.dialog_fragment_edit_quote, null)
        return AlertDialog.Builder(requireContext()).apply {
            setView(dialogView)
        }.create()
    }

    override fun setCancelable(cancelable: Boolean) {
        super.setCancelable(false)
    }
}