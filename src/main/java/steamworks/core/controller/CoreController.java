package steamworks.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import steamworks.core.domain.exception.SampleException;
import steamworks.core.domain.request.SimpleRequest;
import steamworks.core.domain.response.SimpleStatusResponse;

import javax.validation.Valid;

@Controller
@RestController
public class CoreController {
    @PostMapping(value = "/core:HEALTH_CHECK")
    public ResponseEntity<SimpleStatusResponse> healthCheck() {
        return ResponseEntity.ok(new SimpleStatusResponse(true));
    }

    @PostMapping(value = "/core/request:TEST")
    public ResponseEntity<SimpleStatusResponse> requestTest(@Valid @RequestBody SimpleRequest simpleRequest) {
        return ResponseEntity.ok(new SimpleStatusResponse(simpleRequest.getStatus()));
    }


    @PostMapping(value = "/core/request:ERROR")
    public ResponseEntity<SimpleStatusResponse> requestError() {
        throw new SampleException();
    }
}
