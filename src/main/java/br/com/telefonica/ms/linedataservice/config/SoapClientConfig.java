package br.com.telefonica.ms.linedataservice.config;

import feign.jaxb.JAXBContextFactory;
import feign.soap.SOAPDecoder;
import feign.soap.SOAPEncoder;
import feign.codec.Encoder;
import feign.codec.Decoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SoapClientConfig {

    private static final JAXBContextFactory jaxbFactory = new JAXBContextFactory.Builder()
            .withMarshallerJAXBEncoding("UTF-8").build();

    @Bean
    public Encoder feignEncoder() {
        return new SOAPEncoder(jaxbFactory);
    }

    @Bean
    public Decoder feignDecoder() {
        return new SOAPDecoder(jaxbFactory);
    }

}
