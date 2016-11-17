package com.cocoa.piccolo

public class ParamsExtension {
    String packageName

    String sdkJarPath  // the sdk env path,  /Users/sj/Library/Android/sdk/platforms/android-24/android.jar

    String onClick
    String onItemClick
    String onCheckedChanged  //onCheckedChanged(RadioGroup radioGroup, int i) {

    String onCreate
    String onPause
    String onResume
    String onStart
    String onStop
    String onDestroy


    @Override
    public String toString() {
        return "ParamsExtension{" +
                "packageName='" + packageName + '\'' +
                ", sdkJarPath='" + sdkJarPath + '\'' +
                ", onClick='" + onClick + '\'' +
                ", onItemClick='" + onItemClick + '\'' +
                ", onCheckedChanged='" + onCheckedChanged + '\'' +
                ", onCreate='" + onCreate + '\'' +
                ", onPause='" + onPause + '\'' +
                ", onResume='" + onResume + '\'' +
                ", onStart='" + onStart + '\'' +
                ", onStop='" + onStop + '\'' +
                ", onDestroy='" + onDestroy + '\'' +
                '}';
    }
}

