/**
 * TEMA 2 TODO:
 * Create a small application that mimics a library catalog. It needs to be able to add books, delete books and list books.
 * Books are of two kinds: novels and art albums. They both have names and number of pages.
 * Novels have type (like mystery, sf, etc.) while albums have paper quality.
 *
 * Model these entities (book, novel, album) with different classes and inheritance.
 */

public class MainLibrary {
    public static void main(String[] args) {
        //2 nuvele, prima facuta in constructor, a 2-a folosind setters
        Nuvela nuvela1 = new Nuvela("horror", "Valea Lupului", 346);
        Nuvela nuvela2 = new Nuvela();
        nuvela2.setName("Mana de om");
        nuvela2.setType("SF");
        nuvela2.setNumPages(196);

        //2 albume, prima facuta in constructor, a 2-a folosind setters
        Album album1 = new Album("good", "VA greatest hits 23", 201);
        Album album2 = new Album();
        album2.setName("Photorealistic nature");
        album2.setPaperQuality("Outstanding");
        album2.setNumPages(70);

        //adaugare manuala intr-un array de books
        Book[] books = new Book[2];
        books[0] = nuvela1;
        books[1] = album1;

        //creare librarie si adaugarea array-ului de books in librarie
        Library library = new Library();
        library.setBooks(books);

        //adaugare manuala a cate unui book in librarie
        library.addBook(nuvela2);
        library.addBook(album2);

        //afiseaza toate cartile din librarie
        library.listBooks();

        System.out.println("");

        //sterge cartea nuvela1 din librarie
        library.removeBook(nuvela1, library.getBooks());
        //afisare din nou fara nuvela1
        library.listBooks();
    }
}
