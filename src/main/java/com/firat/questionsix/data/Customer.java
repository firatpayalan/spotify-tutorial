package com.firat.questionsix.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {
    private Information[] information;

    public Information[] getInformation ()
    {
        return information;
    }

    @XmlElement
    public void setInformation (Information[] information)
    {
        this.information = information;
    }
}
