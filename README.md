# Dokumentasi Aplikasi Android NewsNow 

NewsNow adalah aplikasi pembaca berita yang mengambil data dari newsapi.org dan menampilkannya kepada pengguna. Aplikasi ini dirancang untuk memberikan pengalaman membaca berita yang nyaman dan terorganisir. Pengguna dapat melihat detail dari setiap berita dengan mengetuk item berita yang diinginkan. Selain itu, aplikasi ini memiliki fitur untuk menyimpan berita favorit di bagian favorit. Penyimpanan berita favorit dilakukan menggunakan SQLite, sehingga pengguna dapat mengakses berita favorit mereka kapan saja meskipun sedang offline.

## Petunjuk Penggunaan

### 1. Layar SplashScreen

Layar ini muncul saat aplikasi dibuka dan menampilkan logo atau nama aplikasi untuk beberapa detik sebelum beralih ke layar utama.

### 2. Layar Home

Pada layar ini terdapat beberapa fitur yang bisa digunakan oleh pengguna:
- **Pemilihan Kategori**: Di bilah atas, pengguna dapat memilih kategori berita yang diinginkan.
- **Pencarian Berita**: Di bilah tengah, pengguna dapat mencari berita berdasarkan kata kunci.
- **Browsing Berita**: Di bilah bawah, pengguna dapat melihat daftar berita terbaru.

### 3. Layar Detail

Layar ini menampilkan informasi lebih lanjut mengenai berita yang dipilih oleh pengguna. Pengguna dapat mengetuk tombol di kanan bawah untuk menyimpan berita ke favorit.

### 4. Layar Favorites

Layar ini menampilkan berita-berita yang telah tersimpan ketika pengguna menekan tombol favorit. Pengguna dapat mengakses berita favorit kapan saja meskipun sedang offline.

## Implementasi Teknis

### 1. Pengambilan Data dari News API

- Aplikasi ini menggunakan News API dari newsapi.org untuk mendapatkan data berita terbaru.
- Pengambilan data dilakukan dengan mengirimkan permintaan HTTP ke endpoint API, dan hasilnya diolah dalam format JSON.
- Data yang diterima mencakup informasi seperti judul berita, deskripsi, URL gambar, dan konten lengkap berita.

### 2. Tampilan Berita

- Berita ditampilkan dalam antarmuka pengguna yang dirancang dengan menggunakan RecyclerView di Android.
- Setiap item berita dalam daftar dapat diketuk oleh pengguna untuk melihat detail berita secara lengkap.
- Detail berita ditampilkan dalam activity terpisah dengan informasi yang lebih mendalam.

### 3. Penyimpanan Berita Favorit

- Aplikasi ini memungkinkan pengguna untuk menyimpan berita ke bagian favorit.
- Penyimpanan dilakukan menggunakan SQLite, sebuah database lokal yang memungkinkan penyimpanan data secara offline.
- Struktur database mencakup tabel yang menyimpan informasi berita seperti ID, judul, deskripsi, dan URL.
- Menu favorit dapat diakses melalui bilah navigasi di bawah, dan akan menuju activity terpisah.

### 4. Navigasi dalam Aplikasi

- Aplikasi ini menggunakan komponen navigasi untuk mengelola transisi antara berbagai layar (fragments).
- BottomNavigationView digunakan untuk memudahkan pengguna dalam bernavigasi antara halaman utama dan halaman favorit.

### 5. Splash Screen

- Aplikasi ini memiliki splash screen yang muncul saat aplikasi pertama kali dibuka.
- SplashScreen menggunakan handler untuk menampilkan layar splash selama beberapa detik sebelum beralih ke MainActivity.

### 6. MainActivity

- MainActivity adalah activity utama yang mengatur navigasi dan menampilkan daftar berita.
- Menggunakan NavController dan NavigationUI untuk mengatur navigasi di dalam aplikasi.

