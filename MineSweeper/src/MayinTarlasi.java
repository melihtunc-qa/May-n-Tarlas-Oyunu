import java.util.Random;
import java.util.Scanner;

public class MayinTarlasi {

    private char[][] oyunTahtasi;
    private char[][] mayinKonumlari;
    private int satirSayisi;
    private int sutunSayisi;
    private int mayinSayisi;
    private int acilanHucreSayisi;
    private int zorlukSeviyesi ;

    private int skor;

    public MayinTarlasi(int satirSayisi, int sutunSayisi,int zorlukSeviyesi) {
        this.satirSayisi = satirSayisi;
        this.sutunSayisi = sutunSayisi;
        this.oyunTahtasi = new char[satirSayisi][sutunSayisi];
        this.mayinKonumlari = new char[satirSayisi][sutunSayisi];
        this.zorlukSeviyesi=zorlukSeviyesi;
        if (this.zorlukSeviyesi==1){
            this.mayinSayisi = satirSayisi * sutunSayisi / 8;
        } else if (this.zorlukSeviyesi==2) {
            this.mayinSayisi = satirSayisi * sutunSayisi / 6;
        }else if (this.zorlukSeviyesi==3){
            this.mayinSayisi = satirSayisi * sutunSayisi / 4;
        }
        this.acilanHucreSayisi = 0;

        oyunTahtasiniOlustur();
        mayinlariYerlestir();
        komsudakiMayinlariHesapla();
    }

    private void oyunTahtasiniOlustur() {
        for (int i = 0; i < this.satirSayisi; i++) {
            for (int j = 0; j < this.sutunSayisi; j++) {
                this.oyunTahtasi[i][j] = '-';
            }
        }
    }

    private void mayinlariYerlestir() {
        Random rand = new Random();
        int yerlestirilenMayinSayisi = 0;

        while (yerlestirilenMayinSayisi < this.mayinSayisi) {
            int randSatir = rand.nextInt(this.satirSayisi);
            int randSutun = rand.nextInt(this.sutunSayisi);

            if (this.mayinKonumlari[randSatir][randSutun] != '*') {
                this.mayinKonumlari[randSatir][randSutun] = '*';
                yerlestirilenMayinSayisi++;
            }
        }
    }

    private void komsudakiMayinlariHesapla() {
        for (int i = 0; i < this.satirSayisi; i++) {
            for (int j = 0; j < this.sutunSayisi; j++) {
                if (this.mayinKonumlari[i][j] != '*') {
                    int sayac = komsudakiMayinSayisiHesapla(i, j);
                    this.mayinKonumlari[i][j] = (char) (sayac + '0');
                }
            }
        }
    }

    private int komsudakiMayinSayisiHesapla(int satir, int sutun) {
        int sayac = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int yeniSatir = satir + i;
                int yeniSutun = sutun + j;

                if (yeniSatir >= 0 && yeniSatir < this.satirSayisi && yeniSutun >= 0 && yeniSutun < this.sutunSayisi && this.mayinKonumlari[yeniSatir][yeniSutun] == '*') {
                    sayac++;
                }
            }
        }

        return sayac;
    }

    public void oyunTahtasiniYazdir() {
        for (int i = 0; i < this.satirSayisi; i++) {
            for (int j = 0; j < this.sutunSayisi; j++) {
                System.out.print(this.oyunTahtasi[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void mayinKonumlariniYazdir() {
        for (int i = 0; i < this.satirSayisi; i++) {
            for (int j = 0; j < this.sutunSayisi; j++) {
                System.out.print(this.mayinKonumlari[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void oyunuOyna() {
        Scanner scanner = new Scanner(System.in);


        while (this.acilanHucreSayisi < this.satirSayisi * this.sutunSayisi - this.mayinSayisi) {
            oyunTahtasiniYazdir();

            System.out.print("Satır ve sütun giriniz (boşlukla ayrılmış): ");
            int satir = scanner.nextInt();
            int sutun = scanner.nextInt();

            if (satir < 0 || satir >= this.satirSayisi || sutun < 0 || sutun >= this.sutunSayisi) {
                System.out.println("Geçersiz koordinatlar. Lütfen tekrar girin.");
                continue;
            }

            if (this.oyunTahtasi[satir][sutun] == '-') {
                hucreyiAc(satir, sutun);

                if (this.mayinKonumlari[satir][sutun] == '*') {
                    System.out.println("Oyun Bitti! Mayına Bastınız.Skorunuz :  " + this.acilanHucreSayisi*10 + " puan.");
                    mayinKonumlariniYazdir();
                    break;
                }

                if (this.mayinKonumlari[satir][sutun] == '0') {
                    bosHucreyiAc(satir, sutun);
                }
            } else {
                System.out.println("Bu hücreyi daha önce açtınız. Lütfen başka bir hücre seçin.");
            }
        }

        if (this.acilanHucreSayisi == this.satirSayisi * this.sutunSayisi - this.mayinSayisi) {
            System.out.println("Tebrikler! Oyunu Kazandınız!");
            System.out.println("Skorunuz : " + this.acilanHucreSayisi*10 + " puan ");
        }

        scanner.close();
    }

    private void hucreyiAc(int satir, int sutun) {
        this.oyunTahtasi[satir][sutun] = this.mayinKonumlari[satir][sutun];
        this.acilanHucreSayisi++;
    }

    private void bosHucreyiAc(int satir, int sutun) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int yeniSatir = satir + i;
                int yeniSutun = sutun + j;

                if (yeniSatir >= 0 && yeniSatir < this.satirSayisi && yeniSutun >= 0 && yeniSutun < this.sutunSayisi && this.oyunTahtasi[yeniSatir][yeniSutun] == '-') {
                    hucreyiAc(yeniSatir, yeniSutun);

                    if (this.mayinKonumlari[yeniSatir][yeniSutun] == '0') {
                        bosHucreyiAc(yeniSatir, yeniSutun);
                    }
                }
            }
        }
    }


}
