package com.rogo.ch4.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rogo.ch4.data.local.database.entity.NoteEntity
import com.rogo.ch4.databinding.ItemNoteBinding


class HomeAdapter(private val listener: Listener) :
    RecyclerView.Adapter<HomeAdapter.ListViewHolder>() {
    private val callback = object : DiffUtil.ItemCallback<NoteEntity>() {
        override fun areItemsTheSame(
            oldItem: NoteEntity,
            newItem: NoteEntity
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: NoteEntity,
            newItem: NoteEntity
        ): Boolean =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ListViewHolder =
        ListViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) =
        holder.bind(differ.currentList[position])

    override fun getItemCount(): Int = differ.currentList.size

    inner class ListViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteEntity) {
            binding.noteEntity = item
            binding.itemListener = listener
        }
    }

    interface Listener {
        fun editListener(noteEntity: NoteEntity)
        fun deleteListener(noteEntity: NoteEntity)
    }

}