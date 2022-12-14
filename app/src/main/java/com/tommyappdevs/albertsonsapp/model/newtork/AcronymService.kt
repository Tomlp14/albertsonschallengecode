package com.tommyappdevs.albertsonsapp.model.newtork

import com.tommyappdevs.albertsonsapp.model.AcronymsResponseItem
import com.tommyappdevs.albertsonsapp.util.END_POINT
import com.tommyappdevs.albertsonsapp.util.RESULT_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymService {
    @GET(END_POINT)
    suspend fun getAcronyms(
        @Query(RESULT_KEY) ancronymInput: String): Response<List<AcronymsResponseItem>>
}