package go.skillbox.domain.usecases

import go.skillbox.domain.exceptions.GetPosterByItemException
import go.skillbox.domain.models.params.GetPosterByItemParam
import go.skillbox.domain.models.results.Poster
import javax.inject.Inject

class GetPosterByItemUseCase @Inject constructor() {

    fun execute(getPosterByItemParam: GetPosterByItemParam?): Poster {
        return getPosterByItemParam?.marsPhoto?.let { Poster(it) }
            ?: throw GetPosterByItemException("Poster item can't be null")
    }
}