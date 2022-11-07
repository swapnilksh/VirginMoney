package com.task.virginmoney.presentation.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.task.virginmoney.databinding.FragmentRoomListBinding
import com.task.virginmoney.presentation.room.state.ResponseState
import com.task.virginmoney.presentation.room.adapter.RoomListAdapter
import com.task.virginmoney.presentation.room.viewmodel.RoomListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomListFragment : Fragment() {

    private lateinit var binding: FragmentRoomListBinding
    private val viewModel: RoomListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRoomListBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val roomListAdapter: RoomListAdapter by lazy {
        RoomListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialiseData()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.roomListLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                ResponseState.Loading -> binding.pbProgressBar.visibility = View.VISIBLE
                is ResponseState.Success -> {
                    binding.pbProgressBar.visibility = View.GONE
                    roomListAdapter.submitList(state.item)
                }
                is ResponseState.Error -> {
                    binding.pbProgressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun initialiseData() {
        with(binding) {
            rcvRoomList.adapter = roomListAdapter
            rcvRoomList.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }
}
