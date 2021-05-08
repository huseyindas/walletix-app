package com.huseyindas.walletixapp.walletix

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.huseyindas.walletixapp.R
import com.huseyindas.walletixapp.database.ExpenseViewModel
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.item_card.view.*

class DetailFragment : Fragment() {

    private val args by navArgs<DetailFragmentArgs>()

    private lateinit var mExpenseViewModel: ExpenseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val context: Context = this.requireContext()
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        if (args.selectedItem.category.equals("Kira")) {
            view.detailIcon.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_rent
                )
            )
        }

        else if (args.selectedItem.category.equals("Fatura")) {
            view.detailIcon.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_invoice
                )
            )
        }

        else if (args.selectedItem.category.equals("Diğer")) {
            view.detailIcon.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_other
                )
            )
        }
        view.detailExp.setText(args.selectedItem.explain)
        view.detailAmount.setText(args.selectedItem.amount.toString())

        view.deleteBtn.setOnClickListener {
            deleteExp()
        }

        return view
    }



    fun deleteExp() {
        mExpenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Evet") { _, _ ->
            mExpenseViewModel.deleteExpense(args.selectedItem)
            Toast.makeText(
                requireContext(), "Başarıyla silindi: ${args.selectedItem.explain}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_detailFragment_to_homepageFragment)
        }
        builder.setNegativeButton("Hayır") { _, _ ->}
        builder.setTitle("${args.selectedItem.explain} silinsin mi?")
        builder.setMessage("${args.selectedItem.explain} öğesini silmek istediğinize emin misiniz?")
        builder.create().show()
    }

}
