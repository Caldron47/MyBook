package com.example.mybook

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mybook.Book
import com.example.mybook.databinding.ActivityBookDetailBinding

class BookDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val book = intent?.getParcelableExtra<Book>("book")
        if (book != null) {
            binding.bookImageDetail.setImageResource(book.imageID)
            binding.bookTitle.text = book.title
            binding.bookAuthor.text = book.author
            binding.bookDesc.text = book.description
            binding.buttonLink.setOnClickListener {
                openExternalLink(book.detailUrl)
            }
        }
    }

    private fun openExternalLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
