package Model;

public class Camera
{
    private Integer id;
    private Integer nr_camera;
    private String camin;
    private Integer nr_locuri;
    private String tip_camera;

    public Camera(Integer id, Integer nr_camera, String camin, Integer nr_locuri, String tip_camera)
    {
        this.id = id;
        this.nr_camera = nr_camera;
        this.camin = camin;
        this.nr_locuri = nr_locuri;
        this.tip_camera = tip_camera;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getNr_camera()
    {
        return nr_camera;
    }

    public void setNr_camera(Integer nr_camera)
    {
        this.nr_camera = nr_camera;
    }

    public String getCamin()
    {
        return camin;
    }

    public void setCamin(String camin)
    {
        this.camin = camin;
    }

    public Integer getNr_locuri()
    {
        return nr_locuri;
    }

    public void setNr_locuri(Integer nr_locuri)
    {
        this.nr_locuri = nr_locuri;
    }

    public String getTip_camera()
    {
        return tip_camera;
    }

    public void setTip_camera(String tip_camera)
    {
        this.tip_camera = tip_camera;
    }
}
