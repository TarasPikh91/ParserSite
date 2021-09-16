package customertimes.springParserDb.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static final String HTML_TAG_PATTERN = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>";
    public static final Format OPENNET_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public static boolean checkWebPage(final String webData) {
        return checkRegex(webData, HTML_TAG_PATTERN).find();
    }

    public static boolean ifContainsDate(final String webData, final String patternString) {
        return checkRegex(webData, patternString).matches();
    }

    private static Matcher checkRegex(final String webData, final String patternString) {
        final Pattern p = Pattern.compile(patternString);
        return p.matcher(webData);
    }

    public static String parseDate(final Date date, final Format formatter) {
        return formatter.format(date);
    }

    public static String parseDateToString(final Date date) {
        return OPENNET_DATE_FORMAT.format(date);
    }

}
