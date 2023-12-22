/**
* ${comment}
*/

package ${controller.packageName()};

import javax.inject.Inject;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ${controller.className()} {

    private final ${controller.serviceType()} ${controller.serviceName()}

    @Inject
    public ${controller.packageName()}(final ${controller.serviceType()} ${controller.serviceName()}) {
        this.${controller.serviceName()} = ${controller.serviceName()};
    }

    // TODO generate CRUD methods

}