package com.mdsdanny.display.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

@Component
public class MainController {

    private static final Logger LOGGER = Logger.getLogger(MainController.class.getName());

    @Autowired
    private Environment env;

    public MainController() {
    }

    public Environment getEnv() {
        return env;
    }
}
