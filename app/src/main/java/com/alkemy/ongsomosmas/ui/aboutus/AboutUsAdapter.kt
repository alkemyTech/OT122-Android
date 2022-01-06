package com.alkemy.ongsomosmas.ui.aboutus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alkemy.ongsomosmas.R
import com.alkemy.ongsomosmas.data.model.MembersResponse
import com.alkemy.ongsomosmas.databinding.ItemAboutUsBinding
import com.squareup.picasso.Picasso

class AboutUsAdapter(
    private val listMembers: List<MembersResponse>,
    private val onClick: (position: Int) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class ViewHolder(view: View,
                     onClick: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemAboutUsBinding.bind(view)
        val memberName: TextView = binding.tvAboutUsName
        val memberImage: ImageView = binding.ivAboutUsImage

        fun bind(member: MembersResponse) {
            Picasso.get().load(member.image).into(memberImage)
            memberName.text = member.name
        }
        init {
            view.setOnClickListener{
                onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_about_us, parent, false)
        return ViewHolder(view,onClick)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(listMembers[position])
    }

    override fun getItemCount(): Int = listMembers.count()

}