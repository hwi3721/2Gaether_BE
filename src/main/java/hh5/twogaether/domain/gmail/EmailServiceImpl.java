package hh5.twogaether.domain.gmail;

import hh5.twogaether.domain.users.repository.UserRepository;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.security.message.ResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    @Autowired
    JavaMailSender emailSender;
//    private static String hostAddress = "http://localhost:8080"; //미드콘으로 바꿔야함
    private static String hostAddress = "https://midcon.shop"; //미드콘으로 바꿔야함
    private final EmailRepository emailRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public String emailLinkCheck(String emailLink, String userEmail) {
        log.info("이메일 링크 확인 중");

        Optional<EmailValidation> emailValidationOptional = emailRepository.findByLink(emailLink);

        if(emailValidationOptional.isPresent()) {
            //user 이메일 인증여부 1로 변경
            User user = userRepository.findByUsername(userEmail).orElseThrow(
                    ()-> new IllegalArgumentException("아아아아이디가 없어요")
            );
            user.updateUserEmailCheck();
            log.info("이메일 인증 완료");
            } else {    //  이메일 링크가 DB에 존재하지 않음
                throw new IllegalArgumentException(ResponseMessage.EMAIL_FAIL_CHECK_LINK);
        }
        return null;
    }
    private MimeMessage createMessage(String to, String link, String username)throws Exception{
        System.out.println("보내는 대상 : "+ to);
        System.out.println("인증 링크 : "+ link);
        MimeMessage  message = emailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to);//보내는 대상
        message.setSubject("투개더 이메일 인증");//제목

        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h1> 안녕하세요, 투개더입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>"+username+"님의 메일 인증을 위해 아래 링크를 클릭해주세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다.<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>회원가입 인증 링크입니다.</h3>";
        msgg+= "LINK : <strong>";
        msgg+= "<a href=\"" + hostAddress + "/users/email/" + link + "/" + username + "\">";
        msgg+= link+"</strong><div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("lsh3721@gmail.com","투개더 주인장"));//보내는 사람

        return message;
    }
    public String createLink(String userId) throws NoSuchAlgorithmException {
        StringBuffer key = new StringBuffer();

        MessageDigest messageDigest = MessageDigest.getInstance("SHa-256");
        messageDigest.update(userId.getBytes());

        return bytesToHex(messageDigest.digest());
    }
    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
//    public String createKey() {
//        StringBuilder key = new StringBuilder();
//        Random rnd = new Random();
//        rnd.setSeed(System.currentTimeMillis());
////        rnd.setSeed(1);   //  되긴 하네
//        for (int i = 0; i < 8; i++) { // 인증코드 8자리
//            int index = rnd.nextInt(3); // 0~2 까지 랜덤
//            switch (index) {
//                case 0:
//                    key.append((char) (rnd.nextInt(26) + 97));
//                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
//                    break;
//                case 1:
//                    key.append((char) (rnd.nextInt(26) + 65));
//                    //  A~Z
//                    break;
//                case 2:
//                    key.append((rnd.nextInt(10)));
//                    // 0~9
//                    break;
//            }
//        }
//        return key.toString();
//    }
    @Override
    public String sendSimpleMessage(String nickname, String to)throws Exception, SQLIntegrityConstraintViolationException {
        // TODO Auto-generated method stub
        String link = createLink(to);
        MimeMessage message = createMessage(to, link, nickname);
        try{//예외처리
            // DB에 이미 해당 유저의 인증링크가 존재하면 Exception
            // 이메일 링크 저장부터 먼저하기
            EmailValidation emailValidation = new EmailValidation(link);
            emailRepository.save(emailValidation);
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException("이메일 발송 오류입니다.");
        } catch(DataIntegrityViolationException es) {
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }
        return link;
    }
}