package Model;

public class PreparateCantina
{
    private Integer id;
    private String nume;
    private String descriere;

    public PreparateCantina(Integer id, String nume, String descriere)
    {
        this.id = id;
        this.nume = nume;
        this.descriere = descriere;
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

    public String getDescriere()
    {
        return descriere;
    }

    public void setDescriere(String descriere)
    {
        this.descriere = descriere;
    }
}
