package com.huseyindas.walletixapp.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.huseyindas.walletixapp.R

class OnboardingItemsAdapter(private val onboardingItems: List<OnboardinItem>) :
RecyclerView.Adapter<OnboardingItemsAdapter.OnboardingItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        return OnboardingItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.onboarding_item_container,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        holder.bind(onboardingItems[position])
    }

    override fun getItemCount(): Int {
        return onboardingItems.size
    }

    inner class OnboardingItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageOnbarding = view.findViewById<ImageView>(R.id.imageOnboarding)
        private val texTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)

        fun bind(onboardingItem: OnboardinItem) {
            imageOnbarding.setImageResource(onboardingItem.onboardinImage)
            texTitle.text = onboardingItem.tilte
            textDescription.text = onboardingItem.description
        }
    }
}