package Base.App;

import java.util.HashMap;
import java.util.Map;

public final class Language {
    public static final Map<String,String> langCode = new HashMap<String,String>(){{
        put("English", "en-us");
        put("Vietnamese", "vi-vn");
        put("French", "fr-fr");
        put("Japanese", "ja-jp");
        put("Korean", "ko-kr");
    }};

}
