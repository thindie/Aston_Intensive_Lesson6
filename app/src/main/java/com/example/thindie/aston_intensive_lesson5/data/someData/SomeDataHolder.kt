package com.example.thindie.aston_intensive_lesson5.data.someData

interface SomeDataHolder {
    fun getSomeData(): List<HavocDataModel>
    fun updateSomeData(
        oldData: HavocDataModel,
        newData: HavocDataModel
    )

    fun deleteData(data: HavocDataModel)
}