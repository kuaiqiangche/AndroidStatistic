package com.cocoa.piccolo

import javassist.ClassPool
import javassist.CtClass
import javassist.CtMethod

public class Modifyer {

    private static ClassPool pool = ClassPool.getDefault()

    static CtClass viewClass
    static CtClass bundleClass
    static CtClass radioGroupClass
    static CtClass adapterViewClass
    static CtClass intClass
    static CtClass longClass

    public static void injectDir(String path, ParamsExtension paramsExtension) {
        pool.appendClassPath(path)
        pool.appendClassPath("/Users/sj/Library/Android/sdk/platforms/android-24/android.jar")

        viewClass = pool.getCtClass("android.view.View")
        bundleClass = pool.getCtClass("android.os.Bundle")
        radioGroupClass = pool.getCtClass("android.widget.RadioGroup")
//        intClass = pool.getCtClass("int")
//        longClass = pool.getCtClass("java.lang.Long")

        File dir = new File(path)
        if (dir.isDirectory()) {
            dir.eachFileRecurse { File file ->
                String filePath = file.absolutePath

                if (filePath.endsWith(".class")
                        && !filePath.contains('R$')
                        && !filePath.contains('R.class')
                        && !filePath.contains("BuildConfig.class")) {
                    int index = filePath.indexOf(paramsExtension.getPackageName());

                    boolean isMyPackage = index != -1;

                    if (isMyPackage) {

                        int end = filePath.length() - 6 // .class = 6
                        String className = filePath.substring(index, end)
                                .replace('\\', '.').replace('/', '.')
                        CtClass c = pool.getCtClass(className)


                        println "piccolo modfiy start " + c.getName()

                        if (c.isFrozen()) {
                            c.defrost()
                        }

                        modfiyEvent(c, "onCheckedChanged", paramsExtension.getOnCheckedChanged())
                        modfiyEvent(c, "onClick", paramsExtension.getOnClick())
                        modfiyEvent(c, "onItemClick", paramsExtension.getOnItemClick())

//                        try {
//                            CtMethod method = c.getDeclaredMethod("onClick", [viewClass] as CtClass[])
//                            method.insertBefore("com.cocoa.piccolo.piccolo.Logger.log(view);");
//
//
//                        } catch (Exception e) {
//                            println "piccolo modfiy method error" + e.toString()
//                        }

                        c.writeFile(path)
                        c.detach()
                    }
                }
            }
        }
    }


    public static void modfiyEvent(CtClass c, String methodName, String methodInstanceStr) {

        try {
            if (methodInstanceStr == null || methodInstanceStr.length() == 0) {
                return
            }
            CtMethod method;
            if ("onClick".equals(methodName)) {

                method = c.getDeclaredMethod("onClick", [viewClass] as CtClass[])

            } else if ("onItemClick".equals(methodName)) {
                method = c.getDeclaredMethod("onItemClick", [adapterViewClass, viewClass, CtClass.intType, CtClass.longType] as CtClass[])

            } else if ("onCheckedChanged".equals(methodName)) {

                method = c.getDeclaredMethod("onCheckedChanged",[radioGroupClass, CtClass.intType] as CtClass[] )
            }
            if (method != null) {
                method.insertBefore(methodInstanceStr);
            }
        } catch (Exception e) {
            println "piccolo modfiy method error" + e.toString()+e.getMessage()+e.localizedMessage
        }
    }


}
