package com.example.exercise;


public class ExerInfo {
    private String partName;
    private String exerName;
    private String exerPic;
    private String exerHow;

    public ExerInfo(String partName, String exerName, String exerHow, String exerPic) {
        this.partName=partName;
        this.exerName = exerName;
        this.exerPic = exerPic;
        this.exerHow = exerHow;
    }

    public String getPartName(){ return partName; }

    public String getExerName() {
        return exerName;
    }

    public String getExerPic() {
        return exerPic;
    }

    public String getExerHow() {
        return exerHow;
    }
}
