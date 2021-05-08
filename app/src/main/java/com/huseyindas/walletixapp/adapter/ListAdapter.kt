package com.huseyindas.walletixapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.huseyindas.walletixapp.R
import com.huseyindas.walletixapp.database.ExpenseViewModel
import com.huseyindas.walletixapp.database.Wallet
import com.huseyindas.walletixapp.walletix.HomepageFragmentDirections
import com.huseyindas.walletixapp.walletix.eur
import com.huseyindas.walletixapp.walletix.gbp
import com.huseyindas.walletixapp.walletix.usd
import kotlinx.android.synthetic.main.fragment_homepage.view.*
import kotlinx.android.synthetic.main.item_card.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var expenseList = emptyList<Wallet>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_card,
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int {
        return expenseList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val context: Context = holder.itemView.getContext()
        val selected = expenseList[position]

        if (selected.category.equals("Kira")) {
            holder.itemView.iconImage.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_rent
                )
            )
        }

        else if (selected.category.equals("Fatura")) {
            holder.itemView.iconImage.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_invoice
                )
            )
        }

        else if (selected.category.equals("Diğer")) {
            holder.itemView.iconImage.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_other
                )
            )
        }


        holder.itemView.explainText.text = selected.explain
        holder.itemView.amountText.text = selected.amount.toString()




        holder.itemView.rowLayout.setOnClickListener {
            val action = HomepageFragmentDirections.actionHomepageFragmentToDetailFragment(selected)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(wallet: List<Wallet>) {
        this.expenseList = wallet
        notifyDataSetChanged()
    }
}