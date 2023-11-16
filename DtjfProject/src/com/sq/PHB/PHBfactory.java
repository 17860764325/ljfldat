package com.sq.PHB;

public class PHBfactory  {

    public PHB getPHB(String  PHBname,String a,int b){
        if (PHBname==null){
                return null;
        }else  if (PHBname.equalsIgnoreCase("school")){
            return new SchoolPHB(a,b);
        }else  if (PHBname.equalsIgnoreCase("nianji")){
            return new NianJiPHB(a,b);
        }else  if (PHBname.equalsIgnoreCase("people")){
            return new PeoplePHB(a,b);
        }else  if (PHBname.equalsIgnoreCase("jiedao")){
            return new JiedaoPHB(a,b);
        }else  if (PHBname.equalsIgnoreCase("qiye")){
            return new QiyePHB(a,b);
        }else  if (PHBname.equalsIgnoreCase("qypeople")){
            return new QypeoplePHB(a,b);
        }
        return  null;

    }


}
