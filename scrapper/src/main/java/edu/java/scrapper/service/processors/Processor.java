package edu.java.scrapper.service.processors;

import edu.java.scrapper.entity.Link;

public interface Processor {
    boolean process(String[] pathComponents, Link link);
}
