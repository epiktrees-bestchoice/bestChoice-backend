package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.dto.SearchReqDto;
import bestChoicebackend.spring.dto.rowmapper.RowMappers;
import bestChoicebackend.spring.exception.BaseException;
import bestChoicebackend.spring.exception.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor // jdbcTemplate 의존성 추가
@Slf4j
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;
    private final RowMappers rowMappers;

    private String createParameterPlaceholders(List<Long> keywords) {
        StringBuilder placeholders = new StringBuilder();
        for (int i = 0; i < keywords.size(); i++) {
            placeholders.append("?");
            if (i < keywords.size() - 1) {
                placeholders.append(",");
            }
        }
        return placeholders.toString();
    }

    public List<Accommodation> checkProduct(int type, SearchReqDto searchReqDto) {
        String BaseQuery ="SELECT a.* FROM accommodation a " +
                "LEFT JOIN accommodation_keyword ak ON a.accommodation_id = ak.accommodation_id ";


        if(!searchReqDto.getKeywords().isEmpty()){
            BaseQuery +=
                    "WHERE keyword_id IN (" + createParameterPlaceholders(searchReqDto.getKeywords()) + ") ";
        }
        BaseQuery += "GROUP BY a.accommodation_id " +
                "HAVING COUNT(DISTINCT keyword_id) >= " + searchReqDto.getKeywords().size() + " AND " +
                "a.price BETWEEN ? AND ? AND " +
                "a.accommodation_id NOT IN ( " +
                "    SELECT accommodation_id FROM reserve " +
                "    WHERE NOT ((DATE_FORMAT(reserve_date, '%Y-%m-%d') >= ?) AND " +
                "        (DATE_FORMAT(end_date, '%Y-%m-%d') <= ?))";
        try{
            Object[] ProductSearchObj = new Object[]{
                    searchReqDto.getMin_price(),
                    searchReqDto.getMax_price(),
                    searchReqDto.getSel_date2(),
                    searchReqDto.getSel_date()
            };
            return jdbcTemplate.query(BaseQuery, rowMappers.accommodationRowMapper(), ProductSearchObj);
        }
        catch (RuntimeException e){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }

    }
}
