package sgen.backup.dr.etc;


public class Events {
    public static class SymptomInputEvent {
        public String key;
        public String value;

        public SymptomInputEvent(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
