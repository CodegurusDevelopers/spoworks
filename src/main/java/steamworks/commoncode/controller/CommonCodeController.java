package steamworks.commoncode.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import steamworks.commoncode.service.CommonCodeService;

@RestController
@AllArgsConstructor
public class CommonCodeController {

    private CommonCodeService commonCodeService;
}
