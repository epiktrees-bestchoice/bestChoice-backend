package bestChoicebackend.spring.config;


import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "bestChoicebackend.spring.repository")
@Slf4j
public class ElasticConfig extends ElasticsearchConfiguration {

    @Value("${elasticsearch.url}")
    public String elasticsearchUrl;
    // es-etting.json에 연결 시작할 때 설정을 작성할 수 있다.
    @Override
    public ClientConfiguration clientConfiguration() {
        log.info(elasticsearchUrl+"에 연결!");
        return ClientConfiguration.builder()
                .connectedTo(elasticsearchUrl)
                .build();
    }
}
