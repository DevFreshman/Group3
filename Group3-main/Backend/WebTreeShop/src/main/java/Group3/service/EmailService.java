package Group3.service;

import Group3.model.OrderItems;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender mailSender;

  // Đường dẫn file logo local trong dự án (thay theo đúng đường dẫn bạn có)
  private static final String LOGO_PATH = "src/main/resources/static/images/logo.png";

  public void sendOrderConfirmationEmail(String toEmail, String customerName, String orderId,
      List<OrderItems> items) throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();

    // true = multipart message
    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

    BigDecimal totalAmount = BigDecimal.ZERO;
    StringBuilder productRows = new StringBuilder();

    for (OrderItems item : items) {
      totalAmount = totalAmount.add(item.getPrice());

      productRows.append(String.format(
          """
                  <tr>
                    <td style="padding: 8px; border: 1px solid #ccc; display: flex; align-items: center;">
                      <img src="%s" alt="%s" style="width: 60px; height: 60px; object-fit: contain; margin-right: 10px; border: 1px solid #ddd; border-radius: 4px;" />
                      <span>%s</span>
                    </td>
                    <td style="padding: 8px; border: 1px solid #ccc; text-align: center;">%d</td>
                    <td style="padding: 8px; border: 1px solid #ccc; text-align: right;">$ %.2f</td>
                    <td style="padding: 8px; border: 1px solid #ccc; text-align: right;"><strong>$ %.2f</strong></td>
                  </tr>
              """,
          item.getProduct().getImage_url(),
          item.getProduct().getName(),
          item.getProduct().getName(),
          item.getQuantity(),
          item.getProduct().getPrice(),
          item.getPrice()));
    }

    String htmlContent = String.format(
        """
                <html>
                  <body style="font-family: Arial, sans-serif; padding: 20px; background-color: #e6f4ea;">
                    <div style="background: white; padding: 25px; border-radius: 12px; max-width: 650px; margin: auto;
                                box-shadow: 0 4px 12px rgba(46, 125, 50, 0.2); color: #1b3a1a;">
                      <div style="text-align: center; margin-bottom: 25px;">
                        <img src="cid:logoImage" alt="Verdancia Market Logo" style="max-width: 160px; height: auto;" />
                      </div>
                      <h2 style="color: #2e7d32; margin-bottom: 10px;">Hello %s,</h2>
                      <p style="font-size: 16px;">Thank you for your order at <strong style="color: #2e7d32;">Verdancia Market</strong>.</p>
                      <h3 style="color: #2e7d32; margin-top: 30px;">Order ID: %s</h3>
                      <table style="width: 100%%; border-collapse: separate; border-spacing: 0 8px; margin-top: 15px;">
                        <thead>
                          <tr style="background-color: #a5d6a7; color: #1b3a1a; font-weight: bold;">
                            <th style="padding: 12px 10px; border-radius: 8px 0 0 8px; text-align: left;">Product</th>
                            <th style="padding: 12px 10px; text-align: center;">Quantity</th>
                            <th style="padding: 12px 10px; text-align: right;">Unit Price</th>
                            <th style="padding: 12px 10px; border-radius: 0 8px 8px 0; text-align: right;">Total</th>
                          </tr>
                        </thead>
                        <tbody>
                          %s
                        </tbody>
                        <tfoot>
                          <tr style="font-weight: bold; border-top: 3px solid #2e7d32; color: #2e7d32;">
                            <td colspan="3" style="padding: 12px 10px; text-align: right;">Grand Total:</td>
                            <td style="padding: 12px 10px; text-align: right;">$ %.2f</td>
                          </tr>
                        </tfoot>
                      </table>
                      <p style="margin-top: 30px; font-size: 15px; color: #2e7d32;">
                        We will contact you soon for delivery.
                      </p>
                    </div>
                  </body>
                </html>
            """,
        customerName, orderId, productRows.toString(), totalAmount);

    helper.setFrom("hoangducmanh2004789@gmail.com");
    helper.setTo(toEmail);
    helper.setSubject("Order Confirmation #" + orderId);
    helper.setText(htmlContent, true);

    FileSystemResource logo = new FileSystemResource(new File(LOGO_PATH));
    helper.addInline("logoImage", logo);

    mailSender.send(message);
  }
}
