package org.pmv.myspring.gijonevents.application.port.in.command;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;

@Getter
@Builder
public class ImageInput {

    private InputStream inputStream;
    private String fileName;
    private String contentType;
}
