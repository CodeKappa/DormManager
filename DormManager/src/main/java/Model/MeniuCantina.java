package Model;

public class MeniuCantina
{
    private Integer id;
    private Integer cantina;
    private String data_servirii;
    private Integer preparat;

    public MeniuCantina(Integer id, Integer cantina, String data_servirii, Integer preparat)
    {
        this.id = id;
        this.cantina = cantina;
        this.data_servirii = data_servirii;
        this.preparat = preparat;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getCantina()
    {
        return cantina;
    }

    public void setCantina(Integer cantina)
    {
        this.cantina = cantina;
    }

    public String getData_servirii()
    {
        return data_servirii;
    }

    public void setData_servirii(String data_servirii)
    {
        this.data_servirii = data_servirii;
    }

    public Integer getPreparat()
    {
        return preparat;
    }

    public void setPreparat(Integer preparat)
    {
        this.preparat = preparat;
    }
}
