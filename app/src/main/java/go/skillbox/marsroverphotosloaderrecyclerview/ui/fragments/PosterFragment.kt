package go.skillbox.marsroverphotosloaderrecyclerview.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import go.skillbox.domain.models.entities.MarsPhoto
import go.skillbox.marsroverphotosloaderrecyclerview.R
import go.skillbox.marsroverphotosloaderrecyclerview.databinding.FragmentPosterBinding
import go.skillbox.marsroverphotosloaderrecyclerview.models.MarsPhotoUiModel
import go.skillbox.marsroverphotosloaderrecyclerview.ui.activities.MainActivity
import go.skillbox.marsroverphotosloaderrecyclerview.util.UiModelsConverter

class PosterFragment(item: MarsPhoto?) : Fragment() {

    constructor() : this(null)

    companion object {

        private const val ITEM_ARG_KEY = "item"

        fun newInstance(item: MarsPhoto) = PosterFragment(item)
    }

    private var binding: FragmentPosterBinding? = null
    private var photo: MarsPhotoUiModel? = convertItem(item)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPosterBinding.inflate(inflater, container, false)
        if (savedInstanceState != null) photo =
            savedInstanceState.getSerializable(ITEM_ARG_KEY) as MarsPhotoUiModel

        val actionBar = (activity as MainActivity?)!!.supportActionBar
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.idTextView.text =
            binding!!.idTextView.context.getString(R.string.mars_photo_id, photo?.id.toString())
        binding!!.roverTextView.text =
            binding!!.roverTextView.context.getString(R.string.rover_name, photo?.rover?.name)
        binding!!.cameraTextView.text =
            binding!!.cameraTextView.context.getString(
                R.string.camera_full_name,
                photo?.camera?.fullName
            )
        binding!!.solTextView.text =
            binding!!.solTextView.context.getString(R.string.sol, photo?.sol.toString())
        binding!!.dateTextView.text =
            binding!!.dateTextView.context.getString(R.string.data_earth, photo?.earthDate)
        photo?.let { photo ->
            Glide.with(this).load(photo.imgSrc).into(binding!!.imageView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(ITEM_ARG_KEY, photo)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun convertItem(item: MarsPhoto?): MarsPhotoUiModel? {
        if (item == null) return null
        return UiModelsConverter().convert(item)
    }
}