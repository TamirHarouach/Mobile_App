package com.example.cirestechnologiesmobilechallenge.data.remote.repository


import com.example.cirestechnologiesmobilechallenge.core.util.Resource
import com.example.cirestechnologiesmobilechallenge.data.remote.dto.NewsDto
import kotlinx.coroutines.flow.Flow

interface NewsServices {

    fun getNewsByCategory(category: String): Flow<Resource<NewsDto>>

}