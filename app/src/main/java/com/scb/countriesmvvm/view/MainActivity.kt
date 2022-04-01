package com.scb.countriesmvvm.view

import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.scb.countriesmvvm.R
import com.scb.countriesmvvm.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {

    @BindView(R.id.rvCountriesList)
    lateinit var rvCountriesList: RecyclerView

    @BindView(R.id.tvListError)
    lateinit var tvListError: TextView

    @BindView(R.id.pbLoadingView)
    lateinit var pbLoadingView: ProgressBar

    lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        rvCountriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.countries.observe(this, Observer {countries ->
            countries?.let {
                rvCountriesList.visibility = VISIBLE
                countriesAdapter.updateCountries(it) }
        })

        viewModel.countryLoadError.observe(this, Observer { isError ->
            isError?.let { tvListError.visibility = if(it) VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                pbLoadingView.visibility = if(it) VISIBLE else View.GONE
                if(it) {
                    tvListError.visibility = View.GONE
                    rvCountriesList.visibility = View.GONE
                }
            }
        })
    }
}