package com.example.mybook

import com.example.mybook.BookAdapter
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mybook.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var bookAdapter: BookAdapter

    private val fictionBooks = listOf(
        Book("Just the Person I Loved Before", "Ollyjayzee", "Cinta pertama begitu berkesan. Cinta remaja, tak berbalas, dan sangat membekas. Mungkin karena semua serba pertama. Pertama jatuh cinta, dan pertama patah hati. Cinta yang aku bawa hingga masa dewasa. Sampai pada suatu ketika semua berubah. Itulah yang dirasakan oleh Sissy yang jatuh hati kepada Bram. Bram adalah kakak dari sahabat Sissy. Namun cinta tak berbalas itu justru membuat Sissy membuka hati pada pria lain, yaitu Hanif. Apakah Sissy bisa merekalan Bram dan apakah ia akan bahagia bersana Hanif?", R.drawable.fiksi1, "https://biancatwinbee.blogspot.com/2019/05/judul-just-person-i-loved-before.html"),
        Book("Dilan: Dia Adalah Dilanku Tahun 1990", "Pidi Baiq", "Siapa di sini yang suka dengan karakter Dilan? Film adaptasinya sendiri menjadi salah satu yang terbilang sukses. Kisah Dilan dan Milea yang dibawa ke film layar lebar berhasil membangkitkan kenangan era 90-an awal.\n" +
                "\n" +
                "Tidak berbeda jauh dengan filmnya, di bukunya pun ceritanya kental dengan gaya pacaran remaja di masa itu yang masih apa adanya; surat menyurat atau berkomunikasi dengan telepon umum.", R.drawable.fiksi2, "https://www.goodreads.com/book/show/22037542-dilan"),
        Book("Resign", "Almira Bastari", "Buku ini sendiri bercerita tentang The Cungpret, empat pegawai di kantor konsultan di Jakarta. Mereka kompak untuk bertaruh untuk resign dari pekerjaan mereka. Keseruan kisah di buku ini akan segera difilmkan lho!", R.drawable.fiksi3, "https://www.goodreads.com/book/show/37560884-resign"),
        Book("Bad Boy", "AliaZalea", "Jika kamu pembaca setia kisah para personel Band Pentagon, kamu pastinya menjadi salah satu yang membaca buku ini. Buku berjudul Bad Boy ini adalah buku terbaru dari seri Pentagon karya AliaZalea.\n" +
                "\n" +
                "Kali ini ceritanya tentang Adam dan Ziva. Adam digambarkan mengejar cinta masa SMA-nya yang bernama Ziva. Namun siapa sangka usahanya untuk memenangkan hati Ziva harus melalui jalan yang tak mulus. Bagaimana kisah akhir Adam dan Ziva di buku ini?", R.drawable.fiksi4, "https://www.gramedia.com/best-seller/review-novel-bad-boy-alia-azalea-seri-pentagon/"),
        Book("Dua Garis Biru", "Lucia Priandarini", "Dara, gadis pintar kesayangan guru. Bima, murid santai yang cenderung masa bodoh. Keduanya menyadari bahwa mereka bukan pasangan sempurna. Tetapi perbedaan justru membuat keduanya bahagia menciptakan dunia mereka sendiri.\n" +
                "\n" +
                "Namun di satu waktu mereka harus memilih memperjuangkan masa depan atau kehidupan lain yang tiba-tiba hadir. Cinta sederhana saja ternyata tak cukup. Kenyataan dan harapan keluarga membuat Bima dan Dara semakin terdesak ke persimpangan, siap menjalani bersama atau melangkah pergi ke dua arah berbeda.", R.drawable.fiksi5, "https://www.goodreads.com/book/show/52243156-dua-garis-biru"),
        Book("Le Mariage: The Game of Love", "Ika Vihara", "Bisakah seorang wanita menjalankan peran sebagai istri tanpa jatuh cinta kepada suaminya? Itu adalah perasaan Edna yang harus menikah dengan Alwin, namun pernikahan itu sebatas berdasar perjanjian yang saling menguntungkan.\n" +
                "\n" +
                "Sementara itu, Edna mulai meragukan keputusannya menikah dengan Alwin. Di sisi lain, Alwin masih dengan hatinya yang membatu setelah sang kekasih justru menikah dengan kembarannya.\n" +
                "\n" +
                "Apa yang akan terjadi dengan Edna dan Alwin kemudian?", R.drawable.fiksi6, "https://www.gramedia.com/products/le-mariage-the-game-of-love"),
    )

    private val nonFictionBooks = listOf(
        Book("Filosofi Teras", "Henry Manampiring", "Buku filsafat yang mudah dipahami dan sudah booming ialah Filosofi Teras karya Henry Manampiring. Fokus buku ini membahas mengenai filsafat stoisisme dengan bahasa yang tentunya mudah untuk dipahami.\n" +
                "\n" +
                "Kamu akan diajak penulis untuk belajar menjalani hidup dengan lebih baik dan legowo. Selain itu, meminimalisir overthinking karena cukup menguras tenaga dan energi. Kerennya, buku ini punya ilustrasi yang ciamik. Kamu jadi nggak bosan saat membacanya.", R.drawable.nonfiksi1, "https://www.gramedia.com/blog/review-buku-filosofi-teras-filsafat-kuno-panduan-hidup-zaman-now/"),
        Book("Atomic Habits", "James Clear", "Di samping Filosofi Teras, buku non fiksi Atomic Habits sama populernya. Kisah kesuksesan orang-orang terkenal dan ternama bermula dari melakukan hal-hal kecil.\n" +
                "\n" +
                "Kamu akan belajar mengenai kebiasaan-kebiasaan kecil yang nyatanya berdampak besar jika dilakukan secara rutin.", R.drawable.nonfiksi2, "https://www.gramedia.com/best-seller/review-buku-atomic-habits/"),
        Book("The Lean Startup", "Eric Ries", "Startup atau perusahaan baru banyak dibangun di era ini. Walaupun menawarkan berbagai inovasi baru, pada kenyataannya tidak semua startup berhasil untuk survive dengan perusahaan-perusahaan lain. Ini karena startup sendiri bersifat lebih fleksibel dan tidak pasti.\n" +
                "\n" +
                "Buku non fiksi The Lean Startup berisikan hal-hal apa saja yang bisa membuat startup itu gagal di tengah jalan. Padahal, di dalam buku ini dikatakan bahwa kegagalan dalam startup bisa diantisipasi. Cara-cara antisipasi tersebut ada di buku The Lean Startup karya Eric Ries.", R.drawable.nonfiksi3, "https://books.google.co.id/books/about/The_Lean_Startup_Indonesian_Edition.html?id=IqXcCwAAQBAJ&redir_esc=y"),
        Book("Sapiens", "Yuval Noah Harari", "Kamu mau menambah insight baru mengenai sejarah? Yuval Noah Harari mampu mewujudkannya lewat karya di buku Sapiens. Buku ini bercerita mengenai sejarah peradaban manusia mulai dari zaman purba hingga modern.\n" +
                "\n" +
                "Penulis pun menyampaikan fakta dan menghubungkannya dengan berbagai teori soal perkembangan manusia yang sebagian besar dipelajari di buku sejarah.\n" +
                "\n" +
                "Selain itu, lewat buku ini penulis membantu meningkatkan pemahaman pembaca mengenai apa arti menjadi manusia itu sendiri. Buku ini bisa jadi sangat bermakna, terutama bagi para remaja.", R.drawable.nonfiksi4, "https://www.gramedia.com/best-seller/review-buku-sapiens/"),
        Book("Thinking, Fast and Slow", "Daniel Kahneman", "Buku ini menjelaskan tentang cara berpikir seseorang. Jadi, pada dasarnya cara berpikir seseorang terbagi menjadi 2 tipe yakni cepat dan lambat.\n" +
                "\n" +
                "Buku ini membantu kamu menemukan tipe berpikir apakah dirimu. Hal ini akan mempengaruhi kamu dalam proses pengambilan keputusan.\n" +
                "\n" +
                "Daniel Kahneman, penulis buku Thinking, Fast and Slow menuliskan eksprimen mengenai kedua tipe berpikir tersebut. Tertarik baca? Kamu bisa dapatkan bukunya secara online atau melalui toko buku terdekat.", R.drawable.nonfiksi5, "https://www.gramedia.com/best-seller/review-buku-thinking-fast-and-slow-daniel-kahneman/"),
        Book("How To Win Friends and Influence People", "Dale Carnegie", "Sulit berinteraksi dalam pertemanan atau lingkungan baru? Dale Carnegie akan mengajari kamu caranya lewat buku “How To Win Friends and Influence People”. Utamanya dari buku ini mengajarkan bagaimana caranya berinteraksi di media sosial.\n" +
                "\n" +
                "Lewat buku ini, Dale menyampaikan bahwa untuk berinteraksi kita harus bisa mengelola sifat egois. Jadi, kamu disarankan untuk banyak mendengar, mengenal nama dalam berkomunikasi, sampai dengan mengucapkan terima kasih sebagai sesuatu yang penting tetapi masih jarang untuk dilakukan.", R.drawable.nonfiksi6, "https://www.goodreads.com/book/show/4865.How_to_Win_Friends_and_Influence_People"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences = getSharedPreferences("MyBookPrefs", Context.MODE_PRIVATE)
        val selectedCategory = sharedPreferences.getInt("SelectedCategory", R.id.menu_fiction)
        val isDarkTheme = sharedPreferences.getBoolean("IsDarkTheme", false)

        bookAdapter = BookAdapter(if (selectedCategory == R.id.menu_fiction) fictionBooks else nonFictionBooks)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = bookAdapter

        if (selectedCategory == R.id.menu_fiction) {
            bookAdapter.notifyDataSetChanged()
            bookAdapter.updateBooks(fictionBooks)
        } else {
            bookAdapter.updateBooks(nonFictionBooks)
            bookAdapter.notifyDataSetChanged()
        }

        val bottomNavMenu = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavMenu.selectedItemId = selectedCategory
        bottomNavMenu.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_fiction -> {
                    bookAdapter.updateBooks(fictionBooks)
                    saveSelectedCategory(R.id.menu_fiction)
                    true
                }
                R.id.menu_nonfiction -> {
                    bookAdapter.updateBooks(nonFictionBooks)
                    saveSelectedCategory(R.id.menu_nonfiction)
                    true
                }
                else -> false
            }
        }

        val themeSwitch = findViewById<Switch>(R.id.themeSwitch)
        themeSwitch.isChecked = isDarkTheme
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setDarkTheme()
                saveThemeSelection(true)
            } else {
                setLightTheme()
                saveThemeSelection(false)
            }
        }

        // Apply the saved theme
        if (isDarkTheme) {
            setDarkTheme()
        } else {
            setLightTheme()
        }
    }

    private fun saveSelectedCategory(categoryId: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt("SelectedCategory", categoryId)
        editor.apply()
    }

    private fun saveThemeSelection(isDarkTheme: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("IsDarkTheme", isDarkTheme)
        editor.apply()
    }

    private fun setDarkTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        bookAdapter.notifyDataSetChanged()
    }

    private fun setLightTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        bookAdapter.notifyDataSetChanged()
    }
}
