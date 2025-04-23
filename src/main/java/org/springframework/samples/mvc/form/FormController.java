package org.springframework.samples.mvc.form;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/form")
@SessionAttributes("formBean")
public class FormController {

    // Invoked on every request
    @ModelAttribute
    public void ajaxAttribute(WebRequest request, Model model) {
        model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
    }

    // Invoked initially to create the "form" attribute
    // Once created the "form" attribute comes from the HTTP session (see @SessionAttributes)
    @ModelAttribute("formBean")
    public FormBean createFormBean() {
        return new FormBean();
    }

    @GetMapping
    public String form() {
        return "form";
    }

    @PostMapping
    public String processSubmit(@Valid FormBean formBean, BindingResult result,
                                @ModelAttribute("ajaxRequest") boolean ajaxRequest,
                                Model model, RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            return "form";
        }
        // Typically you would save to a db and clear the "form" attribute from the session 
        // via SessionStatus.setCompleted(). For the demo we leave it in the session.
        String message = "Form submitted successfully.  Bound " + formBean;
        // Success response handling
        if (ajaxRequest) {
            // prepare model for rendering success message in this request
            model.addAttribute("message", message);
            return "form";
        } else {
            // store a success message for rendering on the next request after redirect
            // redirect back to the form to render the success message along with newly bound values
            redirectAttrs.addFlashAttribute("message", message);
            return "redirect:/form";
        }
    }

    /**
     * Utility class for AJAX detection
     */
    public static class AjaxUtils {
        public static boolean isAjaxRequest(WebRequest webRequest) {
            String requestedWith = webRequest.getHeader("X-Requested-With");
            return requestedWith != null && "XMLHttpRequest".equals(requestedWith);
        }

        private AjaxUtils() {
        }
    }

    /**
     * Form backing bean
     */
    public static class FormBean {
        private String name;
        private String age;
        private String birthDate;
        private String phone;
        private String currency;
        private String percent;
        private String emailAddress;
        private String inquiry;
        private boolean subscribeNewsletter;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getPercent() {
            return percent;
        }

        public void setPercent(String percent) {
            this.percent = percent;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public String getInquiry() {
            return inquiry;
        }

        public void setInquiry(String inquiry) {
            this.inquiry = inquiry;
        }

        public boolean isSubscribeNewsletter() {
            return subscribeNewsletter;
        }

        public void setSubscribeNewsletter(boolean subscribeNewsletter) {
            this.subscribeNewsletter = subscribeNewsletter;
        }

        @Override
        public String toString() {
            return "FormBean [name=" + name + ", age=" + age + ", birthDate=" + birthDate + ", phone=" + phone
                    + ", currency=" + currency + ", percent=" + percent + ", emailAddress=" + emailAddress
                    + ", inquiry=" + inquiry + ", subscribeNewsletter=" + subscribeNewsletter + "]";
        }
    }
}