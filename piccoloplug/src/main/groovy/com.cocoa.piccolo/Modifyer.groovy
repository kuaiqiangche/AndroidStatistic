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


    public static void injectDir(String path, ParamsExtension paramsExtension) {
        pool.appendClassPath(path)
        pool.appendClassPath("/Users/sj/Library/Android/sdk/platforms/android-24/android.jar")
        pool.appendClassPath(paramsExtension.sdkJarPath)

        viewClass = pool.getCtClass("android.view.View")
        bundleClass = pool.getCtClass("android.os.Bundle")
        radioGroupClass = pool.getCtClass("android.widget.RadioGroup")


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

                        injectEvent(c, "onCheckedChanged", paramsExtension.getOnCheckedChanged())
                        injectEvent(c, "onClick", paramsExtension.getOnClick())
                        injectEvent(c, "onItemClick", paramsExtension.getOnItemClick())

                        injectLifeCycle(c, "onCreate", paramsExtension.getOnCreate())
                        injectLifeCycle(c, "OnPause", paramsExtension.getOnPause())


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


    public static void injectEvent(CtClass c, String methodName, String methodInstanceStr) {

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

    /**
     *
     * @param c
     * @param lcName  LifeCycle Name
     * @param lcStr   LifeCycle
     */
    public static void injectLifeCycle(CtClass c, String lcName, String lcStr) {

        try {
            if (lcStr == null || lcStr.length() == 0) {
                return
            }
            CtMethod method;
            if ("onCreate".equals(lcName)) {
                method = c.getDeclaredMethod("onCreate", [bundleClass] as CtClass[])
            }else{
                method = c.getDeclaredMethod(lcName, null)
            }
        } catch (Exception e) {
            println "piccolo modfiy method error" + e.toString()+e.getMessage()+e.localizedMessage
        }
    }

}
