package com.example.kotlin_newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.kotlin_newsapp.R
import com.example.kotlin_newsapp.databinding.FragmentBreakingNewsBinding
import com.example.kotlin_newsapp.databinding.ItemArticlePreviewBinding
import com.example.kotlin_newsapp.models.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    inner class ArticleViewHolder(
        val binding:ItemArticlePreviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.tvDescription.text = article.description
            binding.tvPublishedAt.text = article.publishedAt
            binding.tvTitle.text = article.title
            binding.tvSource.text = article.source.name
            Glide.with(binding.ivArticleImage.context).load(article.urlToImage)
                .placeholder(R.drawable.ic_loading).error(R.drawable.ic_no_image)
                .transform(CenterCrop(), RoundedCorners(15))
                .into(binding.ivArticleImage)

            binding.root.setOnClickListener {
                onItemCliclListener?.let {
                    it(article)
                }
            }


        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding=ItemArticlePreviewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentArticle = differ.currentList[position]
        holder.bind(currentArticle)
    }

    override fun getItemCount(): Int = differ.currentList.size


    private var onItemCliclListener: ((Article) -> Unit)? = null
    fun setOnItemCliclListener(listener: ((Article) -> Unit)) {
        onItemCliclListener = listener
    }
}