package com.cocoa.piccolo

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.io.FileUtils
import org.gradle.api.Project

public class ClassTransform extends Transform {


    Project project
    BaseParams baseParams

    public ClassTransform(Project project, BaseParams baseParams) {
        this.project = project
        this.baseParams = baseParams
    }


    @Override
    String getName() {
        return "MyTrans"
    }


    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(Context context, Collection<TransformInput> inputs,
                   Collection<TransformInput> referencedInputs,
                   TransformOutputProvider outputProvider, boolean isIncremental)
            throws IOException, TransformException, InterruptedException {


        inputs.each { TransformInput input ->
            input.directoryInputs.each { DirectoryInput directoryInput ->


                ParamsExtension params = project.extensions.findByName('piccolo')
                println "params.username" + params.toString()

                if (params.packageName == null || params.packageName.length() == 0) {
                    params.packageName = baseParams.packageName
                }

//                baseParams.envPath;

                Injecter.injectDir(directoryInput.file.absolutePath, params)

                def dest = outputProvider.getContentLocation(directoryInput.name,
                        directoryInput.contentTypes, directoryInput.scopes,
                        Format.DIRECTORY)

                FileUtils.copyDirectory(directoryInput.file, dest)
            }
            input.jarInputs.each { JarInput jarInput ->


                def jarName = jarInput.name
                def md5Name = DigestUtils.md5Hex(jarInput.file.getAbsolutePath())
                if (jarName.endsWith(".jar")) {
                    jarName = jarName.substring(0, jarName.length() - 4)
                }
                def dest = outputProvider.getContentLocation(jarName + md5Name,
                        jarInput.contentTypes, jarInput.scopes, Format.JAR)
                FileUtils.copyFile(jarInput.file, dest)
            }
        }
    }
}