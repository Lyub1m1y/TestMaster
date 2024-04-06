package com.testmaster.service.impl.io.system;

import com.testmaster.service.impl.io.OutputStreamProvider;
import org.springframework.stereotype.Component;

import java.io.OutputStream;

@Component
public class SystemOutputStream implements OutputStreamProvider {

    @Override
    public OutputStream getOutputStream() {
        return System.out;
    }
}
