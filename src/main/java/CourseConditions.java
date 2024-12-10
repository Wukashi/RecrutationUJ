public class CourseConditions {
    private String language;
    private boolean hasCwiczenia;
    private String cwiczeniaUrl;
    private boolean isCurrent;

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

    @Override
    public String toString() {
        return "CourseConditions{" +
                "language='" + language + '\'' +
                ", hasCwiczenia=" + hasCwiczenia +
                ", cwiczeniaUrl='" + cwiczeniaUrl + '\'' +
                ", term='" + isCurrent + '\'' +
                '}';
    }

    public String getCwiczeniaUrl() {
        return cwiczeniaUrl;
    }
}
