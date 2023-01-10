package Model;

public class TerenDeFotbal
{
    private Integer id;
    private String nume;
    private String adresa;
    private String complex;

    public TerenDeFotbal(Integer id, String nume, String adresa, String complex)
    {
        this.id = id;
        this.nume = nume;
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

    public String getNume()
    {
        return nume;
    }

    public void setNume(String nume)
    {
        this.nume = nume;
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
