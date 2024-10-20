package ru.skillfactorydemo.tgbot.dto;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="GetCursOnDateXmlResult")
@Data
public class GetCursOnDateXmlResult {

    @XmlElementWrapper(name="ValutateData", namespace = "")
    @XmlElement(name = "ValutateCursOnDate", namespace = "")
    private List<ValuteCursOnDate> valutateData=new ArrayList<>();
}
