package com.firat.questionsix.data;

import javax.xml.bind.annotation.XmlElement;

public class Information {
    private Extra extra;

    private String name;

    private String age;

    private String surname;

    public Extra getExtra ()
    {
        return extra;
    }
    @XmlElement
    public void setExtra (Extra extra)
    {
        this.extra = extra;
    }

    public String getName ()
    {
        return name;
    }
    @XmlElement
    public void setName (String name)
    {
        this.name = name;
    }

    public String getAge ()
    {
        return age;
    }
    @XmlElement
    public void setAge (String age)
    {
        this.age = age;
    }

    public String getSurname ()
    {
        return surname;
    }
    @XmlElement
    public void setSurname (String surname)
    {
        this.surname = surname;
    }

}
