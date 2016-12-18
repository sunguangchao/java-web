package entity;

/**Gsales g购物结算实体类
 * Created by 11981 on 2016/12/10.
 */
public final class Gsales {

    private int gId;
    private int sId;
    private int sNum;

    private String gName;
    private double gPrice;
    private int gNum;
    private int allSnum;//单种销量总和

    public Gsales(int gId, int sId, int sNum)
    {
        this.gId = gId;
        this.sId = sId;
        this.sNum = sNum;
    }

    public Gsales(String gName,double gPrice, int gNum, int allSum)
    {
        this.gName = gName;
        this.gPrice = gPrice;
        this.gNum = gNum;
        this.allSnum = allSum;
    }

    public int getGId()
    {
        return gId;
    }
    public void setGId(int id)
    {
        gId = id;
    }
    public int getSId()
    {
        return sId;
    }
    public void setSId(int id)
    {
        sId = id;
    }
    public int getSNum()
    {
        return sNum;
    }
    public void setSNum(int num)
    {
        sNum = num;
    }
    public String getGName()
    {
        return gName;
    }
    public void setGName(String name)
    {
        gName = name;
    }
    public double getGPrice()
    {
        return gPrice;
    }
    public void setGPrice(double price)
    {
        gPrice = price;
    }
    public int getGNum()
    {
        return gNum;
    }
    public void setGNum(int num)
    {
        gNum = num;
    }
    public int getAllSnum()
    {
        return allSnum;
    }
    public void setAllSnum(int allSnum)
    {
        this.allSnum = allSnum;
    }



}
