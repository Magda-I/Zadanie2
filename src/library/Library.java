package library;

import java.util.Scanner;

public class Library {

    public static void main(String[] args) {


        Book book = new Book();
        DBBookDao dao = new DBBookDao();

        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("\nWybierz opcję: " +
                    "\n1 - Dodaj nowy rekord" +
                    "\n2 - Wyświetl wszystkie książki" +
                    "\n3 - Usuwanie" +
                    "\n4 - Aktualizacja");


            switch (Integer.parseInt(scanner.nextLine())) {
                case 1://save
                    System.out.println("Podaj Tytuł");
                    book.setTitle(scanner.nextLine());
                    System.out.println("Podaj Autora");
                    book.setAuthor(scanner.nextLine());
                    System.out.println("Podaj Rok Wydania (1901-2155)");
                    book.setYear(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Podaj ISBN");
                    book.setIsbn(scanner.nextLine());

                    dao.save(book);
                    break;
                case 2://show all
                    System.out.println("Książki w bazie: ");
                    dao.getAllBooks();
                    break;
                case 3://delete
                    System.out.println("Podaj ISBN");
                    book.setIsbn(scanner.nextLine());
                    dao.delete(book);
                    break;
                case 4: //update
                    System.out.println("Podaj ISBN");
                    book.setIsbn(scanner.nextLine());
                    System.out.println("Podaj Tytuł");
                    book.setTitle(scanner.nextLine());
                    System.out.println("Podaj Autora");
                    book.setAuthor(scanner.nextLine());
                    System.out.println("Podaj Rok Wydania (1901-2155)");
                    book.setYear(Integer.parseInt(scanner.nextLine()));
                    dao.update(book);
                    break;
                case 0000:
                    System.out.println("Czyszczenie bazy...");
                    dao.dropTable();
                default:
                    System.out.println("Niepoprawna wartość");
            }
        }
    }
}
