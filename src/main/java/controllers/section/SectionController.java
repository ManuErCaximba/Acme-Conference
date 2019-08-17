package controllers.section;

import controllers.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import services.ActorService;
import services.SectionService;

@Controller
@RequestMapping("section")
public class SectionController extends AbstractController{

    @Autowired
    private SectionService sectionService;

    @Autowired
    private ActorService actorService;
}
