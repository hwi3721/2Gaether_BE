package hh5.twogaether.domain.match.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MatchDto {

    private Long dogId;

    private Long opponentId;

    private Double latitude = 37.537187;   //  위도

    private Double longitude = 127.005476;  //  경도

    @QueryProjection
    public MatchDto(Long dogId, Long opponentId, Double latitude, Double longitude) {
        this.dogId = dogId;
        this.opponentId = opponentId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @QueryProjection
    public MatchDto(Long dogId, Long opponentId) {
        this.dogId = dogId;
        this.opponentId = opponentId;
    }
}
