package hh5.twogaether.domain.gmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;

@Service
public class GmailServiceImpl implements GmailService{
    @Autowired
    JavaMailSender emailSender;
    public String ePw = createKey();

    private MimeMessage createMessage(GmailRequestDto to)throws Exception{
        System.out.println("보내는 대상 : "+ to.getEmail());
        System.out.println("인증 번호 : "+ePw);
        MimeMessage  message = emailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to.getEmail());//보내는 대상
        message.setSubject("이메일 인증 테스트");//제목

        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h1> 안녕하세요, 투개더입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 복사해 입력해주세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다.<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= ePw+"</strong><div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("lsh3721@gmail.com","투개더 주인장"));//보내는 사람

        return message;
    }

    public String createKey() {
        StringBuilder key = new StringBuilder();
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0:
                    key.append((char) (rnd.nextInt(26) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) (rnd.nextInt(26) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }
    @Override
    public String sendSimpleMessage(GmailRequestDto to)throws Exception {
        // TODO Auto-generated method stub
        MimeMessage message = createMessage(to);
        try{//예외처리
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }
//
//    @Override
//    public Object save(Object entity) {
//        return null;
//    }
//
//    @Override
//    public Optional findById(Object o) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(Object o) {
//        return false;
//    }
//
//    @Override
//    public List findAll() {
//        return null;
//    }
//
//    @Override
//    public List findAll(Sort sort) {
//        return null;
//    }
//
//    @Override
//    public Page findAll(Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public List findAllById(Iterable iterable) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(Object o) {
//
//    }
//
//    @Override
//    public void delete(Object entity) {
//
//    }
//
//    @Override
//    public void deleteAllById(Iterable iterable) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable entities) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//
//    @Override
//    public void flush() {
//
//    }
//
//    @Override
//    public void deleteAllInBatch(Iterable entities) {
//
//    }
//
//    @Override
//    public void deleteAllByIdInBatch(Iterable iterable) {
//
//    }
//
//    @Override
//    public void deleteAllInBatch() {
//
//    }
//
//    @Override
//    public Object getOne(Object o) {
//        return null;
//    }
//
//    @Override
//    public Object getById(Object o) {
//        return null;
//    }
//
//    @Override
//    public Object getReferenceById(Object o) {
//        return null;
//    }
//
//    @Override
//    public List findAll(Example example, Sort sort) {
//        return null;
//    }
//
//    @Override
//    public List findAll(Example example) {
//        return null;
//    }
//
//    @Override
//    public List saveAllAndFlush(Iterable entities) {
//        return null;
//    }
//
//    @Override
//    public Object saveAndFlush(Object entity) {
//        return null;
//    }
//
//    @Override
//    public List saveAll(Iterable entities) {
//        return null;
//    }
//
//    @Override
//    public Optional findOne(Example example) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Page findAll(Example example, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public long count(Example example) {
//        return 0;
//    }
//
//    @Override
//    public boolean exists(Example example) {
//        return false;
//    }
//
//    @Override
//    public Object findBy(Example example, Function queryFunction) {
//        return null;
//    }
}