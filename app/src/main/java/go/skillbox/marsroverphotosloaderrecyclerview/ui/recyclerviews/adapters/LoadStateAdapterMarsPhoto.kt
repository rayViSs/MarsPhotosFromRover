package go.skillbox.marsroverphotosloaderrecyclerview.ui.recyclerviews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import go.skillbox.marsroverphotosloaderrecyclerview.databinding.LoadProgressBinding

class LoadStateAdapterMarsPhoto : LoadStateAdapter<LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) = Unit

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding =
            LoadProgressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding)
    }
}

class LoadStateViewHolder(binding: LoadProgressBinding) : ViewHolder(binding.root)