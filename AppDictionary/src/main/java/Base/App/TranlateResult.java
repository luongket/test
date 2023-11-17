package Base.App;

import java.util.ArrayList;
import java.util.List;

public final class TranlateResult {
    public static String HelperResult(String result) {
        String[] tmp=result.split("\"");

        return tmp[1];
    }
}
