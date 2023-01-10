package Model;

public class ProgramSpalatorie
{
    private Integer id;
    private Integer cameraSpalatorie;
    private Integer cameraStudenti;
    private String data_programare;

    public ProgramSpalatorie(Integer id, Integer cameraSpalatorie, Integer cameraStudenti, String data_programare)
    {
        this.id = id;
        this.cameraSpalatorie = cameraSpalatorie;
        this.cameraStudenti = cameraStudenti;
        this.data_programare = data_programare;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getCameraSpalatorie()
    {
        return cameraSpalatorie;
    }

    public void setCameraSpalatorie(Integer cameraSpalatorie)
    {
        this.cameraSpalatorie = cameraSpalatorie;
    }

    public Integer getCameraStudenti()
    {
        return cameraStudenti;
    }

    public void setCameraStudenti(Integer cameraStudenti)
    {
        this.cameraStudenti = cameraStudenti;
    }

    public String getData_programare()
    {
        return data_programare;
    }

    public void setData_programare(String data_programare)
    {
        this.data_programare = data_programare;
    }
}
