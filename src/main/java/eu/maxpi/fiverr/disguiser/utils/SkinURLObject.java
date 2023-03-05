package eu.maxpi.fiverr.disguiser.utils;

public class SkinURLObject {

    public String id;
    public String name;
    public SkinProperties[] properties;

    static class SkinProperties{
        public String name;
        public String value;
        public String signature;
    }

}
