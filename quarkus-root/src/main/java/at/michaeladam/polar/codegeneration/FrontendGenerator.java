package at.michaeladam.polar.codegeneration;


import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.LaunchMode;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.logging.Logger;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@ApplicationScoped
@Path("/generate")
@IfBuildProfile("dev")
public class FrontendGenerator {


    protected static final Logger LOGGER = Logger.getLogger(FrontendGenerator.class.getName());

    @GET
    @Path("/")
    @Produces(APPLICATION_JSON)
    public String generate() {
        LaunchMode current = LaunchMode.current();
        if (current.equals(LaunchMode.DEVELOPMENT)) {
            LOGGER.info("FrontendGenerator.onStart");
            //run npm "generate-service" from ../resource/frontend/src/main/webapp
            //npm/node is installed in resource/frontend/node

          //check if windows
            if(System.getProperty("os.name").toLowerCase().contains("windows")) {
                return updateWindows();
            }   else {
                return updateLinux();
            }

        }
        return "not in development mode";
    }
    private String updateWindows() {
        String npm = new File("../resource/frontend/node/npm.cmd").getAbsolutePath();
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", "/c", npm, "run", "generate-service");
        return runProcess(processBuilder);
    }
    private String updateLinux() {
        String npm = new File("../resource/frontend/node/npm").getAbsolutePath();
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", npm, "run", "generate-service");
        return runProcess(processBuilder);
    }

    private String runProcess(ProcessBuilder processBuilder) {
        processBuilder.directory(new File("../resource/frontend/src/main/webapp"));
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                LOGGER.info(line);
            }
            int exitCode = process.waitFor();
            LOGGER.info("Exited with error code : {0}", exitCode);
        } catch (IOException | InterruptedException e) {
            LOGGER.severe(MessageFormat.format("Error while running npm: {0}", e.getMessage()));
            Thread.currentThread().interrupt();
            return e.getMessage();
        }
        return "ok";
    }




}
