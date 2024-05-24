import javax.lang.model.type.NullType;
import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books=new ArrayList<>();
    private ArrayList<Magazine> magazines=new ArrayList<>();
    private ArrayList<Member> members=new ArrayList<>();
    public void addBook(Book book)
    {
        this.books.add(book);
    }
    public void addMember(Member member)
    {
        this.members.add(member);
    }
    public void addMagazine(Magazine magazine)
    {
       this.magazines.add(magazine);
    }

    public Member getMemberByID(int ID)
    {
        for (Member member : this.members)
        {
            if(member.getID()==ID)
            {
                return member;
            }
        }
        return null;

    }
    public Book getBook(int ISBN)
    {
        for (Book book : this.books) {
            if (book.getISBN()==ISBN)
            {
              return book;
            }
        }
        return null;
    }
    public Magazine getMagazine(int ISSN,int number)
    {
        for (Magazine magazine : this.magazines)
        {
            if(magazine.getISSN()==ISSN&&magazine.getNumber()==number)
            {
                return magazine;
            }
        }
        return null;
    }
    public void removeBook(Book book)
    {
        this.books.remove(book);
    }
    public void removeMagazine(Magazine magazine)
    {
        this.magazines.remove(magazine);
    }
    public ArrayList<Book> getBooks()
    {
        return this.books;
    }
    public ArrayList<Magazine> getMagazines()
    {
        return this.magazines;
    }



}
