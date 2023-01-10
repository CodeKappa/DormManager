package Model;

public class CereriReparatii
{
    private Integer id;
    private String cameraCuProbleme;
    private String data_creare;
    private String stadiu;
    private String id_mester;

    public CereriReparatii(Integer id, String cameraCuProbleme, String data_creare, String stadiu, String id_mester)
    {
        this.id = id;
        this.cameraCuProbleme = cameraCuProbleme;
        this.data_creare = data_creare;
        this.stadiu = stadiu;
        this.id_mester = id_mester;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getCameraCuProbleme()
    {
        return cameraCuProbleme;
    }

    public void setCameraCuProbleme(String cameraCuProbleme)
    {
        this.cameraCuProbleme = cameraCuProbleme;
    }

    public String getData_creare()
    {
        return data_creare;
    }

    public void setData_creare(String data_creare)
    {
        this.data_creare = data_creare;
    }

    public String getStadiu()
    {
        return stadiu;
    }

    public void setStadiu(String stadiu)
    {
        this.stadiu = stadiu;
    }

    public String getId_mester()
    {
        return id_mester;
    }

    public void setId_mester(String id_mester)
    {
        this.id_mester = id_mester;
    }
}
