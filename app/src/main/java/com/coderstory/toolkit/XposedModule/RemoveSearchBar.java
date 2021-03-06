package com.coderstory.toolkit.XposedModule;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by CoderStory on 2016/6/4.
 */
public class RemoveSearchBar implements IXposedHookInitPackageResources {

    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam paramInitPackageResourcesParam)
            throws Throwable {
        XSharedPreferences prefs = new XSharedPreferences("com.coderstory.toolkit", "UserSettings");
        prefs.makeWorldReadable();
        prefs.reload();
        XposedBridge.log("count===" + String.valueOf(prefs.getAll().size()));
        if (!prefs.getBoolean("RemoveSearchBar", false)) {
            return;
        }

        if (paramInitPackageResourcesParam.packageName.equals("com.android.systemui")) {
            XposedBridge.log("去除搜索栏");
            paramInitPackageResourcesParam.res.setReplacement(paramInitPackageResourcesParam.packageName, "bool", "config_show_statusbar_search", Boolean.valueOf(false));
        }
    }
}
