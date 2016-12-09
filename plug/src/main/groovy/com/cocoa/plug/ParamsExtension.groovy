package com.cocoa.plug



public class ParamsExtension{

    String onClick
    String onItemClick
    String onCheckedChanged  //onCheckedChanged(RadioGroup radioGroup, int i) {


    @Override
    public String toString() {
        return "ParamsExtension{" +
                "onClick='" + onClick + '\'' +
                ", onItemClick='" + onItemClick + '\'' +
                ", onCheckedChanged='" + onCheckedChanged + '\'' +
                '}';
    }
}