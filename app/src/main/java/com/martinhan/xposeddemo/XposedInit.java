package com.martinhan.xposeddemo;

import android.content.Context;
import android.os.Bundle;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodHook.MethodHookParam;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class XposedInit implements IXposedHookLoadPackage {
    private static String PACKAGE_NAME = "com.tencent.mm";
    private static final String TAG = "MartinHan_xposed: ";

    public void handleLoadPackage(LoadPackageParam lpparam) {
        if (lpparam.packageName.equals(PACKAGE_NAME)) {
            try {
                wx(lpparam);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    private void wx(LoadPackageParam lpparam) {
        XposedBridge.log("MartinHan_xposed: wx success");
        try {
            final ClassLoader classLoader = lpparam.classLoader;
            XposedHelpers.findAndHookMethod("com.tencent.mm.ui.tools.MultiStageCitySelectUI", classLoader, "initView", new Object[]{new XC_MethodHook() {
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("MartinHan_xposed: wx success MultiStageCitySelectUI beforeHookedMethod");
                }

                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedBridge.log("MartinHan_xposed: wx success MultiStageCitySelectUI afterHookedMethod");
                }
            }});
            XposedHelpers.findAndHookMethod("com.tencent.mm.ui.tools.MultiStageCitySelectUI", classLoader, "onCreate", new Object[]{Bundle.class, new XC_MethodHook() {
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("MartinHan_xposed: wx success MultiStageCitySelectUI onCreate beforeHookedMethod");
                }

                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedBridge.log("MartinHan_xposed: wx success MultiStageCitySelectUI  afterHookedMethod");
                    Class mscsu = XposedHelpers.findClass("com.tencent.mm.ui.tools.MultiStageCitySelectUI", classLoader);
                    Method[] methods = param.thisObject.getClass().getMethods();
                    List<Method> myMethods = new ArrayList();
                    for (Method item : methods) {
                        if (item.getDeclaringClass().equals(mscsu)) {
                            myMethods.add(item);
                        }
                    }
                }
            }});
            XposedHelpers.findAndHookMethod("com.tencent.mm.ui.tools.MultiStageCitySelectUI", classLoader, "hQs", new Object[]{new XC_MethodHook() {
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("MartinHan_xposed: wx success MultiStageCitySelectUI hQs beforeHookedMethod");
                }

                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedBridge.log("MartinHan_xposed: wx success MultiStageCitySelectUI hQs  afterHookedMethod");
                    Object wdnObj = XposedHelpers.findField(XposedHelpers.findClass("com.tencent.mm.ui.tools.MultiStageCitySelectUI", classLoader), "TER").get(param.thisObject);
                    Class regionClazz = XposedHelpers.findClass("com.tencent.mm.storage.RegionCodeDecoder$Region", classLoader);
                    Field codeField = XposedHelpers.findField(regionClazz, "code");
                    Field nameField = XposedHelpers.findField(regionClazz, "name");
                    Field hasChildrenField = XposedHelpers.findField(regionClazz, "hasChildren");
                    Object arrayHarryporrt = Array.get(wdnObj, 1);
                    codeField.set(arrayHarryporrt, "哈利波特魔法学校1");
                    nameField.set(arrayHarryporrt, "哈利波特魔法学校1");
                    hasChildrenField.set(arrayHarryporrt,false);
                    Array.set(wdnObj, 0, arrayHarryporrt);
                }
            }});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}