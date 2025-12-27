package in.system.studentmanagement.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Disable ambiguity checking to allow manual mapping of coordinatorTeacherName
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }
}
