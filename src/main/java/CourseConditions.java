public class CourseConditions {
    private String language;
    private boolean hasCwiczenia;
    private String cwiczeniaUrl;
    private boolean isCurrent;

    public CourseConditions() {
    }

    public CourseConditions(String language, boolean hasCwiczenia, String cwiczeniaUrl, boolean isCurrent) {
        this.language = language;
        this.hasCwiczenia = hasCwiczenia;
        this.cwiczeniaUrl = cwiczeniaUrl;
        this.isCurrent = isCurrent;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setHasCwiczenia(boolean hasCwiczenia) {
        this.hasCwiczenia = hasCwiczenia;
    }

    public void setCwiczeniaUrl(String cwiczeniaUrl) {
        this.cwiczeniaUrl = cwiczeniaUrl;
    }


    public void setIsCurrent(boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    public boolean findsCriteria(){
        return this.language.equals("polski")
                && this.hasCwiczenia
                && !this.cwiczeniaUrl.isEmpty()
                && this.isCurrent;
    }

    public String getCwiczeniaUrl() {
        return cwiczeniaUrl;
    }
}
