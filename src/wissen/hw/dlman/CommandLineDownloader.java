package wissen.hw.dlman;


import core.Downloader;
import core.Manager;
import java.util.Scanner;

// CommandLineDownloader Sinifi
public class CommandLineDownloader implements Downloader
{
    public static Scanner oku = new Scanner(System.in);

    public CommandLineDownloader() 
    {
        new Manager(this);
    }

    
    // Main Methodu
    public static void main(String[] args)
    {
        new CommandLineDownloader();
    }

    @Override
    public String getURLAdres() 
    {
        System.out.print("Indirilecek Dosyanın Adresi : ");
        String adr = oku.next();
        return adr;
    }

    @Override
    public String getSaveAdres() 
    {
        System.out.println("Kaydedilecek Dosyanın Yolu : ");
        String yol = oku.next();
        return yol;
    }

    @Override
    public void update(double percentage, long totalSize, long dlSize) 
    {
        String s = String.format("%d / %d : %.2f Tamamlandı\n", (int)dlSize, (int) totalSize, percentage);
        System.out.printf(s);
    }

    @Override
    public void finished() 
    {
        System.out.println("Dosya Indirme Tamamlandı");
    }

    @Override
    public void onError(String msg) 
    {
        System.err.println("Dosya Indirmede Hata : "+msg);
    }
    
    
}
