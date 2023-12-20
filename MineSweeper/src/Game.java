import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Satır sayısını giriniz: ");
        int satirSayisi = scanner.nextInt();

        System.out.print("Sütun sayısını giriniz: ");
        int sutunSayisi = scanner.nextInt();
        
        int zorlukSeviyesi = 4;

        if (satirSayisi < 2 || sutunSayisi < 2) {
            System.out.println("Geçersiz giriş. Satır ve sütun sayısı en az 2 olmalıdır.");
            return;
        }

        System.out.println("Zorluk Seviyeleri");
        System.out.println("1. Kolay");
        System.out.println("2. Orta");
        System.out.println("3. Zor");

        while (zorlukSeviyesi>3){
            System.out.print("Zorluk seviyesi seçiniz (1-3 arasında ) ");
            zorlukSeviyesi=scanner.nextInt();
        }

        MayinTarlasi mayinTarlasi = new MayinTarlasi(satirSayisi, sutunSayisi,zorlukSeviyesi);
        mayinTarlasi.oyunuOyna();
    }
}