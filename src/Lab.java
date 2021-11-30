import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Lab {
    public static void main(String[] args) throws IOException,
            InputMismatchException{
        try {
            File f1 = new File("E:\\jp\\file.txt");
            File f2 = new File("E:\\jp\\file1.txt");
            if (f1.exists()) {
                f1.delete();
                f2.delete();
            }
            f1.createNewFile();
            f2.createNewFile();
            RandomAccessFile rf = new RandomAccessFile(f1,"rw");
            System.out.println(rf.length());
            Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);
            System.out.print("кол-во товаров: ");
            int kolvo = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < kolvo; i++) {
                System.out.print("введите наименование " + (i + 1) + " товара ");
                String name = sc.next();
                rf.seek(rf.length());
                rf.writeUTF(name);
                for (int j = 0; j < 100 - name.length(); j++)
                    rf.writeByte(1);
                System.out.print("производитель: ");
                String proizv = sc.next();
                rf.writeUTF(proizv);
                for (int j = 0; j < 100 - proizv.length(); j++)
                    rf.writeByte(1);
                System.out.print("количество единиц: ");
                int koled = sc.nextInt();
                sc.nextLine();
                rf.writeInt(koled);
                System.out.print("цена: ");
                int price = sc.nextInt();
                sc.nextLine();
                rf.writeInt(price);
            }
            System.out.println(rf.length());

            RandomAccessFile rf1 = new RandomAccessFile(f2, "rw");
            rf.seek(0);
            System.out.println("информация о товарах");
            System.out.println("наименование \t\t производитель \t\t кол-во единиц \t\t цена");

            int x = 0; //номер тов
            for (int i = 0; i < kolvo; i++) {
                rf.seek(i * 52L);
                String name = rf.readUTF();
                rf.seek(i * 52L + 22);
                String city = rf.readUTF();
                rf.seek(i * 52L + 44);
                int price = rf.readInt();

                if (price > 1000) {
                    rf1.seek(rf1.length());
                    rf1.writeUTF(name);
                    for (int j = 0; j < 100 - name.length(); j++) {
                        rf1.writeByte(1);
                    }
                    rf1.writeInt(price);

                    x = x + 1;
                }
                System.out.println(name + "\t\t\t" + price);
            }
            rf1.seek(0);
            System.out.println("товары с ценой выше 1000");
            System.out.println("наименование \\t\\t производитель \\t\\t кол-во единиц \\t\\t цена\"");
            for (int i = 0; i < x; i++) {
                rf1.seek(i * 52L);
                String name = rf1.readUTF();
                rf1.seek(i * 52L + 22);
                String proizv = rf1.readUTF();
                rf1.seek(i * 52L + 44);
                int koled = rf1.readInt();
                rf1.seek(i * 52L + 48);
                int price = rf1.readInt();
                System.out.println(name + "\t\t\t" + proizv + "\t\t\t" + koled + "\t\t\t\t" + price);
            }
            rf1.close();
            rf.close();
        } catch (IOException e) {
            System.out.println("End of file " + e);
        } catch (InputMismatchException e) {
            System.out.println("Exception " + e);
        }
    }
}
