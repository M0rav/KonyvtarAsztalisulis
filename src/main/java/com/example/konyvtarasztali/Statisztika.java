package com.example.konyvtarasztali;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class Statisztika {
    private static List<Konyv> konyvek;

    public static void main(String[] args) {
        try {
            beolvas();
            System.out.printf("500 oldalnál hosszabb könyvek száma: %d\n " , get500oldalnalhoszabbKonyvekSzama());
            System.out.printf("%s 1950-nél régebbi könyv\n", is1950nelRegebbi()?"Van":"Nincs");
            System.out.printf("A leghoszabb könyv:\n%\n",getleghoszabbkonyv());
            System.out.printf("A legtöbb könyvel rendelkező szerző %s\n", getLegtobbkonyvelRendelkezoSzerzo());

            String cim = cimOlvasasKonzolrol();
            String szerzo = getSzerzo(cim);
            if (szerzo==null)

        } catch (SQLException e) {
            System.out.println("Hiba törtébt az adatbázis kapcsolat kialakításakor");
        }
    }
    private static String getSzerzo(){
        Optional<Konyv> optional = konyvek.stream().filter(konyv -> konyv.getTitle().equals(cim)).findFirst();
        if (optional.isEmpty()) {
            return null;
        }
    }
    private static String cimOlvasasKonzolrol(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Kérlek adj meg egy könyv címet : ");
        return  sc.nextLine();
    }

    private static  long get500oldalnalhoszabbKonyvekSzama(){
        return konyvek.stream().filter(konyv -> konyv.getPage_count() > 500).count();
    }
    private static String getLegtobbkonyvelRendelkezoSzerzo(){
        return konyvek.stream()
               .collect(Collectors.groupingBy(Konyv::getAuthor,Collectors.counting())).entrySet().stream()
               .max(Comparator.comparingLong(Map.Entry::getValue)).get().getKey();

    }
    private static boolean is1950nelRegebbi(){
        return konyvek.stream().anyMatch(konyv -> konyv.getOublish_year()< 1950);
    }
    private static Konyv getleghoszabbkonyv(){
        return konyvek.stream().max(Comparator.comparingInt(Konyv::getPage_count)).get();
    }

    private static void beolvas() throws SQLException {
        Adatbazis db = new Adatbazis();
        konyvek = db.ReadAllBooks();

    }
}
