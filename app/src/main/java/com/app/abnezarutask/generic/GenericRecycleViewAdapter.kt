package com.app.abnezarutask.generic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.app.abnezarutask.BR
import com.app.abnezarutask.basic.ListItemModel

class GenericRecycleViewAdapter<T : ListItemModel>(@LayoutRes
                                                   val layoutId: Int) : RecyclerView.Adapter<GenericRecycleViewAdapter.GenericViewHolder<T>>()
{

    private var items = mutableListOf<T>()
    private var filterItems = mutableListOf<T>()
    private var inflater: LayoutInflater? = null
    private var onListItemViewClickListener: OnListItemViewClickListener? = null

    fun addItems(items: List<T>)
    {
        this.items.addAll(items)
        this.filterItems.addAll(items)
        notifyDataSetChanged()
    }

    fun onItemUpdated(item: T, position: Int){
        this.items[position] = item
        notifyItemChanged(position)
    }

    fun replaceItems(items: List<T>)
    {
        this.items.clear()
        this.filterItems.clear()
        this.items.addAll(items)
        this.filterItems.addAll(items)
        notifyDataSetChanged()
    }

    fun clearAll()
    {
        this.items.clear()
        this.filterItems.clear()
        notifyDataSetChanged()
    }

    fun removeItemByPos(position: Int)
    {
        this.items.removeAt(position)
        this.filterItems.removeAt(position)
        notifyDataSetChanged()
    }

    fun getItemByPosition(position: Int): Any
    {
        return items[position]
    }

    fun setOnListItemViewClickListener(onListItemViewClickListener: OnListItemViewClickListener?)
    {
        this.onListItemViewClickListener = onListItemViewClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T>
    {
        val layoutInflater = inflater ?: LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, layoutId, parent, false)
        return GenericViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int)
    {
        val itemViewModel = items[position]
        itemViewModel.adapterPosition = position
        onListItemViewClickListener?.let { itemViewModel.onListItemViewClickListener = it }
        holder.bind(itemViewModel)
    }

    class GenericViewHolder<T : ListItemModel>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
    {

        fun bind(itemViewModel: T)
        {
            binding.setVariable(BR.listItemViewModel, itemViewModel)
            binding.executePendingBindings()
        }
    }

    interface OnListItemViewClickListener
    {
        fun onClick(view: View, position: Int)
    }
}