package io.github.danielkhalils.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@Configuration
public class InternacionalizacaoConfig {

    //Busca as mensagens padronizadas de erros no messages.properties
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");

        //Reconhecendo os caracteres brasileiros
        messageSource.setDefaultEncoding("ISO-8859-1");
        messageSource.setDefaultLocale(Locale.getDefault() );
        return messageSource;
    }

    //Bean criado para validar o bean anterior: MessageSource
    //Interpolarização das entidades
    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

}
