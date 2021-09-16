package customertimes.springParserDb.service.downloadService;

import customertimes.springParserDb.domain.Url;
import customertimes.springParserDb.domain.WebData;

public interface WebPageDownloadService {

    WebData download(final Url url);
}
