package com.narvi.delivery.movie.presentation.ui.review

import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.narvi.delivery.movie.R
import com.narvi.delivery.movie.data.model.Movie
import com.narvi.delivery.movie.data.model.Review
import com.narvi.delivery.movie.databinding.ItemMovieInformationBinding
import com.narvi.delivery.movie.databinding.ItemMyReviewBinding
import com.narvi.delivery.movie.databinding.ItemReviewBinding
import com.narvi.delivery.movie.databinding.ItemReviewFormBinding
import com.narvi.delivery.movie.presentation.uitll.Constant.ITEM_VIEW_TYPE_ITEM
import com.narvi.delivery.movie.presentation.uitll.Constant.REVIEWS_ITEM_VIEW_MY_REVIEW
import com.narvi.delivery.movie.presentation.uitll.Constant.REVIEWS_ITEM_VIEW_REVIEW_FORM
import com.narvi.delivery.movie.presentation.uitll.Constant.REVIEWS_ITEM_VIEW_TYPE_HEADER
import com.narvi.delivery.movie.presentation.uitll.Constant.REVIEWS_ITEM_VIEW_TYPE_ITEM
import java.util.*

class MovieReviewsAdapter(private val movie: Movie) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var myReview : Review? = null
    var reviews: List<Review> = emptyList()

    var onReviewSubmitButtonClickListener: ((content: String, score: Float) -> Unit)? = null
    var onReviewDeleteButtonClickListener : ((Review) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            REVIEWS_ITEM_VIEW_TYPE_HEADER -> {
                MovieInformationViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_movie_information,
                        parent,
                        false
                    )
                )
            }
            REVIEWS_ITEM_VIEW_TYPE_ITEM -> {
                ReviewViewHolder(parent)
            }

            REVIEWS_ITEM_VIEW_REVIEW_FORM -> {
                ReviewFormViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_review_form,
                        parent,
                        false
                    )
                )
            }

            REVIEWS_ITEM_VIEW_MY_REVIEW -> {
                MyReviewViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_my_review,
                        parent,
                        false
                    )
                )
            }

            else -> throw RuntimeException("알 수 없는 ViewType 입니다.")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is MovieInformationViewHolder -> {
                holder.bind(movie)
            }
            is ReviewViewHolder -> {
                holder.bind(reviews[position - 2])
            }
            is MyReviewViewHolder -> {
                myReview ?: return
                holder.bind(myReview!!)
            }
            is ReviewFormViewHolder -> Unit

            else -> throw RuntimeException("알 수 없는 ViewHolder 입니다.")
        }
    }

    override fun getItemCount(): Int {
        return 2 + reviews.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0 -> REVIEWS_ITEM_VIEW_TYPE_HEADER
            1 -> {
                if (myReview == null){
                    REVIEWS_ITEM_VIEW_REVIEW_FORM
                } else {
                    REVIEWS_ITEM_VIEW_MY_REVIEW
                }
            }
            else -> REVIEWS_ITEM_VIEW_TYPE_ITEM
        }
    }

    class MovieInformationViewHolder(
        private val binding : ItemMovieInformationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie) {
            binding.movie = item
        }
    }

    inner class ReviewFormViewHolder(
        private val binding: ItemReviewFormBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.submitButton.setOnClickListener {
                onReviewSubmitButtonClickListener?.invoke(
                    binding.reviewFieldEditText.text.toString(),
                    binding.ratingBar.rating
                )
                binding.reviewFieldEditText.text.clear()
            }

            binding.reviewFieldEditText.addTextChangedListener { editable ->
                binding.contentLimitTextView.text = "(${editable?.length ?: 0}/50)"
                binding.submitButton.isEnabled = (editable?.length ?: 0) >= 5
            }
        }
    }

    inner class ReviewViewHolder(
        parent: ViewGroup,
        private val binding : ItemReviewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_review,
            parent,
            false
        )
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Review) {
            binding.review = item
        }
    }

    inner class MyReviewViewHolder(
        private val binding: ItemMyReviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.deleteButton.setOnClickListener {
                onReviewDeleteButtonClickListener?.invoke(myReview!!)
                notifyDataSetChanged()
            }
        }

        fun bind(item: Review) {
            binding.review = item
        }
    }
}