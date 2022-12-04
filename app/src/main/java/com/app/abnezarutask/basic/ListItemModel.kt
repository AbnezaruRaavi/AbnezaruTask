package com.app.abnezarutask.basic

import androidx.room.Ignore
import com.app.abnezarutask.generic.GenericRecycleViewAdapter


abstract class ListItemModel {
    @Ignore
    var adapterPosition: Int = -1
    @Ignore
    var onListItemViewClickListener: GenericRecycleViewAdapter.OnListItemViewClickListener? = null
}