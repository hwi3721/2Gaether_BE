package hh5.twogaether.domain.loves;

import hh5.twogaether.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoveService {
    private final LoveRepository loveRepository;
    private final UserRepository userRepository;

    @Transactional
    public void love(Long lovedId, Long lovingId) {
        userRepository.findById(lovedId).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 사용자입니다.useruser")
        );  // 좋아요 받는 사용자 아이디로 사용자 있는지 확인
        Love love = loveRepository.findByLovedIdAndLovingId(lovedId, lovingId);
        if (loveRepository.findByLovedIdAndLovingId(lovedId, lovingId).getMatchCode() == 0) {
            loveRepository.save(new Love(lovedId, lovingId, 1));
        } else if (loveRepository.findByLovedIdAndLovingId(lovedId, lovingId).getMatchCode() == 1) {
            loveRepository.save(new Love(lovedId, lovingId, 1));
        } else if (loveRepository.findByLovedIdAndLovingId(lovedId, lovingId).getMatchCode() == 2) {
            loveRepository.save(new Love(lovedId, lovingId, 2));
        }
        // 그리고 여기에 상대방을 제외하는 엄청난 로직이 필요
        loveRepository.save(love);
    }
    @Transactional
    public void dislove(Long disLovedId, Long disLovingId) {
        userRepository.findById(disLovedId).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 사용자입니다.")
        );  //  싫어요 받는 사용자를 아이디로 찾아온다
        Love love = loveRepository.findByLovedIdAndLovingId(disLovedId, disLovingId);

        if (loveRepository.findByLovedIdAndLovingId(disLovedId, disLovingId).getMatchCode() == 0) {
            loveRepository.save(new Love(disLovedId, disLovingId, 1));
        } else if (loveRepository.findByLovedIdAndLovingId(disLovedId, disLovingId).getMatchCode() == 1) {
            loveRepository.save(new Love(disLovedId, disLovingId, 1));
        } else if (loveRepository.findByLovedIdAndLovingId(disLovedId, disLovingId).getMatchCode() == 2) {
            loveRepository.save(new Love(disLovedId, disLovingId, 2));
        }
        // 그리고 여기에 상대방을 제외하는 엄청난 로직이 필요
        loveRepository.save(love);
    }
}
