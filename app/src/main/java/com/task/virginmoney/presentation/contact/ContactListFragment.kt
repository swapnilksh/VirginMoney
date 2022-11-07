package com.task.virginmoney.presentation.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.task.virginmoney.databinding.FragmentContactListBinding
import com.task.virginmoney.presentation.room.state.ResponseState
import com.task.virginmoney.presentation.contact.adapter.ContactListAdapter
import com.task.virginmoney.presentation.contact.viewmodel.ContactListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactListFragment : Fragment() {

    private lateinit var binding: FragmentContactListBinding
    private val viewModel: ContactListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val contactListAdapter: ContactListAdapter by lazy {
        ContactListAdapter { contactItem ->
            val direction =
                ContactListFragmentDirections.actionContactListToContactDetails(contactItem.id.toString())
            findNavController().navigate(direction)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialiseData()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.contactsLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                ResponseState.Loading -> binding.pbProgressBar.visibility = View.VISIBLE
                is ResponseState.Success -> {
                    binding.pbProgressBar.visibility = View.GONE
                    contactListAdapter.submitList(state.item)
                }
                is ResponseState.Error -> {
                    binding.pbProgressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun initialiseData() {
        with(binding) {
            rcvPeopleList.adapter = contactListAdapter
            rcvPeopleList.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }
}
