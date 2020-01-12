package com.araujo.training.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
/**
 * @Component Indica para o spring podera ser utilizado atraves do @Autowired com injecao de depencendia,
 * pode ser utilizado tambem as annotaion abaixo, que sao uma especializacao da annotation @Component.
 *
 * @Repository - deve ser utilizada quando usada para acessar o DAO para que as execoes nao checadas possam ser
 * tratadas possam ser traduzidas utilizando o spring data exeption.
 *
 * @Service - Especifica que a classe deve ser utilizada como classe de servico
 */
public class DateUtil {

    public String formatLocalDateTimeToDatabaseStyle(LocalDateTime localDateTime){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
    }
}
