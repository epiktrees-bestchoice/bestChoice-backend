package bestChoicebackend.spring.elastic.repository;

import bestChoicebackend.spring.elastic.document.AccommodationDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface AccommodationDocumentRepository extends ElasticsearchRepository<AccommodationDocument, String> {

    // Custom Queary Method accommodationName, type, region, introduce
    //
    @Query(value = "{ \"query\" : {\n" +
            "\"bool\" : {\n" +
            "\"should\" : [\n" +
            "{ \"query_string\" : { \"query\" : \":query\", \"fields\" : [ \"accommodationName\" ] } },\n" +
            "{ \"query_string\" : { \"query\" : \":query\", \"fields\" : [ \"type\" ] } }\n" +
            "{ \"query_string\" : { \"query\" : \":query\", \"fields\" : [ \"region\" ] } }\n" +
            "{ \"query_string\" : { \"query\" : \":query\", \"fields\" : [ \"introduce\" ] } }\n" +
            "]\n" +
            "}\n" +
            "}}")
    List<AccommodationDocument> customFullTextSearch(String query);

    Long findTopByOrderByAccommodationIdDesc();

    List<AccommodationDocument> findByAccommodationName(String name);
}
