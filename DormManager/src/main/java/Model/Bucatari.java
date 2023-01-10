package Model;

public class Bucatari
{
    private String cnp;
    private String cantina;

    public Bucatari(String cnp, String cantina)
    {
        this.cnp = cnp;
        this.cantina = cantina;
    }

    public String getCnp()
    {
        return cnp;
    }

    public void setCnp(String cnp)
    {
        this.cnp = cnp;
    }

    public String getCantina()
    {
        return cantina;
    }

    public void setCantina(String cantina)
    {
        this.cantina = cantina;
    }
}
