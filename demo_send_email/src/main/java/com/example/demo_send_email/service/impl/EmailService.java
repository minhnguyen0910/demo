package com.example.demo_send_email.service.impl;

import com.example.demo_send_email.model.Demo;
import com.example.demo_send_email.repository.DemoRepository;
import com.example.demo_send_email.service.IEmailService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailService implements IEmailService {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    DemoRepository demoRepository;

    @Override
    public void sendEmail(String email) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = null;

        try {
            String path = "E:\\codegym\\demo_send_email\\src\\main\\resources\\static\\demo3.png";
            message = new MimeMessageHelper(mimeMessage, true);
            message.setTo(email);
            message.setSubject("Mã QR");
            message.setText("<html>" +
                    "<body>" +
                    "<div style=\" font-size:15px;\">" +
                    "Kính gửi Quý khách hàng," + "<br>" + "<br>" +
                    "<div style =\" font-weight:bold \"> Đây là mã QR của bạn: <img src=\"https://www.apache.org/images/asf_logo_wide.gif\"/> </div>" + "<br>" +
                    "Cảm ơn quý khách đã đặt vé xem phim của chúng tôi. " +
                    "Vui lòng không chia sẻ mã này với bất kỳ ai, " +
                    "vì nó được sử dụng để xác thực tài khoản của bạn." +
                    "<br>" +
                    "Nếu bạn không yêu cầu mã OTP, " +
                    "vui lòng bỏ qua email này hoặc liên hệ với chúng tôi để được hỗ trợ."
                    + "<br>"
                    + "<br>"
                    + "Trân trọng," +
                    "<div style =\"color:#183661; font-size:20px ; font-weight:bold\">DN Cinema</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>", true);
            FileSystemResource file1 = new FileSystemResource(new File(path));
            message.addAttachment("png", file1);
            javaMailSender.send(message.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendQR(String data, String path) {
        try {
            Map<EncodeHintType, ErrorCorrectionLevel> hashMap
                    = new HashMap<EncodeHintType,
                    ErrorCorrectionLevel>();

            hashMap.put(EncodeHintType.ERROR_CORRECTION,
                    ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(data.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8),
                    BarcodeFormat.QR_CODE, 50, 50);
            MatrixToImageWriter.writeToFile(
                    matrix,
                    path.substring(path.lastIndexOf('.') + 1),
                    new File(path));
        } catch (IOException | WriterException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getQRCodeImage() {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode("Minh", BarcodeFormat.QR_CODE, 400, 400);

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            byte[] pngData = pngOutputStream.toByteArray();
            ByteArrayInputStream inStreambj = new ByteArrayInputStream(pngData);

            // read image from byte array
            BufferedImage newImage = ImageIO.read(inStreambj);
            System.out.println(newImage);
            return pngData;
        } catch (IOException | WriterException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Demo demo) {
        demoRepository.save(demo);
    }

    public List<Demo> findAll() {
        return demoRepository.findAll();
    }
}
