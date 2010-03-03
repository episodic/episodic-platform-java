Java client library for Episodic's Platform REST API
===============

For more information about Episodic's Platform API see [http://app.episodic.com/help/server_api](http://app.episodic.com/help/server_api)

Javadocs are located at [http://episodic.github.com/episodic-platform-java/doc/index.html](http://episodic.github.com/episodic-platform-java/doc/index.html)

Getting Started
---------------------------

### Downloading A Release Package

The recommended way to get the Episodic Platform SDK for Java is to download the latest release zip from [http://github.com/episodic/episodic-platform-java/downloads](http://github.com/episodic/episodic-platform-java/downloads). Unpack this zip somewhere, grab the episodic-platform-sdk-[VERSION].jar and add it to your project.

### Including The Required Jar Files

The Episodic Platform SDK has some dependencies. You will need to download these JARs and add them to your project also.

* `apache-mime4j` - v0.6 [http://www.apache.org/dist/james/mime4j/](http://www.apache.org/dist/james/mime4j/)
* `commons-codec` - v1.4 [http://commons.apache.org/codec/download_codec.cgi](http://commons.apache.org/codec/download_codec.cgi)
* `commons-logging` - v1.1.1 [http://commons.apache.org/downloads/download_logging.cgi](http://commons.apache.org/downloads/download_logging.cgi)
* `httpclient` - v4.0.1 [http://hc.apache.org/downloads.cgi](http://hc.apache.org/downloads.cgi)
* `httpmime` - v4.0.1 [http://hc.apache.org/downloads.cgi](http://hc.apache.org/downloads.cgi)
* `httpcore` - v4.0.1 [http://hc.apache.org/downloads.cgi](http://hc.apache.org/downloads.cgi)
* `joda-time` - v1.6 [http://sourceforge.net/projects/joda-time/files/joda-time/](http://sourceforge.net/projects/joda-time/files/joda-time/)

Sample Usage
-------------------------------------------------------

### Initializing a Connection

Before you can use any of the services, you need to create a connection using com.episodic.platform.client.Connection. The constructor requires that you pass your Episodic API Key and Episodic Secret Key.
  
    Connection connection = new Connection("my_api_key", "my_secret_key");

After creating a connection you need to set it on the service before making any requests.

    com.episodic.platform.client.AnalyticsService.getInstance().setConnection(connection);
    com.episodic.platform.client.QueryService.getInstance().setConnection(connection);
    com.episodic.platform.client.WriteService.getInstance().setConnection(connection);

### Query for a List of Episodes

    Map<String, Object> params = new HashMap<String, Object>();
    params.put("show_id", "12345678");
    params.put("search_term", "fun");
    params.put("status", "on_the_air");
    params.put("sort_by", "air_date");
    EpisodesResponse response = queryService.episodes(params);
  
    System.out.println("There are a total of " + episodes.getTotal() + " episodes matching the query")
  
    // For each episode display the episode's name and embed code for the default player.
    for (Iterator<EpisodeItem> itr = response.getEpisodes().iterator(); itr.hasNext();) {
        EpisodeItem episodeItem = itr.next();
        System.out.println(episodeItem.getName());
        PlayerItem playerItem = episode.getPlayers().get(0);
        System.out.println(playerItem.getEmbedCode());
    }
  
### Create a New Episode

    // Create the episode and tell Episodic about the video file and thumbnail file to use
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("air_date", new Date()/1000L + 86400);
    params.put("upload_types", "s3");
    params.put("video_filename", "my_video.mp4");
    params.put("thumbnail_filename", "my_thumb.png");
    CreateEpisodeResponse response = writeService.createEpisode("oz04s1q0i29t", "My Episode", params);
    
    // Now upload the video and thumbnail.  It is best to upload the thumbnail first since it is usually smaller.
    File thumbnail = new File("/home/videos/my_thumb.png");
    writeService.uploadFileForEpisode(response.getUploadForFile(thumbnail), thumbnail);
    video = new File("/home/videos/video.mp4");
    writeService.uploadFileForEpisode(response.getUploadForFile(video), video);
  
### Handling Errors

Any errors returned from the Episodic Platform API are converted to exceptions and raised from the called method. For example,
the following response would cause com.episodic.platform.client.exceptions.InvalidAPIKeyException to be thrown.

    <?xml version="1.0" encoding="UTF-8"?>
    <error>
        <code>1</code>
        <message>Invalid API Key</message>
    </error>
  
License
------------------------------------------------------
  
Episodic Platform SDK for Java
Copyright (c) 20010 by Episodic, Inc.  
Licensed under the terms of The MIT License.  
Please see the LICENSE included with this distribution for details.  