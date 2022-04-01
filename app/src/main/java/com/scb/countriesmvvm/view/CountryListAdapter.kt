package com.scb.countriesmvvm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.scb.countriesmvvm.model.Country
import com.scb.countriesmvvm.R
import com.scb.countriesmvvm.util.getProgressDrawable
import com.scb.countriesmvvm.util.loadImage


class CountryListAdapter(var countries: ArrayList<Country>) :
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    fun updateCountries(newCountries: List<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = CountryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
    )

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @BindView(R.id.tvNameCountry)
        lateinit var countryName: TextView

        @BindView(R.id.ivCountry)
        lateinit var imageViewCountry: ImageView

        @BindView(R.id.tvCapital)
        lateinit var capitalName: TextView

        private val progressDrawable = getProgressDrawable(view.context)
        init {
            ButterKnife.bind(this, view)
        }

        fun bind(country: Country) {
            countryName.text = country.countryName
            capitalName.text = country.capital
            imageViewCountry.loadImage(country.flag, progressDrawable)
        }
    }
}