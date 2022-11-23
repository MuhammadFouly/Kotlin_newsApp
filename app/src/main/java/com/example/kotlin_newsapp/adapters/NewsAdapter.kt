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
import com.example.kotlin_newsapp.R
import com.example.kotlin_newsapp.models.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // TODO use viewBinding instead
        var imageView: ImageView
        var name: TextView
        var title: TextView
        var description: TextView
        var publishedAt: TextView

        init {
            imageView = itemView.findViewById(R.id.ivArticleImage)
            name = itemView.findViewById(R.id.tvSource)
            title = itemView.findViewById(R.id.tvTitle)
            description = itemView.findViewById(R.id.tvDescription)
            publishedAt = itemView.findViewById(R.id.tvPublishedAt)
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentArticle = differ.currentList[position]
        holder.apply {
            Glide.with(itemView).load(currentArticle.urlToImage).into(imageView)
            name.text = currentArticle.source.name
            title.text = currentArticle.title
            description.text = currentArticle.description
            publishedAt.text = currentArticle.publishedAt
            onItemCliclListener?.let { it(currentArticle) }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size


    private var onItemCliclListener: ((Article) -> Unit)? = null
    fun setOnItemCliclListener(listener: ((Article) -> Unit)) {
        onItemCliclListener = listener
    }
}