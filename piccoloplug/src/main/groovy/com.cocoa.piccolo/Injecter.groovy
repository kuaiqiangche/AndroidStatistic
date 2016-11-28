package com.cocoa.piccolo

import javassist.ClassPool
import javassist.CtClass
import javassist.CtMethod

public class Injecter {

    private static ClassPool pool = ClassPool.getDefault()

    static CtClass viewClass
    static CtClass bundleClass
    static CtClass radioGroupClass
    static CtClass adapterViewClass


    public static void injectDir(String path, ParamsExtension paramsExtension) {
        pool.appendClassPath(path)
        pool.appendClassPath("/Users/sj/Library/Android/sdk/platforms/android-24/android.jar")
//        pool.appendClassPath("/Users/sj/Documents/kqc_pro/Piccolo/app/build/intermediates/exploded-aar/com.android.support/appcompat-v7/24.2.1/jars/classes.jar")
//        pool.appendClassPath("/Users/sj/Documents/kqc_pro/Piccolo/app/build/intermediates/exploded-aar/com.android.support/support-v4/24.2.1/jars/classes.jar")
//        pool.appendClassPath(paramsExtension.sdkJarPath)

        viewClass = pool.getCtClass("android.view.View")
        bundleClass = pool.getCtClass("android.os.Bundle")
        radioGroupClass = pool.getCtClass("android.widget.RadioGroup")
        adapterViewClass = pool.getCtClass("android.widget.AdapterView")


        File dir = new File(path)
        if (dir.isDirectory()) {
            dir.eachFileRecurse { File file ->
                String filePath = file.absolutePath


                println file.toString()
                println filePath


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

//                        injectLifeCycle(c, "onCreate", paramsExtension.getOnCreate())
//                        injectLifeCycle(c, "onPause", paramsExtension.getOnPause())
//                        injectLifeCycle(c, "onStop", paramsExtension.getOnStop())

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

                method = c.getDeclaredMethod("onCheckedChanged", [radioGroupClass, CtClass.intType] as CtClass[])
            }
            if (method != null) {
                method.insertBefore(methodInstanceStr);
            }
        } catch (Exception e) {
            println "piccolo injectEvent error" + e.toString()
        }
    }

//    /**
//     *
//     * @param c
//     * @param lcName LifeCycle Name
//     * @param lcStr LifeCycle  inject String
//     */
//    public static void injectLifeCycle(CtClass c, String lcName, String lcStr) {
//
//        try {
//            if (lcStr == null || lcStr.length() == 0) {
//                return
//            }
//            CtMethod method = null;
//
//            boolean hasMethod = false
//            for (CtMethod ctMethod : c.getDeclaredMethods()) {
//                if (lcName.equals(ctMethod.getName())) {
//                    hasMethod = true
//                    break
//                }
//            }
//            if ("onCreate".equals(lcName)) {
//                if (hasMethod) {
//                    method = c.getDeclaredMethod("onCreate", [bundleClass] as CtClass[])
//                }
//            } else {
//                if (hasMethod) {
//
//                    method = c.getDeclaredMethod(lcName, [] as CtClass[])
//
//                } else {
////                    method = new CtMethod(CtClass.voidType, lcName, new CtClass[0], c)
//////                    method =  CtNewMethod.make("protected void "+lcName+"() ", c);
////                    method.setModifiers(Modifier.PROTECTED);
////                    method.setBody("{super." + lcName + "();}");
//                    println "protected void " + lcName + "(){ super." + lcName + "(); }"
//
//                    method = CtNewMethod.make("protected void " + lcName + "(){ }", c);
//
//                    c.addMethod(method)
//
////                    c.setSuperclass()
//                    method.insertBefore("super." + lcName + "();");
//
//
//                    println "add new Method" + lcName
//                }
//            }
//            if (method != null) {
//                method.insertAfter(lcStr);
//            }
//        } catch (Exception e) {
//            println "piccolo injectLifeCycle error" + e.toString()
//        }
//    }

}
