package Model;

public class Persoane
{
    private String cnp;
    private String nume;
    private String prenume;
    private String adresa;
    private String nr_telefon;
    private String email;

    public Persoane(String cnp, String nume, String prenume, String adresa, String nr_telefon, String email)
    {
        this.cnp = cnp;
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.nr_telefon = nr_telefon;
        this.email = email;
    }

    public String getCnp()
    {
        return cnp;
    }

    public void setCnp(String cnp)
    {
        this.cnp = cnp;
    }

    public String getNume()
    {
        return nume;
    }

    public void setNume(String nume)
    {
        this.nume = nume;
    }

    public String getPrenume()
    {
        return prenume;
    }

    public void setPrenume(String prenume)
    {
        this.prenume = prenume;
    }

    public String getAdresa()
    {
        return adresa;
    }

    public void setAdresa(String adresa)
    {
        this.adresa = adresa;
    }

    public String getNr_telefon()
    {
        return nr_telefon;
    }

    public void setNr_telefon(String nr_telefon)
    {
        this.nr_telefon = nr_telefon;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}