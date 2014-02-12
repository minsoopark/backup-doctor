package sgen.backup.dr.etc;

import sgen.backup.dr.R;

import java.util.HashMap;
import java.util.Map;

public class ResourceWrapper {
    public static Map<String, Integer> getStringResourceMap() {
        Map<String, Integer> resourceMap = new HashMap<String, Integer>();

        resourceMap.put("medicine", R.string.medicine);
        resourceMap.put("surgeon", R.string.surgeon);

        resourceMap.put("head", R.string.head);
        resourceMap.put("shoulder", R.string.shoulder);
        resourceMap.put("elbow", R.string.elbow);
        resourceMap.put("wrist", R.string.wrist);
        resourceMap.put("chest", R.string.chest);
        resourceMap.put("stomach", R.string.stomach);
        resourceMap.put("thigh", R.string.thigh);
        resourceMap.put("calf", R.string.calf);
        resourceMap.put("knee", R.string.knee);
        resourceMap.put("ankle", R.string.ankle);
        resourceMap.put("foot", R.string.foot);

        return resourceMap;
    }

    public static Map<String, Integer> getDrawableResourceMap() {
        Map<String, Integer> resourceMap = new HashMap<String, Integer>();

        resourceMap.put("h_1", R.drawable.h_1);
        resourceMap.put("h_2", R.drawable.h_2);
        resourceMap.put("h_3", R.drawable.h_3);
        resourceMap.put("h_4", R.drawable.h_4);

        resourceMap.put("l_1", R.drawable.l_1);
        resourceMap.put("l_2", R.drawable.l_2);
        resourceMap.put("l_3", R.drawable.l_3);
        resourceMap.put("l_4", R.drawable.l_4);
        resourceMap.put("l_5", R.drawable.l_5);

        resourceMap.put("d_1", R.drawable.d_1);
        resourceMap.put("d_2", R.drawable.d_2);
        resourceMap.put("d_3", R.drawable.d_3);
        resourceMap.put("d_4", R.drawable.d_4);
        resourceMap.put("d_5", R.drawable.d_5);
        resourceMap.put("d_6", R.drawable.d_6);
        resourceMap.put("d_7", R.drawable.d_7);
        resourceMap.put("d_8", R.drawable.d_8);
        resourceMap.put("d_9", R.drawable.d_9);
        resourceMap.put("d_10", R.drawable.d_10);
        resourceMap.put("d_11", R.drawable.d_11);
        resourceMap.put("d_12", R.drawable.d_12);
        resourceMap.put("d_13", R.drawable.d_13);
        resourceMap.put("d_14", R.drawable.d_14);
        resourceMap.put("d_15", R.drawable.d_15);
        resourceMap.put("d_16", R.drawable.d_16);
        resourceMap.put("d_17", R.drawable.d_17);
        resourceMap.put("d_18", R.drawable.d_18);
        resourceMap.put("d_19", R.drawable.d_19);

        return resourceMap;
    }
}
