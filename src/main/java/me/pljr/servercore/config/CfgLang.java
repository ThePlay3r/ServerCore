package me.pljr.servercore.config;

import me.pljr.pljrapi.managers.ConfigManager;
import me.pljr.servercore.enums.Lang;

import java.util.HashMap;

public class CfgLang {
    public static HashMap<Lang, String> lang;

    public static void load(ConfigManager config){
        CfgLang.lang = new HashMap<>();
        for (Lang lang : Lang.values()){
            CfgLang.lang.put(lang, config.getString("lang."+lang.toString()));
        }
    }
}
