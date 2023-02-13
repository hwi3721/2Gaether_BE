package hh5.twogaether.domain.loves;

import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoveService {
    private final LoveRepository loveRepository;
    private final UserRepository userRepository;

    @Transactional
    public void love(Long lovedId, User user) {
        User lovedUser = userRepository.findById(lovedId).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 사용자입니다.")
        );  // 좋아요 받는 사용자 아이디로 사용자 찾아오기
        Love love = new Love(lovedUser, user);  //  좋아요 받는 사용자, 하는 사용자로 '좋아요' 생성 후 저장
        loveRepository.save(love);
    }
}
