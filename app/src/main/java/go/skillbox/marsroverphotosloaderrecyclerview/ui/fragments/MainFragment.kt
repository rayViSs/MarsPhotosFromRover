package go.skillbox.marsroverphotosloaderrecyclerview.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import go.skillbox.marsroverphotosloaderrecyclerview.R
import go.skillbox.marsroverphotosloaderrecyclerview.databinding.FragmentMainBinding
import go.skillbox.marsroverphotosloaderrecyclerview.ui.activities.MainActivity
import go.skillbox.marsroverphotosloaderrecyclerview.ui.recyclerviews.adapters.LoadStateAdapterMarsPhoto
import go.skillbox.marsroverphotosloaderrecyclerview.ui.recyclerviews.adapters.MarsPhotosRecyclerViewAdapter
import go.skillbox.marsroverphotosloaderrecyclerview.ui.viewmodels.MainViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var binding: FragmentMainBinding? = null
    private val viewModel by viewModels<MainViewModel>()

    private val adapter = MarsPhotosRecyclerViewAdapter { item -> viewModel.onItemClick(item) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        val actionBar = (activity as MainActivity?)!!.supportActionBar
        actionBar?.setHomeButtonEnabled(false)
        actionBar?.setDisplayHomeAsUpEnabled(false)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.recyclerView.adapter = adapter.withLoadStateFooter(LoadStateAdapterMarsPhoto())

        binding!!.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }

        viewModel.pagedPhotos.onEach {
            adapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.loadStateFlow.onEach {
            val state = it.refresh
            binding!!.swipeRefresh.isRefreshing = state == LoadState.Loading
            when (state) {
                is LoadState.Error -> {
                    Toast.makeText(activity, state.error.message, Toast.LENGTH_SHORT).show()
                }
                else -> {
//                    TODO nothing
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.marsPhotoFlow.onEach {
            parentFragmentManager.beginTransaction()
                .addToBackStack(this.toString())
                .replace(R.id.container, PosterFragment.newInstance(it))
                .commit()
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}