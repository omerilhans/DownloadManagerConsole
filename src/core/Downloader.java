package core;

/**
 * Downloader Interface'i Manager Sınıfı Tarafından Kullanılan
 * Dosya Indirme Islemindeki Cesitli Adımları , Manager Sınıfına Sağlayan
 * Interface'tir.
 * @author EmirCivas
 */
public interface Downloader 
{
    // Indirilecek Dosyanın Adresini Donduren Methodun Imzası
    public String getURLAdres();
    // Kaydedilecek Dosyanın Adresini Donduren Methodun Imzası
    public String getSaveAdres();
    // Dosya Indirildikce, Indirilen %'yi, Dosyanın Toplam Boyutunu
    // Ve Inen Parcanın Boyutunu Gonderen Methodun Imzası
    public void update(double percentage, long totalSize, long dlSize);
    // Dosya Indirme Islemi Tamamlandiginda Cagrilan Methodun Imzası
    public void finished();
    
    public void onError(String msg);
            
}
