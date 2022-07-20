package com.project.memelist.api

import com.project.memelist.model.MemeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MemeService {

    //https://alpha-meme-maker.herokuapp.com/
    @GET("{pageNum}")
    suspend fun getMemesByPageNum(
        @Path("pageNum") num : Int
    ):Response<MemeResponse>
}