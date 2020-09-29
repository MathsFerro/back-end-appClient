package io.github.mathsferro.clientes.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@Configuration
public class InternacionalizacaoConfig {

    @Bean // Esse método Definir um arquivo que vai ser a fonte das mensagens que vão ser utilizadas no sistema
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages"); // classpath( é path da raiz ), nome do arquivo messages.properties ( não precisa colocar o properties )
        messageSource.setDefaultEncoding("ISO-8859-1"); // Reconhecer os characters brasileiros
        messageSource.setDefaultLocale(Locale.getDefault()); // Pegando atraves do SO ( Sistema Operacional ) o local ( Brasil )
        return messageSource;
    }

    // Objeto que faz a integração entre as mensagens
    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
