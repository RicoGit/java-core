package com.jdk.runtime;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * Project: Shamrock Web Portal.
 * User: Solovev Constantine
 * Date: 18.11.14
 * Time: 21:42
 */

public abstract class ScriptRunner {

    public static String executeCommand(String commandName) {

        try {
            return getString(getInputStream(Runtime.getRuntime().exec(commandName)));
        } catch (IOException e) {
            return null;
        }
    }

    public static String executeCommand(String commandName, String[] environment) {
        try {
            return getString(getInputStream(Runtime.getRuntime().exec(commandName, environment)));
        } catch (IOException e) {
            return null;
        }
    }

    public static InputStream execute(String commandName, String[] environment, String folder, int timeOutInMilliseconds ) {

        try {
            Process process = Runtime.getRuntime().exec(commandName, environment, new File(folder));

            if (timeOutInMilliseconds > 0) {
                process.waitFor(timeOutInMilliseconds, TimeUnit.MILLISECONDS);
            } else {
                process.waitFor();
            }

            return getInputStream(process);

        } catch (IOException | InterruptedException e) {
            return null;
        }
    }

    /* Private Methods */

    private static InputStream getInputStream(Process process) {
        InputStream defaultInputStream = process.getInputStream();
        InputStream errorInputStream = process.getErrorStream();
        return new SequenceInputStream(defaultInputStream, errorInputStream);
    }

    private static String getString(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            return null;
        }

    }

}
