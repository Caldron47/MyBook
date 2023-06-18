package com.example.mybook

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybook.Book
import com.example.mybook.databinding.ListItemBookBinding

class BookAdapter(private var books: List<Book>) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ListItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.bookCard1.setOnClickListener {
                val position = adapterPosition
                val bookIndex = position * 2
                val clickedBook = books.getOrNull(bookIndex)
                clickedBook?.let {
                    val intent = Intent(binding.root.context, BookDetailActivity::class.java).apply {
                        putExtra("book", it)
                    }
                    binding.root.context.startActivity(intent)
                }
            }

            binding.bookCard2.setOnClickListener {
                val position = adapterPosition
                val bookIndex = position * 2 + 1
                val clickedBook = books.getOrNull(bookIndex)
                clickedBook?.let {
                    val intent = Intent(binding.root.context, BookDetailActivity::class.java).apply {
                        putExtra("book", it)
                    }
                    binding.root.context.startActivity(intent)
                }
            }
        }

        fun bind(book: Book) {
            binding.bookTitle1.text = book.title
            binding.bookImage1.setImageResource(book.imageID)
            binding.bookCard1.visibility = View.VISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val layoutParams = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        binding.root.layoutParams = layoutParams
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        val bookCount = books.size
        return (bookCount + 1) / 2 // Mengubah jumlah item berdasarkan jumlah kartu yang diperlukan
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bookIndex = position * 2
        val book1 = books.getOrNull(bookIndex)
        book1?.let {
            holder.bind(it)
        } ?: run {
            holder.binding.bookCard1.visibility = View.INVISIBLE
        }

        val book2 = books.getOrNull(bookIndex + 1)
        book2?.let {
            holder.binding.bookTitle2.text = it.title
            holder.binding.bookImage2.setImageResource(it.imageID)
            holder.binding.bookCard2.visibility = View.VISIBLE
        } ?: run {
            holder.binding.bookCard2.visibility = View.INVISIBLE
        }
    }

    fun updateBooks(newBooks: List<Book>) {
        books = newBooks
        notifyDataSetChanged()
    }
}
