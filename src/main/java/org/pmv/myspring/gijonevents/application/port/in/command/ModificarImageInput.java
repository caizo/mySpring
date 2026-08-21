package org.pmv.myspring.gijonevents.application.port.in.command;

public record ModificarImageInput(
        String nombreOriginal,
        String contentType,
        byte[] contenido) {
}
