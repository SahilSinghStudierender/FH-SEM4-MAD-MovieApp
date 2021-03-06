package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.database.FavouriteMovieEntity
import com.example.movieapp.databinding.FavoritesItemBinding


class FavoritesListAdapter(
    private var dataSet: List<FavouriteMovieEntity>,
    val onDeleteClicked: (Long) -> Unit,
    val onEditClicked: (FavouriteMovieEntity) -> Unit
): RecyclerView.Adapter<FavoritesListAdapter.ViewHolder>() {

    /**
     * If data set needs to be updated, call me!
     */
    fun updateDataSet(movies: List<FavouriteMovieEntity>){
        dataSet = movies
        notifyDataSetChanged()
    }

    /**
     * Provide a reference to the type of views that you are using
     * custom ViewHolder.
     */
    inner class ViewHolder(private val binding: FavoritesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            // Define click listener for the ViewHolder's View.
            binding.removeBtn.setOnClickListener {
                binding.movie?.id?.let { itemId ->
                    onDeleteClicked(itemId)
                }
            }

            binding.editBtn.setOnClickListener {
                binding.movie?.let { movie ->
                    onEditClicked(movie)
                }
            }
        }

        fun bind(movieItem: FavouriteMovieEntity) {
            with(binding) {
                movie = movieItem
                /**
                 * Evaluates the pending bindings, updating any Views that have expressions bound to
                 * modified variables.
                 */
                executePendingBindings()
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view binding, which defines the UI of the list item
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.context),
                R.layout.favorites_item,
                viewGroup,
                false
            )   //the binding
        )

    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        holder.bind(dataSet[position])
    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
