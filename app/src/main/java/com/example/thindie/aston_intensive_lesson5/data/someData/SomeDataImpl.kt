package com.example.thindie.aston_intensive_lesson5.data.someData


import androidx.compose.runtime.mutableStateListOf
import com.github.javafaker.Faker
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SomeDataImpl @Inject constructor(private val faker: Faker) : SomeDataHolder {
    private val _someData: MutableList<HavocDataModel> = mutableStateListOf(
    )

    @Inject
    fun onStart() {
        for (e in 0..110) {
            _someData.add(
                HavocDataModel(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.phoneNumber().phoneNumber(),
                    generateUrl()
                )
            )
        }

    }


    override fun getSomeData() = _someData.toList()

    override fun updateSomeData(
        oldData: HavocDataModel,
        newData: HavocDataModel
    ) {
        val modifiedData = _someData.map { oldValue ->
            if (oldData.havocName == oldValue.havocName && oldData.havocConnection == oldValue.havocConnection) {
                HavocDataModel(newData.havocName, newData.havocFullName, newData.havocConnection, newData.havocPicUrl)
            } else oldValue
        }
        _someData.clear()
        _someData.addAll(modifiedData)
    }


    override fun deleteData(data: HavocDataModel) {
        _someData.removeIf { oldData ->
            oldData.havocName == data.havocName && oldData.havocConnection == data.havocConnection
                    && oldData.havocFullName == data.havocFullName
        }
    }

    companion object {
        private var imageIndex = 1
        private const val IMAGE_URL = "https://loremflickr.com/320/240/person?lock="

        fun generateUrl(): String {
            return IMAGE_URL.plus(imageIndex++)
        }
    }
}

