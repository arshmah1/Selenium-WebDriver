package YouTubeThing;

public class Xpaths {
    public static String youTubeHomePageURL = "https://www.youtube.com/";
    public static String searchInputXpath = "//input[@name='search_query']";
    public static String searchButtonXpath = "//button[@id='search-icon-legacy']";
    public static String filtersButtonXpath = "//button[@aria-label='Search filters']";
    public static String filtersPresenceXpath = "//tp-yt-paper-dialog";
    public static String xpathsForFilter(String string) {
        return "//div[contains(@title,'" + string + "')]";
    }
    public static String xpathsForFilterSelected(String string) {
        return "//ytd-search-filter-renderer[contains(@class,'selected')]//div[contains(@title,'" + string + "')]";
    }
    public static String videoTitle = "(//a[@id='video-title'])";
    public static String firstVideo = "(//a[@id='video-title'])[1]";
}
