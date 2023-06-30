package go.skillbox.marsroverphotosloaderrecyclerview.ui.recyclerviews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import go.skillbox.domain.models.entities.MarsPhoto
import go.skillbox.marsroverphotosloaderrecyclerview.R
import go.skillbox.marsroverphotosloaderrecyclerview.databinding.ItemPhotoBinding

class MarsPhotosRecyclerViewAdapter constructor(
    private val onClick: (MarsPhoto) -> Unit
) :
    PagingDataAdapter<MarsPhoto, MarsPhotoViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context))
        return MarsPhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarsPhotoViewHolder, position: Int) {
        val photo = getItem(position)

        with(holder.binding) {
            cameraTextView.text =
                cameraTextView.context.getString(R.string.camera_full_name, photo?.camera?.fullName)
            dateTextView.text =
                dateTextView.context.getString(R.string.data_earth, photo?.earthDate)
            idTextView.text =
                idTextView.context.getString(R.string.mars_photo_id, photo?.id.toString())
            solTextView.text =
                solTextView.context.getString(R.string.sol, photo?.sol.toString())
            roverTextView.text =
                roverTextView.context.getString(R.string.rover_name, photo?.rover?.name)
            photo?.let {
                Glide.with(imageView).load(it.imgSrc).into(imageView)
            }
            root.setOnClickListener {
                photo?.let {
                    onClick(photo)
                }
            }
        }
    }
}

class MarsPhotoViewHolder(val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root)

class DiffUtilCallback : DiffUtil.ItemCallback<MarsPhoto>() {
    override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean =
        oldItem == newItem
}