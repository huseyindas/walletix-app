package com.huseyindas.walletixapp.walletix

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.huseyindas.walletixapp.R
import com.huseyindas.walletixapp.database.ExpenseViewModel
import com.huseyindas.walletixapp.database.Wallet
import kotlinx.android.synthetic.main.fragment_expense.*
import kotlinx.android.synthetic.main.fragment_expense.view.*


class ExpenseFragment : Fragment() {

    private lateinit var mExpenseViewModel: ExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_expense, container, false)

        mExpenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addBtn.setOnClickListener {
            addDataToDb()
        }

    }

    private fun addDataToDb(){
        var explain = expInput.text.toString()
        var category = ""
        var amount : Int = Integer.parseInt(ampInput.text.toString())
        var rate =  ""

        when {
            radioRent.isChecked -> {
                category = "Kira"
            }
            radioİnvoice.isChecked -> {
                category = "Fatura"
            }
            radioOther.isChecked -> {
                category = "Diğer"
            }
        }

        when {
            radioTl.isChecked -> {
                rate = "TL"
            }
            radioEuro.isChecked -> {
                rate = "EUR"
                amount=amount * eur.toInt()
            }
            radioDollar.isChecked -> {
                rate = "USD"
                amount=amount * usd.toInt()
            }
            radioPound.isChecked -> {
                rate = "GBP"
                amount=amount * gbp.toInt()
            }
        }

        if(inputCheck(explain, category, rate)){
            val walletDb = Wallet(0,explain, amount, category, rate)

            mExpenseViewModel.addExpense(walletDb)

            Toast.makeText(requireContext(),"Ekleme Basarili.",Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_expenseFragment_to_homepageFragment)
        }
        else{
            Toast.makeText(requireContext(),"Ekleme Başarısız.",Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(explain:String, category: String, rate: String): Boolean {
        return !(TextUtils.isEmpty(explain) && TextUtils.isEmpty(category) && TextUtils.isEmpty(rate))
    }

}