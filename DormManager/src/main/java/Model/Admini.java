package Model;

public class Admini
{
    private String cnp;
    private String id_camin;

    public Admini(String cnp, String id_camin)
    {
        this.cnp = cnp;
        this.id_camin = id_camin;
    }

    public String getCnp()
    {
        return cnp;
    }

    public void setCnp(String cnp)
    {
        this.cnp = cnp;
    }

    public String getId_camin()
    {
        return id_camin;
    }

    public void setId_camin(String id_camin)
    {
        this.id_camin = id_camin;
    }
}
