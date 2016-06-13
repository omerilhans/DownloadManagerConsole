package core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Downloader Interface'inin Yardımı Ile, Dosya Indirme Islemini Yapan
 * Indirme Suresince & Sonrasinda Cesitli Eventler Calistiran Sinif
 * @author EmirCivas
 */
public class Manager implements Runnable
{
    Downloader d;
    /**
     * Constructor'a Kullanilacak Downloaded Interface'ini Gonderiyoruz
     * Ve Bunu Alttaki Diger Methodlarda Kullanmak Icin
     * Global'e Atiyoruz
     * @param d 
     */
    public Manager(Downloader d)
    {
        this.d = d;
        // Run Methodu Icerigini Ayri Bir Thread'te Calistir
        new Thread(this).start();
    }

    public void run() 
    {
        try
        {
            // Kabuk'tan Kaydedilecek Dosyanın Adresini Al
            File kaydedilecekDosya = new File(d.getSaveAdres());
            // Kabuktan Indirilecek Dosyanın Adresini Al
            URL indirilecekDosya = new URL(d.getURLAdres());
            
            byte[] tmp = new byte[4096];
            
            // Indirilecek Dosyaya Web Baglantisi Ac
            URLConnection uc = indirilecekDosya.openConnection();
            // Indirilecek Dosyanın Boyutunu Al
            long dosyaBoyutu = uc.getContentLengthLong();
            long indirilen = 0, toplamIndirilen = 0;
            
            FileOutputStream fos = new FileOutputStream(kaydedilecekDosya);
            InputStream is = uc.getInputStream();
            // Dosya Parca Parca Indiriliyor
            while ( (indirilen = is.read(tmp)) != -1)
            {
                // Su Ana Kadar Ne Kadari Indi ?
                toplamIndirilen += indirilen;
                // % Kacı Tamamlandı
                double p = ((double)toplamIndirilen * 100) / (dosyaBoyutu);
                // Kabuga, Anlik Olarak Dosya Indirme Bilgilerini Gonder
                d.update(p, dosyaBoyutu, toplamIndirilen);
                fos.write(tmp,0,(int)indirilen);
            }
            fos.close();
            // Islem Tamamlandiginda, Kabuğun Finish Methodunu Cagir
            d.finished();
            
        } catch (Exception e) 
        { 
            //e.printStackTrace(); 
            d.onError(e.toString());
        }
    }
    
    
}
