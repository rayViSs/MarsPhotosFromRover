package go.skillbox.marsroverphotosloaderrecyclerview.ui.recyclerviews.pagingsources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import go.skillbox.domain.models.entities.MarsPhoto
import go.skillbox.domain.models.params.GetPhotosByPageParam

class PhotosPagingSource constructor(
    private val getPhotosByPage: suspend (GetPhotosByPageParam) -> List<MarsPhoto>,
    private val getParam: (Int) -> GetPhotosByPageParam
) : PagingSource<Int, MarsPhoto>() {

    companion object {
        private const val FIRST_PAGE = 1
    }

    override fun getRefreshKey(state: PagingState<Int, MarsPhoto>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarsPhoto> {
        val page = params.key ?: 1
        val param = getParam(page)
        return kotlin.runCatching {
            getPhotosByPage(param)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else page + 1
                )
            },
            onFailure = {
                LoadResult.Error(it)
            }
        )
    }
}