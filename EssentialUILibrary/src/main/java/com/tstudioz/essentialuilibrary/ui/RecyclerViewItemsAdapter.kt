package com.tstudioz.essentialuilibrary.ui

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RecyclerViewItemsAdapter<T, V> (private val listener: OnItemRecyclerViewListener<T>,
                                     private val diffCallback: DiffCallback<T>,
                                     private val viewHolderHandler: ViewHolderHandler<T, V>)
        : RecyclerView.Adapter<V>() where V : RecyclerView.ViewHolder {

    interface OnItemRecyclerViewListener<T> {
        fun onItemSelected(item: T);
    }

    interface DiffCallback<T> {
        fun areItemsTheSame(item1: T, item2: T): Boolean;
        fun areContentsTheSame(item1: T, item2: T): Boolean;
    }

    interface ViewHolderHandler<T, V> {
        fun getLayoutId(viewType: Int): Int;
        fun createViewHolder(view: View): V;
        fun bind(holder: V, item: T);
    }

    var values: List<T>? = null
        private set;

    fun setItems(newValues: List<T>?) {
        if (values == null) {
            values = newValues;
            notifyItemRangeInserted(0, newValues!!.size);
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return diffCallback.areItemsTheSame(values!![oldItemPosition], newValues!![newItemPosition]);
                }

                override fun getOldListSize(): Int {
                    return values!!.size;
                }

                override fun getNewListSize(): Int {
                    return newValues!!.size;
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return diffCallback.areContentsTheSame(values!![oldItemPosition], newValues!![newItemPosition]);
                }

            });
            values = newValues;
            result.dispatchUpdatesTo(this);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): V {
        val view = LayoutInflater.from(parent.context)
                .inflate(viewHolderHandler.getLayoutId(viewType), parent, false);
        return viewHolderHandler.createViewHolder(view);
    }

    override fun getItemCount(): Int {
        return values?.size ?: 0;
    }

    override fun onBindViewHolder(holder: V, position: Int) {
        val item = values!![position]
        viewHolderHandler.bind(holder, item);
        holder.itemView.setOnClickListener {
            listener.onItemSelected(item);
        }
    }
}