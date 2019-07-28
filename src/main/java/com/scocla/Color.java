package com.scocla;

//import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
public class Color { //implements Serializable {

    //private static final long serialVersionUID = 1L;

    private Boolean status;

    private int colorInt;

    private int brightness;

    public Color(){}

    /*public Color(ColorEntity colorEntity) {

        try {
            BeanUtils.copyProperties(this, colorEntity);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }*/

    public Color(Boolean status, int colorInt, int brightness){

        this.status = status;
        this.colorInt = colorInt;
        this.brightness = brightness;

    }

    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public int getColorInt() {
        return colorInt;
    }
    public void setColorInt(int color) {
        this.colorInt = color;
    }
    public int getBrightness() {
        return brightness;
    }
    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

}