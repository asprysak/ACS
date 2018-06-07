package anna.domain;

public class HistArgs {

    private Integer year;
    private String state;

    public HistArgs(Integer year, String state) {
        this.year = year;
        this.state = state;
    }

    public HistArgs() {
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
