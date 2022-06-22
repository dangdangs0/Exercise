package com.example.exercise;

public class ExerPart {
    private String exerPartName;
    private int resId;//운동 부위 사진 번호
    private ExerInfo[] exerInfoArray;

    public ExerPart(String exerPartName){
        this.exerPartName=exerPartName;
    }

    public String getExerPartName() {
        return exerPartName;
    }

    public ExerInfo[] getExerInfoArray() {
        return exerInfoArray;
    }

    public void setExerPartName(String exerPartName) {
        this.exerPartName = exerPartName;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
