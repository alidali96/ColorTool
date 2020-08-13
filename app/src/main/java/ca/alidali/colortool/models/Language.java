package ca.alidali.colortool.models;

/**
 * @author Ali Dali
 * @since 12-08-2020
 */
public enum Language {
    EN("en"),
    FR("fr");

    public static final String LANGUAGE = "language";
    private String lang;

    Language(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return lang.toUpperCase();
    }

}