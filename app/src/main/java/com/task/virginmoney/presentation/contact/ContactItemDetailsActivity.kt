package com.task.virginmoney.presentation.contact

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.task.virginmoney.R
import com.task.virginmoney.databinding.ActivityContactItemDetailsBinding
import com.task.virginmoney.extensions.concatenate
import com.task.virginmoney.presentation.room.state.ResponseState
import com.task.virginmoney.presentation.contact.model.ContactItem
import com.task.virginmoney.presentation.contact.viewmodel.ContactDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ContactItemDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactItemDetailsBinding
    private val args by navArgs<ContactItemDetailsActivityArgs>()
    private val viewModel: ContactDetailsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactItemDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getContactDetails(args.id)
        setupActionBar()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.contactDetailsStateLiveData.observe(this) { state ->
            when (state) {
                ResponseState.Loading -> {
                    binding.pbProgressBar.visibility = View.VISIBLE
                }
                is ResponseState.Success -> {
                    binding.pbProgressBar.visibility = View.GONE
                    setContactDetailsData(state.item)
                }
                is ResponseState.Error -> {
                    Toast.makeText(this, "Error:$state.toString()", Toast.LENGTH_SHORT).show()
                    binding.pbProgressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun setContactDetailsData(contactItem: ContactItem) {
        with(binding) {
            tvName.text = contactItem.firstName.concatenate(contactItem.lastName)
            tvEmailId.text = contactItem.email
            tvJobTitle.text = contactItem.jobtitle.orEmpty()
            tvFavoriteColor.text = contactItem.favouriteColor.orEmpty()
            Glide.with(root.context)
                .load(contactItem.avatar)
                .placeholder(R.drawable.ic_person_place_holder)
                .error(R.drawable.ic_person_place_holder)
                .into(ivPersonAvatar)
        }
    }

    private fun setupActionBar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = this.getString(R.string.contact_details)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}