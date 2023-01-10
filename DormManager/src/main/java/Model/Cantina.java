package Model;

public class Cantina
{
    private Integer id;
    private String adresa;
    private String complex;

    public Cantina(Integer id, String adresa, String complex)
    {
        this.id = id;
        this.adresa = adresa;
        this.complex = complex;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getAdresa()
    {
        return adresa;
    }

    public void setAdresa(String adresa)
    {
        this.adresa = adresa;
    }

    public String getComplex()
    {
        return complex;
    }

    public void setComplex(String complex)
    {
        this.complex = complex;
    }
}
