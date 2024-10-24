package com.example.contract_app.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.contract_app.database.ContactService
import com.example.contract_app.database.User

class UserPagingSource(
    private val contactService: ContactService
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key ?: 0
        return try {
            val response = contactService.getContacts()
            val nextKey = if (response.isEmpty()) null else page + 1

            LoadResult.Page(
                data = response,
                prevKey = if (page == 0) null else page - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
