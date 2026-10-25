# Buku Belanja Harian

Aplikasi merekod perbelanjaan harian. Tekan mana-mana tarikh pada kalendar dan tulis nota perbelanjaan (perkara, jumlah RM, kategori, nota).

- `index.html`: aplikasi web (boleh dibuka terus dalam pelayar)
- `android/`: projek Android yang membungkus `index.html` dan jalan tanpa internet
- `play-store/`: ikon, grafik ciri, tangkapan skrin, polisi privasi dan teks senarai kedai
- `.github/workflows/android.yml`: membina APK dan AAB secara automatik di GitHub

## Bina app Android

Setiap kali kod ditolak (push), GitHub Actions membina:
- **APK ujian**: pasang terus di telefon untuk cuba
- **AAB**: fail yang dimuat naik ke Google Play

Muat turun dari tab **Actions** > larian terkini > bahagian **Artifacts**.

### Tandatangan untuk Google Play
AAB mesti ditandatangani dengan kunci muat naik. Tambah 4 rahsia di
**Settings > Secrets and variables > Actions**:

| Nama rahsia | Isi |
|---|---|
| `ANDROID_KEYSTORE_BASE64` | fail kunci `.jks` dalam bentuk base64 |
| `ANDROID_KEYSTORE_PASSWORD` | kata laluan kunci |
| `ANDROID_KEY_ALIAS` | `upload` |
| `ANDROID_KEY_PASSWORD` | kata laluan kunci |

Jangan sekali-kali masukkan fail kunci atau kata laluan ke dalam repo.

## Terbitkan di Google Play
1. Daftar akaun pembangun di https://play.google.com/console (yuran sekali USD 25).
2. Untuk jual app berbayar: lengkapkan profil pembayaran (payments profile) dalam Play Console.
3. Cipta app baharu, isi maklumat dari `play-store/SENARAI-KEDAI.md`.
4. Muat naik AAB bertandatangan ke trek **Ujian tertutup** dahulu.
   Akaun peribadi baharu perlu sekurang-kurangnya 12 penguji selama 14 hari sebelum boleh terbit ke Pengeluaran.
5. Polisi privasi perlu URL awam: hoskan `play-store/privacy-policy.html`
   (contoh: GitHub Pages pada repo awam, atau Google Sites).
6. Setiap kemas kini baharu: push perubahan, muat turun AAB baharu (nombor versi naik automatik), muat naik ke Play Console.
