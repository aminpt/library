public class Publication {
    private String name;
    private String author;
    private String language;
    private int price;
    public Publication(String name,String author,String language,int price )
    {
        this.name=name;
        this.author=author;
        this.language=language;
        this.price=price;
    }
    public int getPrice()
    {
        return this.price;
    }
    public String getName()
    {
        return this.name;
    }
    public String getAuthor()
    {
        return this.author;
    }
    public String getLanguage()
    {
        return this.language;
    }
}
