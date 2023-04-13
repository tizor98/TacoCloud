package com.local.tacocloud.filesystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;

import java.io.File;

@Configuration
public class FileWriterIntegrationConfig {

   @Bean
   public IntegrationFlow fileWriterFlow() {
      return IntegrationFlows
         .from(MessageChannels.direct("textInChannel"))
         .<String, String>transform(String::toUpperCase)
         .handle(Files
            .outboundAdapter(new File("logs/"))
            .fileExistsMode(FileExistsMode.APPEND)
            .appendNewLine(true))
         .get();
   }

   /* This is the config without using Spring integration's DSL config as above
   @Bean
   @Transformer(inputChannel = "textInChannel", outputChannel = "fileWriterChannel")
   public GenericTransformer<String, String> upperCaseTransformer() {
      return String::toUpperCase;
   }

   @Bean
   @ServiceActivator(inputChannel = "fileWriterChannel")
   public FileWritingMessageHandler fileWriter() {
      FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("logs/"));
      handler.setExpectReply(false);
      handler.setFileExistsMode(FileExistsMode.APPEND);
      handler.setAppendNewLine(true);
      return handler;
   }
   */

}
