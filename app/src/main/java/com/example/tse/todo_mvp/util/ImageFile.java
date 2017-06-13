package com.example.tse.todo_mvp.util;

import java.io.IOException;

/**
 * Created by TSE on 7/6/2017.
 */

public interface ImageFile {

    // Esta interfaz me permite definir los metodos abstractos que utilizare para manejar
    // lo relacionado a la imagen capturada.

    void create(String name, String extension) throws IOException;
    boolean exists();
    void delete();
    String getPath();

}
