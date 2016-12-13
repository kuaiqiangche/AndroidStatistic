package com.cocoa.plug

import com.android.build.gradle.api.ApplicationVariant
import javassist.ClassPool
import javassist.CtClass
import javassist.CtMethod
import javassist.bytecode.CodeAttribute
import javassist.bytecode.LocalVariableAttribute
import javassist.bytecode.MethodInfo
import org.gradle.api.Plugin
import org.gradle.api.Project

public class Piccolo implements Plugin<Project>, ClassFilter {


    static String CLASS_DIR = File.separator + "app" + File.separator + "build" + File.separator + "intermediates" + File.separator + "classes"
    static ClassPool pool = ClassPool.getDefault()
    static CtClass viewClass
    static CtClass bundleClass
    static CtClass radioGroupClass
    static CtClass adapterViewClass


    @Override
    void apply(Project project) {

        project.extensions.create('piccolo', ParamsExtension)
        project.afterEvaluate {

            String jarPath = project.android.bootClasspath[0].toString()
            println "piccolo:jarpath ${jarPath}"
            pool.appendClassPath(jarPath)
            viewClass = pool.getCtClass("android.view.View")
            bundleClass = pool.getCtClass("android.os.Bundle")
            radioGroupClass = pool.getCtClass("android.widget.RadioGroup")
            adapterViewClass = pool.getCtClass("android.widget.AdapterView")

            // test
            println StringUtils.toUpperCaseFirstChat("Shenjun")
            println StringUtils.toUpperCaseFirstChat("cocoa")

            project.android.applicationVariants.each { variant ->
                variant = variant as ApplicationVariant // For type inference.

                String variantStr = variant.getName()
                String buildType = variant.buildType.getName()

                if (variantStr.endsWith("Debug")) {
                    variantStr = variantStr.replace("Debug", "")
                }
                if (variantStr.endsWith("Release")) {
                    variantStr = variantStr.replace("Release", "")
                }
                if (variantStr.equals(buildType)) {
                    variantStr = null
                }

                println "piccolo:variantStr ${variantStr}"
                println "piccolo:buildType ${buildType}"

                change(project, variantStr, buildType)
            }

        }
    }


    void change(Project project, String variant, String buildType) {
        ParamsExtension params = project.extensions.findByName('piccolo')
        println "piccolo:params " + params

        String str = project.getRootDir().toString() + CLASS_DIR
        String taskName = "compile&&@@JavaWithJavac"
        if (variant != null) {
            str += File.separator + variant.toLowerCase()
            taskName = taskName.replace("&&", StringUtils.toUpperCaseFirstChat(variant))
        } else {
            taskName = taskName.replace("&&", "")
        }
        if (buildType != null) {
            str += File.separator + buildType.toLowerCase()
            taskName = taskName.replace("@@", StringUtils.toUpperCaseFirstChat(buildType))
        } else {
            taskName = taskName.replace("@@", "")
        }

        println "piccolo:taskName ${taskName}"
        println "piccolo:classPath ${str}"

        project.tasks.getByName(taskName) {
            doLast {
                try {
                    File parentFile = new File(str)

                    if (parentFile.exists()) {

                        pool.appendClassPath(str)

                        listDir(parentFile, str, params)
                    }
                } catch (Exception e) {
                    println e.toString()
                }
            }
        }


    }


    public void listDir(File file, String parent, ParamsExtension params) {
        if (file.isDirectory()) {
            File[] files = file.listFiles()
            for (File f : files) {
                listDir(f, parent, params)
            }
        } else {
            if (shouldModify(file.getName()) && file.toString().contains(parent)) {
                try {
                    String str = file.toString().replace(parent + File.separator, "");

//                    print file.toString()
//                    print parent
//                    print str
                    String className = str.replace(File.separator, ".").replace(".class", "")
                    //like this => com.cocoa.MainActyivity

                    CtClass c = pool.getCtClass(className)

                    injectEvent(c, "onCheckedChanged", params.getOnCheckedChanged(), className, parent)
                    injectEvent(c, "onClick", params.getOnClick(), className, parent)
                    injectEvent(c, "onItemClick", params.getOnItemClick(), className, parent)

//                    CtMethod method = c.getDeclaredMethod("onClick", [viewClass] as CtClass[])
//                    MethodInfo methodInfo = method.getMethodInfo()
//                    CodeAttribute codeAttribute = methodInfo.getCodeAttribute()
//                    LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag)
//                    String paramsName = "";
//                    for (int i = 0; i < attr.tableLength(); i++) {
//                        if ("v".equals(attr.variableName(i)) || "view".equals(attr.variableName(i))) {
//                            paramsName = attr.variableName(i);
//                            break;
//                        }
//                    }
//
//                    println paramsName + "=======" + str.replace(File.separator, ".").replace(".class", "")
//                    if (paramsName.equals("")) {
//                        method.insertBefore("android.util.Log.e(\"12312\",\"no view\");")
//                    } else {
//                        method.insertBefore("android.util.Log.e(\"12312\"," + paramsName + ".toString());")
//                    }
                    c.detach()

                } catch (Exception e) {
                    print e.toString()
                }
            }
        }
    }


    public void injectEvent(CtClass c, String methodName, String methodInstanceStr, String clazz, String parent) {

        try {
            if (methodInstanceStr == null || methodInstanceStr.length() == 0) {
                return
            }
            //解冻- -
            if (c.isFrozen()) {
                c.defrost()
            }

            CtMethod method;
            if ("onClick".equals(methodName)) {
                method = c.getDeclaredMethod("onClick", [viewClass] as CtClass[])

//                if (Modifier.isStatic(method.getModifiers())) {
//
//                }

                MethodInfo methodInfo = method.getMethodInfo()
                CodeAttribute codeAttribute = methodInfo.getCodeAttribute()
                LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag)

                String params = "";
                for (int i = 0; i < attr.tableLength(); i++) {
                    if (i == 0) {
                        continue
                    }
                    params += "," + attr.variableName(i);
                }
                methodInstanceStr = methodInstanceStr.replace("mContext", "mContext" + params)


            } else if ("onItemClick".equals(methodName)) {
                method = c.getDeclaredMethod("onItemClick", [adapterViewClass, viewClass, CtClass.intType, CtClass.longType] as CtClass[])

                MethodInfo methodInfo = method.getMethodInfo()
                CodeAttribute codeAttribute = methodInfo.getCodeAttribute()
                LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag)

                String params = "";
                for (int i = 0; i < attr.tableLength(); i++) {
                    if (i == 0) {
                        continue
                    }
                    params += "," + attr.variableName(i);
                }
                methodInstanceStr = methodInstanceStr.replace("mContext", "mContext" + params)


            } else if ("onCheckedChanged".equals(methodName)) {
                method = c.getDeclaredMethod("onCheckedChanged", [radioGroupClass, CtClass.intType] as CtClass[])

                MethodInfo methodInfo = method.getMethodInfo()
                CodeAttribute codeAttribute = methodInfo.getCodeAttribute()
                LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag)

                String params = "";
                for (int i = 0; i < attr.tableLength(); i++) {
                    if (i == 0) {
                        continue
                    }
                    params += "," + attr.variableName(i);
                }
                methodInstanceStr = methodInstanceStr.replace("mContext", "mContext" + params)

            }
            if (method != null) {
                if (clazz.contains("\$")) {
                    //内部类
                    methodInstanceStr = methodInstanceStr.replace("mContext", "this.this\$0")
                } else {
                    methodInstanceStr = methodInstanceStr.replace("mContext", "this")
                }
                println "piccolo:methodInstanceStr ${methodInstanceStr}"
                method.insertBefore(methodInstanceStr)
                c.writeFile(parent)
            }

        } catch (Exception e) {
            println "piccolo injectEvent error" + e.toString()
        }
    }


    private void getTypeByClass(CtClass ctClass) {

        String className = ctClass.getName()
        if (className.contains("Activity") || className.contains("activity")) {

        }


    }

    @Override
    boolean shouldModify(String className) {
        if (className == null || className.length() == 0) {
            return false;
        }
        //filter R.id.class  MainActivity$$ViewBinder  xxxCst
        //fileName.startsWith("Base")
        if (className.startsWith("R") || className.contains("\$ViewBinder") || className.contains("Cst")) {
            return false;
        }
        return true;
    }
}