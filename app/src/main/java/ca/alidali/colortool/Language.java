package ca.alidali.colortool;
/**
 * @author Ali Dali
 * @since 12-08-2020
 */
public enum Language {
    EN("en"),
    FR("fr");


    private String language;

    Language(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language.toUpperCase();
    }

}