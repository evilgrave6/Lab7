import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

    class Strana implements Serializable {
        String name;
        double square;
    }public class Prim4 {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Scanner sc=new Scanner(System.in, StandardCharsets.UTF_8);
        // создается файл на диске
        File f=new File("E:\\jp\\file");
        f.createNewFile();

        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
// Вводится информация об объекте (стране)
        Strana strana = new Strana();
        System.out.println("Введите информацию о стране: ");
        System.out.print("Название страны => ");
        strana.name=sc.nextLine();
        System.out.print("Площадь страны => ");
        strana.square=sc.nextDouble();

        oos.writeObject(strana);

        oos.flush();
        oos.close();
// -------------ЧТЕНИЕ ОБЪЕКТА ИЗ ФАЙЛА-------------

        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream oin = new ObjectInputStream(fis);
        strana = (Strana) oin.readObject();
        System.out.println(" Название страны "+ strana.name);
        System.out.println(" ее площадь = "+ strana.square);
// Поток закрывается
        oos.close();
    }
    }

