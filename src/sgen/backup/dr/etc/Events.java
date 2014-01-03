package sgen.backup.dr.etc;

import java.util.Map;

public class Events {
    public static class SymptomInputEvent {
        public String key;
        public Object value;

        public SymptomInputEvent(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}
