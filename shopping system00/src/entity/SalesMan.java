package entity;

/**SalesMan 售货员实体类
 * Created by 11981 on 2016/12/10.
 */
public final class SalesMan {
    private int sId;
    private String sName;
    private String sPassWord;

    /**
     * 验证用户登陆
     * @param sId,spassWord
     */
    public SalesMan(int sId, String sPassWord)
    {
        this.sId = sId;
        this.sPassWord = sPassWord;
    }
    public SalesMan(int sId, String sName, String sPassWord)
    {
        this.sId = sId;
        this.sName = sName;
        this.sPassWord = sPassWord;
    }
    public SalesMan(String sName, String sPassWord)
    {
        this.sName = sName;
        this.sPassWord = sPassWord;
    }

    public int getSId()
    {
        return sId;
    }
    public void setSId(int id)
    {
        sId = id;
    }
    public String getSName()
    {
        return sName;
    }
    public void setSName(String name)
    {
        sName = name;
    }
    public String getSPassWord()
    {
        return sPassWord;
    }
    public void setSPassWord(String passWord)
    {
        sPassWord = passWord;
    }




}
