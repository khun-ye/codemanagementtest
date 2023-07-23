package com.example.codemanagementtest.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.codemanagementtest.data.remote.movie.Movie
import com.example.codemanagementtest.domain.repository.NetworkRepository
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val networkRepository: NetworkRepository,
    private val source: Source
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPageNumber = params.key ?: 1

            val response = when (source) {
                is Source.Popular -> networkRepository.getPopularMovies(page = nextPageNumber)
                is Source.UpComming -> networkRepository.getUpCommingMovies(page = nextPageNumber)

            }

            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = response.page.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    sealed class Source(val genreId: Int? = null) {
        object Popular : Source()
        object UpComming : Source()
    }
}
