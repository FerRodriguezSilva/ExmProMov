package com.calyrsoft.ucbp1.features.movie.domain.repository

import com.calyrsoft.ucbp1.features.movie.domain.model.MovieModel

interface IMoviesRepository {
    suspend fun fetchPopularMovies(): Result<List<MovieModel>>
    suspend fun likeMovie(movie: MovieModel)
    suspend fun getAllMovies(): Result<List<MovieModel>>
}