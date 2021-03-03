package com.test.danamontest.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.danamontest.api.DataRepository
import com.test.danamontest.model.DataModel
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class DataViewModel(
    private val compositeDisposable: CompositeDisposable,
    private val repository: DataRepository,
    private val backgroundScheduler: Scheduler,
    private val mainScheduler: Scheduler
) : ViewModel() {
    private var listData = MutableLiveData<MutableList<DataModel>>()

    @SuppressLint("NullSafeMutableLiveData")
    fun setListData() {
        compositeDisposable.add(
            repository.getData()
                .observeOn(backgroundScheduler)
                .subscribeOn(mainScheduler)
                .subscribe({ DataViewModel ->
                    listData.postValue(DataViewModel as java.util.ArrayList<DataModel>?)
                }, { error ->
                    println("error message " + error.message)
                    listData.postValue(null)
                })
        )
    }

    fun getListData(): LiveData<MutableList<DataModel>> {
        return listData
    }
}

class ViewModelFactory(
    private val compositeDisposable: CompositeDisposable,
    private val repository: DataRepository,
    private val backgroundScheduler: Scheduler,
    private val mainScheduler: Scheduler
) : ViewModelProvider.NewInstanceFactory() {
    @SuppressWarnings("unchecked")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DataViewModel(
            compositeDisposable,
            repository,
            backgroundScheduler,
            mainScheduler
        ) as T
    }
}