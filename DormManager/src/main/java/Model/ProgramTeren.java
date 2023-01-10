package Model;

public class ProgramTeren
{
    private Integer id;
    private String student;
    private String data_programare;
    private String teren;

    public ProgramTeren(Integer id, String student, String data_programare, String teren)
    {
        this.id = id;
        this.student = student;
        this.data_programare = data_programare;
        this.teren = teren;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getStudent()
    {
        return student;
    }

    public void setStudent(String student)
    {
        this.student = student;
    }

    public String getData_programare()
    {
        return data_programare;
    }

    public void setData_programare(String data_programare)
    {
        this.data_programare = data_programare;
    }

    public String getTeren()
    {
        return teren;
    }

    public void setTeren(String teren)
    {
        this.teren = teren;
    }
}
