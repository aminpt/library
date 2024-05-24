import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProgramController {
    private Library library=new Library();
    public void run(Scanner scanner)
    {
        boolean check_first=false;
        while (true) {
            String temp = scanner.nextLine();
            if(temp.startsWith("Create"))
            {
                String regex="^Create Library$";
                Matcher matcher=getCommandMatcher(temp,regex);
                if(matcher!=null)
                {
                    check_first=true;
                    System.out.println("Library created successfully");
                }
                else
                {
                    System.out.println("You have to create the library first");
                }
            }
            else
            {
                if(check_first)
                {
                    if (temp.equals("end"))
                        break;
                    else if(temp.startsWith("Add account"))
                    {
                        String regex="^Add account Account-Name ([a-zA-Z ]+) Account-Number (\\d{5})$";
                       Matcher matcher=getCommandMatcher(temp,regex);
                       if(matcher!=null)
                       addAccount(matcher);
                       else
                           System.out.println("invalid command");
                    }
                    else if(temp.startsWith("Increase balance"))
                    {
                        String regex="^Increase balance Account-Number (\\d{5}) Amount (\\d+)$";
                        Matcher matcher=getCommandMatcher(temp,regex);
                        if(matcher!=null)
                            increaseBalance(matcher);
                        else
                            System.out.println("invalid command");
                    }
                    else if(temp.startsWith("Cashout"))
                    {
                        String regex="^Cashout Account-Number (\\d{5})$";
                        Matcher matcher=getCommandMatcher(temp,regex);
                        if(matcher!=null)
                            cashout(matcher);
                        else
                            System.out.println("invalid command");
                    }
                    else if(temp.startsWith("Add book"))
                    {
                        String regex="^Add book Book-Name ([a-zA-Z\\d ]+) ISBN (\\d+) Author ([a-zA-Z ]+) Language ([a-zA-Z]+) Price (\\d+) Amount (\\d+)$";
                        Matcher matcher=getCommandMatcher(temp,regex);
                        if(matcher!=null)
                            addBook(matcher);
                        else
                            System.out.println("invalid command");
                    }
                    else if(temp.startsWith("Add magazine"))
                    {
                        String regex="^Add magazine Magazine-Name ([a-zA-Z\\d ]+) ISSN (\\d+) Author ([a-zA-Z ]+) Language ([a-zA-Z]+) Price (\\d+) Amount (\\d+) Number (\\d+)$";
                        Matcher matcher=getCommandMatcher(temp,regex);
                        if(matcher!=null)
                            addMagazine(matcher);
                        else
                            System.out.println("invalid command");
                    }
                    else if(temp.startsWith("Borrow book"))
                    {
                        String regex="^Borrow book ISBN (\\d+) Account-Number (\\d{5})$";
                        Matcher matcher=getCommandMatcher(temp,regex);
                        if(matcher!=null)
                            borrowBook(matcher);
                        else
                            System.out.println("invalid command");
                    }
                    else if(temp.startsWith("Borrow magazine"))
                    {
                        String regex="^Borrow magazine ISSN (\\d+) Account-Number (\\d{5}) Number (\\d+)$";
                        Matcher matcher=getCommandMatcher(temp,regex);
                        if(matcher!=null)
                            borrowMagazine(matcher);
                        else
                            System.out.println("invalid command");
                    }
                    else if(temp.startsWith("Return book"))
                    {
                        String regex="^Return book ISBN (\\d+) Account-Number (\\d{5})$";
                        Matcher matcher=getCommandMatcher(temp,regex);
                        if(matcher!=null)
                            returnBook(matcher);
                        else
                            System.out.println("invalid command");
                    }
                    else if(temp.startsWith("Return magazine"))
                    {
                        if(temp.startsWith("Return magazine and"))
                        {
                            String regex="^Return magazine and borrow next ISSN (\\d+) Account-Number (\\d{5}) Number (\\d+)$";
                            Matcher matcher=getCommandMatcher(temp,regex);
                            if(matcher!=null)
                                returnMagazineAndBorrowNext(matcher);
                            else
                                System.out.println("invalid command");
                        }
                        else
                        {
                            String regex="^Return magazine ISSN (\\d+) Account-Number (\\d{5}) Number (\\d+)$";
                            Matcher matcher=getCommandMatcher(temp,regex);
                            if(matcher!=null)
                                returnMagazin(matcher);
                            else
                                System.out.println("invalid command");

                        }

                    }
                    else if(temp.startsWith("Donate book"))
                    {
                        String regex="^Donate book Account-Number (\\d{5}) Book-Name ([a-zA-Z0-9 ]+) ISBN (\\d+) Author ([a-zA-Z ]+) Language ([a-zA-Z]+) Price (\\d+) Amount (\\d+)$";
                        Matcher matcher=getCommandMatcher(temp,regex);
                        if(matcher!=null)
                            donateBook(matcher);
                        else
                            System.out.println("invalid command");
                    }
                    else if(temp.startsWith("Print books"))
                    {
                     String regex="^Print books$";
                     Matcher matcher=getCommandMatcher(temp,regex);
                     if(matcher!=null) {
                         printBooks(matcher);
                     }
                     else
                     {
                         System.out.println("invalid command");
                     }
                    }
                    else if(temp.startsWith("Print magazines"))
                    {
                        String regex="^Print magazines$";
                        Matcher matcher=getCommandMatcher(temp,regex);
                        if(matcher!=null)
                        {
                            printMagazines(matcher);
                        }
                        else
                        {
                            System.out.println("invalid command");
                        }
                    }
                    else
                    {
                        System.out.println("invalid command");
                    }
                }
                else
                {
                    System.out.println("You have to create the library first");
                }
            }
        }
    }
    private Matcher getCommandMatcher(String input, String regex)
    {
        Matcher matcher=null;
        if(input.matches(regex))
        {
            Pattern pattern = Pattern.compile(regex);
            matcher = pattern.matcher(input);
        }
        return matcher;
    }
    private void increaseBalance(Matcher matcher)
    {
        matcher.find();
        Member temp=this.library.getMemberByID(Integer.parseInt(matcher.group(1)));
        if(temp!=null)
        {
            temp.setBalance(Integer.parseInt(matcher.group(2))+ temp.getBalance());
            System.out.println("Balance increased successfully");
        }
        else
        {
            System.out.println("No member with this ID exists");
        }

    }
    private void cashout(Matcher matcher)
    {
        matcher.find();
        Member temp=this.library.getMemberByID(Integer.parseInt(matcher.group(1)));
        if(temp!=null)
        {
            System.out.println(temp.getName() + " cashed out successfully. Amount: " +temp.getBalance());
            temp.setBalance(0);

        }
        else
        {
            System.out.println("No member with this ID exists");
        }
    }
    private void addAccount(Matcher matcher)
    {
        matcher.find();
        Member temp=this.library.getMemberByID(Integer.parseInt(matcher.group(2)));
        if(temp==null)
        {
            this.library.addMember(new Member(matcher.group(1),Integer.parseInt(matcher.group(2))));
            this.library.getMemberByID(Integer.parseInt(matcher.group(2))).setBalance(0);
            System.out.println("Account created successfully");
        }
        else
        {
            System.out.println("A member with this ID already exists");
        }
    }
    private void addBook(Matcher matcher)
    {
        matcher.find();
    for (int i=0;i<Integer.parseInt(matcher.group(6));i++)
    {
        this.library.addBook(new Book(matcher.group(1),Integer.parseInt(matcher.group(2)), matcher.group(3), matcher.group(4),Integer.parseInt(matcher.group(5))));
    }
        System.out.println(matcher.group(6) +" books were added to the library successfully");
    }
    private void addMagazine(Matcher matcher)
    {
        matcher.find();
      for(int i=0;i<Integer.parseInt(matcher.group(6));i++)
      {
          this.library.addMagazine(new Magazine(matcher.group(1),Integer.parseInt(matcher.group(2)), matcher.group(3), matcher.group(4),Integer.parseInt(matcher.group(5)),Integer.parseInt(matcher.group(7))));
      }
        System.out.println(matcher.group(6) +" magazines were added to the library successfully");
    }
    private void borrowBook(Matcher matcher)
    {
        matcher.find();
        Book temp_book=this.library.getBook(Integer.parseInt(matcher.group(1)));
        Member temp_member=this.library.getMemberByID(Integer.parseInt(matcher.group(2)));
         if(temp_member==null)
        {
        System.out.println("No member with this ID exists");
        }
        else if(temp_book==null)
        {
            System.out.println("No book with this ISBN was found in the library");
        }
        else if(temp_book.getPrice()>temp_member.getBalance())
        {
            System.out.println("The member's balance is not enough");
        }
        else
        {
                temp_member.borrowBook(temp_book);
                temp_member.setBalance(temp_member.getBalance()-temp_book.getPrice());
                System.out.println(temp_book.getName() + " was borrowed by "+temp_member.getName()+" successfully");
                this.library.removeBook(temp_book);
        }
    }
    private void borrowMagazine(Matcher matcher)
    {
        matcher.find();
        Magazine temp_magazine=this.library.getMagazine(Integer.parseInt(matcher.group(1)),Integer.parseInt(matcher.group(3)));
        Member temp_member=this.library.getMemberByID(Integer.parseInt(matcher.group(2)));
        if(temp_member==null)
        {
        System.out.println("No member with this ID exists");
        }
        else if(temp_magazine==null)
        {
            System.out.println("No magazine with this ISSN and number was found in the library");
        }

        else if(temp_magazine.getPrice()>temp_member.getBalance())
        {
            System.out.println("The member's balance is not enough");
        }
        else
        {
                temp_member.borrowMagazine(temp_magazine);
                temp_member.setBalance(temp_member.getBalance()-temp_magazine.getPrice());
                System.out.println(temp_magazine.getName() + " was borrowed by "+temp_member.getName()+" successfully");
                this.library.removeMagazine(temp_magazine);
        }

    }
    private void returnBook(Matcher matcher)
    {
        matcher.find();
        Member temp_member=this.library.getMemberByID(Integer.parseInt(matcher.group(2)));
        Book temp_book=null;
        if(temp_member!=null) {
            temp_book=temp_member.getBookFromBorrowedBooks(Integer.parseInt(matcher.group(1)));
        }
        if(temp_member==null)
        {
        System.out.println("No member with this ID exists");
        }
        else if(temp_book==null)
        {
            System.out.println("This member has never borrowed this book or has returned it");
        }

        else
        {
            this.library.addBook(temp_book);
            temp_member.setBalance(temp_member.getBalance()+(int)(temp_book.getPrice()*0.9));
            System.out.println(temp_member.getName()+" returned "+temp_book.getName()+" successfully");
            temp_member.returnBook(temp_book);

        }

    }
    private void returnMagazin(Matcher matcher)
    {
        matcher.find();
        Member temp_member=this.library.getMemberByID(Integer.parseInt(matcher.group(2)));
        Magazine temp_magazine=null;
        if(temp_member!=null) {
            temp_magazine= temp_member.getMagazineFromBorrowedMagazines(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(3)));
        }
        if(temp_member==null)
        {
        System.out.println("No member with this ID exists");
        }
        else if(temp_magazine==null)
        {
            System.out.println("This member has never borrowed this magazine or has returned it");
        }

        else
        {
            this.library.addMagazine(temp_magazine);
            temp_member.setBalance( temp_member.getBalance()+(int)(temp_magazine.getPrice()*0.8));
            System.out.println(temp_member.getName()+" returned "+temp_magazine.getName()+" successfully");
            temp_member.returnMagazine(temp_magazine);

        }
    }
    private void donateBook(Matcher matcher)
    {
        matcher.find();
        Member temp_member=this.library.getMemberByID(Integer.parseInt(matcher.group(1)));
        if(temp_member==null)
        {
            System.out.println("No member with this ID exists");
        }
        else
        {
        for (int i=0;i<Integer.parseInt(matcher.group(7));i++)
        {
            this.library.addBook(new Book(matcher.group(2),Integer.parseInt(matcher.group(3)), matcher.group(4), matcher.group(5),Integer.parseInt(matcher.group(6))));
        }
            temp_member.setBalance( temp_member.getBalance()+(int)(Integer.parseInt(matcher.group(6))*0.4*Integer.parseInt(matcher.group(7))));
            System.out.println(temp_member.getName() +" donated " + matcher.group(7)+ " books successfully");
        }
    }
    private void returnMagazineAndBorrowNext(Matcher matcher)
    {
        matcher.find();
        Member temp_member=this.library.getMemberByID(Integer.parseInt(matcher.group(2)));
        Magazine temp_magazine=temp_member.getMagazineFromBorrowedMagazines(Integer.parseInt(matcher.group(1)),Integer.parseInt(matcher.group(3)));
       if(temp_member==null)
        {
        System.out.println("No member with this ID exists");
        }
       else if(temp_magazine==null)
        {
            System.out.println("This member has never borrowed this magazine or has returned it");
        }

        else if(this.library.getMagazine(temp_magazine.getISSN(),temp_magazine.getNumber()+1)==null)
        {
            System.out.println("The library does not have the next issue of this magazine");
        }
        else
        {

                temp_member.borrowMagazine(this.library.getMagazine(temp_magazine.getISSN(), temp_magazine.getNumber() + 1));
                this.library.removeMagazine(this.library.getMagazine(temp_magazine.getISSN(),temp_magazine.getNumber()+1));
                temp_member.returnMagazine(temp_magazine);
                this.library.addMagazine(temp_magazine);
                System.out.println(temp_member.getName() + " returned " + temp_magazine.getName() + " and borrowed the next issue");

        }
    }
    private void printBooks(Matcher matcher)
    {
        ArrayList<Book> temp=this.library.getBooks();
        Comparator<Book> compareByName = Comparator.comparing( Book::getName );
        Comparator<Book> compareByAuthor=Comparator.comparing(Book::getAuthor);
        Comparator<Book> compareByLanguage=Comparator.comparing(Book::getLanguage);
        Comparator<Book> compareFull = compareByName.thenComparing(compareByAuthor).thenComparing(compareByLanguage);
        Collections.sort(temp,compareFull);
        System.out.println("List of all books:");
        for (Book book : this.library.getBooks()) {
            System.out.println(book.getISBN() + ": " + book.getName());
        }

    }
    private void printMagazines(Matcher matcher)
    {
        ArrayList<Magazine> temp=this.library.getMagazines();
        Comparator<Magazine> compareByName = Comparator.comparing( Magazine::getName );
        Comparator<Magazine> compareByNumber=Comparator.comparing(Magazine::getNumber);
        Comparator<Magazine> compareByAuthor=Comparator.comparing(Magazine::getAuthor);
        Comparator<Magazine> compareByLanguage=Comparator.comparing(Magazine::getLanguage);
        Comparator<Magazine> compareFull = compareByName.thenComparing(compareByNumber).thenComparing(compareByAuthor).thenComparing(compareByLanguage);
        Collections.sort(temp,compareFull);
        System.out.println("List of all magazines:");
        for (Magazine magazine : this.library.getMagazines()) {
               System.out.println(magazine.getISSN() + ": " + magazine.getName() + " " + magazine.getNumber());
        }
    }
}
