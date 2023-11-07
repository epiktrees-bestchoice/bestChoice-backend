package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.dto.SearchReqDto;
import bestChoicebackend.spring.dto.accommodationDto.AccommodationResDto;
import bestChoicebackend.spring.dto.rowmapper.RowMappers;
import bestChoicebackend.spring.exception.BaseException;
import bestChoicebackend.spring.exception.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor // jdbcTemplate 의존성 추가
@Slf4j
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;
    private final RowMappers rowMappers;

    private String createParameterPlaceholders(List<Integer> keywords) {
        StringBuilder placeholders = new StringBuilder();
        for (int i = 0; i < keywords.size(); i++) {
            placeholders.append("?");
            if (i < keywords.size() - 1) {
                placeholders.append(", ");
            }
        }
        //log.info("keyword string : "+placeholders);
        return placeholders.toString();
    }


    public List<AccommodationResDto> checkProduct(String type, SearchReqDto searchReqDto) {
        // jdbc 쿼리 작성 요령 중간에 if문 때문에 문제가 생기는듯!
        // createParameterPlaceholders에 ?를 붙여놓고 값을 입력하지 않았다.
        String BaseQuery = "SELECT a.* FROM accommodation a " +
                "LEFT JOIN accommodation_keyword ak ON a.accommodation_id = ak.accommodation_id " +
                "WHERE a.type = ? " +
                "AND a.region = ? ";

        if(!searchReqDto.getKeywords().isEmpty()){
            String keywordPlaceholders = createParameterPlaceholders(searchReqDto.getKeywords());
            BaseQuery += " AND keyword_id IN (" + keywordPlaceholders + ") ";
            }

        BaseQuery += "GROUP BY a.accommodation_id " +
                "HAVING COUNT(DISTINCT keyword_id) >= ? " +
                "AND a.price BETWEEN ? AND ? " +
                "AND a.accommodation_id NOT IN (" +
                "SELECT accommodation_id FROM reserve " +
                "WHERE NOT (DATE_FORMAT(reserve_date, '%Y-%m-%d') >= ? " +
                "AND DATE_FORMAT(end_date, '%Y-%m-%d') <= ?));";
        try{
            List<Object> parameters = new ArrayList<>();
            parameters.add(type);
            parameters.add(searchReqDto.getRegion());
            parameters.addAll(searchReqDto.getKeywords());
            parameters.add(searchReqDto.getKeywords().size());
            parameters.add(searchReqDto.getMin_price());
            parameters.add(searchReqDto.getMax_price());
            parameters.add(searchReqDto.getSel_date2());
            parameters.add(searchReqDto.getSel_date());

            //log.info("query "+BaseQuery);
            //log.info("params : "+ parameters);
            return jdbcTemplate.query(BaseQuery, rowMappers.accommodationRowMapper(), parameters.toArray());
        }
        catch (RuntimeException e){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public List<AccommodationResDto> checkJDBCProduct() {
        String sql = "SELECT a.* FROM accommodation a " +
                "LEFT JOIN accommodation_keyword ak ON a.accommodation_id = ak.accommodation_id " +
                "WHERE a.type = 1 " +
                "GROUP BY a.accommodation_id " +
                "HAVING COUNT(DISTINCT keyword_id) >= 0 " +
                "AND a.price BETWEEN 0 AND 10000000 " +
                "AND a.accommodation_id NOT IN (" +
                "SELECT accommodation_id FROM reserve " +
                "WHERE NOT ((DATE_FORMAT(reserve_date, '%Y-%m-%d') >= ?) " +
                "AND (DATE_FORMAT(end_date, '%Y-%m-%d') <= ? )));";

        Object[] ProductObj = new Object[]{
                Date.valueOf("2023-10-10"),
                Date.valueOf("2023-10-06")
        };

        log.info("BaseQuery : " + sql);

        List<AccommodationResDto> results = jdbcTemplate.query(sql, rowMappers.accommodationRowMapper(), ProductObj);

        return results;
    }
}
