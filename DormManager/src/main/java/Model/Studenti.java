package Model;

public class Studenti
{
    private String cnp;
    private String sex;
    private Integer camera;

    public Studenti(String cnp, String sex, Integer camera)
    {
        this.cnp = cnp;
        this.sex = sex;
        this.camera = camera;
    }

    public void setCnp(String cnp)
    {
        this.cnp = cnp;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public void setCamera(Integer camera)
    {
        this.camera = camera;
    }

    public String getCnp()
    {
        return cnp;
    }

    public String getSex()
    {
        return sex;
    }

    public Integer getCamera()
    {
        return camera;
    }
}
