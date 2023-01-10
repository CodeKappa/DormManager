package Model;

public class Mesteri
{
    private String cnp;
    private String complex;

    public Mesteri(String cnp, String complex)
    {
        this.cnp = cnp;
        this.complex = complex;
    }

    public String getCnp()
    {
        return cnp;
    }

    public void setCnp(String cnp)
    {
        this.cnp = cnp;
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
