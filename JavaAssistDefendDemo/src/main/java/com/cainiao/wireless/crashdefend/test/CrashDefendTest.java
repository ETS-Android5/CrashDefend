package com.cainiao.wireless.crashdefend.test;

import com.cainiao.wireless.crashdefend.Constants;
import com.cainiao.wireless.crashdefend.CrashDefendCompiler;
import com.cainiao.wireless.crashdefend.DefendIgnore;
import javassist.*;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.MethodInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrashDefendTest {

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException, ClassNotFoundException {
        ClassPool pool = ClassPool.getDefault();
        addTryCatch("test.RunnableTest", pool);
        addTryCatch("test.RunnableTest$1", pool);
        addTryCatch("test.JavaBeanTest", pool);
        addTryCatch("test.exclude.ExcludeTest", pool);
        addTryCatch("test.StartUp", pool);
        addTryCatch("test.DefendTestDemo", pool);
        addTryCatch("test.DefendTestDemo$1", pool);
    }


    public static  void addTryCatch(String className, ClassPool pool) throws NotFoundException, CannotCompileException, IOException, ClassNotFoundException {
        CrashDefendCompiler.addTryCatch(pool.getCtClass(className), pool,  pool.getClassLoader().loadClass(className));
    }




}
