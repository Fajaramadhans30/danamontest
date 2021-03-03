package com.test.danamontest

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.danamontest.adapter.DataAdapter
import com.test.danamontest.api.DataProvider
import com.test.danamontest.model.DataModel
import com.test.danamontest.viewmodel.DataViewModel
import com.test.danamontest.viewmodel.ViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.progress_loading.*

class UserActivity :AppCompatActivity() {
    private lateinit var adapterData: DataAdapter
    private lateinit var dataViewModel: DataViewModel
    private var dataList: MutableList<DataModel?> = mutableListOf()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val compositeDisposable = CompositeDisposable()
    private val repository = DataProvider.dataProviderRepository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        linearLayoutManager = LinearLayoutManager(applicationContext)
        rvList.layoutManager = linearLayoutManager
        rvList.hasFixedSize()
        adapterData = DataAdapter(dataList, this)
        rvList.adapter = adapterData

        dataViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                compositeDisposable,
                repository,
                AndroidSchedulers.mainThread(),
                Schedulers.io()
            )
        ).get(DataViewModel::class.java)
        dataViewModel.setListData()
        dataViewModel.getListData().observe(this, getDatas)
    }

    private val getDatas = Observer<MutableList<DataModel>> { datasItem ->
        progressBar1.visibility = View.VISIBLE
        rvList.visibility = View.GONE
        if (datasItem != null) {
            dataList.clear()
            rvList.visibility = View.VISIBLE
            progressBar1.visibility = View.GONE
            if (datasItem.size > 0) {
                val datalistMovieNew: MutableList<DataModel?> = mutableListOf()
                datasItem.let { datalistMovieNew.addAll(it) }
                adapterData.addData(datalistMovieNew)
            }
        } else {
            rvList.visibility = View.GONE
            progressBar1.visibility = View.VISIBLE
        }
    }
}