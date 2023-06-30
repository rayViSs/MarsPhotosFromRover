package go.skillbox.marsroverphotosloaderrecyclerview.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import go.skillbox.domain.models.entities.MarsPhoto
import go.skillbox.domain.models.params.GetPhotosByPageParam
import go.skillbox.domain.models.params.GetPosterByItemParam
import go.skillbox.domain.usecases.GetPhotosByPageUseCase
import go.skillbox.domain.usecases.GetPosterByItemUseCase
import go.skillbox.marsroverphotosloaderrecyclerview.ui.recyclerviews.pagingsources.PhotosPagingSource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    companion object {
        private const val PAGE_SIZE = 5
    }

    @Inject
    lateinit var getPhotosByPageUseCase: GetPhotosByPageUseCase

    @Inject
    lateinit var getPosterByItemUseCase: GetPosterByItemUseCase

    val pagedPhotos: Flow<PagingData<MarsPhoto>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE),
        pagingSourceFactory = {
            PhotosPagingSource(
                getParam = { page -> getParamForRequestByPage(page) },
                getPhotosByPage = { param -> getPhotosByPage(param) }
            )
        }
    ).flow.cachedIn(viewModelScope)

    private val _marsPhotoFlow = Channel<MarsPhoto>()
    val marsPhotoFlow = _marsPhotoFlow.receiveAsFlow()

    fun onItemClick(item: MarsPhoto) {
        viewModelScope.launch {
            _marsPhotoFlow.send(getPosterByItemUseCase.execute(GetPosterByItemParam(item)).marsPhoto)
        }
    }

    private suspend fun getPhotosByPage(param: GetPhotosByPageParam): List<MarsPhoto> {
        return getPhotosByPageUseCase.execute(param)
    }

    private fun getParamForRequestByPage(page: Int): GetPhotosByPageParam {
        return GetPhotosByPageParam(page)
    }
}