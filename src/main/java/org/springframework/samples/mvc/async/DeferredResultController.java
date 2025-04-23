package org.springframework.samples.mvc.async;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/async")
public class DeferredResultController {

    private final Queue<DeferredResult<String>> responseBodyQueue = new ConcurrentLinkedQueue<>();

    private final Queue<DeferredResult<ModelAndView>> mavQueue = new ConcurrentLinkedQueue<>();

    private final Queue<DeferredResult<String>> exceptionQueue = new ConcurrentLinkedQueue<>();

    @GetMapping("/deferred-result/response-body")
    public @ResponseBody DeferredResult<String> deferredResult() {
        DeferredResult<String> result = new DeferredResult<>();
        this.responseBodyQueue.add(result);
        return result;
    }

    @GetMapping("/deferred-result/model-and-view")
    public DeferredResult<ModelAndView> deferredResultWithView() {
        DeferredResult<ModelAndView> result = new DeferredResult<>();
        this.mavQueue.add(result);
        return result;
    }

    @GetMapping("/deferred-result/exception")
    public @ResponseBody DeferredResult<String> deferredResultWithException() {
        DeferredResult<String> result = new DeferredResult<>();
        this.exceptionQueue.add(result);
        return result;
    }

    @GetMapping("/deferred-result/timeout-value")
    public @ResponseBody DeferredResult<String> deferredResultWithTimeoutValue() {
        // Provide a default result in case of timeout and override the timeout value
        return new DeferredResult<>(1000L, "Deferred result after timeout");
    }

    @Scheduled(fixedRate = 2000)
    public void processQueues() {
        // Use iterator pattern to avoid ConcurrentModificationException
        DeferredResult<String> resultBody;
        while ((resultBody = responseBodyQueue.poll()) != null) {
            resultBody.setResult("Deferred result");
        }
        
        DeferredResult<String> resultException;
        while ((resultException = exceptionQueue.poll()) != null) {
            resultException.setErrorResult(new IllegalStateException("DeferredResult error"));
        }
        
        DeferredResult<ModelAndView> resultMav;
        while ((resultMav = mavQueue.poll()) != null) {
            resultMav.setResult(new ModelAndView("views/html", "javaBean", new JavaBean("bar", "apple")));
        }
    }

    @ExceptionHandler
    @ResponseBody
    public String handleException(IllegalStateException ex) {
        return "Handled exception: " + ex.getMessage();
    }

    public static class JavaBean {
        private String foo;
        private String fruit;

        public JavaBean(String foo, String fruit) {
            this.foo = foo;
            this.fruit = fruit;
        }

        public String getFoo() {
            return foo;
        }

        public void setFoo(String foo) {
            this.foo = foo;
        }

        public String getFruit() {
            return fruit;
        }

        public void setFruit(String fruit) {
            this.fruit = fruit;
        }

        @Override
        public String toString() {
            return "JavaBean [foo=" + foo + ", fruit=" + fruit + "]";
        }
    }
}