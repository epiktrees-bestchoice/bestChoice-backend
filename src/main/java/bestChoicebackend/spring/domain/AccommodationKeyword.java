package bestChoicebackend.spring.domain;

import jakarta.persistence.*;

public class AccommodationKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accommodationKeywordId;

    @ManyToOne(cascade = CascadeType.REMOVE) // 현재 클래스 : 목표 클래스 = N : 1
    @JoinColumn(name = "accommodationId", nullable = false, columnDefinition = "Long")
    Accommodation accommodationId;

    @ManyToOne(cascade = CascadeType.REMOVE) // 현재 클래스 : 목표 클래스 = N : 1
    @JoinColumn(name = "keywordId", nullable = false, columnDefinition = "Long")
    Keyword keywordId;
}
